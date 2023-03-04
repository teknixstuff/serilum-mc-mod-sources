/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.functions;

import com.natamus.collective.events.CollectiveEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.WeakHashMap;

public class SpawnEntityFunctions {
	public static void spawnEntityOnNextTick(ServerLevel serverlevel, Entity entity) {
		CollectiveEvents.entitiesToSpawn.computeIfAbsent(serverlevel, k -> new ArrayList<Entity>()).add(entity);
	}

	public static void startRidingEntityOnNextTick(ServerLevel serverlevel, Entity ridden, Entity rider) {
		CollectiveEvents.entitiesToRide.computeIfAbsent(serverlevel, k -> new WeakHashMap<Entity, Entity>()).put(ridden, rider);
	}
}
