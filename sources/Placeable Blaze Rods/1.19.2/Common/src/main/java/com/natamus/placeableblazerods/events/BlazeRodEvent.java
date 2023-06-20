/*
 * This is the latest source code of Placeable Blaze Rods.
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

package com.natamus.placeableblazerods.events;

import com.natamus.placeableblazerods.data.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BlazeRodEvent {
	public static boolean onBlockClick(Level level, Player player, InteractionHand hand, BlockPos pos, BlockHitResult hitVec) {
		if (level.isClientSide) {
			return true;
		}

		ItemStack handstack = player.getItemInHand(hand);
		if (!handstack.getItem().equals(Items.BLAZE_ROD)) {
			return true;
		}

		BlockPos placepos = pos.relative(hitVec.getDirection());
		BlockState targetstate = level.getBlockState(placepos);
		if (!targetstate.getBlock().equals(Blocks.AIR)) {
			return true;
		}

		Direction direction = hitVec.getDirection();
		BlockState blockState = level.getBlockState(placepos.relative(direction.getOpposite()));

		BlockState defaultBlazeRodState = Constants.BLAZE_ROD_BLOCK.defaultBlockState();

		BlockState newstate;
		if (blockState.is(Constants.BLAZE_ROD_BLOCK) && blockState.getValue(DirectionalBlock.FACING) == direction)
			newstate = defaultBlazeRodState.setValue(DirectionalBlock.FACING, direction.getOpposite());
		else {
			newstate = defaultBlazeRodState.setValue(DirectionalBlock.FACING, direction);
		}

		level.setBlock(placepos, newstate, 2);

		if (!player.isCreative()) {
			handstack.shrink(1);
		}

		player.swing(hand, true);
		level.playSound(null, player.getX(), player.getY(), player.getZ(), defaultBlazeRodState.getSoundType().getPlaceSound(), SoundSource.NEUTRAL, 1.0F, 1.0F);
		return true;
	}
}