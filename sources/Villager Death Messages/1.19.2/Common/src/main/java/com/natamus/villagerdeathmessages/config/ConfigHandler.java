/*
 * This is the latest source code of Villager Death Messages.
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

package com.natamus.villagerdeathmessages.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.villagerdeathmessages.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean showLocation = true;
	@Entry public static boolean mentionModdedVillagers = true;

	public static void initConfig() {
		configMetaData.put("showLocation", Arrays.asList(
			"If enabled, shows the location of the villager in the death message."
		));
		configMetaData.put("mentionModdedVillagers", Arrays.asList(
			"If enabled, also shows death messages of modded villagers. If you've found a 'villager'-entity that isn't named let me know by opening an issue so I can add it in."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}