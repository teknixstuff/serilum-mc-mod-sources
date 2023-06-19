/*
 * This is the latest source code of Campfire Spawn and Tweaks.
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

package com.natamus.campfirespawnandtweaks.forge.events;

import com.natamus.campfirespawnandtweaks.events.CampfireEvent;
import com.natamus.collective.functions.WorldFunctions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeCampfireEvent {
	@SubscribeEvent
	public void onWorldLoad(LevelEvent.Load e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
		if (level == null) {
			return;
		}

		CampfireEvent.onWorldLoad((ServerLevel)level);
	}
	
	@SubscribeEvent
	public void onWorldTick(LevelTickEvent e) {
		Level level = e.level;
		if (level.isClientSide) {
			return;
		}

		CampfireEvent.onWorldTick((ServerLevel)level);
	}
	
	@SubscribeEvent
	public void onEntityBlockPlace(EntityPlaceEvent e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
		if (level == null) {
			return;
		}

		Entity entity = e.getEntity();
		if (!(entity instanceof LivingEntity)) {
			return;
		}

		CampfireEvent.onEntityBlockPlace(level, e.getPos(), e.getPlacedBlock(), (LivingEntity)entity, null);
	}
	
	@SubscribeEvent
	public void onRightClickCampfireBlock(PlayerInteractEvent.RightClickBlock e) {
		if (!CampfireEvent.onRightClickCampfireBlock(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec())) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onCampfireBreak(BlockEvent.BreakEvent e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
		if (level == null) {
			return;
		}

		CampfireEvent.onCampfireBreak(level, e.getPlayer(), e.getPos(), e.getState(), null);
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player player = e.getEntity();
		Level level = player.level();
		if (level.isClientSide) {
			return;
		}

		CampfireEvent.onPlayerRespawn(null, (ServerPlayer)player, true);
	}
}