/*
 * This is the latest source code of Grabby Mobs.
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

package com.natamus.grabbymobs.util;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Util {
	public static HashMap<EntityType<?>, List<EquipmentSlot>> possibleEquipment = new HashMap<EntityType<?>, List<EquipmentSlot>>();

	public static void setEquipmentData() {
		final List<EquipmentSlot> allEquipment = new ArrayList<EquipmentSlot>(Arrays.asList(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
		final List<EquipmentSlot> noArmour = new ArrayList<EquipmentSlot>(Arrays.asList(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

		possibleEquipment.put(EntityType.DROWNED, new ArrayList<EquipmentSlot>(allEquipment));
		possibleEquipment.put(EntityType.EVOKER, new ArrayList<EquipmentSlot>(noArmour));
		possibleEquipment.put(EntityType.HUSK, new ArrayList<EquipmentSlot>(allEquipment));
		possibleEquipment.put(EntityType.PIGLIN, new ArrayList<EquipmentSlot>(allEquipment));
		possibleEquipment.put(EntityType.PIGLIN_BRUTE, new ArrayList<EquipmentSlot>(allEquipment));
		possibleEquipment.put(EntityType.SKELETON, new ArrayList<EquipmentSlot>(allEquipment));
		possibleEquipment.put(EntityType.STRAY, new ArrayList<EquipmentSlot>(allEquipment));
		possibleEquipment.put(EntityType.VEX, new ArrayList<EquipmentSlot>(allEquipment));
		possibleEquipment.put(EntityType.VINDICATOR, new ArrayList<EquipmentSlot>(noArmour));
		possibleEquipment.put(EntityType.WITCH, new ArrayList<EquipmentSlot>(Arrays.asList(EquipmentSlot.MAINHAND)));
		possibleEquipment.put(EntityType.WITHER_SKELETON, new ArrayList<EquipmentSlot>(allEquipment));
		possibleEquipment.put(EntityType.ZOMBIE, new ArrayList<EquipmentSlot>(allEquipment));
		possibleEquipment.put(EntityType.ZOMBIE_VILLAGER, new ArrayList<EquipmentSlot>(allEquipment));
		possibleEquipment.put(EntityType.ZOMBIFIED_PIGLIN, new ArrayList<EquipmentSlot>(allEquipment));
	}

	public static boolean canPickUp(Mob mob) {
		return Util.possibleEquipment.containsKey(mob.getType());
	}

	public static boolean wantsToPickUp(Mob mob, ItemStack itemStack) {
		EntityType<?> entityType = mob.getType();
		if (Util.possibleEquipment.containsKey(entityType)) {
			return Util.possibleEquipment.get(entityType).contains(LivingEntity.getEquipmentSlotForItem(itemStack));
		}

		return false;
	}
}
