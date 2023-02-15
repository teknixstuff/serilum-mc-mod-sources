/*
 * This is the latest source code of All Loot Drops.
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

package com.natamus.alllootdrops.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.alllootdrops.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean keepOriginalLootQuantityIfHigher = true;
	@Entry(min = 1, max = 128) public static int lootQuantity = 1;
	@Entry public static boolean lootingEnchantAffectsQuantity = true;
	@Entry(min = 0, max = 1.0) public static double lootingEnchantExtraQuantityChance = 0.5;

	public static void initConfig() {
		configMetaData.put("keepOriginalLootQuantityIfHigher", Arrays.asList(
			"If for example Iron Golems drop more iron than the default lootQuantity setting, keep that original amount instead of nerfing it."
		));
		configMetaData.put("lootQuantity", Arrays.asList(
			"Determines the amount of loot dropped by each mob."
		));
		configMetaData.put("lootingEnchantAffectsQuantity", Arrays.asList(
			"If enabled, the looting enchant has a chance to increase the quantity of loot dropped. Per level a roll is done with the chance from 'lootingEnchantExtraQuantityChancePerLevel'. If the roll succeeds, 1 is added to the loot amount."
		));
		configMetaData.put("lootingEnchantExtraQuantityChance", Arrays.asList(
			"The chance a roll succeeds in adding 1 to the total loot amount."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}