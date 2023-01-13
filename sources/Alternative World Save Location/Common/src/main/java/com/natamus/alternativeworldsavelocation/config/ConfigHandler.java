/*
 * This is the latest source code of Alternative World Save Location.
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

package com.natamus.alternativeworldsavelocation.config;

import com.natamus.alternativeworldsavelocation.util.Reference;
import com.natamus.collective.config.DuskConfig;
import com.natamus.collective.functions.DataFunctions;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean changeDefaultWorldSaveLocation = false;
	@Entry public static String defaultMinecraftWorldSaveLocation = (DataFunctions.getGameDirectory() + File.separator + "saves").replace(File.separator, "/");
	@Entry public static boolean changeDefaultWorldBackupLocation = false;
	@Entry public static String defaultMinecraftWorldBackupLocation = (DataFunctions.getGameDirectory() + File.separator + "backups").replace(File.separator, "/");

	public static void initConfig() {
		configMetaData.put("changeDefaultWorldSaveLocation", Arrays.asList(
			"Disabled by default, to prevent unwanted modpack behaviour. When enabled, changes the world location to 'defaultMinecraftWorldSaveLocation'"
		));
		configMetaData.put("defaultMinecraftWorldSaveLocation", Arrays.asList(
			"Use either \\\\ or / as a path separator. The location of the folder containing the world saves."
		));
		configMetaData.put("changeDefaultWorldBackupLocation", Arrays.asList(
			"Disabled by default. Enable this to set a specific world backup folder. If disabled, this will be set to 'defaultMinecraftWorldSaveLocation'/_Backup."
		));
		configMetaData.put("defaultMinecraftWorldBackupLocation", Arrays.asList(
			"Use either \\\\ or / as a path separator. The world backup folder if both 'changeDefaultWorldSaveLocation' and 'changeDefaultWorldBackupLocation' are enabled."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}