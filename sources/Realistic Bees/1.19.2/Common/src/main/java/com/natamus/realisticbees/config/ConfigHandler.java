/*
 * This is the latest source code of Realistic Bees.
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

package com.natamus.realisticbees.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.realisticbees.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static double beeSizeModifier = 0.25;
	@Entry public static boolean beesDieFromStingingPlayer = true;
	@Entry public static boolean beesDieFromStingingMob = true;
	@Entry public static boolean beesLeaveTheirStinger = true;
	@Entry public static boolean sendStungPlayerWithStingerAMessage = true;
	@Entry public static double chanceBeeLeavesItsStinger = 0.33;
	@Entry public static double chanceBeeStingerIsPulledOut = 0.5;
	@Entry public static int timeInSecondsStingerPumpsPoison = 30;
	@Entry public static int timeInSecondsBeeWithoutStingerDies = 60;
	@Entry public static int extraBeeSpawnsPerBee = 9;
	@Entry public static int beeHiveBeeSpace = 20;
	@Entry public static boolean preventBeeSuffocationDamage = true;

	public static void initConfig() {
		configMetaData.put("beeSizeModifier", Arrays.asList(
			"This value determines the size of a bee. By default 0.25, which means they are 1/4th of their vanilla size. Set to 1.0 to disable the different bee size feature.",
			"min: 0.01, max: 5.0"
		));
		configMetaData.put("beesDieFromStingingPlayer", Arrays.asList(
			"When enabled, bees die after stinging a player. This takes roughly a few minutes."
		));
		configMetaData.put("beesDieFromStingingMob", Arrays.asList(
			"When enabled, bees die after stinging a mob. This takes roughly a few minutes."
		));
		configMetaData.put("beesLeaveTheirStinger", Arrays.asList(
			"After a bee stings, it has a chance to leave its stinger inside the entity. It must be pulled out."
		));
		configMetaData.put("sendStungPlayerWithStingerAMessage", Arrays.asList(
			"After a bee stings a player and its stinger is left behind, send the player a message."
		));
		configMetaData.put("chanceBeeLeavesItsStinger", Arrays.asList(
			"The chance the bee's stinger lodges in the stung entity, resulting in death.",
			"min: 0, max: 1.0"
		));
		configMetaData.put("chanceBeeStingerIsPulledOut", Arrays.asList(
			"The chance the bee's stinger is pulled out by a player after right-clicking shears.",
			"min: 0, max: 1.0"
		));
		configMetaData.put("timeInSecondsStingerPumpsPoison", Arrays.asList(
			"The time in seconds a stinger that's left in an entity continues to pump poison.",
			"min: 0, max: 300"
		));
		configMetaData.put("timeInSecondsBeeWithoutStingerDies", Arrays.asList(
			"The time in a seconds after a bee without its stinger dies.",
			"min: 0, max: 600"
		));
		configMetaData.put("extraBeeSpawnsPerBee", Arrays.asList(
			"In order to make bees a little more common. Whenever a bee naturally spawns, the mod spawns an additional 'extraBeeSpawnsPerBee' bees.",
			"min: 0, max: 50"
		));
		configMetaData.put("beeHiveBeeSpace", Arrays.asList(
			"How many bees should be able to enter the hive in total. Minecraft's default is 3, but bees are a lot smaller so there should be more space.",
			"min: 0, max: 50"
		));
		configMetaData.put("preventBeeSuffocationDamage", Arrays.asList(
			"The smaller (baby) bees can sometimes do something unexpected with their AI and get suffocation damage. Preventing this damage fixes them disappearing."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}