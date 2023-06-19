/*
 * This is the latest source code of Beautified Chat Client.
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

package com.natamus.beautifiedchatclient.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.beautifiedchatclient.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static String chatMessageFormat = "%timestamp% | %username%: %chatmessage%";
	@Entry public static String timestampFormat = "HH:mm";
	@Entry(min = 0, max = 15) public static int chatTimestampColour = 8;
	@Entry(min = 0, max = 15) public static int chatUsernameColour = 2;
	@Entry(min = 0, max = 15) public static int chatMessageColour = 15;
	@Entry(min = 0, max = 15) public static int chatOtherSymbolsColour = 7;

	public static void initConfig() {
		configMetaData.put("chatMessageFormat", Arrays.asList(
			"Variables: %timestamp% = timestamp set in timestampFormat. %username% = the username of the player who sent the message. %username% = the username of who sent the message."
		));
		configMetaData.put("timestampFormat", Arrays.asList(
			"Example time in formats: 5 seconds past 9 o' clock in the evening. *=Default. *(HH:mm) = 21:00, (HH:mm:ss) = 21:00:05, (hh:mm a) = 9:00 PM"
		));
		configMetaData.put("chatTimestampColour", Arrays.asList(
			"The colour of the timestamp in the chat message. The possible values are; 0: black, 1: dark_blue, 2: dark_green, 3: dark_aqua, 4: dark_red, 5: dark_purple, 6: gold, 7: gray, 8: dark_gray, 9: blue, 10: green, 11: aqua, 12: red, 13: light_purple, 14: yellow, 15: white."
		));
		configMetaData.put("chatUsernameColour", Arrays.asList(
			"The colour of the username in the chat messsage. The possible values are; 0: black, 1: dark_blue, 2: dark_green, 3: dark_aqua, 4: dark_red, 5: dark_purple, 6: gold, 7: gray, 8: dark_gray, 9: blue, 10: green, 11: aqua, 12: red, 13: light_purple, 14: yellow, 15: white."
		));
		configMetaData.put("chatMessageColour", Arrays.asList(
			"The colour of the chat message. The possible values are; 0: black, 1: dark_blue, 2: dark_green, 3: dark_aqua, 4: dark_red, 5: dark_purple, 6: gold, 7: gray, 8: dark_gray, 9: blue, 10: green, 11: aqua, 12: red, 13: light_purple, 14: yellow, 15: white."
		));
		configMetaData.put("chatOtherSymbolsColour", Arrays.asList(
			"The colour of the other symbols from chatMessageFormat. So everything except the variables. The possible values are; 0: black, 1: dark_blue, 2: dark_green, 3: dark_aqua, 4: dark_red, 5: dark_purple, 6: gold, 7: gray, 8: dark_gray, 9: blue, 10: green, 11: aqua, 12: red, 13: light_purple, 14: yellow, 15: white."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}