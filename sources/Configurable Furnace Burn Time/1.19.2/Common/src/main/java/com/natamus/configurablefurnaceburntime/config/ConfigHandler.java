/*
 * This is the latest source code of Configurable Furnace Burn Time.
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

package com.natamus.configurablefurnaceburntime.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.configurablefurnaceburntime.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static double burnTimeModifier = 1.0;

	public static void initConfig() {
		configMetaData.put("burnTimeModifier", Arrays.asList(
			"How much the fuel burn time should be modified. It's calculated by default burn time * burnTimeModifier. For example: sticks are by default 100 ticks (5 seconds). A burnTimeModifier of 2.0 makes it 200 ticks (10 seconds).",
			"min: 0.0, max: 1000.0"
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}