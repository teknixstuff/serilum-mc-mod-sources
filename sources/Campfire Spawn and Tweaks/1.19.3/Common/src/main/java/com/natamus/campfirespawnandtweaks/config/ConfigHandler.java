/*
 * This is the latest source code of Campfire Spawn and Tweaks.
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

package com.natamus.campfirespawnandtweaks.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.campfirespawnandtweaks.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean campfiresStartUnlit = true;
	@Entry public static boolean sneakRightClickCampfireToUnset = true;
	@Entry public static boolean bedsOverrideCampfireSpawnOnSneakRightClick = true;
	@Entry public static boolean createAirPocketIfBlocksAboveCampfire = true;
	@Entry public static boolean sendMessageOnNewCampfireSpawnSet = true;
	@Entry public static boolean sendMessageOnCampfireSpawnUnset = true;
	@Entry public static boolean sendMessageOnCampfireSpawnMissing = true;
	@Entry public static boolean sendMessageOnCampfireSpawnOverride = true;
	@Entry(min = 0, max = 3600000) public static int fireResitanceDurationOnRespawnInMs = 10000;

	public static void initConfig() {
		configMetaData.put("campfiresStartUnlit", Arrays.asList(
			"When enabled, a newly placed campfire will be unlit."
		));
		configMetaData.put("sneakRightClickCampfireToUnset", Arrays.asList(
			"Crouching/Sneaking and right-clicking on a campfire unsets the campfire spawn point."
		));
		configMetaData.put("bedsOverrideCampfireSpawnOnSneakRightClick", Arrays.asList(
			"When enabled, sneak/crouch + right-clicking a bed will override the campfire spawn point."
		));
		configMetaData.put("createAirPocketIfBlocksAboveCampfire", Arrays.asList(
			"When enabled, the mod breaks the blocks above a campfire on respawn if it would somehow be blocked."
		));
		configMetaData.put("sendMessageOnNewCampfireSpawnSet", Arrays.asList(
			"When enabled, a message will be sent to the player whenever a new campfire spawn point is set."
		));
		configMetaData.put("sendMessageOnCampfireSpawnUnset", Arrays.asList(
			"When enabled, a message will be sent to the player whenever a campfire spawn point is unset."
		));
		configMetaData.put("sendMessageOnCampfireSpawnMissing", Arrays.asList(
			"When enabled, a message will be sent to the player whenever a campfire spawn point is missing on respawn."
		));
		configMetaData.put("sendMessageOnCampfireSpawnOverride", Arrays.asList(
			"When enabled, a message will be sent to the player whenever a campfire spawn point is overridden by the PlayerSetSpawnEvent."
		));
		configMetaData.put("fireResitanceDurationOnRespawnInMs", Arrays.asList(
			"The duration of fire resistance when a player respawns at a campfire. A value of 0 disables this feature, and places the player next to the campfire instead."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}