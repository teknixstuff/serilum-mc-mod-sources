/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.fabric.mixin;

import com.natamus.collective.fabric.callbacks.CollectiveChatEvents;
import net.minecraft.Util;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.client.multiplayer.chat.ChatTrustLevel;
import net.minecraft.network.chat.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.annotation.Nullable;
import java.time.Instant;

@Mixin(value = ChatListener.class, priority = 1001)
public abstract class ChatListenerMixin {
    @Shadow private @Final Minecraft minecraft;
    @Shadow private long previousMessageTime;
    @Shadow protected abstract void narrateChatMessage(ChatType.Bound bound, Component component);
    @Shadow protected abstract void logPlayerMessage(PlayerChatMessage playerChatMessage, ChatType.Bound bound, @Nullable PlayerInfo playerInfo, ChatTrustLevel chatTrustLevel);

    @Inject(method = "showMessageToPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;getChat()Lnet/minecraft/client/gui/components/ChatComponent;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    public void showMessageToPlayer(ChatType.Bound bound, PlayerChatMessage playerChatMessage, Component component, PlayerInfo playerInfo, boolean bl, Instant instant, CallbackInfoReturnable<Boolean> cir, ChatTrustLevel chatTrustLevel, GuiMessageTag guiMessageTag, MessageSignature messageSignature, FilterMask filterMask) {
        Component newMessage = CollectiveChatEvents.CLIENT_CHAT_RECEIVED.invoker().onClientChat(bound.chatType(), component, playerInfo.getProfile().getId());
		if (component != newMessage) {
            this.minecraft.gui.getChat().addMessage(newMessage, messageSignature, guiMessageTag);
            this.narrateChatMessage(bound, playerChatMessage.serverContent());
            this.logPlayerMessage(playerChatMessage, bound, playerInfo, chatTrustLevel);
            this.previousMessageTime = Util.getMillis();
            cir.setReturnValue(true);
		}
    }
}
