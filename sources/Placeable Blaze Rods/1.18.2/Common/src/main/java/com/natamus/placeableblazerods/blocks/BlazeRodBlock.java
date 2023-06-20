/*
 * This is the latest source code of Placeable Blaze Rods.
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

package com.natamus.placeableblazerods.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RodBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@SuppressWarnings("deprecation")
public class BlazeRodBlock extends RodBlock {
	public BlazeRodBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Direction direction = blockPlaceContext.getClickedFace();
        BlockState blockState = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().relative(direction.getOpposite()));
        return blockState.is(this) && blockState.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
    }

    public void animateTick(BlockState blockState, @NotNull Level level, BlockPos blockPos, Random random) {
        Direction $$4 = blockState.getValue(FACING);
        double $$5 = (double)blockPos.getX() + 0.55 - (double)(random.nextFloat() * 0.1F);
        double $$6 = (double)blockPos.getY() + 0.55 - (double)(random.nextFloat() * 0.1F);
        double $$7 = (double)blockPos.getZ() + 0.55 - (double)(random.nextFloat() * 0.1F);
        double $$8 = 0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F;
        if (random.nextInt(5) == 0) {
            level.addParticle(ParticleTypes.END_ROD, $$5 + (double)$$4.getStepX() * $$8, $$6 + (double)$$4.getStepY() * $$8, $$7 + (double)$$4.getStepZ() * $$8, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005);
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
        blockState.add(new Property[]{FACING});
    }

    public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState blockState) {
        return PushReaction.NORMAL;
    }
}