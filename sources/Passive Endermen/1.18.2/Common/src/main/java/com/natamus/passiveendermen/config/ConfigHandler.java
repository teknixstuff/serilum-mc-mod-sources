/*
 * This is the latest source code of Passive Endermen.
 * Minecraft version: 1.18.2.
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

package com.natamus.passiveendermen.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.passiveendermen.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean preventEndermenFromTeleporting = true;
	@Entry public static boolean preventEndermenFromGriefing = true;
	@Entry public static boolean preventEndermenFromAttackingFirst = true;

	public static void initConfig() {
		configMetaData.put("preventEndermenFromTeleporting", Arrays.asList(
			"If enabled, prevents the endermen from teleporting."
		));
		configMetaData.put("preventEndermenFromGriefing", Arrays.asList(
			"If enabled, prevents from picking up and placing blocks."
		));
		configMetaData.put("preventEndermenFromAttackingFirst", Arrays.asList(
			"If enabled, stops the endermen from attacking."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}