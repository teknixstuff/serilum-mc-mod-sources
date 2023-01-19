/*
 * This is the latest source code of Humbling Bundle.
 * Minecraft version: 1.18.2.
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

package com.natamus.humblingbundle.events;

import com.natamus.collective.functions.TaskFunctions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class EntityDroppingEvent {
	public static void mobItemDrop(Level level, Entity entity, DamageSource damageSource) {
		if (level.isClientSide) {
			return;
		}

		if (entity instanceof Player) {
			return;
		}

		List<ItemStack> equipment = new ArrayList<ItemStack>();
		for (ItemStack next : entity.getAllSlots()) {
			equipment.add(next);
		}

		BlockPos epos = entity.blockPosition();
		TaskFunctions.enqueueTask(level, () -> {
			List<ItemEntity> dropEntities = new ArrayList<ItemEntity>();
			for (Entity ea : level.getEntities(null, new AABB(epos.getX()-1, epos.getY()-1, epos.getZ()-1, epos.getX()+1, epos.getY()+1, epos.getZ()+1))) {
				if (ea instanceof ItemEntity) {
					dropEntities.add((ItemEntity)ea);
				}
			}

			for (ItemEntity dropEntity : dropEntities) {
				int tickCount = dropEntity.tickCount;
				if (tickCount <= 1) {
					ItemStack itemStack = dropEntity.getItem();
					if (equipment.contains(itemStack)) {
						continue;
					}

					itemStack.setCount(itemStack.getCount() * 2);
					dropEntity.setItem(itemStack);
				}
			}
		}, 0);
	}
}