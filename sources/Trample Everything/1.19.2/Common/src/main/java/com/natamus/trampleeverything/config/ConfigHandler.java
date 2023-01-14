/*
 * This is the latest source code of Trample Everything.
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

package com.natamus.trampleeverything.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.trampleeverything.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean _enableTrampledBlockItems = true;
	@Entry public static boolean _crouchingPreventsTrampling = true;
	@Entry public static boolean trampleSnow = false;
	@Entry public static boolean trampleBambooSaplings = false;
	@Entry public static boolean trampleCrops = true;
	@Entry public static boolean trampleDeadBushes = true;
	@Entry public static boolean trampleDoublePlants = true;
	@Entry public static boolean trampleFlowers = true;
	@Entry public static boolean trampleFungi = true;
	@Entry public static boolean trampleLilyPads = false;
	@Entry public static boolean trampleMushrooms = true;
	@Entry public static boolean trampleNetherRoots = true;
	@Entry public static boolean trampleNetherSprouts = true;
	@Entry public static boolean trampleNetherWart = true;
	@Entry public static boolean trampleSaplings = true;
	@Entry public static boolean trampleSeaGrass = false;
	@Entry public static boolean trampleSeaPickles = true;
	@Entry public static boolean trampleStems = true;
	@Entry public static boolean trampleSugarCane = false;
	@Entry public static boolean trampleSweetBerryBushes = false;
	@Entry public static boolean trampleTallGrass = true;

	public static void initConfig() {
		configMetaData.put("_enableTrampledBlockItems", Arrays.asList(
			"If enabled, will drop blocks from a trampled block as if a player breaks it by hand."
		));
		configMetaData.put("_crouchingPreventsTrampling", Arrays.asList(
			"If enabled, crouching/sneaking will prevent any block from being trampled."
		));
		configMetaData.put("trampleSnow", Arrays.asList(
			"Whether snow should be trampled."
		));
		configMetaData.put("trampleBambooSaplings", Arrays.asList(
			"Whether bamboo saplings should be trampled."
		));
		configMetaData.put("trampleCrops", Arrays.asList(
			"Whether growable crops should be trampled."
		));
		configMetaData.put("trampleDeadBushes", Arrays.asList(
			"Whether dead bushes should be trampled"
		));
		configMetaData.put("trampleDoublePlants", Arrays.asList(
			"Whether double plants should be trampled, for example tall flowers."
		));
		configMetaData.put("trampleFlowers", Arrays.asList(
			"Whether flowers should be trampled."
		));
		configMetaData.put("trampleFungi", Arrays.asList(
			"Whether nether mushrooms should be trampled."
		));
		configMetaData.put("trampleLilyPads", Arrays.asList(
			"Whether lily pads should be trampled."
		));
		configMetaData.put("trampleMushrooms", Arrays.asList(
			"Whether mushrooms should be trampled."
		));
		configMetaData.put("trampleNetherRoots", Arrays.asList(
			"Whether nether roots should be trampled."
		));
		configMetaData.put("trampleNetherSprouts", Arrays.asList(
			"Whether nether sprouts should be trampled."
		));
		configMetaData.put("trampleNetherWart", Arrays.asList(
			"Whether nether wart should be trampled."
		));
		configMetaData.put("trampleSaplings", Arrays.asList(
			"Whether saplings should be trampled."
		));
		configMetaData.put("trampleSeaGrass", Arrays.asList(
			"Whether sea grass should be trampled."
		));
		configMetaData.put("trampleSeaPickles", Arrays.asList(
			"Whether sea pickles should be trampled."
		));
		configMetaData.put("trampleStems", Arrays.asList(
			"Whether stems should be trampled, such as pumpkin and melon stems."
		));
		configMetaData.put("trampleSugarCane", Arrays.asList(
			"Whether sugar cane should be trampled."
		));
		configMetaData.put("trampleSweetBerryBushes", Arrays.asList(
			"Whether sweet berry bushes should be trampled."
		));
		configMetaData.put("trampleTallGrass", Arrays.asList(
			"Whether tall grass should be trampled."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}