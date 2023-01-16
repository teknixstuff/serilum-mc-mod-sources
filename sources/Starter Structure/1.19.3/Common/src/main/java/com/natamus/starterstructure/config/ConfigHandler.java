/*
 * This is the latest source code of Starter Structure.
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

package com.natamus.starterstructure.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.starterstructure.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean shouldGenerateStructure = true;
	@Entry public static boolean forceExactSpawnMiddleStructure = true;
	@Entry public static int generatedStructureYOffset = 0;
	@Entry public static boolean ignoreTreesDuringStructurePlacement = true;
	@Entry public static boolean generationIgnoreJigsawAndStructureBlocks = true;
	@Entry public static boolean protectStructureBlocks = true;
	@Entry public static boolean protectSpawnedEntities = true;
	@Entry public static boolean playersInCreativeModeIgnoreProtection = true;
	@Entry public static boolean playersInCreativeModeIgnoreEntityProtection = false;
	@Entry public static boolean preventSpawnedEntityMovement = false;
	@Entry public static boolean shouldSetSpawnPoint = false;
	@Entry public static int spawnXCoordinate = 0;
	@Entry public static int spawnYCoordinate = -1;
	@Entry public static int spawnZCoordinate = 0;
	@Entry public static boolean spawnNonSignEntitiesFromSupportedSchematics = true;

	public static void initConfig() {
		configMetaData.put("shouldGenerateStructure", Arrays.asList(
			"Whether a schematic that's located in './config/starterstructure/schematics/...' should be generated."
		));
		configMetaData.put("forceExactSpawnMiddleStructure", Arrays.asList(
			"Usually player spawn points are in a randomized area. With this enabled, players will always spawn in the middle of the structure (at the nearest air pocket)."
		));
		configMetaData.put("generatedStructureYOffset", Arrays.asList(
			"The y offset for the generated structure. Can for example be set to -1 if you notice a building always spawns one block too high.",
			"min: -100, max: 100"
		));
		configMetaData.put("ignoreTreesDuringStructurePlacement", Arrays.asList(
			"Prevents structures from being placed on top of trees. Any leaf and log blocks will be ignored during placement."
		));
		configMetaData.put("generationIgnoreJigsawAndStructureBlocks", Arrays.asList(
			"Some schematic files might contain jigsaw or structure blocks. These are by default ignored during structure generation."
		));
		configMetaData.put("protectStructureBlocks", Arrays.asList(
			"Whether the blocks from the generated structure should be protected from breaking/griefing."
		));
		configMetaData.put("protectSpawnedEntities", Arrays.asList(
			"Whether entities spawned inside the generated structure should be protected from damage."
		));
		configMetaData.put("playersInCreativeModeIgnoreProtection", Arrays.asList(
			"If enabled, players that are in creative mode will be able to break and place the structure blocks."
		));
		configMetaData.put("playersInCreativeModeIgnoreEntityProtection", Arrays.asList(
			"If enabled, players that are in creative mode will be able to damage protected entities which spawned in structures."
		));
		configMetaData.put("preventSpawnedEntityMovement", Arrays.asList(
			"If spawned entities inside the generated structure should not be allowed to move away from the block they spawned on. Disabled by default."
		));
		configMetaData.put("shouldSetSpawnPoint", Arrays.asList(
			"Whether the initial world spawn point should be set to specific coordinates."
		));
		configMetaData.put("spawnXCoordinate", Arrays.asList(
			"The new X coordinate of the spawn when shouldSetSpawnPoint is enabled.",
			"min: -10000000, max: 10000000"
		));
		configMetaData.put("spawnYCoordinate", Arrays.asList(
			"The new Y coordinate of the spawn when shouldSetSpawnPoint is enabled. A value of -1 means on the surface.",
			"min: -1, max: 10000"
		));
		configMetaData.put("spawnZCoordinate", Arrays.asList(
			"The new Z coordinate of the spawn when shouldSetSpawnPoint is enabled.",
			"min: -10000000, max: 10000000"
		));
		configMetaData.put("spawnNonSignEntitiesFromSupportedSchematics", Arrays.asList(
			"If entities from (structure block) schematic files should be spawned when found. These are entities not created with signs."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}