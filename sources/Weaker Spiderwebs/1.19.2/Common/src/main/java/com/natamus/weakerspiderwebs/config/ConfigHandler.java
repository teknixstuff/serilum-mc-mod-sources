/*
 * This is the latest source code of Weaker Spiderwebs.
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

package com.natamus.weakerspiderwebs.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.weakerspiderwebs.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static int breakSpiderwebDelay = 500;

	public static void initConfig() {
		configMetaData.put("breakSpiderwebDelay", Arrays.asList(
			"The delay in ms after walking in a spiderweb until it breaks.",
			"min: 0, max: 10000"
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}