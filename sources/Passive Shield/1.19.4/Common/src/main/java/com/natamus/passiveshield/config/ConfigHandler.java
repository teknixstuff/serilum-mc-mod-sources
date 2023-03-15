/*
 * This is the latest source code of Passive Shield.
 * Minecraft version: 1.19.4.
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

package com.natamus.passiveshield.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.passiveshield.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean hideShieldWhenNotInUse = true;
	@Entry(min = 0.0, max = 1.0) public static double passiveShieldPercentageDamageNegated = 0.3333;

	public static void initConfig() {
		configMetaData.put("hideShieldWhenNotInUse", Arrays.asList(
			"When enabled, the shield will be hidden unless a player presses right-click."
		));
		configMetaData.put("passiveShieldPercentageDamageNegated", Arrays.asList(
			"The percentage of damage that will be negated when a player is hit while holding a shield that's not held high."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}