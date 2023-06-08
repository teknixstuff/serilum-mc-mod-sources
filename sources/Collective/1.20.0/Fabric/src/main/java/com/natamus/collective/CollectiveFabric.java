/*
 * This is the latest source code of Collective.
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

package com.natamus.collective;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.events.CollectiveEvents;
import com.natamus.collective.util.CollectiveReference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class CollectiveFabric implements ModInitializer { 
	@Override
	public void onInitialize() {
		setGlobalConstants();
		CollectiveCommon.init();

		ServerTickEvents.START_WORLD_TICK.register((ServerLevel world) -> {
			CollectiveEvents.onWorldTick(world);
		});

		ServerTickEvents.START_SERVER_TICK.register((MinecraftServer minecraftServer) -> {
			CollectiveEvents.onServerTick(minecraftServer);
		});
		
		ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerLevel world) -> {
			CollectiveEvents.onEntityJoinLevel(world, entity);
		});

		RegisterMod.register(CollectiveReference.NAME, CollectiveReference.MOD_ID, CollectiveReference.VERSION, CollectiveReference.ACCEPTED_VERSIONS);
	}

	private static void setGlobalConstants() {

	}
}
