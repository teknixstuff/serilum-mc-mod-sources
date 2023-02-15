/*
 * This is the latest source code of Random Mob Effects.
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

package com.natamus.randommobeffects.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.randommobeffects.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 1, max = 50) public static int potionEffectLevel = 1;
	@Entry public static boolean hideEffectParticles = false;
	@Entry public static boolean disableCreepers = true;

	public static void initConfig() {
		configMetaData.put("potionEffectLevel", Arrays.asList(
			"The default level of the effects the mod applies, by default 1."
		));
		configMetaData.put("hideEffectParticles", Arrays.asList(
			"When enabled, hides the particles from the mobs with an effect."
		));
		configMetaData.put("disableCreepers", Arrays.asList(
			"Creepers can create infinite lingering potion effects which is probably not what you want. When enabled, the mod doesn't give creepers a random effect."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}