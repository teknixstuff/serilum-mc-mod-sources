/*
 * This is the latest source code of Areas.
 * Minecraft version: 1.19.2.
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

package com.natamus.areas.events;

import com.natamus.areas.config.ConfigHandler;
import com.natamus.areas.objects.AreaObject;
import com.natamus.areas.data.AreaVariables;
import com.natamus.areas.util.Util;
import com.natamus.collective.functions.FABFunctions;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class ClientEvent {
	public static void onClientTick(Minecraft mc) {
		Player player = mc.player;
        if (player == null) {
            return;
        }

		GUIEvent.tickHUDFade();

		if (player.tickCount % 20 != 0) {
			return;
		}

		for (AreaObject enteredAreaObject : AreaVariables.enteredAreas) {
			if (Util.getAreaSign(enteredAreaObject.level, enteredAreaObject.location) == null) {
				Util.removedArea(enteredAreaObject, player);
			}
		}

		Level level = player.level;
		BlockPos ppos = player.blockPosition();

		List<AreaObject> allAreaObjects = new ArrayList<AreaObject>();
		for (BlockEntity nearbyBlockEntity : FABFunctions.getBlockEntitiesAroundPosition(level, player.blockPosition(), ConfigHandler.radiusAroundPlayerToCheckForSigns)) {
			BlockState blockEntityState = nearbyBlockEntity.getBlockState();
			if (!blockEntityState.is(BlockTags.SIGNS)) {
				continue;
			}

			BlockPos nspos = nearbyBlockEntity.getBlockPos();
			AreaObject areaObject = Util.getAreaSign(level, nspos);
			if (areaObject == null) {
				continue;
			}

			allAreaObjects.add(areaObject);
		}

		for (AreaObject areaObject : allAreaObjects) {
			if (areaObject == null) {
				continue;
			}

			BlockPos nspos = areaObject.location;
			if (ppos.closerThan(nspos, areaObject.radius)) {
				if (AreaVariables.enteredAreas.contains(areaObject)) {
					continue;
				}

				Util.enterArea(areaObject, player);
			}
			else if (AreaVariables.enteredAreas.contains(areaObject)) {
				Util.exitArea(areaObject, player);
			}
		}
	}
}
