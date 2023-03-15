/*
 * This is the latest source code of Despawning Eggs Hatch.
 * Minecraft version: 1.19.4.
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

package com.natamus.despawningeggshatch.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.despawningeggshatch.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean eggOnlyHatchesWhenOnTopOfHayBlock = true;
	@Entry(min = 0, max = 1.0) public static double eggWillHatchChance = 1.0;
	@Entry(min = 0, max = 1000) public static int onlyHatchIfLessChickensAroundThan = 50;
	@Entry(min = 1, max = 256) public static int radiusEntityLimiterCheck = 32;
	@Entry public static boolean newHatchlingIsBaby = true;

	public static void initConfig() {
		configMetaData.put("eggOnlyHatchesWhenOnTopOfHayBlock", Arrays.asList(
			"When enabled, an egg will only hatch when it is laid on top a hay block. This prevents wild chicken colonies from expanding without your knowledge."
		));
		configMetaData.put("eggWillHatchChance", Arrays.asList(
			"The chance an egg will hatch just before despawning if the entity limiter is not active."
		));
		configMetaData.put("onlyHatchIfLessChickensAroundThan", Arrays.asList(
			"Prevents too many entities from hatching. A despawning egg will only hatch if there are less chickens than defined here in a radius of 'radiusEntityLimiterCheck' blocks around."
		));
		configMetaData.put("radiusEntityLimiterCheck", Arrays.asList(
			"The radius around the despawned egg for 'onlyHatchIfLessChickensAroundThan'."
		));
		configMetaData.put("newHatchlingIsBaby", Arrays.asList(
			"If enabled, the newly hatched chicken is a small chick."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}