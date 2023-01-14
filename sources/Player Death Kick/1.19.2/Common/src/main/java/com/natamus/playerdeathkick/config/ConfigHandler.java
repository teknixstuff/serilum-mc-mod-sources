/*
 * This is the latest source code of Player Death Kick.
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

package com.natamus.playerdeathkick.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.playerdeathkick.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static String disconnectMessage = "You died by %death%! You have been disconnected from the server.";
	@Entry public static boolean addDeathCauseToMessage = true;
	@Entry public static boolean exemptAdminPlayers = true;
	@Entry public static boolean broadcastKickToServer = true;

	public static void initConfig() {
		configMetaData.put("disconnectMessage", Arrays.asList(
			"The message players will receive when disconnected on death."
		));
		configMetaData.put("addDeathCauseToMessage", Arrays.asList(
			"If enabled, replaces %death% in the disconnect message with the death cause."
		));
		configMetaData.put("exemptAdminPlayers", Arrays.asList(
			"If enabled, exempts admin players (with cheat access, OPs) from being kicked on death."
		));
		configMetaData.put("broadcastKickToServer", Arrays.asList(
			"If enabled, sends a message to all players online with who was kicked."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}