/*
 * This is the latest source code of Better Conduit Placement.
 * Minecraft version: 1.20.0.
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

package com.natamus.betterconduitplacement.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.betterconduitplacement.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean breakConduitBlocks = true;
	@Entry public static boolean dropReplacedBlockTopConduit = true;

	public static void initConfig() {
		configMetaData.put("breakConduitBlocks", Arrays.asList(
			"If enabled, drops all conduit blocks when the conduit itself is broken."
		));
		configMetaData.put("dropReplacedBlockTopConduit", Arrays.asList(
			"If enabled, when prismarine replaces a normal block that block is dropped on top of the conduit."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}