/*
 * This is the latest source code of Zombie Horse Spawn.
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

package com.natamus.zombiehorsespawn.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.zombiehorsespawn.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 0, max = 1.0) public static double chanceSurfaceZombieHasHorse = 0.05;
	@Entry public static boolean shouldBurnZombieHorsesInDaylight = true;
	@Entry public static boolean onlySpawnZombieHorsesOnSurface = true;

	public static void initConfig() {
		configMetaData.put("chanceSurfaceZombieHasHorse", Arrays.asList(
			"The chance a zombie that has spawned on the surface is riding a horse."
		));
		configMetaData.put("shouldBurnZombieHorsesInDaylight", Arrays.asList(
			"If enabled, burns zombie horses when daylight shines upon them."
		));
		configMetaData.put("onlySpawnZombieHorsesOnSurface", Arrays.asList(
			"If enabled, a zombie horse with rider will only spawn on the surface."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}