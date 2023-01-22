/*
 * This is the latest source code of Extended Creative Inventory.
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

package com.natamus.extendedcreativeinventory.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.extendedcreativeinventory.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static int extendedItemGroupIndex = 4;

	public static void initConfig() {
		configMetaData.put("extendedItemGroupIndex", Arrays.asList(
			"The index of the 'Extended' creative tab. Can be changed if another mod already uses the default fifth spot (index 4).",
			"min: 0, max: 100"
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}