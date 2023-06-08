/*
 * This is the latest source code of Death Backup.
 * Minecraft version: 1.20.0.
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

package com.natamus.deathbackup.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.deathbackup.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean sendBackupReminderMessageToThoseWithAccessOnDeath = true;
	@Entry public static String backupReminderMessage = "A backup of your inventory before your death has been created and can be loaded with '/deathbackup load 0'.";

	public static void initConfig() {
		configMetaData.put("sendBackupReminderMessageToThoseWithAccessOnDeath", Arrays.asList(
			"When enabled, sends a message to a player when they die and have cheat-access that a backup has been created and can be loaded."
		));
		configMetaData.put("backupReminderMessage", Arrays.asList(
			"The message sent to players with cheat-access when 'sendBackupReminderMessageToThoseWithAccessOnDeath' is enabled."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}