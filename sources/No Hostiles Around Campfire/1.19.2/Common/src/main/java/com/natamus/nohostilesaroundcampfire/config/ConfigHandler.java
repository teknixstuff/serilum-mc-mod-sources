/*
 * This is the latest source code of No Hostiles Around Campfire.
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

package com.natamus.nohostilesaroundcampfire.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.nohostilesaroundcampfire.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 1, max = 128) public static int preventHostilesRadius = 48;
	@Entry public static boolean burnHostilesAroundWhenPlaced = true;
	@Entry(min = 0, max = 1.0) public static double burnHostilesRadiusModifier = 0.5;
	@Entry public static boolean preventMobSpawnerSpawns = false;
	@Entry public static boolean campfireMustBeLit = true;
	@Entry public static boolean campfireMustBeSignalling = false;
	@Entry public static boolean enableEffectForNormalCampfires = true;
	@Entry public static boolean enableEffectForSoulCampfires = true;

	public static void initConfig() {
		configMetaData.put("preventHostilesRadius", Arrays.asList(
			"The radius around the campfire in blocks where hostile mob spawns will be blocked."
		));
		configMetaData.put("burnHostilesAroundWhenPlaced", Arrays.asList(
			"If enabled, burns all hostile mobs around the campfire within the radius whenever a player places a campfire."
		));
		configMetaData.put("burnHostilesRadiusModifier", Arrays.asList(
			"By default set to 0.5. This means that if the radius is 16, the campfire burns prior mobs in a radius of 8."
		));
		configMetaData.put("preventMobSpawnerSpawns", Arrays.asList(
			"When enabled, the mob spawners spawns are also disabled when a campfire is within the radius."
		));
		configMetaData.put("campfireMustBeLit", Arrays.asList(
			"When enabled, the campfire only has an effect when the block is lit up."
		));
		configMetaData.put("campfireMustBeSignalling", Arrays.asList(
			"When enabled, the campfire only has an effect when the block is signalling."
		));
		configMetaData.put("enableEffectForNormalCampfires", Arrays.asList(
			"When enabled, the mod will work with normal campfires."
		));
		configMetaData.put("enableEffectForSoulCampfires", Arrays.asList(
			"When enabled, the mod will work with soul campfires."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}