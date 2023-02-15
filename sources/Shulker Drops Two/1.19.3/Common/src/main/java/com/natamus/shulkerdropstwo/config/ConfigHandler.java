/*
 * This is the latest source code of Shulker Drops Two.
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

package com.natamus.shulkerdropstwo.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.shulkerdropstwo.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean alwaysDropShells = false;
	@Entry(min = 1, max = 64) public static int shulkerDropAmount = 2;

	public static void initConfig() {
		configMetaData.put("alwaysDropShells", Arrays.asList(
			"Ignore the drop chance (default 50%) that a Shulker will drop their shell and instead makes them always drop it."
		));
		configMetaData.put("shulkerDropAmount", Arrays.asList(
			"Sets the amount of shells Shulkers drop."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}