/*
 * This is the latest source code of Starter Structure.
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

package com.natamus.starterstructure.events;

import com.natamus.collective.functions.FeatureFunctions;
import com.natamus.collective.functions.TaskFunctions;
import com.natamus.starterstructure.config.ConfigHandler;
import com.natamus.starterstructure.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.storage.ServerLevelData;

public class StructureCreationEvents {
	public static InteractionResult onLevelSpawn(ServerLevel serverLevel, ServerLevelData serverLevelData) {
		WorldOptions worldOptions = null;
		try {
			worldOptions = serverLevel.getServer().getWorldData().worldGenOptions();
		}
		catch (NoSuchMethodError ignored) {}

		InteractionResult result = InteractionResult.PASS;
		if (ConfigHandler.shouldUseSpawnCoordinates || ConfigHandler.shouldUseSpawnCoordOffsets) {
			BlockPos curSpawnPos = serverLevel.getSharedSpawnPos();
			BlockPos spawnPos = Util.getSpawnPos(serverLevel, curSpawnPos, true);

			serverLevel.setDefaultSpawnPos(spawnPos, 1.0F);

			if (worldOptions != null) {
				if (worldOptions.generateBonusChest()) {
					FeatureFunctions.placeBonusChest(serverLevel, spawnPos);
				}
			}

			result = InteractionResult.SUCCESS;
		}

		TaskFunctions.enqueueCollectiveTask(serverLevel.getServer(), () -> {
			if (ConfigHandler.shouldGenerateStructure) {
				Util.generateSchematic(serverLevel);
			}
		}, 1);

		return result;
	}

	public static void onLevelLoad(ServerLevel serverLevel) {
		Util.readProtectedList(serverLevel);
	}
}
