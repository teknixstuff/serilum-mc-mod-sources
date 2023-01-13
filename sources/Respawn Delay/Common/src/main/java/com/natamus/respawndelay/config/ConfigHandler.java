/*
 * This is the latest source code of Respawn Delay.
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

package com.natamus.respawndelay.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.respawndelay.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean ignoreAdministratorPlayers = false;
	@Entry public static boolean ignoreCreativePlayers = true;
	@Entry public static boolean keepItemsOnDeath = false;
	@Entry public static boolean dropItemsOnDeath = true;
	@Entry public static boolean respawnAtWorldSpawn = true;
	@Entry public static boolean respawnWhenPlayerLogsOut = true;
	@Entry public static boolean respawnWhenPlayerLogsIn = true;
	@Entry public static int respawnDelayInSeconds = 10;
	@Entry public static String onDeathMessage = "You died! Your gamemode has been set to spectator.";
	@Entry public static String onRespawnMessage = "You respawned.";
	@Entry public static String waitingForRespawnMessage = "You will respawn in <seconds_left> seconds.";
	@Entry public static int lowestPossibleYCoordinate = -10;

	public static void initConfig() {
		configMetaData.put("ignoreAdministratorPlayers", Arrays.asList(
			"If enabled, player operators/administrators will not be put in spectator mode on death."
		));
		configMetaData.put("ignoreCreativePlayers", Arrays.asList(
			"If enabled, player in creative mode will not be put in spectator mode on death."
		));
		configMetaData.put("keepItemsOnDeath", Arrays.asList(
			"If enabled, players will keep their items on death, and no drop event will be ran. This will also ignore the 'dropItemsOnDeath' config."
		));
		configMetaData.put("dropItemsOnDeath", Arrays.asList(
			"If enabled, players will drop their items on death as normal vanilla behaviour."
		));
		configMetaData.put("respawnAtWorldSpawn", Arrays.asList(
			"If enabled, players will be respawned at the world spawn point. If disabled, they'll respawn wherever they're spectating."
		));
		configMetaData.put("respawnWhenPlayerLogsOut", Arrays.asList(
			"If enabled, players will respawn when they logout while waiting for a respawn. Prevents players from getting stuck in spectator mode."
		));
		configMetaData.put("respawnWhenPlayerLogsIn", Arrays.asList(
			"If enabled, players will respawn when they log in and are still in spectator mode. Prevents players from getting stuck in spectator mode."
		));
		configMetaData.put("respawnDelayInSeconds", Arrays.asList(
			"The delay in seconds after which a spectating player will respawn. A value of -1 makes players never respawn automatically. The '/respawnall' command can still be used.",
			"min: -1, max: 3600"
		));
		configMetaData.put("onDeathMessage", Arrays.asList(
			"The message which is sent to the player on death. Leave empty to disable."
		));
		configMetaData.put("onRespawnMessage", Arrays.asList(
			"The message which is sent to players when they respawn. Leave empty to disable."
		));
		configMetaData.put("waitingForRespawnMessage", Arrays.asList(
			"The message which is sent to players when they are waiting to be respawned. The text '<seconds_left>' will be replaced with the actual seconds left. Leave empty to disable."
		));
		configMetaData.put("lowestPossibleYCoordinate", Arrays.asList(
			"When a player falls into the void, this determines the y position that's set after when a player enters spectator mode.",
			"min: -50, max: 256"
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}