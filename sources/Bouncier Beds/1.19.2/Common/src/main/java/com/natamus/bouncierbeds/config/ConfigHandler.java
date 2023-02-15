/*
 * This is the latest source code of Bouncier Beds.
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

package com.natamus.bouncierbeds.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.bouncierbeds.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean bedsPreventFallDamage = true;
	@Entry(min = 0.0, max = 100.0) public static double bedBounciness = 1.5;

	public static void initConfig() {
		configMetaData.put("bedsPreventFallDamage", Arrays.asList(
			"Whether beds should prevent fall damage when a player lands on them. It's recommended to keep this enabled if you have lots of bounciness."
		));
		configMetaData.put("bedBounciness", Arrays.asList(
			"The modifier of how much a bed bounces. A value of 2.0 makes the player jump around 30 blocks. A value of 100.0 makes the player jump around 4500 blocks."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}