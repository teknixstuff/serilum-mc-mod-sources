/*
 * This is the latest source code of Followers Teleport Too.
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

package com.natamus.followersteleporttoo.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.followersteleporttoo.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean disableFollowerDamageAfterTeleport = true;
	@Entry(min = 0, max = 86400) public static int durationInSecondsDamageShouldBeDisabled = 20;

	public static void initConfig() {
		configMetaData.put("disableFollowerDamageAfterTeleport", Arrays.asList(
			"When enabled, disables damage for followers shortly after a teleport. This can prevent fall damage or suffocation from an estimate target position."
		));
		configMetaData.put("durationInSecondsDamageShouldBeDisabled", Arrays.asList(
			"How long in seconds damage should be disabled for after a teleport when disableFollowerDamageAfterTeleport is enabled."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}