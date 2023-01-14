/*
 * This is the latest source code of Beautified Chat Server.
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

package com.natamus.beautifiedchatserver.forge.events;

import com.mojang.datafixers.util.Pair;
import com.natamus.beautifiedchatserver.data.Constants;
import com.natamus.beautifiedchatserver.events.BeautifulChatEvent;
import com.natamus.collective.functions.MessageFunctions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeBeautifulChatEvent {
	@SubscribeEvent
	public void onServerChat(ServerChatEvent e) {
		ServerPlayer serverPlayer = e.getPlayer();
		Component originalMessage = e.getMessage();
		MutableComponent fullMessage = Component.literal("<" + serverPlayer.getName().getString() + "> ").append(originalMessage);

		Pair<Boolean, Component> pair = BeautifulChatEvent.onServerChat(serverPlayer, fullMessage, serverPlayer.getUUID());
		if (pair != null) {
			if (pair.getFirst()) {
				MutableComponent newMessage = pair.getSecond().copy();
				if (fullMessage != newMessage) {
					e.setCanceled(true);

					serverPlayer.server.execute(() -> {
						Constants.logger.info(newMessage.getString());
						MessageFunctions.broadcastMessage(serverPlayer.level, newMessage);
					});
				}
			}
		}
	}
}
