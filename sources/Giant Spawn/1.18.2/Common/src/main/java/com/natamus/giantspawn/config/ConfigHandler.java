/*
 * This is the latest source code of Giant Spawn.
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

package com.natamus.giantspawn.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.giantspawn.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static double chanceSurfaceZombieIsGiant = 0.05;
	@Entry public static boolean shouldBurnGiantsInDaylight = true;
	@Entry public static boolean onlySpawnGiantOnSurface = true;
	@Entry public static double giantMovementSpeedModifier = 1.0;
	@Entry public static double giantAttackDamageModifier = 2.0;

	public static void initConfig() {
		configMetaData.put("chanceSurfaceZombieIsGiant", Arrays.asList(
			"The chance a zombie that has spawned on the surface is a giant.",
			"min: 0, max: 1.0"
		));
		configMetaData.put("shouldBurnGiantsInDaylight", Arrays.asList(
			"If enabled, burns giants when daylight shines upon them."
		));
		configMetaData.put("onlySpawnGiantOnSurface", Arrays.asList(
			"If enabled, a giant will only spawn on the surface."
		));
		configMetaData.put("giantMovementSpeedModifier", Arrays.asList(
			"The giant movement speed modifier relative to the base zombie movement speed.",
			"min: 0, max: 20.0"
		));
		configMetaData.put("giantAttackDamageModifier", Arrays.asList(
			"The giant attack damage modifier relative to the base zombie attack damage.",
			"min: 0, max: 20.0"
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}