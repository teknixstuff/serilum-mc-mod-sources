/*
 * This is the latest source code of Respawn Delay.
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

package com.natamus.respawndelay.events;

import com.natamus.collective.functions.StringFunctions;
import com.natamus.respawndelay.config.ConfigHandler;
import com.natamus.respawndelay.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class RespawningEvent {
	public static HashMap<Player, Date> death_times = new HashMap<Player, Date>();

	public static void onPlayerTick(ServerLevel serverLevel, ServerPlayer serverPlayer) {
		if (serverPlayer.tickCount % 20 != 0) {
			return;
		}

		if (!serverPlayer.isSpectator()) {
			return;
		}

		if (!death_times.containsKey(serverPlayer)) {
			return;
		}

		int respawndelay = ConfigHandler.respawnDelayInSeconds;
		if (respawndelay < 0) {
			return;
		}

		Date now = new Date();
		Date timedied = death_times.get(serverPlayer);

		long ms = (now.getTime()-timedied.getTime());
		if (ms > respawndelay * 1000L) {
			Util.respawnPlayer(serverLevel, serverPlayer);
			return;
		}

		int seconds = respawndelay - (int)(ms/1000);
		String waitingmessage = ConfigHandler.waitingForRespawnMessage;
		waitingmessage = waitingmessage.replaceAll("<seconds_left>", seconds + "");

		StringFunctions.sendMessage(serverPlayer, waitingmessage, ChatFormatting.GRAY);
	}

	public static boolean onPlayerDeath(ServerPlayer serverPlayer, DamageSource damageSource, float damageAmount) {
		if (ConfigHandler.ignoreAdministratorPlayers) {
			if (serverPlayer.hasPermissions(2)) {
				return true;
			}
		}

		if (ConfigHandler.ignoreCreativePlayers) {
			if (serverPlayer.isCreative()) {
				return true;
			}
		}

		ServerLevel serverLevel = (ServerLevel)serverPlayer.level();

		serverPlayer.setGameMode(GameType.SPECTATOR);
		serverPlayer.setHealth(20);
		serverPlayer.getFoodData().setFoodLevel(20);
		serverPlayer.getFoodData().setSaturation(5F);
		serverPlayer.awardStat(Stats.DEATHS);
		serverPlayer.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_DEATH));
		serverPlayer.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
		serverPlayer.clearFire();

		Vec3 pvec = serverPlayer.position();
		if (pvec.y() < ConfigHandler.lowestPossibleYCoordinate) {
			pvec = new Vec3(pvec.x(), ConfigHandler.lowestPossibleYCoordinate, pvec.z());
			serverPlayer.setDeltaMovement(0, 0, 0);
			serverPlayer.teleportTo(pvec.x(), pvec.y(), pvec.z());
		}

		if (!ConfigHandler.keepItemsOnDeath) {
			Collection<ItemEntity> playerdrops = new ArrayList<ItemEntity>();

			Inventory inv = serverPlayer.getInventory();
			for (int i=0; i < 36; i++) {
				ItemStack slot = inv.getItem(i);
				if (!slot.isEmpty()) {
					playerdrops.add(new ItemEntity(serverLevel, pvec.x, pvec.y+1, pvec.z, slot.copy()));
					slot.setCount(0);
				}
			}

			for (ItemStack next : serverPlayer.getAllSlots()) {
				if (!next.isEmpty()) {
					playerdrops.add(new ItemEntity(serverLevel, pvec.x, pvec.y + 1, pvec.z, next.copy()));
					next.setCount(0);
				}
			}

			//ForgeHooks.onLivingDrops((LivingEntity)player, e.getSource(), playerdrops, 0, true);

			if (ConfigHandler.dropItemsOnDeath) {
				for (ItemEntity drop : playerdrops) {
					serverLevel.addFreshEntity(drop);
				}
			}
		}

		Date now = new Date();
		death_times.put(serverPlayer, now);

		StringFunctions.sendMessage(serverPlayer, ConfigHandler.onDeathMessage, ChatFormatting.DARK_RED);
		return false;
	}

	public static void onPlayerLogout(Level level, Player player) {
		if (level.isClientSide) {
			return;
		}

		if (death_times.containsKey(player)) {
			Util.respawnPlayer(level, (ServerPlayer)player);
		}
	}

	public static void onPlayerLogin(Level level, Player player) {
		if (level.isClientSide) {
			return;
		}

		if (player.isSpectator() && !death_times.containsKey(player)) {
			Util.respawnPlayer(level, (ServerPlayer)player);
		}
	}
}