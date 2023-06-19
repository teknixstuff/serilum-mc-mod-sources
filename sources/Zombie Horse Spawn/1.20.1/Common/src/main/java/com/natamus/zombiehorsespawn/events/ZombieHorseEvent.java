/*
 * This is the latest source code of Zombie Horse Spawn.
 * Minecraft version: 1.20.1.
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

package com.natamus.zombiehorsespawn.events;

import com.natamus.collective.functions.BlockPosFunctions;
import com.natamus.collective.functions.HashMapFunctions;
import com.natamus.zombiehorsespawn.config.ConfigHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ZombieHorseEvent {
	public static HashMap<Level, CopyOnWriteArrayList<Entity>> zombiehorses_per_world = new HashMap<Level, CopyOnWriteArrayList<Entity>>();
	public static HashMap<Level, Integer> tickdelay_per_world = new HashMap<Level, Integer>();

	public static void onEntityJoin(Level level, Entity entity) {
		if (level.isClientSide) {
			return;
		}

		if (entity instanceof ZombieHorse) {
			if (!HashMapFunctions.computeIfAbsent(zombiehorses_per_world, level, k -> new CopyOnWriteArrayList<Entity>()).contains(entity)) {
				zombiehorses_per_world.get(level).add(entity);
			}
		}
	}
	
	public static void onWorldTick(ServerLevel level) {
		int ticks = HashMapFunctions.computeIfAbsent(tickdelay_per_world, level, k -> 1);
		if (ticks % 20 != 0) {
			tickdelay_per_world.put(level, ticks+1);
			return;
		}
		tickdelay_per_world.put(level, 1);
		
		if (!ConfigHandler.shouldBurnZombieHorsesInDaylight) {
			return;
		}
		
		if (!level.isDay()) {
			return;
		}
		
		for (Entity zombiehorse : HashMapFunctions.computeIfAbsent(zombiehorses_per_world, level, k -> new CopyOnWriteArrayList<Entity>())) {
			if (zombiehorse.isAlive()) {
				if (!zombiehorse.isInWaterRainOrBubble()) {
					BlockPos epos = zombiehorse.blockPosition();
					if (BlockPosFunctions.isOnSurface(level, epos)) {
						zombiehorse.setSecondsOnFire(3);
					}
				}	
			}
			else {
				zombiehorses_per_world.get(level).remove(zombiehorse);
			}		
		}
	}
}
