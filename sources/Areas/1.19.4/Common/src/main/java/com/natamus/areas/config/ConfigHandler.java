/*
 * This is the latest source code of Areas.
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

package com.natamus.areas.config;

import com.natamus.areas.util.Reference;
import com.natamus.collective.config.DuskConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean giveUnnamedAreasRandomName = true;
	@Entry(min = 0, max = 1000) public static int radiusAroundPlayerToCheckForSigns = 100;
	@Entry(min = 0, max = 1000) public static int defaultAreaRadius = 30;
	@Entry public static boolean sendChatMessages = false;
	@Entry public static boolean showHUDMessages = true;
	@Entry public static boolean showEnterMessage = true;
	@Entry public static String enterPrefix = "Entering ";
	@Entry public static String enterSuffix = ".";
	@Entry public static boolean showLeaveMessage = true;
	@Entry public static String leavePrefix = "Leaving ";
	@Entry public static String leaveSuffix = ".";

	@Entry public static boolean showHUDTextShadow = true;
	@Entry(min = 100, max = 360000) public static int HUDMessageFadeDelayMs = 4000;
	@Entry(min = 0, max = 3000) public static int HUDMessageHeightOffset = 10;
	@Entry(min = 0, max = 10.0) public static double HUD_FontSizeScaleModifier = 1.0;
	@Entry(min = 0, max = 255) public static int HUD_RGB_R = 100;
	@Entry(min = 0, max = 255) public static int HUD_RGB_G = 200;
	@Entry(min = 0, max = 255) public static int HUD_RGB_B = 50;

	public static void initConfig() {
		configMetaData.put("giveUnnamedAreasRandomName", Arrays.asList(
			"When enabled, gives signs without an area name a randomly chosen one from a preset list."
		));
		configMetaData.put("radiusAroundPlayerToCheckForSigns", Arrays.asList(
			"The radius in blocks around the player in which to check for area signs."
		));
		configMetaData.put("defaultAreaRadius", Arrays.asList(
			"The default radius for areas when it's left empty on the sign. It will be added automatically."
		));
		configMetaData.put("sendChatMessages", Arrays.asList(
			"When enabled, sends the player the area notifications in chat."
		));
		configMetaData.put("showHUDMessages", Arrays.asList(
			"When enabled, sends the player the area notifications in the HUD on screen."
		));
		configMetaData.put("showEnterMessage", Arrays.asList(
			"Whether a message should be sent when a player enters an area."
		));
		configMetaData.put("enterPrefix", Arrays.asList(
			"The prefix of the message whenever a player enters an area."
		));
		configMetaData.put("enterSuffix", Arrays.asList(
			"The suffix of the message whenever a player enters an area."
		));
		configMetaData.put("showLeaveMessage", Arrays.asList(
			"Whether a message should be sent when a player leaves an area."
		));
		configMetaData.put("leavePrefix", Arrays.asList(
			"The prefix of the message whenever a player leaves an area."
		));
		configMetaData.put("leaveSuffix", Arrays.asList(
			"The suffix of the message whenever a player leaves an area."
		));
		configMetaData.put("showHUDTextShadow", Arrays.asList(
			"Whether the text shown should be drawn with a shadow."
		));
		configMetaData.put("HUDMessageFadeDelayMs", Arrays.asList(
			"The delay in ms after which the HUD message should fade out."
		));
		configMetaData.put("HUDMessageHeightOffset", Arrays.asList(
			"The vertical offset (y coord) for the HUD message. This determines how far down the message should be on the screen. Can be changed to prevent GUIs from overlapping."
		));
		configMetaData.put("HUD_FontSizeScaleModifier", Arrays.asList(
			"Increases the font size of the text in the HUD message. If you change this value, make sure to test the different GUI scale settings in-game. 6.0 is considered large."
		));
		configMetaData.put("HUD_RGB_R", Arrays.asList(
			"The red RGB value for the HUD message."
		));
		configMetaData.put("HUD_RGB_G", Arrays.asList(
			"The green RGB value for the HUD message."
		));
		configMetaData.put("HUD_RGB_B", Arrays.asList(
			"The blue RGB value for the HUD message."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}