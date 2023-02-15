/*
 * This is the latest source code of Mineral Chance.
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

package com.natamus.mineralchance.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.mineralchance.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 0, max = 1.0) public static double extraMineralChanceOnOverworldStoneBreak = 0.02;
	@Entry(min = 0, max = 1.0) public static double extraMineralChanceOnNetherStoneBreak = 0.01;
	@Entry public static boolean enableOverworldMinerals = true;
	@Entry public static boolean enableNetherMinerals = true;
	@Entry public static boolean sendMessageOnMineralFind = true;
	@Entry public static String foundMineralMessage = "You've found a mineral hidden in the block!";
	@Entry public static boolean ignoreFakePlayers = true;

	public static void initConfig() {
		configMetaData.put("extraMineralChanceOnOverworldStoneBreak", Arrays.asList(
			"The chance a mineral is dropped when an overworld stone block is broken. By default 1/50."
		));
		configMetaData.put("extraMineralChanceOnNetherStoneBreak", Arrays.asList(
			"The chance a mineral is dropped when a nether stone block is broken. By default 1/100."
		));
		configMetaData.put("enableOverworldMinerals", Arrays.asList(
			"If enabled, mining overworld stone blocks in the overworld has a chance to drop an overworld mineral. These consist of diamonds, gold nuggets, iron nuggets, lapis lazuli, redstone and emeralds."
		));
		configMetaData.put("enableNetherMinerals", Arrays.asList(
			"If enabled, mining nether stone blocks in the nether has a chance to drop a nether mineral. These consist of quartz and gold nuggets."
		));
		configMetaData.put("sendMessageOnMineralFind", Arrays.asList(
			"If enabled, sends a message when a mineral is found to the player who broke the stone block."
		));
		configMetaData.put("foundMineralMessage", Arrays.asList(
			"The message sent to the player who found a hidden mineral when 'sendMessageOnMineralFind' is enabled."
		));
		configMetaData.put("ignoreFakePlayers", Arrays.asList(
			"If enabled, minerals won't be dropped if the player is a fake. For example when a mod breaks a block as a simulated player."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}