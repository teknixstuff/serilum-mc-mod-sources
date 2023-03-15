/*
 * This is the latest source code of All Loot Drops.
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

package com.natamus.alllootdrops.events;

import com.natamus.alllootdrops.config.ConfigHandler;
import com.natamus.alllootdrops.data.Constants;
import com.natamus.alllootdrops.util.Reference;
import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.ItemFunctions;
import com.natamus.collective.functions.TaskFunctions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class EntityDroppingEvent {
	public static void onWorldLoad(ServerLevel level) {
		if (GlobalVariables.entitydrops != null) {
			return;
		}

		ItemFunctions.generateEntityDropsFromLootTable(level);
	}
	
	public static void mobItemDrop(Level level, Entity entity, DamageSource damageSource) {
		if (level.isClientSide) {
			return;
		}
		
		if (entity instanceof Player || !(entity instanceof LivingEntity)) {
			return;
		}

		if (entity instanceof MagmaCube && damageSource.getEntity() instanceof Frog) {
			return;
		}
		
		if (GlobalVariables.entitydrops == null) {
			Constants.logger.warn("[" + Reference.NAME + "] Error: Unable to find generated loot drops. Attempting to generate them now.");
			ItemFunctions.generateEntityDropsFromLootTable(level);
			if (GlobalVariables.entitydrops == null) {
				Constants.logger.warn("[" + Reference.NAME + "] Error: Still unable to generate loot drops. Please submit a bug report at 'https://github.com/ricksouth/serilum-mc-mods/labels/Mod:%20All%20Loot%20Drops'.");
				return;
			}
		}

		EntityType<?> type = entity.getType();

		if (!GlobalVariables.entitydrops.containsKey(type)) {
			return;
		}
		
		List<Item> alldrops = new ArrayList<Item>(GlobalVariables.entitydrops.get(type));
		
		int amount = ConfigHandler.lootQuantity;
		if (ConfigHandler.lootingEnchantAffectsQuantity) {
			Entity source = damageSource.getEntity();
			if (source instanceof Player) {
				int lootinglevel = EnchantmentHelper.getMobLooting((LivingEntity)source);
				double increasechance = ConfigHandler.lootingEnchantExtraQuantityChance;
				for (int n = 0; n < lootinglevel; n++) {
					double num = GlobalVariables.random.nextDouble();
					if (num <= increasechance) {
						amount += 1;
					}
				}
			}
		}
		
		BlockPos epos = entity.blockPosition();
		int finalAmount = amount;
		TaskFunctions.enqueueTask(level, () -> {
			List<ItemEntity> dropEntities = new ArrayList<ItemEntity>();
			for (Entity ea : level.getEntities(null, new AABB(epos.getX()-1, epos.getY()-1, epos.getZ()-1, epos.getX()+1, epos.getY()+1, epos.getZ()+1))) {
				if (ea instanceof ItemEntity) {
					int tickCount = ea.tickCount;
					if (tickCount <= 1) {
						dropEntities.add((ItemEntity) ea);
					}
				}
			}

			List<ItemEntity> tr = new ArrayList<ItemEntity>();

			for (ItemEntity ie : dropEntities) {
				ItemStack stack = ie.getItem();
				Item item = stack.getItem();
				if (alldrops.contains(item)) {
					tr.add(ie);
				}
			}

			if (tr.size() > 0) {
				for (ItemEntity ie : tr) {
					ItemStack ieitemstack = ie.getItem();
					if (ConfigHandler.keepOriginalLootQuantityIfHigher) {
						if (ieitemstack.getCount() > ConfigHandler.lootQuantity) {
							alldrops.remove(ieitemstack.getItem());
							continue;
						}
					}
					ie.remove(Entity.RemovalReason.DISCARDED);
				}
			}

			for (Item item : alldrops) {
				ItemEntity ie = new ItemEntity(level, epos.getX(), epos.getY()+1, epos.getZ(), new ItemStack(item, finalAmount));
				level.addFreshEntity(ie);
			}
		}, 0);
	}
}
