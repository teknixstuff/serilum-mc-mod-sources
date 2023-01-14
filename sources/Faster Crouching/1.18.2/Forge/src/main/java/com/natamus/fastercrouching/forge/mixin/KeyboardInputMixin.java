/*
 * This is the latest source code of Faster Crouching.
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

package com.natamus.fastercrouching.forge.mixin;

import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = KeyboardInput.class, priority = 1001)
public class KeyboardInputMixin extends Input {
    @Inject(method = "tick(Z)V", at = @At(value = "TAIL"))
    public void tick(boolean pIsMovingSlowly, CallbackInfo ci) {
        if (pIsMovingSlowly) {
            this.leftImpulse *= 10.0F;
            this.forwardImpulse *= 10.0F;
        }
    }
}
