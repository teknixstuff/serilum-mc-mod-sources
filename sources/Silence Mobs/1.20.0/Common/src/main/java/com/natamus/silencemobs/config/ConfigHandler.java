/*
 * This is the latest source code of Silence Mobs.
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

package com.natamus.silencemobs.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.silencemobs.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean onlyAllowCommandWhenCheatsEnabled = true;
	@Entry public static boolean mustHoldStick = true;
	@Entry public static boolean renameSilencedMobs = true;

	public static void initConfig() {
		configMetaData.put("onlyAllowCommandWhenCheatsEnabled", Arrays.asList(
			"If enabled, only allows the /silencestick command with cheats enabled."
		));
		configMetaData.put("mustHoldStick", Arrays.asList(
			"If disabled, a stick will be generated via the /silencestick command instead of having to hold a stick while using the command."
		));
		configMetaData.put("renameSilencedMobs", Arrays.asList(
			"If enabled, whenever a player hits a non-silenced mob with The Silence Stick it will set their name to 'Silenced Entity'."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}