/*
 * This is the latest source code of Breedable Killer Rabbit.
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

package com.natamus.breedablekillerrabbit.forge.events;

import com.natamus.breedablekillerrabbit.events.RabbitEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeRabbitEvent {
	@SubscribeEvent
	public void onBaby(BabyEntitySpawnEvent e) {
		AgeableMob child = e.getChild();
		Level level = child.getCommandSenderWorld();
		if (level.isClientSide) {
			return;
		}

		Mob parentA = e.getParentA();
		Mob parentB = e.getParentB();
		if (!(parentA instanceof Animal) || !(parentB instanceof Animal)) {
			return;
		}

		RabbitEvent.onBaby((ServerLevel)level, (Animal)parentA, (Animal)parentB, e.getChild());
	}
	
	@SubscribeEvent
	public void onEntityInteract(PlayerInteractEvent.EntityInteract e) {
		Level world = e.getWorld();
		if (world.isClientSide) {
			return;
		}

		RabbitEvent.onEntityInteract(e.getPlayer(), e.getWorld(), e.getHand(), e.getTarget(), null);
	}
	
	@SubscribeEvent
	public void onTarget(LivingAttackEvent e) {
		Entity entity = e.getEntity();
		if (!RabbitEvent.onTarget(entity.level, entity, e.getSource(), e.getAmount())) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void mobSpawn(EntityJoinWorldEvent e) {
		RabbitEvent.mobSpawn(e.getWorld(), e.getEntity());
	}
	
	@SubscribeEvent
	public void onPlayerDamage(LivingHurtEvent e) {
		Entity entity = e.getEntity();
		RabbitEvent.onPlayerDamage(entity.level, entity, e.getSource(), e.getAmount());
	}
}
