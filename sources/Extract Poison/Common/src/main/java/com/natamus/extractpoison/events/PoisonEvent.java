/*
 * This is the latest source code of Extract Poison.
 * Minecraft version: 1.19.3.
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

package com.natamus.extractpoison.events;

import com.natamus.collective.functions.EntityFunctions;
import com.natamus.collective.functions.ItemFunctions;
import com.natamus.extractpoison.config.ConfigHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PoisonEvent {
	private static final Map<UUID, LocalTime> lastuse = new HashMap<UUID, LocalTime>();
	
	public static InteractionResult onEntityInteract(Player player, Level world, InteractionHand hand, Entity target, EntityHitResult hitResult) {
		if (world.isClientSide) {
			return InteractionResult.PASS;
		}

		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getItem().equals(Items.GLASS_BOTTLE)) {
			String entityname = EntityFunctions.getEntityString(target).toLowerCase();
			if (entityname.contains("cavespider") || target instanceof CaveSpider || target instanceof Bee) {
				LocalTime now = LocalTime.now();
				UUID targetuuid = target.getUUID();
				if (lastuse.containsKey(targetuuid)) {
					LocalTime lastnow = lastuse.get(targetuuid); 
					
					int msbetween = (int)Duration.between(lastnow, now).toMillis();
					if (msbetween < ConfigHandler.extractDelayMs) {
						return InteractionResult.PASS;
					}
				}
				
				ItemStack poison = new ItemStack(Items.POTION, 1);
				PotionUtils.setPotion(poison, Potions.POISON);
				
				ItemFunctions.shrinkGiveOrDropItemStack(player, hand, itemstack, poison);
				lastuse.put(targetuuid, now);
				
				return InteractionResult.SUCCESS;
			}
		}
		
		return InteractionResult.PASS;
	}

	public static InteractionResultHolder<ItemStack> onWaterClick(Player player, Level world, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (world.isClientSide) {
			return InteractionResultHolder.pass(itemstack);
		}

		if (itemstack.getItem().equals(Items.GLASS_BOTTLE)) {
			LocalTime now = LocalTime.now();
			BlockPos pos = player.blockPosition();

			List<Entity> entitiesaround = world.getEntities(player, new AABB(pos.getX()-4, pos.getY()-4, pos.getZ()-4, pos.getX()+4, pos.getY()+4, pos.getZ()+4));
			for (Entity ea : entitiesaround) {
				if (ea instanceof Pufferfish) {
					UUID targetuuid = ea.getUUID();
					if (lastuse.containsKey(targetuuid)) {
						LocalTime lastnow = lastuse.get(targetuuid);

						int msbetween = (int)Duration.between(lastnow, now).toMillis();
						if (msbetween < ConfigHandler.extractDelayMs) {
							continue;
						}
					}

					ItemStack poison = new ItemStack(Items.POTION, 1);
					PotionUtils.setPotion(poison, Potions.POISON);

					ItemFunctions.shrinkGiveOrDropItemStack(player, hand, itemstack, poison);
					lastuse.put(targetuuid, now);
					return InteractionResultHolder.fail(itemstack);
				}
			}
		}

		return InteractionResultHolder.pass(itemstack);
	}
}
