/*
 * This is the latest source code of GUI Compass.
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

package com.natamus.guicompass.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.guicompass.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static String guiCompassFormat = "FXYZ";
	@Entry public static boolean mustHaveCompassInInventory = true;
	@Entry public static boolean compassPositionIsLeft = true;
	@Entry public static boolean compassPositionIsCenter = false;
	@Entry public static boolean compassPositionIsRight = false;
	@Entry(min = 0, max = 3000) public static int compassHeightOffset = 5;
	@Entry public static boolean drawTextShadow = true;
	@Entry(min = 0, max = 255) public static int RGB_R = 255;
	@Entry(min = 0, max = 255) public static int RGB_G = 255;
	@Entry(min = 0, max = 255) public static int RGB_B = 255;

	public static void initConfig() {
		configMetaData.put("guiCompassFormat", Arrays.asList(
			"What of the GUI compass should be displayed. Default: [FXYZ]. F: facing (direction), X: x coord, Y: y coord (depth), Z: z coord."
		));
		configMetaData.put("mustHaveCompassInInventory", Arrays.asList(
			"When enabled, will only show the GUI compass when a compass is present in the inventory."
		));
		configMetaData.put("compassPositionIsLeft", Arrays.asList(
			"Places the GUI compass on the left."
		));
		configMetaData.put("compassPositionIsCenter", Arrays.asList(
			"Places the GUI compass in the middle."
		));
		configMetaData.put("compassPositionIsRight", Arrays.asList(
			"Places the GUI compass on the right."
		));
		configMetaData.put("compassHeightOffset", Arrays.asList(
			"The vertical offset (y coord) for the Compass. This determines how far down the time should be on the screen. Can be changed to prevent GUIs from overlapping."
		));
		configMetaData.put("drawTextShadow", Arrays.asList(
			"If the text displayed should have a shadow drawn below it."
		));
		configMetaData.put("RGB_R", Arrays.asList(
			"The red RGB value for the compass text."
		));
		configMetaData.put("RGB_G", Arrays.asList(
			"The green RGB value for the compass text."
		));
		configMetaData.put("RGB_B", Arrays.asList(
			"The blue RGB value for the compass text."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}