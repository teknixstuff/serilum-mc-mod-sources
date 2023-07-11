/*
 * This is the latest source code of Automatic Operator.
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

package com.natamus.automaticoperator.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.automaticoperator.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean enableAutomaticOperator = false;
	@Entry public static boolean onlyRunOnDedicatedServers = true;
	@Entry public static boolean onlyMakeSpecificPlayerNamesOP = false;
	@Entry public static String specificOperatorPlayerNames = "Player1,Player2";

	public static void initConfig() {
		configMetaData.put("enableAutomaticOperator", Arrays.asList(
			"If the mod should be enabled. Turned off by default to prevent unwanted behaviour in modpacks."
		));
		configMetaData.put("onlyRunOnDedicatedServers", Arrays.asList(
			"If the mod should only run on dedicated servers."
		));
		configMetaData.put("onlyMakeSpecificPlayerNamesOP", Arrays.asList(
			"If enabled, only player names specified in 'specificOperatorPlayerNames' will be made operators. If disabled, everyone will."
		));
		configMetaData.put("specificOperatorPlayerNames", Arrays.asList(
			"The specific names of players that the mod automatically makes operators. Seperated by a comma ( , )."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}