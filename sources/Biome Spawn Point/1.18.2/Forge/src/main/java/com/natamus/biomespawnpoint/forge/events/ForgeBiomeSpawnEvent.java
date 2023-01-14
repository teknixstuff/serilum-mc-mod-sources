/*
 * This is the latest source code of Biome Spawn Point.
 * Minecraft version: 1.18.2.
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

package com.natamus.biomespawnpoint.forge.events;

import com.mojang.logging.LogUtils;
import com.natamus.biomespawnpoint.events.BiomeSpawnEvent;
import com.natamus.collective.functions.WorldFunctions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.slf4j.Logger;

@EventBusSubscriber
public class ForgeBiomeSpawnEvent {
	private static final Logger logger = LogUtils.getLogger();

	@SubscribeEvent(receiveCanceled = true)
	public void onWorldLoad(WorldEvent.CreateSpawnPosition e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getWorld());
		if (level == null) {
			return;
		}

		if (BiomeSpawnEvent.onWorldLoad((ServerLevel)level, (ServerLevelData)level.getLevelData())) {
			e.setCanceled(true);
		}
	}
}
