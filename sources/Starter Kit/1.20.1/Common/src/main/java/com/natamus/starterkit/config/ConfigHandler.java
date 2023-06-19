/*
 * This is the latest source code of Starter Kit.
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

package com.natamus.starterkit.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.starterkit.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean addExistingItemsAfterKitSet = true;
	@Entry public static boolean enableFTBIslandCreateCompatibility = true;

	public static void initConfig() {
		configMetaData.put("addExistingItemsAfterKitSet", Arrays.asList(
			"Whether items that existed in the inventory, such as books added by other mods, should be added back to the inventory after the kit was set. If disabled, they'll be removed. You can still manually set them via the kit."
		));
		configMetaData.put("enableFTBIslandCreateCompatibility", Arrays.asList(
			"Whether the starter kit should be re-set after the '/ftbteamislands create' command from FTB Team Islands. Does nothing when it's not installed."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}