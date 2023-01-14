/*
 * This is the latest source code of Difficulty Lock.
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

package com.natamus.difficultylock.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.difficultylock.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean forcePeaceful = false;
	@Entry public static boolean forceEasy = false;
	@Entry public static boolean forceNormal = false;
	@Entry public static boolean forceHard = true;
	@Entry public static boolean shouldLockDifficulty = true;
	@Entry public static boolean shouldChangeDifficultyWhenAlreadyLocked = false;

	public static void initConfig() {
		configMetaData.put("forcePeaceful", Arrays.asList(
			"Priority 1: Sets the difficulty in any world to peaceful when enabled."
		));
		configMetaData.put("forceEasy", Arrays.asList(
			"Priority 2: Sets the difficulty in any world to easy when enabled."
		));
		configMetaData.put("forceNormal", Arrays.asList(
			"Priority 3: Sets the difficulty in any world to normal when enabled."
		));
		configMetaData.put("forceHard", Arrays.asList(
			"Priority 4: Sets the difficulty in any world to hard when enabled."
		));
		configMetaData.put("shouldLockDifficulty", Arrays.asList(
			"When enabled, locks the difficulty in any world so it cannot be changed."
		));
		configMetaData.put("shouldChangeDifficultyWhenAlreadyLocked", Arrays.asList(
			"When enabled, also sets the difficulty in worlds where it has already been locked."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}