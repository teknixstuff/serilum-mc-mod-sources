/*
 * This is the latest source code of Village Spawn Point.
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

package com.natamus.villagespawnpoint.events;

import com.mojang.logging.LogUtils;
import com.natamus.collective.functions.BlockPosFunctions;
import com.natamus.collective.functions.FeatureFunctions;
import com.natamus.villagespawnpoint.data.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.storage.ServerLevelData;
import org.slf4j.Logger;

public class VillageSpawnEvent {
	private static final Logger logger = LogUtils.getLogger();

	public static boolean onWorldLoad(ServerLevel serverLevel, ServerLevelData serverLevelData) {
		if (Constants.biomeSpawnPointLoaded) {
			return false;
		}

		WorldOptions worldGeneratorOptions = serverLevel.getServer().getWorldData().worldGenOptions();

		if (!worldGeneratorOptions.generateStructures()) {
			return false;
		}

		logger.info("[Village Spawn Point] Finding the nearest village. This might take a few seconds.");
		BlockPos spawnpos = BlockPosFunctions.getCenterNearbyVillage(serverLevel);
		if (spawnpos == null) {
			return false;
		}

		logger.info("[Village Spawn Point] Village found! The world will now generate.");

		serverLevel.setDefaultSpawnPos(spawnpos, 1.0f);

		if (worldGeneratorOptions.generateBonusChest()) {
			FeatureFunctions.placeBonusChest(serverLevel, spawnpos);
		}

		return true;
	}
}