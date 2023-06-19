/*
 * This is the latest source code of Fixed Anvil Repair Cost.
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

package com.natamus.fixedanvilrepaircost.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.fixedanvilrepaircost.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 0, max = 100) public static int repairCostLevelAmount = 3;
	@Entry(min = 0, max = 64) public static int repairCostMaterialAmount = 1;
	@Entry(min = 0, max = 1.0) public static double percentRepairedPerAction = 0.3333;

	public static void initConfig() {
		configMetaData.put("repairCostLevelAmount", Arrays.asList(
			"The amount of level it costs to repair an item on an anvil. A value of 0 keeps vanilla behaviour."
		));
		configMetaData.put("repairCostMaterialAmount", Arrays.asList(
			"The amount of material it costs to repair an item on an anvil. A value of 0 keeps vanilla behaviour."
		));
		configMetaData.put("percentRepairedPerAction", Arrays.asList(
			"How much the item is repaired per action. By default 33.33%, so 3 of 'repairCostMaterialAmount' fully repairs any item."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}