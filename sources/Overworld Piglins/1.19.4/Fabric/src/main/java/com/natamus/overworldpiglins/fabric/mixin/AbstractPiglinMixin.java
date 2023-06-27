/*
 * This is the latest source code of Overworld Piglins.
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

package com.natamus.overworldpiglins.fabric.mixin;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AbstractPiglin.class, priority = 1001)
public class AbstractPiglinMixin {
	@Inject(method = "isImmuneToZombification()Z", at = @At(value = "RETURN"), cancellable = true)
	protected void isImmuneToZombification(CallbackInfoReturnable<Boolean> cir) {
		AbstractPiglin abstractPiglin = (AbstractPiglin)(Object)this;
		if (abstractPiglin.hasEffect(MobEffects.WEAKNESS)) {
			abstractPiglin.setTicksFrozen(Integer.MAX_VALUE);
			cir.setReturnValue(false);
		}
		else {
			cir.setReturnValue(true);
		}
	}
}
