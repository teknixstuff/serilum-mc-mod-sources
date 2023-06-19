/*
 * This is the latest source code of Healing Campfire.
 * Minecraft version: 1.20.1.
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

package com.natamus.healingcampfire.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.healingcampfire.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 1, max = 1200) public static int checkForCampfireDelayInTicks = 40;
	@Entry(min = 1, max = 64) public static int healingRadius = 16;
	@Entry(min = 1, max = 600) public static int effectDurationSeconds = 60;
	@Entry(min = 1, max = 50) public static int regenerationLevel = 1;
	@Entry public static boolean healPassiveMobs = true;
	@Entry public static boolean hideEffectParticles = true;
	@Entry public static boolean campfireMustBeLit = true;
	@Entry public static boolean campfireMustBeSignalling = false;
	@Entry public static boolean enableEffectForNormalCampfires = true;
	@Entry public static boolean enableEffectForSoulCampfires = true;

	public static void initConfig() {
		configMetaData.put("checkForCampfireDelayInTicks", Arrays.asList(
			"How often in ticks the mod checks for campfires around the player. 1 second = 20 ticks, so by default every 2 seconds."
		));
		configMetaData.put("healingRadius", Arrays.asList(
			"The radius around the campfire in blocks where players receive the regeneration effect."
		));
		configMetaData.put("effectDurationSeconds", Arrays.asList(
			"The duration of the regeneration effect which the campfire applies."
		));
		configMetaData.put("regenerationLevel", Arrays.asList(
			"The level of regeneration which the campfire applies, by default 1."
		));
		configMetaData.put("healPassiveMobs", Arrays.asList(
			"When enabled, the campfire heals passive mobs around where the radius is half the width of a bounding box."
		));
		configMetaData.put("hideEffectParticles", Arrays.asList(
			"When enabled, hides the particles from the regeneration effect around the campfire."
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