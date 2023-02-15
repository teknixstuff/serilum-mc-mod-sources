/*
 * This is the latest source code of Just Player Heads.
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

package com.natamus.justplayerheads.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.justplayerheads.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean enablePlayerHeadCaching = true;
	@Entry public static boolean playerDropsHeadOnDeath = true;
	@Entry(min = 0.0001, max = 1.0) public static double playerHeadDropChance = 1.0;

	public static void initConfig() {
		configMetaData.put("enablePlayerHeadCaching", Arrays.asList(
			"If enabled, enables the caching of player head textures. This fixes the limitations of the Mojang API, but does mean that whenever players update their skin the old head is still dropped until the server reboots or an administator uses the '/jph cache' command."
		));
		configMetaData.put("playerDropsHeadOnDeath", Arrays.asList(
			"If enabled, allows players to drop their head on death."
		));
		configMetaData.put("playerHeadDropChance", Arrays.asList(
			"Sets the chance of a player dropping their head if 'playerDropsHeadOnDeath' is enabled."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}