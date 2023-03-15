/*
 * This is the latest source code of Eroding Stone Entities.
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

package com.natamus.erodingstoneentities.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.erodingstoneentities.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 1, max = 3600) public static int durationInSecondsStoneErodes = 150;
	@Entry public static boolean preventErosionIfAboveIceBlock = true;
	@Entry public static boolean erodeIntoClayBlockInsteadOfClayBall = false;
	@Entry public static String itemsWhichErodeIntoSand = "minecraft:cobblestone,minecraft:mossy_cobblestone,minecraft:stone,minecraft:stone_bricks,minecraft:chiseled_stone_bricks,minecraft:cracked_stone_bricks,minecraft:smooth_stone,minecraft:gravel,minecraft:andesite,minecraft:polished_andesite,minecraft:diorite,minecraft:polished_diorite,minecraft:granite,minecraft:polished_granite,minecraft:sandstone,minecraft:chiseled_sandstone,minecraft:cut_sandstone,minecraft:smooth_sandstone";
	@Entry public static String itemsWhichErodeIntoRedSand = "minecraft:red_sandstone,minecraft:chiseled_red_sandstone,minecraft:cut_red_sandstone,minecraft:smooth_red_sandstone,minecraft:netherrack,minecraft:nether_bricks,minecraft:red_nether_bricks";
	@Entry public static String itemsWhichErodeIntoClay = "minecraft:terracotta,minecraft:white_terracotta,minecraft:orange_terracotta,minecraft:magenta_terracotta,minecraft:light_blue_terracotta,minecraft:yellow_terracotta,minecraft:lime_terracotta,minecraft:pink_terracotta,minecraft:gray_terracotta,minecraft:light_gray_terracotta,minecraft:cyan_terracotta,minecraft:purple_terracotta,minecraft:blue_terracotta,minecraft:brown_terracotta,minecraft:green_terracotta,minecraft:red_terracotta,minecraft:black_terracotta";

	public static void initConfig() {
		configMetaData.put("durationInSecondsStoneErodes", Arrays.asList(
			"The duration in seconds after a stone-type item entity in the world erodes to sand if it's in a water stream."
		));
		configMetaData.put("preventErosionIfAboveIceBlock", Arrays.asList(
			"If enabled, prevents the erosion of stone blocks if the item entity is above an ice block. Useful for when you use water streams to transport items you don't want to have eroded, just place ice underneath the streams."
		));
		configMetaData.put("erodeIntoClayBlockInsteadOfClayBall", Arrays.asList(
			"If enabled, the items specified in 'itemsWhichErodeIntoClay' will erode into a clay block instead of the default clay ball."
		));
		configMetaData.put("itemsWhichErodeIntoSand", Arrays.asList(
			"The items which erode into normal sand when left in flowing water. Divided by a comma."
		));
		configMetaData.put("itemsWhichErodeIntoRedSand", Arrays.asList(
			"The items which erode into red sand when left in flowing water. Divided by a comma."
		));
		configMetaData.put("itemsWhichErodeIntoClay", Arrays.asList(
			"The items which erode into clay balls when left in flowing water. Divided by a comma."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}