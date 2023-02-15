/*
 * This is the latest source code of Nutritious Milk.
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

package com.natamus.nutritiousmilk.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.nutritiousmilk.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 0, max = 20) public static int hungerLevelIncrease = 10;
	@Entry(min = 0, max = 20.0) public static double saturationLevelIncrease = 10.0;

	public static void initConfig() {
		configMetaData.put("hungerLevelIncrease", Arrays.asList(
			"The hunger level decreased. Example values: cookie = 2, bread = 5, salmon = 6, steak = 8."
		));
		configMetaData.put("saturationLevelIncrease", Arrays.asList(
			"The saturation increase. Example values: melon = 1.2, carrot = 3.6, chicken = 7.2, steak 12.8."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}