/*
 * This is the latest source code of Villager Names.
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

package com.natamus.villagernames.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.villagernames.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean _useCustomNames = true;
	@Entry public static boolean _useFemaleNames = true;
	@Entry public static boolean _useMaleNames = true;
	@Entry public static boolean nameModdedVillagers = true;

	public static void initConfig() {
		configMetaData.put("_useCustomNames", Arrays.asList(
			"Use the custom name list, editable in ./mods/villagernames/customnames.txt, seperated by a comma."
		));
		configMetaData.put("_useFemaleNames", Arrays.asList(
			"Use the list of pre-defined female names when naming villagers."
		));
		configMetaData.put("_useMaleNames", Arrays.asList(
			"Use the list of pre-defined male names when naming villagers."
		));
		configMetaData.put("nameModdedVillagers", Arrays.asList(
			"If enabled, also gives modded villagers a name. If you've found a 'villager'-entity that isn't named let me know by opening an issue so I can add it in."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}