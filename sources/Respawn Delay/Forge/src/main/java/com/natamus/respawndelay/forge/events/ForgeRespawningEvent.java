/*
 * This is the latest source code of Respawn Delay.
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

package com.natamus.respawndelay.forge.events;

import com.natamus.respawndelay.cmds.CommandRespawnall;
import com.natamus.respawndelay.events.RespawningEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeRespawningEvent {
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
    	CommandRespawnall.register(e.getDispatcher());
    }

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent e) {
		Player player = e.player;
		Level level = player.getCommandSenderWorld();
		if (level.isClientSide || e.phase != Phase.START) {
			return;
		}

		RespawningEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}

	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent e) {
		Entity entity = e.getEntity();
		Level level = entity.getCommandSenderWorld();
		if (level.isClientSide) {
			return;
		}

		if (!(entity instanceof Player)) {
			return;
		}

		if (!RespawningEvent.onPlayerDeath((ServerPlayer)entity, e.getSource(), 0)) {
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
		Player player = e.getEntity();
		RespawningEvent.onPlayerLogout(player.level, player);
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		Player player = e.getEntity();
		RespawningEvent.onPlayerLogin(player.level, player);
	}
}