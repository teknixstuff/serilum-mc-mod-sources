/*
 * This is the latest source code of Breedable Killer Rabbit.
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

package com.natamus.breedablekillerrabbit.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.breedablekillerrabbit.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static double chanceBabyRabbitIsKiller = 0.05;
	@Entry public static boolean removeKillerRabbitNameTag = true;

	public static void initConfig() {
		configMetaData.put("chanceBabyRabbitIsKiller", Arrays.asList(
			"The chance that a baby rabbit is of the killer variant.",
			"min: 0, max: 1.0"
		));
		configMetaData.put("removeKillerRabbitNameTag", Arrays.asList(
			"Remove the name 'The Killer Bunny' from the baby killer rabbit."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}