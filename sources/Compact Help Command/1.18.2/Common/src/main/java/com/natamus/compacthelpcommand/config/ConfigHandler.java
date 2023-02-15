/*
 * This is the latest source code of Compact Help Command.
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

package com.natamus.compacthelpcommand.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.compacthelpcommand.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean addVerticalBarSpacing = true;
	@Entry(min = 1, max = 50) public static int amountCommandsPerPage = 8;
	@Entry(min = 0, max = 15) public static int commandColour = 2;
	@Entry(min = 0, max = 15) public static int subcommandColour = 7;

	public static void initConfig() {
		configMetaData.put("addVerticalBarSpacing", Arrays.asList(
			"When enabled, adds a space in front and behind a vertical bar in the subcommands."
		));
		configMetaData.put("amountCommandsPerPage", Arrays.asList(
			"The message which will be sent to players when they use the /vote command."
		));
		configMetaData.put("commandColour", Arrays.asList(
			"The colour of the command in /help. The possible values are; 0: black, 1: dark_blue, 2: dark_green, 3: dark_aqua, 4: dark_red, 5: dark_purple, 6: gold, 7: gray, 8: dark_gray, 9: blue, 10: green, 11: aqua, 12: red, 13: light_purple, 14: yellow, 15: white."
		));
		configMetaData.put("subcommandColour", Arrays.asList(
			"The colour of the subcommand in /help. The possible values are; 0: black, 1: dark_blue, 2: dark_green, 3: dark_aqua, 4: dark_red, 5: dark_purple, 6: gold, 7: gray, 8: dark_gray, 9: blue, 10: green, 11: aqua, 12: red, 13: light_purple, 14: yellow, 15: white."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}