/*
 * This is the latest source code of Areas.
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

package com.natamus.areas.events;

import com.natamus.areas.config.ConfigHandler;
import com.natamus.areas.objects.AreaObject;
import com.natamus.areas.objects.Variables;
import com.natamus.areas.util.Util;
import com.natamus.collective.functions.FABFunctions;
import com.natamus.collective.functions.HashMapFunctions;
import com.natamus.collective.functions.StringFunctions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AreaEvent {
	static int tickdelay = 20;

	public static void onServerTick(MinecraftServer server) {
		if (tickdelay > 0) {
			tickdelay -= 1;
			return;
		}
		tickdelay = 20;

		for (Level level : Variables.checkifshouldignoreperlevel.keySet()) {
			for (BlockPos pos : Variables.checkifshouldignoreperlevel.get(level)) {
				if (HashMapFunctions.computeIfAbsent(Variables.areasperlevel, level, k -> new HashMap<BlockPos, AreaObject>()).containsKey(pos)) {
					Variables.checkifshouldignoreperlevel.get(level).remove(pos);
					HashMapFunctions.computeIfAbsent(Variables.ignoremap, level, k -> new HashMap<BlockPos, Integer>()).remove(pos);
					continue;
				}

				int checkleft = HashMapFunctions.computeIfAbsent(Variables.ignoremap, level, k -> new HashMap<BlockPos, Integer>()).get(pos);
				if (checkleft <= 0) {
					Variables.checkifshouldignoreperlevel.get(level).remove(pos);
					Variables.ignoremap.get(level).remove(pos);

					HashMapFunctions.computeIfAbsent(Variables.ignoresignsperlevel, level, k -> new CopyOnWriteArrayList<BlockPos>()).add(pos);
					continue;
				}

				Variables.ignoremap.get(level).put(pos, checkleft-1);
			}
		}
	}

	public static void onPlayerTick(ServerLevel level, ServerPlayer player) {
		if (level.isClientSide) {
			return;
		}

		if (player.tickCount % 20 != 0) {
			return;
		}

		BlockPos ppos = player.blockPosition();

		List<AreaObject> aos = new ArrayList<AreaObject>();
		List<String> enteredareas = new ArrayList<String>();
		List<BlockPos> ignoresigns = HashMapFunctions.computeIfAbsent(Variables.ignoresignsperlevel, level, k -> new CopyOnWriteArrayList<BlockPos>());

		List<BlockPos> nearbysigns = FABFunctions.getAllTileEntityPositionsNearbyEntity(BlockEntityType.SIGN, ConfigHandler.radiusAroundPlayerToCheckForSigns, level, player);
		for (BlockPos nspos : nearbysigns) {
			if (ignoresigns.contains(nspos)) {
				continue;
			}

			AreaObject ao = Util.getAreaSign(level, nspos);
			if (ao == null) {
				if (!HashMapFunctions.computeIfAbsent(Variables.checkifshouldignoreperlevel, level, k -> new CopyOnWriteArrayList<BlockPos>()).contains(nspos)) {
					Variables.checkifshouldignoreperlevel.get(level).add(nspos);
					HashMapFunctions.computeIfAbsent(Variables.ignoremap, level, k -> new HashMap<BlockPos, Integer>()).put(nspos, 10);
				}
				continue;
			}

			if (ao.containsplayers.contains(player)){
				enteredareas.add(ao.areaname);
			}
			aos.add(ao);
		}

		for (AreaObject ao : aos) {
			if (ao == null) {
				continue;
			}

			BlockPos nspos = ao.location;
			if (ppos.closerThan(nspos, ao.radius)) {
				if (ao.containsplayers.contains(player)) {
					continue;
				}

				if (Collections.frequency(enteredareas, ao.areaname) > 1) {
					Util.enterArea(ao, player, false);
					return;
				}

				Util.enterArea(ao, player, true);
			}
			else if (ao.containsplayers.contains(player)){
				if (Collections.frequency(enteredareas, ao.areaname) > 1) {
					Util.exitArea(ao, player, false);
					return;
				}

				Util.exitArea(ao, player, true);
			}
		}
	}

	public static void onSignBreak(Level level, Player player, BlockPos signpos, BlockState state, BlockEntity blockEntity) {
		if (level.isClientSide) {
			return;
		}

		if (Util.isSignBlock(state.getBlock())) {
			if (HashMapFunctions.computeIfAbsent(Variables.areasperlevel, level, k -> new HashMap<BlockPos, AreaObject>()).containsKey(signpos)) {
				AreaObject ao = Variables.areasperlevel.get(level).get(signpos);
				List<Player> playersinside = ao.containsplayers;
				for (Player insideplayer : playersinside) {
					Util.areaChangeMessage(insideplayer, StringFunctions.capitalizeFirst(ao.areaname) + " no longer exists.", ao.customrgb);
				}

				Variables.areasperlevel.get(level).remove(signpos);
			}

			HashMapFunctions.computeIfAbsent(Variables.ignoresignsperlevel, level, k -> new CopyOnWriteArrayList<BlockPos>()).remove(signpos);
			HashMapFunctions.computeIfAbsent(Variables.checkifshouldignoreperlevel, level, k -> new CopyOnWriteArrayList<BlockPos>()).remove(signpos);
			HashMapFunctions.computeIfAbsent(Variables.ignoremap, level, k -> new HashMap<BlockPos, Integer>()).remove(signpos);
		}
	}
}
