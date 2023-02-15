/*
 * This is the latest source code of GUI Followers.
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

package com.natamus.guifollowers.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.guifollowers.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static String followerListHeaderFormat = "Followers:";
	@Entry public static boolean showFollowerHealth = true;
	@Entry public static String followerHealthFormat = ": <health>%";
	@Entry public static boolean showFollowerDistance = true;
	@Entry public static String followerDistanceFormat = " (<distance> blocks)";
	@Entry(min = -1, max = 300) public static int distanceToCheckForFollowersAround = 50;
	@Entry(min = 0, max = 3600) public static int timeBetweenChecksInSeconds = 2;
	@Entry public static boolean followerListPositionIsLeft = true;
	@Entry public static boolean followerListPositionIsCenter = false;
	@Entry public static boolean followerListPositionIsRight = false;
	@Entry(min = 0, max = 3000) public static int followerListHeightOffset = 20;
	@Entry(min = 0, max = 255) public static int RGB_R = 255;
	@Entry(min = 0, max = 255) public static int RGB_G = 255;
	@Entry(min = 0, max = 255) public static int RGB_B = 255;

	public static void initConfig() {
		configMetaData.put("followerListHeaderFormat", Arrays.asList(
			"The header text above the follower list."
		));
		configMetaData.put("showFollowerHealth", Arrays.asList(
			"If enabled, shows the follower's health in the GUI list."
		));
		configMetaData.put("followerHealthFormat", Arrays.asList(
			"The format of the health string in the GUI. <health> will be replaced by the percentage of total health."
		));
		configMetaData.put("showFollowerDistance", Arrays.asList(
			"If enabled, shows the follower's distance in blocks to the player in the GUI list."
		));
		configMetaData.put("followerDistanceFormat", Arrays.asList(
			"The format of the distance string in the GUI. <distance> will be replaced by distance in blocks."
		));
		configMetaData.put("distanceToCheckForFollowersAround", Arrays.asList(
			"The distance in blocks around the player where the mod checks for tamed, non-sitting followers to add to the list. A value of -1 disables this feature."
		));
		configMetaData.put("timeBetweenChecksInSeconds", Arrays.asList(
			"The time in seconds in between checking for tamed, non-sitting followers around the player."
		));
		configMetaData.put("followerListPositionIsLeft", Arrays.asList(
			"Places the follower list on the left."
		));
		configMetaData.put("followerListPositionIsCenter", Arrays.asList(
			"Places the follower list in the middle."
		));
		configMetaData.put("followerListPositionIsRight", Arrays.asList(
			"Places the follower list on the right."
		));
		configMetaData.put("followerListHeightOffset", Arrays.asList(
			"The vertical offset (y coord) for the follower list. This determines how far down the list should be on the screen. Can be changed to prevent GUIs from overlapping."
		));
		configMetaData.put("RGB_R", Arrays.asList(
			"The red RGB value for the clock text."
		));
		configMetaData.put("RGB_G", Arrays.asList(
			"The green RGB value for the clock text."
		));
		configMetaData.put("RGB_B", Arrays.asList(
			"The blue RGB value for the clock text."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}