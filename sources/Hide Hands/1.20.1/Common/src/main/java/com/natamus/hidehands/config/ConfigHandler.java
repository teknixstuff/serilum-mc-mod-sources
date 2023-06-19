/*
 * This is the latest source code of Hide Hands.
 * Minecraft version: 1.20.1.
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

package com.natamus.hidehands.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.hidehands.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean alwaysHideMainHand = false;
	@Entry public static boolean alwaysHideEmptyMainHand = true;
	@Entry public static String hideMainHandWithItems = "";
	@Entry public static boolean alwaysHideOffhand = false;
	@Entry public static String hideOffhandWithItems = "minecraft:totem_of_undying,minecraft:torch";

	public static void initConfig() {
		configMetaData.put("alwaysHideMainHand", Arrays.asList(
			"If enabled, always hides the main hand. If disabled, hides the main hand when holding the items defined in hideMainHandWithItems."
		));
		configMetaData.put("alwaysHideEmptyMainHand", Arrays.asList(
			"If enabled, always hides the main hand if it's empty. Will still be visible when swung empty."
		));
		configMetaData.put("hideMainHandWithItems", Arrays.asList(
			"The items which when held will hide the main hand if alwaysHideMainHand is disabled."
		));
		configMetaData.put("alwaysHideOffhand", Arrays.asList(
			"If enabled, always hides the offhand. If disabled, hides the offhand when holding the items defined in hideOffhandWithItems."
		));
		configMetaData.put("hideOffhandWithItems", Arrays.asList(
			"The items which when held will hide the offhand if alwaysHideOffhand is disabled."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}