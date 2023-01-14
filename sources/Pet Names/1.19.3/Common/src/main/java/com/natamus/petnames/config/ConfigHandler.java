/*
 * This is the latest source code of Pet Names.
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

package com.natamus.petnames.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.petnames.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean _useFemaleNames = true;
	@Entry public static boolean _useMaleNames = true;
	@Entry public static boolean nameWolves = true;
	@Entry public static boolean nameCats = true;
	@Entry public static boolean nameHorses = true;
	@Entry public static boolean nameDonkeys = true;
	@Entry public static boolean nameMules = true;
	@Entry public static boolean nameLlamas = true;

	public static void initConfig() {
		configMetaData.put("_useFemaleNames", Arrays.asList(
			"Use the list of female names when naming pets."
		));
		configMetaData.put("_useMaleNames", Arrays.asList(
			"Use the list of male names when naming pets."
		));
		configMetaData.put("nameWolves", Arrays.asList(
			"Give baby wolves a name."
		));
		configMetaData.put("nameCats", Arrays.asList(
			"Give kittens a name."
		));
		configMetaData.put("nameHorses", Arrays.asList(
			"Give baby horses a name."
		));
		configMetaData.put("nameDonkeys", Arrays.asList(
			"Give baby donkeys a name."
		));
		configMetaData.put("nameMules", Arrays.asList(
			"Give baby mules a name."
		));
		configMetaData.put("nameLlamas", Arrays.asList(
			"Give baby llamas a name."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}