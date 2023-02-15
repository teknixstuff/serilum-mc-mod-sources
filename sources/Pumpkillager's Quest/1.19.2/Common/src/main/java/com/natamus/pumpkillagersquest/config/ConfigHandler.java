/*
 * This is the latest source code of Pumpkillager's Quest.
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

package com.natamus.pumpkillagersquest.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.pumpkillagersquest.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 0.001, max = 1.0) public static double pumpkillagerSpawnChance = 0.1;
	@Entry public static boolean enablePumpkillagerSpawnDuringCreative = false;
	@Entry(min = 1.0, max = 10000.0) public static double finalBossMaxHealth = 300.0;
	@Entry(min = 0, max = 10000) public static int experienceAmountRewardFinalBoss = 250;
	@Entry(min = 0.0, max = 1.0) public static double chanceForPumpkinBlockToSpawnOnPigSpawn = 0.05;

	public static void initConfig() {
		configMetaData.put("pumpkillagerSpawnChance", Arrays.asList(
			"The chance a Pumpkillager will spawn when a player breaks a pumpkin block."
		));
		configMetaData.put("enablePumpkillagerSpawnDuringCreative", Arrays.asList(
			"Whether the Pumpkillager should spawn when pumpkins are broken while the player is in creative mode."
		));
		configMetaData.put("finalBossMaxHealth", Arrays.asList(
			"The amount of health the final boss should have."
		));
		configMetaData.put("experienceAmountRewardFinalBoss", Arrays.asList(
			"How much experience the final boss should drop on death."
		));
		configMetaData.put("chanceForPumpkinBlockToSpawnOnPigSpawn", Arrays.asList(
			"This is a feature to add more pumpkins to the world, to make people come across the Pumpkillager in a more natural way. Whenever a pig spawns, it has a chance to spawn on top of a pumpkin block. This will make them be sprinkled around the world. To turn it off, set the config chance value to 0."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}