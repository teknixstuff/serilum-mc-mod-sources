/*
 * This is the latest source code of Welcome Message.
 * Minecraft version: 1.19.3.
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

package com.natamus.welcomemessage.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.welcomemessage.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean onlyRunOnDedicatedServers = true;
	@Entry public static boolean sendEmptyLineBeforeFirstMessage = true;
	@Entry public static String messageOneText = "Welcome to the server!";
	@Entry public static int messageOneColourIndex = 2;
	@Entry public static String messageOneOptionalURL = "";
	@Entry public static String messageTwoText = "Downloaded from CurseForge! This is a clickable link.";
	@Entry public static int messageTwoColourIndex = 14;
	@Entry public static String messageTwoOptionalURL = "https://curseforge.com/members/serilum/projects";
	@Entry public static String messageThreeText = "You should probably edit this in the config :)";
	@Entry public static int messageThreeColourIndex = 15;
	@Entry public static String messageThreeOptionalURL = "";

	public static void initConfig() {
		configMetaData.put("onlyRunOnDedicatedServers", Arrays.asList(
			"If the mod should only run on dedicated servers. When enabled it's not sent when in a singleplayer world."
		));
		configMetaData.put("sendEmptyLineBeforeFirstMessage", Arrays.asList(
			"Whether an empty line should be send before to first message to separate the welcome from other chat messages that might be sent."
		));
		configMetaData.put("messageOneText", Arrays.asList(
			"The first message a player will receive when joining the world. Can be left empty."
		));
		configMetaData.put("messageOneColourIndex", Arrays.asList(
			"0: black, 1: dark_blue, 2: dark_green, 3: dark_aqua, 4: dark_red, 5: dark_purple, 6: gold, 7: gray, 8: dark_gray, 9: blue, 10: green, 11: aqua, 12: red, 13: light_purple, 14: yellow, 15: white",
			"min: 0, max: 15"
		));
		configMetaData.put("messageOneOptionalURL", Arrays.asList(
			"If a link is entered here, the complete message will be clickable."
		));
		configMetaData.put("messageTwoText", Arrays.asList(
			"The second message a player will receive when joining the world. Can be left empty."
		));
		configMetaData.put("messageTwoColourIndex", Arrays.asList(
			"0: black, 1: dark_blue, 2: dark_green, 3: dark_aqua, 4: dark_red, 5: dark_purple, 6: gold, 7: gray, 8: dark_gray, 9: blue, 10: green, 11: aqua, 12: red, 13: light_purple, 14: yellow, 15: white",
			"min: 0, max: 15"
		));
		configMetaData.put("messageTwoOptionalURL", Arrays.asList(
			"If a link is entered here, the complete message will be clickable."
		));
		configMetaData.put("messageThreeText", Arrays.asList(
			"The third message a player will receive when joining the world. Can be left empty."
		));
		configMetaData.put("messageThreeColourIndex", Arrays.asList(
			"0: black, 1: dark_blue, 2: dark_green, 3: dark_aqua, 4: dark_red, 5: dark_purple, 6: gold, 7: gray, 8: dark_gray, 9: blue, 10: green, 11: aqua, 12: red, 13: light_purple, 14: yellow, 15: white",
			"min: 0, max: 15"
		));
		configMetaData.put("messageThreeOptionalURL", Arrays.asList(
			"If a link is entered here, the complete message will be clickable."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}