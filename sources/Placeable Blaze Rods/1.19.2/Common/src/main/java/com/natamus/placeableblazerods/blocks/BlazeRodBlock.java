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

package com.natamus.placeableblazerods.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
public class BlazeRodBlock extends DirectionalBlock {
	protected static final VoxelShape Y_AXIS_AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

	protected static final VoxelShape Z_AXIS_AABB = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);

	protected static final VoxelShape X_AXIS_AABB = Block.box(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);

	public BlazeRodBlock(BlockBehaviour.Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
	}

	@Override
	public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootContext.@NotNull Builder builder) {
		return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.BLAZE_ROD, 1)));
	}

	public static @NotNull List<ItemStack> getDrops(BlockState state, ServerLevel worldIn, BlockPos pos, @Nullable BlockEntity tileEntityIn) {
		return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.BLAZE_ROD, 1)));
	}

	public static @NotNull List<ItemStack> getDrops(BlockState state, ServerLevel worldIn, BlockPos pos, @Nullable BlockEntity tileEntityIn, Entity entityIn, @NotNull ItemStack stack) {
		return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.BLAZE_ROD, 1)));
	}

	public @NotNull BlockState rotate(BlockState blockState, Rotation rotation) {
		return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
	}

	public @NotNull BlockState mirror(BlockState blockState, Mirror mirror) {
		return blockState.setValue(FACING, mirror.mirror((blockState.getValue(FACING))));
	}

	public @NotNull VoxelShape getShape(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
		switch ((blockState.getValue(FACING)).getAxis()) {
			default:
				return X_AXIS_AABB;
			case Z:
				return Z_AXIS_AABB;
			case Y:
				break;
		}
		return Y_AXIS_AABB;
	}

	public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
		Direction direction = blockPlaceContext.getClickedFace();
		BlockState blockState = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().relative(direction.getOpposite()));
		if (blockState.is(this) && blockState.getValue(FACING) == direction)
			return defaultBlockState().setValue(FACING, direction.getOpposite());
		return defaultBlockState().setValue(FACING, direction);
	}

	public void animateTick(BlockState blockState, @NotNull Level level, BlockPos blockPos, Random random) {
		Direction direction = blockState.getValue(FACING);
		double d = blockPos.getX() + 0.55D - (random.nextFloat() * 0.1F);
		double e = blockPos.getY() + 0.55D - (random.nextFloat() * 0.1F);
		double f = blockPos.getZ() + 0.55D - (random.nextFloat() * 0.1F);
		double g = (0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F);
		if (random.nextInt(5) == 0)
			level.addParticle(ParticleTypes.END_ROD, d + direction.getStepX() * g, e + direction.getStepY() * g, f + direction.getStepZ() * g, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(new Property[] {FACING});
	}

	public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState blockState) {
		return PushReaction.NORMAL;
	}

	public boolean isPathfindable(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull PathComputationType pathComputationType) {
		return false;
	}
}