/*
 * This is the latest source code of Tree Harvester.
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

package com.natamus.treeharvester.processing;

import com.natamus.collective.functions.CompareBlockFunctions;
import com.natamus.treeharvester.config.ConfigHandler;
import com.natamus.treeharvester.data.Variables;
import com.natamus.treeharvester.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MyceliumBlock;
import net.minecraft.world.level.block.state.BlockState;
import oshi.util.tuples.Triplet;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TreeProcessing {
	public static int isTreeAndReturnLogAmount(Level level, BlockPos pos) {
		Variables.highestleaf.put(pos, 0);

		int leafcount = 8;
		int logCount = 0;
		int prevleafcount = -1;
		int prevlogCount = -1;

		int highesty = 0;
		for (int y = 1; y<=30; y+=1) {
			if (prevleafcount == leafcount && prevlogCount == logCount) {
				break;
			}
			prevleafcount = leafcount;
			prevlogCount = logCount;

			Iterator<BlockPos> it = BlockPos.betweenClosedStream(pos.getX()-2, pos.getY()+(y-1), pos.getZ()-2, pos.getX()+2, pos.getY()+(y-1), pos.getZ()+2).iterator();
			while (it.hasNext()) {
				BlockPos npos = it.next();
				Block nblock = level.getBlockState(npos).getBlock();
				if (CompareBlockFunctions.isTreeLeaf(nblock, ConfigHandler.enableNetherTrees) || Util.isGiantMushroomLeafBlock(nblock)) {
					leafcount-=1;
					if (npos.getY() > highesty) {
						highesty = npos.getY();
					}
				}
				else if (Util.isTreeLog(nblock)) {
					logCount+=1;
				}
			}
		}

		Variables.highestleaf.put(pos.immutable(), highesty);

		if (leafcount < 0) {
			return logCount;
		}
		return -1;
	}

	public static List<BlockPos> getAllLogsToBreak(Level level, BlockPos pos, int logCount, Block logType) {
		CopyOnWriteArrayList<BlockPos> bottomlogs = new CopyOnWriteArrayList<BlockPos>();
		if (ConfigHandler.replaceSaplingOnTreeHarvest) {
			Block blockbelow = level.getBlockState(pos.below()).getBlock();
			if (CompareBlockFunctions.isDirtBlock(blockbelow) || blockbelow instanceof MyceliumBlock) {
				Iterator<BlockPos> it = BlockPos.betweenClosedStream(pos.getX()-1, pos.getY(), pos.getZ()-1, pos.getX()+1, pos.getY(), pos.getZ()+1).iterator();
				while (it.hasNext()) {
					BlockPos npos = it.next();
					Block block = level.getBlockState(npos).getBlock();
					if (block.equals(logType) || Util.areEqualLogTypes(logType, block)) {
						bottomlogs.add(npos.immutable());
					}
				}
			}

			Variables.saplingPositions.add(new Triplet<>(new Date(), pos.immutable(), bottomlogs));
		}

		return getLogsToBreak(level, pos, new ArrayList<BlockPos>(), logCount, logType);
	}

	private static List<BlockPos> getLogsToBreak(Level level, BlockPos pos, List<BlockPos> logsToBreak, int logCount, Block logType) {
		List<BlockPos> checkAround = new ArrayList<BlockPos>();

		boolean isMangrove = Util.isMangroveRootOrLog(logType);
		int downY = pos.getY()-1;

		List<BlockPos> aroundLogs = new ArrayList<BlockPos>();
		for (BlockPos aL : BlockPos.betweenClosed(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1)) {
			aroundLogs.add(aL.immutable());
		}

		for (BlockPos aroundLogPos : aroundLogs) {
			if (logsToBreak.contains(aroundLogPos)) {
				continue;
			}

			BlockState logstate = level.getBlockState(aroundLogPos);
			Block logblock = logstate.getBlock();
			if (logblock.equals(logType) || Util.areEqualLogTypes(logType, logblock)) {
				if (!isMangrove || aroundLogPos.getY() != downY) {
					checkAround.add(aroundLogPos);
				}
				logsToBreak.add(aroundLogPos);
			}
		}

		if (checkAround.size() == 0) {
			return logsToBreak;
		}

		for (BlockPos capos : checkAround) {
			for (BlockPos logpos : getLogsToBreak(level, capos, logsToBreak, logCount, logType)) {
				if (!logsToBreak.contains(logpos)) {
					logsToBreak.add(logpos.immutable());
				}
			}
		}

		BlockPos up = pos.above(2);
		return getLogsToBreak(level, up.immutable(), logsToBreak, logCount, logType);
	}
}
