/*
 * This is the latest source code of Hand Over Your Items.
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

package com.natamus.handoveryouritems.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.handoveryouritems.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean sendItemReceivedMessage = true;
	@Entry public static boolean sendItemGivenMessage = true;
	@Entry public static boolean mustCrouchToGiveItem = true;

	public static void initConfig() {
		configMetaData.put("sendItemReceivedMessage", Arrays.asList(
			"If enabled, players will receive a message when they have been given an item."
		));
		configMetaData.put("sendItemGivenMessage", Arrays.asList(
			"If enabled, players will receive a message when they give an item."
		));
		configMetaData.put("mustCrouchToGiveItem", Arrays.asList(
			"If enabled, players will only be able to give items when they are crouching/sneaking."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}