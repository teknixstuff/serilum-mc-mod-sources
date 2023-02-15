/*
 * This is the latest source code of Just Mob Heads.
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

package com.natamus.justmobheads.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.justmobheads.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean mobSpecificDropChances = true;
	@Entry public static boolean enableStandardHeads = false;
	@Entry public static boolean enableLootingEnchant = true;
	@Entry public static boolean onlyAdultMobsDropTheirHead = true;
	@Entry(min = 0.0001, max = 1.0) public static double overallDropChance = 0.1;
	@Entry(min = 0.0001, max = 1.0) public static double creeperSkeletonZombieDropChance = 0.1;
	@Entry public static boolean onlyDropHeadsByChargedCreeper = false;
	@Entry public static boolean onlyDropHeadsByPlayerKill = false;

	public static void initConfig() {
		configMetaData.put("mobSpecificDropChances", Arrays.asList(
			"If enabled, overrides the 'overallDropChance' variable with the specific values."
		));
		configMetaData.put("enableStandardHeads", Arrays.asList(
			"If enabled, allows Creepers, Skeletons and Zombies to drop their heads."
		));
		configMetaData.put("enableLootingEnchant", Arrays.asList(
			"If enabled, the looting enchant will have an effect on the drop chance."
		));
		configMetaData.put("onlyAdultMobsDropTheirHead", Arrays.asList(
			"If enabled, only adult tameable mobs will have a chance to drop their head on death."
		));
		configMetaData.put("overallDropChance", Arrays.asList(
			"Sets the chance of a mob dropping its head if 'mobSpecificDropChances' is disabled."
		));
		configMetaData.put("creeperSkeletonZombieDropChance", Arrays.asList(
			"Sets head drop chance for Zombies, Skeletons and Creepers if 'enableStandardHeads' is enabled."
		));
		configMetaData.put("onlyDropHeadsByChargedCreeper", Arrays.asList(
			"When enabled, only drops mob heads if the source on death is a charged creeper. This overwrites the onlyDropHeadsByPlayerKill value."
		));
		configMetaData.put("onlyDropHeadsByPlayerKill", Arrays.asList(
			"When enabled, only drops mob heads if the source on death is from a player."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}