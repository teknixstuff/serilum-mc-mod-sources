/*
 * This is the latest source code of Spiders Produce Webs.
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

package com.natamus.spidersproducewebs.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.spidersproducewebs.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 1, max = 256) public static int maxDistanceToSpiderBlocks = 32;
	@Entry(min = 1, max = 72000) public static int spiderWebProduceDelayTicks = 12000;

	public static void initConfig() {
		configMetaData.put("maxDistanceToSpiderBlocks", Arrays.asList(
			"The maximum distance in blocks the player can be away from a spider in order for the it to produce a web periodically."
		));
		configMetaData.put("spiderWebProduceDelayTicks", Arrays.asList(
			"The delay in between spiders producing a web in ticks (1 second = 20 ticks)."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}