/*
 * This is the latest source code of Quick Right-Click.
 * Minecraft version: 1.19.4.
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

package com.natamus.quickrightclick.forge.mixin;

import com.natamus.quickrightclick.data.Variables;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ServerPlayer.class, priority = 1001)
public class ServerPlayerMixin {
    @Inject(method = "setRespawnPosition(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/core/BlockPos;FZZ)V", at = @At(value = "HEAD"), cancellable = true)
       public void setRespawnPosition(ResourceKey<Level> level, BlockPos blockPos, float respawnAngle, boolean forcedRespawn, boolean showMessage, CallbackInfo ci) {
        Player player = (Player)(Object)this;
        String playerName = player.getName().getString();
        if (Variables.bedIsSleeping.contains(playerName)) {
            ci.cancel();
        }
    }
}