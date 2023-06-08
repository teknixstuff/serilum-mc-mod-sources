/*
 * This is the latest source code of Better Conduit Placement.
 * Minecraft version: 1.20.0.
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

package com.natamus.betterconduitplacement.util;

import com.natamus.collective.functions.BlockFunctions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
	public final static List<Block> conduitblocks = new ArrayList<Block>(Arrays.asList(Blocks.PRISMARINE, Blocks.DARK_PRISMARINE, Blocks.PRISMARINE_BRICKS, Blocks.SEA_LANTERN));
	
	public static BlockPos getNextLocation(Level world, BlockPos conduitpos) {
		int n;
		for (n = 0; n < 42; n++) {
			BlockPos npos = getNextConduitPosition(conduitpos, n);
			Block npblock = world.getBlockState(npos).getBlock();
			if (!BlockFunctions.isOneOfBlocks(conduitblocks, npblock)) {
				if (npblock.equals(Blocks.BEDROCK)) {
					return null;
				}
				return npos.immutable();
			}
		}
		
		if (n == 42) {
			for (BlockPos np : BlockPos.betweenClosed(conduitpos.getX() - 1, conduitpos.getY() - 1, conduitpos.getZ() - 1, conduitpos.getX() + 1, conduitpos.getY() + 1, conduitpos.getZ() + 1)) {
				Block nb = world.getBlockState(np).getBlock();
				if (!nb.equals(Blocks.WATER) && !nb.equals(Blocks.CONDUIT) && !BlockFunctions.isOneOfBlocks(conduitblocks, nb)) {
					world.setBlockAndUpdate(np, Blocks.WATER.defaultBlockState());
				}
			}
		}
		
		return null;
	}
	
	public static void destroyAllConduitBlocks(Level world, BlockPos conduitpos) {
		for (int n = 0; n < 42; n++) {
			BlockPos npos = getNextConduitPosition(conduitpos, n);
			Block block = world.getBlockState(npos).getBlock();
			if (BlockFunctions.isOneOfBlocks(conduitblocks, block)) {
				world.setBlockAndUpdate(npos, Blocks.WATER.defaultBlockState());
				ItemEntity ei = new ItemEntity(world, conduitpos.getX(), conduitpos.getY()+1, conduitpos.getZ(), new ItemStack(block, 1));
				world.addFreshEntity(ei);
			}
		}
	}
	
	private static BlockPos getNextConduitPosition(BlockPos conduitpos, int count) {
		return switch (count) {
			case 0 -> conduitpos.above(2).east(2);
			case 1 -> conduitpos.above(2).east(1);
			case 2 -> conduitpos.above(2);
			case 3 -> conduitpos.above(2).west(1);
			case 4 -> conduitpos.above(2).west(2);
			case 5 -> conduitpos.above(1).west(2);
			case 6 -> conduitpos.west(2);
			case 7 -> conduitpos.below(1).west(2);
			case 8 -> conduitpos.below(2).west(2);
			case 9 -> conduitpos.below(2).west(1);
			case 10 -> conduitpos.below(2);
			case 11 -> conduitpos.below(2).east(1);
			case 12 -> conduitpos.below(2).east(2);
			case 13 -> conduitpos.below(1).east(2);
			case 14 -> conduitpos.east(2);
			case 15 -> conduitpos.above(1).east(2);
			case 16 -> conduitpos.above(2).north(1);
			case 17 -> conduitpos.above(2).north(2);
			case 18 -> conduitpos.above(1).north(2);
			case 19 -> conduitpos.north(2);
			case 20 -> conduitpos.below(1).north(2);
			case 21 -> conduitpos.below(2).north(2);
			case 22 -> conduitpos.below(2).north(1);
			case 23 -> conduitpos.below(2).south(1);
			case 24 -> conduitpos.below(2).south(2);
			case 25 -> conduitpos.below(1).south(2);
			case 26 -> conduitpos.south(2);
			case 27 -> conduitpos.above(1).south(2);
			case 28 -> conduitpos.above(2).south(2);
			case 29 -> conduitpos.above(2).south(1);
			case 30 -> conduitpos.south(2).east(1);
			case 31 -> conduitpos.south(2).east(2);
			case 32 -> conduitpos.south(1).east(2);
			case 33 -> conduitpos.north(1).east(2);
			case 34 -> conduitpos.north(2).east(2);
			case 35 -> conduitpos.north(2).east(1);
			case 36 -> conduitpos.north(2).west(1);
			case 37 -> conduitpos.north(2).west(2);
			case 38 -> conduitpos.north(1).west(2);
			case 39 -> conduitpos.south(1).west(2);
			case 40 -> conduitpos.south(2).west(2);
			case 41 -> conduitpos.south(2).west(1);
			default -> null;
		};
	}
}
