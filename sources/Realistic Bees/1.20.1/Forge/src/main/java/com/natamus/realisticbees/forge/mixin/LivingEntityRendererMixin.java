/*
 * This is the latest source code of Realistic Bees.
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

package com.natamus.realisticbees.forge.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.natamus.realisticbees.config.ConfigHandler;
import net.minecraft.client.renderer.entity.BeeRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntityRenderer.class, priority = 1001)
public abstract class LivingEntityRendererMixin {
	@Inject(method = "scale", at = @At("HEAD"))
	private void applyScale(LivingEntity entity, PoseStack stack, float partialTicks, CallbackInfo ci) {
		if (((Object) this) instanceof BeeRenderer) {
			float scale = (float)ConfigHandler.beeSizeModifier;
			stack.scale(scale, scale, scale);
		}
	}
}
