/*
 * This is the latest source code of Realistic Bees.
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

package com.natamus.realisticbees.forge.mixin;

import com.natamus.realisticbees.config.ConfigHandler;
import net.minecraft.client.model.BeeModel;
import net.minecraft.client.renderer.entity.BeeRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.animal.Bee;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BeeRenderer.class, priority = 1001)
public abstract class BeeRendererMixin extends LivingEntityRenderer<Bee, BeeModel<Bee>> {
	public BeeRendererMixin(EntityRendererProvider.Context p_174289_, BeeModel<Bee> p_174290_, float p_174291_) {
		super(p_174289_, p_174290_, p_174291_);
	}

	@Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At(value = "TAIL"))
	public void BeeRenderer(EntityRendererProvider.Context p_173931_, CallbackInfo ci) {
		shadowRadius *= (float)ConfigHandler.beeSizeModifier;
	}
}
