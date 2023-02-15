/*
 * This is the latest source code of Configurable Despawn Timer.
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

package com.natamus.configurabledespawntimer.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.configurabledespawntimer.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 1, max = 2147483647) public static int despawnTimeInTicks = 12000;

	public static void initConfig() {
		configMetaData.put("despawnTimeInTicks", Arrays.asList(
			"The delay in ticks when an item should despawn, called the lifespan. Minecraft's default time is 6000 ticks. 1 second is 20 ticks."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}