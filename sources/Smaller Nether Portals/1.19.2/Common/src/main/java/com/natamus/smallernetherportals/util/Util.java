/*
 * This is the latest source code of Smaller Nether Portals.
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

package com.natamus.smallernetherportals.util;

import com.natamus.collective.services.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Util {
	public static void processSmallerPortal(Level world, BlockPos pos) {
		BlockPos bottomleft;
		String airdirection = "none";
		Rotation rotation = null;
		
		if (isObsidian(world.getBlockState(pos.east()))) {
			if (isAir(world.getBlockState(pos.west()))) {
				bottomleft = pos.west();
				airdirection = "east";
			}
			else {
				bottomleft = pos;
				rotation = Rotation.CLOCKWISE_180;
			}
		}
		else if (isObsidian(world.getBlockState(pos.west()))) {
			if (isAir(world.getBlockState(pos.east()))) {
				airdirection = "east";
			}
			else {
				rotation = Rotation.CLOCKWISE_180;
			}
			bottomleft = pos;
		}
		else if (isObsidian(world.getBlockState(pos.north()))) {
			if (isAir(world.getBlockState(pos.south()))) {
				airdirection = "south";
			}
			else {
				rotation = Rotation.CLOCKWISE_90;
			}
			bottomleft = pos;
		}
		else if (isObsidian(world.getBlockState(pos.south()))) {
			if (isAir(world.getBlockState(pos.north()))) {
				bottomleft = pos.north();
				airdirection = "south";
			}
			else {
				bottomleft = pos;
				rotation = Rotation.CLOCKWISE_90;
			}
		}
		else {
			return;
		}
		bottomleft = bottomleft.immutable();
		
		int height;
		if (!isObsidian(world.getBlockState(bottomleft.below()))) {
			return;
		}

		if (isObsidian(world.getBlockState(bottomleft.above(2)))) {
			height = 2;
		}
		else if (isObsidian(world.getBlockState(bottomleft.above(3)))) {
			height = 3;
		}
		else {
			return;
		}

		List<BlockPos> toportals = new ArrayList<BlockPos>();

		int heighti;
		for (heighti = height; heighti > 0; heighti--) {
			toportals.add(bottomleft.above(heighti-1).immutable());
			if (!airdirection.equals("none")) {
				if (airdirection.equals("south")) {
					if (!isObsidian(world.getBlockState(bottomleft.north()))) {
						break;
					}

					Block wblock = world.getBlockState(bottomleft.south()).getBlock();
					if (isAir(wblock)) {
						if (!isObsidian(world.getBlockState(bottomleft.south(2)))) {
							break;
						}
						toportals.add(bottomleft.above(heighti-1).south().immutable());
					}
					else if (!isObsidian(wblock)) {
						break;
					}
				}
				else if (airdirection.equals("east")) {
					if (!isObsidian(world.getBlockState(bottomleft.west()).getBlock())) {
						break;
					}

					Block wblock = world.getBlockState(bottomleft.east()).getBlock();
					if (isAir(wblock)) {
						if (!isObsidian(world.getBlockState(bottomleft.east(2)))) {
							break;
						}
						toportals.add(bottomleft.above(heighti-1).east().immutable());
					}
					else if (!isObsidian(wblock)) {
						break;
					}
				}
			}

		}

		if (heighti == 0) {
			if (rotation == null) {
				if (airdirection.equals("east")) {
					rotation = Rotation.CLOCKWISE_180;
				}
				else {
					rotation = Rotation.CLOCKWISE_90;
				}
			}

			int obsidiancount = 0;

			for (BlockPos tp0 : toportals) {
				for (BlockPos tp0around : getBlocksAround(tp0.immutable(), rotation)) {
					Block tp0block = world.getBlockState(tp0around).getBlock();
					if (isObsidian(tp0block)) {
						obsidiancount++;
					}
				}
			}

			if (toportals.size() == 2) {
				if (obsidiancount < 6) {
					return;
				}
			}
			else {
				if (obsidiancount < 8) {
					return;
				}
			}

			BlockPos portalpos = null;
			for (BlockPos tp : toportals) {
				world.setBlock(tp, Blocks.NETHER_PORTAL.defaultBlockState().rotate(rotation), 2);
				portalpos = tp;
			}

			if (portalpos == null) {
				return;
			}

			Axis axis;
			if (airdirection.equals("east")) {
				axis = Axis.X;
			}
			else {
				axis = Axis.Z;
			}

			PortalShape size = new PortalShape(world, portalpos, axis);

			Services.EVENTTRIGGER.triggerNetherPortalSpawnEvent(world, portalpos, size);
		}
	}

	public static List<BlockPos> getBlocksAround(BlockPos pos, Rotation rot) {
		List<BlockPos> around = new ArrayList<BlockPos>();
		BlockPos impos = pos.immutable();

		around.add(impos.above().immutable());
		around.add(impos.below().immutable());

		if (rot.equals(Rotation.CLOCKWISE_90)) {
			around.add(impos.north().immutable());
			around.add(impos.south().immutable());
		}
		else {
			around.add(impos.east().immutable());
			around.add(impos.west().immutable());
		}

		return around;
	}

	public static List<BlockPos> getFrontBlocks(Level world, BlockPos portalblock) {
		List<BlockPos> returnblocks = new ArrayList<BlockPos>();

		boolean smallest = false;
		if (isObsidian(world.getBlockState(portalblock.east())) && isObsidian(world.getBlockState(portalblock.west()))) {
			smallest = true;
		}
		else if (isObsidian(world.getBlockState(portalblock.north())) && isObsidian(world.getBlockState(portalblock.south()))) {
			smallest = true;
		}
		else {
			if (isPortal(world.getBlockState(portalblock.north()))) {
				portalblock = portalblock.north().immutable();
			}
			else if (isPortal(world.getBlockState(portalblock.west()))) {
				portalblock = portalblock.west().immutable();
			}
		}

		if (!isPortalOrObsidian(world.getBlockState(portalblock.west()))) {
			returnblocks.add(portalblock.west().below().immutable());
			if (!smallest) {
				returnblocks.add(portalblock.west().south().below().immutable());
			}
		}
		else {
			returnblocks.add(portalblock.south().below().immutable());
			if (!smallest) {
				returnblocks.add(portalblock.south().east().below().immutable());
			}
		}

		return returnblocks;
	}

	public static BlockPos findPortalAround(Level world, BlockPos pos) {
		BlockPos portalpos = null;

		for (int i = 0; i < 10; i++) {
			BlockPos cpos = pos.above(i).immutable();
			Iterator<BlockPos> around = BlockPos.betweenClosedStream(cpos.getX()-1, cpos.getY(), cpos.getZ()-1, cpos.getX()+1, cpos.getY(), cpos.getZ()+1).iterator();
			while (around.hasNext()) {
				BlockPos ap = around.next();
				if (isPortal(world.getBlockState(ap))) {
					portalpos = ap.immutable();
					break;
				}
			}
			if (portalpos != null) {
				break;
			}
		}
		return portalpos;
	}

	public static void setObsidian(Level world, List<BlockPos> toblocks) {
		for (BlockPos tbs : toblocks) {
			if (shouldMakeFront(world.getBlockState(tbs))) {
				world.setBlockAndUpdate(tbs, Blocks.OBSIDIAN.defaultBlockState());
			}
			for (int i = 1; i < 3; i++) {
				BlockPos up = tbs.above(i);
				if (!isAir(world.getBlockState(up))) {
					world.setBlockAndUpdate(up, Blocks.AIR.defaultBlockState());
				}
			}
		}
	}

	public static Boolean shouldMakeFront(BlockState bs) {
		Block block = bs.getBlock();
		return isAir(block) || block.equals(Blocks.NETHERRACK) || block.equals(Blocks.SOUL_SAND);
	}

	public static Boolean isObsidian(BlockState bs) {
		return isObsidian(bs.getBlock());
	}
	public static Boolean isObsidian(Block block) {
		return block.equals(Blocks.OBSIDIAN) || (Services.MODLOADER.isModLoaded("cryingportals") && block.equals(Blocks.CRYING_OBSIDIAN));
	}

	public static Boolean isAir(BlockState bs) {
		Block block = bs.getBlock();
		return block.equals(Blocks.AIR) || block.equals(Blocks.FIRE);
	}
	public static Boolean isAir(Block block) {
		return block.equals(Blocks.AIR) || block.equals(Blocks.FIRE);
	}

	public static Boolean isPortalOrObsidian(BlockState bs) {
		Block block = bs.getBlock();
		return block.equals(Blocks.NETHER_PORTAL) || isObsidian(block);
	}
	
	public static Boolean isPortal(BlockState bs) {
		Block block = bs.getBlock();
		return block.equals(Blocks.NETHER_PORTAL);
	}
}
