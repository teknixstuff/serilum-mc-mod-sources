/*
 * This is the latest source code of Cycle Paintings.
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

package com.natamus.cyclepaintings.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.cyclepaintings.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static String ignorePaintingsInCycleResourceLocation = "infernalexp,";
	@Entry public static boolean showRegisteredPaintingsDebug = false;

	public static void initConfig() {
		configMetaData.put("ignorePaintingsInCycleResourceLocation", Arrays.asList(
			"Split by a , (comma). The paintings to ignore during the cycle. You can either input an entire mod's prefix (only the part before the : (colon)) or the entire line found via 'showRegisteredPaintingsDebug'."
		));
		configMetaData.put("showRegisteredPaintingsDebug", Arrays.asList(
			"When enabled, prints all paintings registered to the console. With this you can find which to add to the 'ignorePaintingsInCycleResourceLocationPrefix' config."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}