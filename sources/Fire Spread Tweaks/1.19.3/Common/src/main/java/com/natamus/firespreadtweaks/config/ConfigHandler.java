/*
 * This is the latest source code of Fire Spread Tweaks.
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

package com.natamus.firespreadtweaks.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.firespreadtweaks.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static int timeFireBurnsInTicks = 300;
	@Entry public static boolean enableRandomizedFireDuration = true;
	@Entry public static int MinRandomExtraBurnTicks = -100;
	@Entry public static int MaxRandomExtraBurnTicks = 100;

	public static void initConfig() {
		configMetaData.put("timeFireBurnsInTicks", Arrays.asList(
			"The time a fire will keep burning for in ticks. 20 ticks = 1 second.",
			"min: 0, max: 72000"
		));
		configMetaData.put("enableRandomizedFireDuration", Arrays.asList(
			"When enabled, uses the MinRandomExtraBurnTicks and MaxRandomExtraBurnTicks config values to randomize the time fire burns for."
		));
		configMetaData.put("MinRandomExtraBurnTicks", Arrays.asList(
			"If randomized fire duration is enabled, a random tick number will be chosen in between the minimum and maximum value. This will be added to timeFireBurnsInTicks. If the outcome is negative, it will be subtracted.",
			"min: -36000, max: 0"
		));
		configMetaData.put("MaxRandomExtraBurnTicks", Arrays.asList(
			"See MinRandomExtraBurnTicks's description.",
			"min: 0, max: 36000"
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}