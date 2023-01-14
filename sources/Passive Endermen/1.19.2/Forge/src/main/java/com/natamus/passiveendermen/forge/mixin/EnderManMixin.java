/*
 * This is the latest source code of Passive Endermen.
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

package com.natamus.passiveendermen.forge.mixin;

import com.natamus.passiveendermen.config.ConfigHandler;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EnderMan.class, priority = 1001)
public class EnderManMixin {
	@Inject(method = "teleport(DDD)Z", at = @At(value = "HEAD"), cancellable = true)
	private void teleport(double p_32544_, double p_32545_, double p_32546_, CallbackInfoReturnable<Boolean> cir) {
		if (ConfigHandler.preventEndermenFromTeleporting) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "isLookingAtMe(Lnet/minecraft/world/entity/player/Player;)Z", at = @At(value = "HEAD"), cancellable = true)
	void isLookingAtMe(Player player, CallbackInfoReturnable<Boolean> cir) {
		if (ConfigHandler.preventEndermenFromAttackingFirst) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "getCarriedBlock()Lnet/minecraft/world/level/block/state/BlockState;", at = @At(value = "HEAD"), cancellable = true)
	public void getCarriedBlock(CallbackInfoReturnable<BlockState> cir) {
		if (ConfigHandler.preventEndermenFromGriefing) {
			cir.setReturnValue(null);
		}
	}
}
