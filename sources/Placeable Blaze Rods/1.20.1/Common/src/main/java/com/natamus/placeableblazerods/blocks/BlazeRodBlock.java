/*
 * This is the latest source code of Placeable Blaze Rods.
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

package com.natamus.placeableblazerods.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RodBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;

public class BlazeRodBlock extends RodBlock {
   public BlazeRodBlock(BlockBehaviour.Properties properties) {
      super(properties);
      this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
   }

   public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
      Direction direction = blockPlaceContext.getClickedFace();
      BlockState blockstate = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().relative(direction.getOpposite()));
      return blockstate.is(this) && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
   }

   public void animateTick(BlockState blockState, @NotNull Level level, BlockPos blockPos, RandomSource randomSource) {
      Direction direction = blockState.getValue(FACING);
      double d0 = (double)blockPos.getX() + 0.55D - (double)(randomSource.nextFloat() * 0.1F);
      double d1 = (double)blockPos.getY() + 0.55D - (double)(randomSource.nextFloat() * 0.1F);
      double d2 = (double)blockPos.getZ() + 0.55D - (double)(randomSource.nextFloat() * 0.1F);
      double d3 = (0.4F - (randomSource.nextFloat() + randomSource.nextFloat()) * 0.4F);
      if (randomSource.nextInt(5) == 0) {
         level.addParticle(ParticleTypes.END_ROD, d0 + (double)direction.getStepX() * d3, d1 + (double)direction.getStepY() * d3, d2 + (double)direction.getStepZ() * d3, randomSource.nextGaussian() * 0.005D, randomSource.nextGaussian() * 0.005D, randomSource.nextGaussian() * 0.005D);
      }

   }

   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
      builder.add(FACING);
   }
}