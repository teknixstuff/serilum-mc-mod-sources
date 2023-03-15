/*
 * This is the latest source code of Set World Spawn Point.
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

package com.natamus.setworldspawnpoint.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.setworldspawnpoint.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean _forceExactSpawn = true;
	@Entry(min = -100000, max = 100000) public static int xCoordSpawnPoint = 0;
	@Entry(min = -1, max = 256) public static int yCoordSpawnPoint = -1;
	@Entry(min = -100000, max = 100000) public static int zCoordSpawnPoint = 0;

	public static void initConfig() {
		configMetaData.put("_forceExactSpawn", Arrays.asList(
			"If enabled, spawns players on the exact world spawn instead of around it."
		));
		configMetaData.put("xCoordSpawnPoint", Arrays.asList(
			"The X coordinate of the spawn point of newly created worlds."
		));
		configMetaData.put("yCoordSpawnPoint", Arrays.asList(
			"The Y coordinate of the spawn point of newly created worlds. By default -1, which means it'll be the first solid block descending from y=256."
		));
		configMetaData.put("zCoordSpawnPoint", Arrays.asList(
			"The Z coordinate of the spawn point of newly created worlds."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}