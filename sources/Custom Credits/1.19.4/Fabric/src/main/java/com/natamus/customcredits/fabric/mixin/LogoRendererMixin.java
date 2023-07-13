/*
 * This is the latest source code of Custom Credits.
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

package com.natamus.customcredits.fabric.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.natamus.customcredits.config.ConfigHandler;
import com.natamus.customcredits.data.Constants;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.gui.screens.WinScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LogoRenderer.class, priority = 1001)
public class LogoRendererMixin {
	@Inject(method = "renderLogo(Lcom/mojang/blaze3d/vertex/PoseStack;IFI)V", at = @At(value = "HEAD"), cancellable = true)
	public void renderLogo(PoseStack poseStack, int i, float f, int j, CallbackInfo ci) {
		if (ConfigHandler.showMinecraftLogoInCredits) {
			return;
		}

		if (Constants.mc.screen instanceof WinScreen) {
			ci.cancel();
		}
	}
}
