/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.fabric.mixin;

import com.natamus.collective.fabric.callbacks.CollectivePistonEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PistonBaseBlock.class, priority = 1001)
public class PistonBaseBlockMixin {
	@Inject(method = "triggerEvent(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;II)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/piston/PistonBaseBlock;moveBlocks(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Z)Z", ordinal = 0), cancellable = true)
	public void triggerEvent_default(BlockState blockState, Level level, BlockPos blockPos, int i, int j, CallbackInfoReturnable<Boolean> cir) {
		if (!CollectivePistonEvents.PRE_PISTON_ACTIVATE.invoker().onPistonActivate(level, blockPos, blockState.getValue(DirectionalBlock.FACING), false)) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "triggerEvent(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;II)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getBlockEntity(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;", ordinal = 0), cancellable = true)
	public void triggerEvent_extended(BlockState blockState, Level level, BlockPos blockPos, int i, int j, CallbackInfoReturnable<Boolean> cir) {
		if (!CollectivePistonEvents.PRE_PISTON_ACTIVATE.invoker().onPistonActivate(level, blockPos, blockState.getValue(DirectionalBlock.FACING), true)) {
			cir.setReturnValue(false);
		}
	}
}
