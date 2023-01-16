/*
 * This is the latest source code of Starter Structure.
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

package com.natamus.starterstructure.events;

import com.natamus.collective.functions.BlockPosFunctions;
import com.natamus.collective.functions.FeatureFunctions;
import com.natamus.starterstructure.config.ConfigHandler;
import com.natamus.starterstructure.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.storage.ServerLevelData;

public class StructureCreationEvents {
	public static InteractionResult onLevelSpawn(ServerLevel serverLevel, ServerLevelData serverLevelData) {
		WorldGenSettings generatorsettings = serverLevel.getServer().getWorldData().worldGenSettings();

		if (ConfigHandler.shouldSetSpawnPoint) {
			int x = ConfigHandler.spawnXCoordinate;
			int y = ConfigHandler.spawnYCoordinate;
			int z = ConfigHandler.spawnZCoordinate;

			if (y < 0) {
				y = BlockPosFunctions.getSurfaceBlockPos(serverLevel, x, z).getY();
			}
			else if (y > serverLevel.getMaxBuildHeight()) {
				y = serverLevel.getMaxBuildHeight() - 1;
			}

			BlockPos spawnPos = new BlockPos(x, y, z);
			serverLevel.setDefaultSpawnPos(spawnPos, 1.0F);

			if (generatorsettings.generateBonusChest()) {
				FeatureFunctions.placeBonusChest(serverLevel, spawnPos);
			}
		}

		serverLevel.getServer().tell(new TickTask(serverLevel.getServer().getTickCount()+1, () -> {
			if (ConfigHandler.shouldGenerateStructure) {
				Util.generateSchematic(serverLevel);
			}
		}));

		return InteractionResult.SUCCESS;
	}

	public static void onLevelLoad(ServerLevel serverLevel) {
		Util.readProtectedList(serverLevel);
	}
}
