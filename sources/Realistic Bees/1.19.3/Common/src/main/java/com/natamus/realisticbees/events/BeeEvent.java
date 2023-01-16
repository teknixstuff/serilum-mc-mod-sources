/*
 * This is the latest source code of Realistic Bees.
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

package com.natamus.realisticbees.events;

import com.natamus.collective.functions.SpawnEntityFunctions;
import com.natamus.collective.functions.TaskFunctions;
import com.natamus.realisticbees.config.ConfigHandler;
import com.natamus.realisticbees.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Set;

public class BeeEvent {
	public static void onBeeCheckSpawn(Mob entity, Level level, BlockPos blockPos, BaseSpawner spawner, MobSpawnType spawnReason) {
		if (!(entity instanceof Bee)) {
			return;
		}

		entity.addTag("SpawnReason." + spawnReason.name());
	}

	public static void onBeeSpawn(Entity entity, Level level) {
		if (level.isClientSide) {
			return;
		}

		if (!(entity instanceof Bee)) {
			return;
		}
		
		Set<String> tags = entity.getTags();
		if (tags.contains(Reference.MOD_ID + ".ignorebee")) {
			return;
		}
		entity.addTag(Reference.MOD_ID + ".ignorebee");

		if (tags.contains("SpawnReason.BUCKET") || tags.contains("SpawnReason.SPAWN_EGG")) {
			return;
		}
		
		BlockPos entitypos = entity.blockPosition();
		if (!level.hasChunk(Mth.floor(entitypos.getX()) >> 4, Mth.floor(entitypos.getZ()) >> 4)) {
			return;
		}
		
		int extrabees = ConfigHandler.extraBeeSpawnsPerBee;
		if (extrabees == 0) {
			return;
		}
		
		Bee bee = (Bee)entity;
		if (bee.isBaby()) {
			return;
		}
		
		TaskFunctions.enqueueImmediateTask(level, () -> {
        	Vec3 beevec = entity.position();

    		ServerLevel serverLevel = (ServerLevel)level;
    		for (int i = 0; i < extrabees; i++) {
    			Bee newbee = EntityType.BEE.create(level);
    			newbee.level = level;
    			newbee.setPos(beevec.x, beevec.y, beevec.z);
    			newbee.addTag(Reference.MOD_ID + ".ignorebee");
    			SpawnEntityFunctions.spawnEntityOnNextTick(serverLevel, newbee);
    		}
        }, false);
	}
}
