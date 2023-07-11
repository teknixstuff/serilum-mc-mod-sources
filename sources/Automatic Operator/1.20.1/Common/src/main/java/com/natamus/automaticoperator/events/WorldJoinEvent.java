/*
 * This is the latest source code of Automatic Operator.
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

package com.natamus.automaticoperator.events;

import com.mojang.authlib.GameProfile;
import com.natamus.automaticoperator.config.ConfigHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WorldJoinEvent {
	public static void onPlayerLoggedIn(Level world, Player player) {
		if (!ConfigHandler.enableAutomaticOperator) {
			return;
		}

		if (world.isClientSide) {
			return;
		}

		if (ConfigHandler.onlyRunOnDedicatedServers) {
			if (!world.getServer().isDedicatedServer()) {
				return;
			}
		}
		
		if (ConfigHandler.onlyMakeSpecificPlayerNamesOP) {
			boolean foundName = false;

			String playerName = player.getName().getString();
			for (String configName : ConfigHandler.specificOperatorPlayerNames.split(",")) {
				if (playerName.equals(configName.trim())) {
					foundName = true;
					break;
				}
			}

			if (!foundName) {
				return;
			}
		}

		ServerPlayer serverPlayer = (ServerPlayer)player;
		PlayerList playerList = serverPlayer.getServer().getPlayerList();
		GameProfile playerGameProfile = serverPlayer.getGameProfile();
		if (playerList.isOp(playerGameProfile)) {
			return;
		}

		playerList.op(playerGameProfile);

		for (ServerPlayer listPlayer : playerList.getPlayers()) {
			listPlayer.sendSystemMessage(Component.translatable("commands.op.success", new Object[]{serverPlayer.getName()}));
		}
	}
}
