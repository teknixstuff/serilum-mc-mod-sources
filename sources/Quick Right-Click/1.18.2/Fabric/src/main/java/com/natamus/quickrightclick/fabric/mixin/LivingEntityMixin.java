/*
 * This is the latest source code of Quick Right-Click.
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

package com.natamus.quickrightclick.fabric.mixin;

import com.natamus.quickrightclick.data.Variables;
import com.natamus.quickrightclick.util.Util;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntity.class, priority = 1001)
public class LivingEntityMixin {
    @Inject(method = "stopSleeping()V", at = @At(value = "HEAD"))
    public void stopSleeping(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        if (livingEntity instanceof Player) {
            Player player = (Player)livingEntity;
            String playerName = player.getName().getString();
            if (!Variables.bedIsSleeping.contains(playerName)) {
                return;
            }

            Util.stopSleeping(player, playerName);
        }
    }
}