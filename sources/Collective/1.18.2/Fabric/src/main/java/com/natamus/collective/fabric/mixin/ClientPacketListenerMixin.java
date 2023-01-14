/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.fabric.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.natamus.collective.fabric.callbacks.CollectiveChatEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundChatPacket;

@Mixin(value = ClientPacketListener.class, priority = 1001)
public class ClientPacketListenerMixin {
	@Final @Shadow private Minecraft minecraft;
	
	@Inject(method = "handleChat(Lnet/minecraft/network/protocol/game/ClientboundChatPacket;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;handleChat(Lnet/minecraft/network/chat/ChatType;Lnet/minecraft/network/chat/Component;Ljava/util/UUID;)V"), cancellable = true)
	public void ClientPacketListener_handleChat(ClientboundChatPacket clientboundChatPacket, CallbackInfo ci) {
		Component message = clientboundChatPacket.getMessage();
		Component newMessage = CollectiveChatEvents.CLIENT_CHAT_RECEIVED.invoker().onClientChat(clientboundChatPacket.getType(), message, clientboundChatPacket.getSender());
		if (message != newMessage) {
			minecraft.gui.handleChat(clientboundChatPacket.getType(), newMessage, clientboundChatPacket.getSender());
			ci.cancel();
		}
	}
}
