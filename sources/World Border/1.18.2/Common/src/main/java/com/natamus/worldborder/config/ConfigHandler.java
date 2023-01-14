/*
 * This is the latest source code of World Border.
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

package com.natamus.worldborder.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.worldborder.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean enableCustomOverworldBorder = true;
	@Entry public static boolean enableCustomNetherBorder = false;
	@Entry public static boolean enableCustomEndBorder = true;
	@Entry public static boolean shouldLoopToOppositeBorder = true;
	@Entry public static int distanceTeleportedBack = 10;
	@Entry public static String nearBorderMessage = "You're getting close to the world border!";
	@Entry public static String hitBorderMessage = "You've hit the world border, and were teleported inside!";
	@Entry public static String loopBorderMessage = "You've hit the world border, and have looped around the world!";
	@Entry public static int overworldBorderPositiveX = 5000;
	@Entry public static int overworldBorderNegativeX = -5000;
	@Entry public static int overworldBorderPositiveZ = 5000;
	@Entry public static int overworldBorderNegativeZ = -5000;
	@Entry public static int netherBorderPositiveX = 625;
	@Entry public static int netherBorderNegativeX = -625;
	@Entry public static int netherBorderPositiveZ = 625;
	@Entry public static int netherBorderNegativeZ = -625;
	@Entry public static int endBorderPositiveX = 5000;
	@Entry public static int endBorderNegativeX = -5000;
	@Entry public static int endBorderPositiveZ = 5000;
	@Entry public static int endBorderNegativeZ = -5000;

	public static void initConfig() {
		configMetaData.put("enableCustomOverworldBorder", Arrays.asList(
			"When enabled, uses the overworldBorderCoords to set the border."
		));
		configMetaData.put("enableCustomNetherBorder", Arrays.asList(
			"When enabled, uses the netherBorderCoords to set the border."
		));
		configMetaData.put("enableCustomEndBorder", Arrays.asList(
			"When enabled, uses the endBorderCoords to set the border."
		));
		configMetaData.put("shouldLoopToOppositeBorder", Arrays.asList(
			"When enabled, instead of teleporting the player inside near where they were, teleports them from the positive to the negative x/z coord and vice versa."
		));
		configMetaData.put("distanceTeleportedBack", Arrays.asList(
			"The amount of blocks the player is teleported inside after hitting the border.",
			"min: 0, max: 1000"
		));
		configMetaData.put("nearBorderMessage", Arrays.asList(
			"The message which will be sent to the player when they are within 'distanceTeleportedBack' to the world border."
		));
		configMetaData.put("hitBorderMessage", Arrays.asList(
			"The message which will be sent to the player when they hit the world border."
		));
		configMetaData.put("loopBorderMessage", Arrays.asList(
			"The message sent to the player when they hit the border and 'shouldLoopToOppositeBorder' is enabled."
		));
		configMetaData.put("overworldBorderPositiveX", Arrays.asList(
			"The overworld border located at the positive x coordinate.",
			"min: 0, max: 100000"
		));
		configMetaData.put("overworldBorderNegativeX", Arrays.asList(
			"The overworld border located at the negative x coordinate.",
			"min: -100000, max: 0"
		));
		configMetaData.put("overworldBorderPositiveZ", Arrays.asList(
			"The overworld border located at the positive z coordinate.",
			"min: 0, max: 100000"
		));
		configMetaData.put("overworldBorderNegativeZ", Arrays.asList(
			"The overworld border located at the negative z coordinate.",
			"min: -100000, max: 0"
		));
		configMetaData.put("netherBorderPositiveX", Arrays.asList(
			"The nether border located at the positive x coordinate.",
			"min: 0, max: 100000"
		));
		configMetaData.put("netherBorderNegativeX", Arrays.asList(
			"The nether border located at the negative x coordinate.",
			"min: -100000, max: 0"
		));
		configMetaData.put("netherBorderPositiveZ", Arrays.asList(
			"The nether border located at the positive z coordinate.",
			"min: 0, max: 100000"
		));
		configMetaData.put("netherBorderNegativeZ", Arrays.asList(
			"The nether border located at the negative z coordinate.",
			"min: -100000, max: 0"
		));
		configMetaData.put("endBorderPositiveX", Arrays.asList(
			"The end border located at the positive x coordinate.",
			"min: 0, max: 100000"
		));
		configMetaData.put("endBorderNegativeX", Arrays.asList(
			"The end border located at the negative x coordinate.",
			"min: -100000, max: 0"
		));
		configMetaData.put("endBorderPositiveZ", Arrays.asList(
			"The end border located at the positive z coordinate.",
			"min: 0, max: 100000"
		));
		configMetaData.put("endBorderNegativeZ", Arrays.asList(
			"The end border located at the negative z coordinate.",
			"min: -100000, max: 0"
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}