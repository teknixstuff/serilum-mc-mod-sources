/*
 * This is the latest source code of Edibles.
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

package com.natamus.edibles.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.edibles.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = -1, max = 1280) public static int maxItemUsesPerDaySingleItem = 16;
	@Entry(min = -1, max = 1280) public static int maxItemUsesPerDayTotal = -1;
	@Entry(min = 1, max = 3600) public static int weaknessDurationSeconds = 45;
	@Entry(min = 0, max = 3600) public static int glowEntityDurationSeconds = 20;
	@Entry(min = 1, max = 128) public static int glowEntitiesAroundAffectedRadiusBlocks = 32;
	@Entry(min = 0, max = 3600000) public static int _cooldownInMsBetweenUses = 1000;
	@Entry(min = 0, max = 3600) public static int blazePowderStrengthDurationSeconds = 15;
	@Entry(min = 0, max = 3600) public static int magmaCreamFireResistanceDurationSeconds = 15;
	@Entry(min = 0, max = 3600) public static int sugarSpeedDurationSeconds = 15;
	@Entry(min = 0, max = 3600) public static int ghastTearDurationSeconds = 15;
	@Entry(min = 0, max = 3600) public static int phantomMembraneDurationSeconds = 15;
	@Entry(min = 0, max = 3600) public static int rabbitsFootDurationSeconds = 15;

	public static void initConfig() {
		configMetaData.put("maxItemUsesPerDaySingleItem", Arrays.asList(
			"The maximum amount of an item a player can eat before receiving the weakness effect. A value of -1 disables this feature."
		));
		configMetaData.put("maxItemUsesPerDayTotal", Arrays.asList(
			"The maximum of the total amount of items a player can eat before receiving the weakness effect. A value of -1 disables this feature."
		));
		configMetaData.put("weaknessDurationSeconds", Arrays.asList(
			"The duration of the weakness effect in seconds when eating too much of an item."
		));
		configMetaData.put("glowEntityDurationSeconds", Arrays.asList(
			"When eating glowstone, the duration in seconds of how long entities around should be glowing with an outline. A value of 0 disables the item use."
		));
		configMetaData.put("glowEntitiesAroundAffectedRadiusBlocks", Arrays.asList(
			"For the glow effect, the radius in blocks around the player of entities affected."
		));
		configMetaData.put("_cooldownInMsBetweenUses", Arrays.asList(
			"The time in miliseconds of cooldown in between uses of eating an edible."
		));
		configMetaData.put("blazePowderStrengthDurationSeconds", Arrays.asList(
			"After eating blaze powder, the duration in seconds of the strength effect the player receives. A value of 0 disables the item use."
		));
		configMetaData.put("magmaCreamFireResistanceDurationSeconds", Arrays.asList(
			"After eating magma cream, the duration in seconds of the fire resistance effect the player receives. A value of 0 disables the item use."
		));
		configMetaData.put("sugarSpeedDurationSeconds", Arrays.asList(
			"After eating some sugar, the duration in seconds of the speed effect the player receives. A value of 0 disables the item use."
		));
		configMetaData.put("ghastTearDurationSeconds", Arrays.asList(
			"After eating a ghast tear, the duration in seconds of the regeneration effect the player receives. A value of 0 disables the item use."
		));
		configMetaData.put("phantomMembraneDurationSeconds", Arrays.asList(
			"After eating some phantom membrane, the duration in seconds of the slow falling effect the player receives. A value of 0 disables the item use."
		));
		configMetaData.put("rabbitsFootDurationSeconds", Arrays.asList(
			"After eating a rabbit's foot, the duration in seconds of the jump boost effect the player receives. A value of 0 disables the item use."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}