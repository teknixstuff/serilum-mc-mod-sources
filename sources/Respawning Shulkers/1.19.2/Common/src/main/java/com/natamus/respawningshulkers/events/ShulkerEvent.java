/*
 * This is the latest source code of Respawning Shulkers.
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

package com.natamus.respawningshulkers.events;

import com.natamus.collective.util.CollectiveReference;
import com.natamus.respawningshulkers.config.ConfigHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShulkerEvent {
	private static final HashMap<Entity, Integer> shulkersTicksLeft = new HashMap<Entity, Integer>();
	private static final HashMap<Level, CopyOnWriteArrayList<Entity>> respawnShulkers = new HashMap<Level, CopyOnWriteArrayList<Entity>>();
	
	public static void onWorldLoad(ServerLevel serverLevel) {
		respawnShulkers.put(serverLevel, new CopyOnWriteArrayList<Entity>());
	}
	
	public static void onWorldTick(ServerLevel serverLevel) {
		if (respawnShulkers.getOrDefault(serverLevel, new CopyOnWriteArrayList<Entity>()).size() > 0) {
			for (Entity shulker : respawnShulkers.get(serverLevel)) {
				int ticksleft = shulkersTicksLeft.get(shulker) - 1;
				if (ticksleft == 0) {
					respawnShulkers.get(serverLevel).remove(shulker);
					shulkersTicksLeft.remove(shulker);
					
					serverLevel.addFreshEntity(shulker);
					continue;
				}
				
				shulkersTicksLeft.put(shulker, ticksleft);
			}
		}
	}
	
	public static void onShulkerDeath(Level level, Entity entity, DamageSource source) {
		if (level.isClientSide) {
			return;
		}

		if (!(entity instanceof Shulker)) {
			return;
		}
		
		Set<String> tags = entity.getTags();
		if (tags.contains(CollectiveReference.MOD_ID + ".fromspawner")) {
			if (ConfigHandler.shulkersFromSpawnersDoNotRespawn) {
				return;
			}
		}
		
		Shulker newshulker = EntityType.SHULKER.create(level);
		newshulker.restoreFrom(entity);
		newshulker.setHealth(30F);
		
		shulkersTicksLeft.put(newshulker, ConfigHandler.timeInTicksToRespawn);
		respawnShulkers.get(level).add(newshulker);
	}
	
	public static void onServerShutdown(MinecraftServer server) {
		Set<Level> levels = respawnShulkers.keySet();
		for (Level level : levels) {
			for (Entity shulker : respawnShulkers.get(level)) {
				level.addFreshEntity(shulker);
			}
		}
	}
}
