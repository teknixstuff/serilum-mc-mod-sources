/*
 * This is the latest source code of Tree Harvester.
 * Minecraft version: 1.19.4.
 *
 * Please don't distribute without permission.
 * For all Minecraft modding projects, feel free to visit my profile page on CurseForge or Modrinth.
 *  CurseForge: https://curseforge.com/members/serilum/projects
 *  Modrinth: https://modrinth.com/user/serilum
 *  Overview: https://serilum.com/
 *
 * If you are feeling generous and would like to support the development of the mods, you can!
 *  https://ricksouth.com/donate contains all the information. <3
 *
 * Thanks for looking at the source code! Hope it's of some use to your project. Happy modding!
 */

package com.natamus.treeharvester.events;

import com.natamus.collective.functions.BlockPosFunctions;
import com.natamus.treeharvester.config.ConfigHandler;
import com.natamus.treeharvester.data.Variables;
import com.natamus.treeharvester.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Triplet;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

public class SaplingEvents {
	public static void onSaplingItem(Level level, Entity entity) {
		if (level.isClientSide) {
			return;
		}

		if (!(entity instanceof ItemEntity)) {
			return;
		}

		if (!ConfigHandler.replaceSaplingOnTreeHarvest) {
			return;
		}

		ItemEntity itemEntity = (ItemEntity)entity;
		ItemStack itemStack = itemEntity.getItem();
		Item item = itemStack.getItem();
		if (!(item instanceof BlockItem)) {
			return;
		}

		Block block = Block.byItem(item);
		if (!Util.isSapling(block)) {
			return;
		}

		BlockPos itemPos = itemEntity.blockPosition();
		BlockPos yZeroItemPos = itemPos.atY(0);

		Date now = new Date();
		for (Triplet<Date, BlockPos, CopyOnWriteArrayList<BlockPos>> triplet : Variables.saplingPositions) {
			long ms = (now.getTime()-triplet.getA().getTime());
			if (ms > 2000) {
				Variables.saplingPositions.remove(triplet);
				continue;
			}

			if (BlockPosFunctions.withinDistance(yZeroItemPos, triplet.getB().atY(0), 6)) {
				for (BlockPos lowerLog : triplet.getC()) {
					if (itemStack.getCount() > 0) {
						level.setBlock(lowerLog, block.defaultBlockState(), 3);
						itemStack.shrink(1);
						triplet.getC().remove(lowerLog);
					}
				}

				if (triplet.getC().size() == 0) {
					Variables.saplingPositions.remove(triplet);
				}
			}

			if (itemStack.getCount() == 0) {
				return;
			}
		}
	}
}