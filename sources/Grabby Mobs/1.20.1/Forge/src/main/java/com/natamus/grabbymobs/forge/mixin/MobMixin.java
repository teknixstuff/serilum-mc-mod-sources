/*
 * This is the latest source code of Grabby Mobs.
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

package com.natamus.grabbymobs.forge.mixin;

import com.natamus.grabbymobs.util.Util;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Mob.class, priority = 1001)
public class MobMixin {
	@Inject(method = "canPickUpLoot()Z", at = @At(value = "HEAD"), cancellable = true)
	public void canPickUpLoot(CallbackInfoReturnable<Boolean> cir) {
		if (Util.canPickUp((Mob)(Object)this)) {
			cir.setReturnValue(true);
		}
	}

	@Inject(method = "wantsToPickUp(Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD"), cancellable = true)
	public void wantsToPickUp(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
		if (Util.wantsToPickUp((Mob)(Object)this, itemStack)) {
			cir.setReturnValue(true);
		}
	}
}
