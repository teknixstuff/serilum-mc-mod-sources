/*
 * This is the latest source code of Skeleton Horse Spawn.
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

package com.natamus.skeletonhorsespawn.events;

import com.natamus.collective.functions.BlockPosFunctions;
import com.natamus.skeletonhorsespawn.config.ConfigHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SkeletonHorseEvent {
	private static final HashMap<Level, CopyOnWriteArrayList<Entity>> skeletonhorses_per_world = new HashMap<Level, CopyOnWriteArrayList<Entity>>();
	private static final HashMap<Level, Integer> tickdelay_per_world = new HashMap<Level, Integer>();
	
	public static void onWorldLoad(ServerLevel level) {
		skeletonhorses_per_world.put(level, new CopyOnWriteArrayList<Entity>());
		tickdelay_per_world.put(level, 1);
	}
	
	public static void onEntityJoin(Level level, Entity entity) {
		if (level.isClientSide) {
			return;
		}
		
		if (entity instanceof SkeletonHorse) {
			if (!skeletonhorses_per_world.containsKey(level)) {
				skeletonhorses_per_world.put(level, new CopyOnWriteArrayList<Entity>());
			}

			if (!skeletonhorses_per_world.get(level).contains(entity)) {
				skeletonhorses_per_world.get(level).add(entity);
			}
		}
	}
	
	public static void onWorldTick(ServerLevel level) {
		int ticks = tickdelay_per_world.getOrDefault(level, 1);
		if (ticks % 20 != 0) {
			tickdelay_per_world.put(level, ticks+1);
			return;
		}
		tickdelay_per_world.put(level, 1);
		
		if (!ConfigHandler.shouldBurnSkeletonHorsesInDaylight) {
			return;
		}
		
		if (!level.isDay()) {
			return;
		}
		
		for (Entity skeletonhorse : skeletonhorses_per_world.get(level)) {
			if (skeletonhorse.isAlive()) {
				if (!skeletonhorse.isInWaterRainOrBubble()) {
					BlockPos epos = skeletonhorse.blockPosition();
					if (BlockPosFunctions.isOnSurface(level, epos)) {
						skeletonhorse.setSecondsOnFire(3);
					}
				}	
			}
			else {
				skeletonhorses_per_world.get(level).remove(skeletonhorse);
			}		
		}
	}
}