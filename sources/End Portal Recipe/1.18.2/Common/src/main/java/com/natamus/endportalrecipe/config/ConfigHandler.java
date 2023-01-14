/*
 * This is the latest source code of End Portal Recipe.
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

package com.natamus.endportalrecipe.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.endportalrecipe.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean mustHaveSilkTouchToBreakPortal = true;
	@Entry public static boolean addBrokenPortalFramesToInventory = true;
	@Entry public static boolean sendMessageOnExtraDragonEggDrop = true;

	public static void initConfig() {
		configMetaData.put("mustHaveSilkTouchToBreakPortal", Arrays.asList(
			"If enabled, players can only break portal frames if they have silk touch on their pickaxe."
		));
		configMetaData.put("addBrokenPortalFramesToInventory", Arrays.asList(
			"If enabled, add portal frames directly to the player's inventory to lower the chance of them being destroyed. Still drops the item entity if the inventory is full."
		));
		configMetaData.put("sendMessageOnExtraDragonEggDrop", Arrays.asList(
			"Whether a message should be sent to the player where the extra dragon egg will drop."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}