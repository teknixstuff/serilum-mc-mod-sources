/*
 * This is the latest source code of Double Doors.
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

package com.natamus.doubledoors.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.doubledoors.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean enableRecursiveOpening = true;
	@Entry public static int recursiveOpeningMaxBlocksDistance = 10;
	@Entry public static boolean enableDoors = true;
	@Entry public static boolean enableFenceGates = true;
	@Entry public static boolean enableTrapdoors = true;

	public static void initConfig() {
		configMetaData.put("enableRecursiveOpening", Arrays.asList(
			"Whether the recursive opening feature should be enabled. This allows you to for example build a giant door with trapdoors which will all open at the same time, as long as they are connected. The 'recursiveOpeningMaxBlocksDistance' config option determines how far the function should search."
		));
		configMetaData.put("recursiveOpeningMaxBlocksDistance", Arrays.asList(
			"How many blocks the recursive function should search when 'enableRecursiveOpening' is enabled.",
			"min: 1, max: 64"
		));
		configMetaData.put("enableDoors", Arrays.asList(
			"When enables, the mod works with double doors."
		));
		configMetaData.put("enableFenceGates", Arrays.asList(
			"When enables, the mod works with double fence gates."
		));
		configMetaData.put("enableTrapdoors", Arrays.asList(
			"When enables, the mod works with double trapdoors."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}