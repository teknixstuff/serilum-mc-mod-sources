/*
 * This is the latest source code of Realistic Bees.
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

package com.natamus.realisticbees.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.realisticbees.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 0.01, max = 5.0) public static double beeSizeModifier = 0.25;
	@Entry(min = 0, max = 50) public static int extraBeeSpawnsPerBee = 9;
	@Entry(min = 0, max = 50) public static int beeHiveBeeSpace = 20;
	@Entry public static boolean preventBeeSuffocationDamage = true;

	public static void initConfig() {
		configMetaData.put("beeSizeModifier", Arrays.asList(
			"This value determines the size of a bee. By default 0.25, which means they are 1/4th of their vanilla size. Set to 1.0 to disable the different bee size feature."
		));
		configMetaData.put("extraBeeSpawnsPerBee", Arrays.asList(
			"In order to make bees a little more common. Whenever a bee naturally spawns, the mod spawns an additional 'extraBeeSpawnsPerBee' bees."
		));
		configMetaData.put("beeHiveBeeSpace", Arrays.asList(
			"How many bees should be able to enter the hive in total. Minecraft's default is 3, but bees are a lot smaller so there should be more space."
		));
		configMetaData.put("preventBeeSuffocationDamage", Arrays.asList(
			"The smaller (baby) bees can sometimes do something unexpected with their AI and get suffocation damage. Preventing this damage fixes them disappearing."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}