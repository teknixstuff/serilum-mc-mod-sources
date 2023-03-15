/*
 * This is the latest source code of TNT Breaks Bedrock.
 * Minecraft version: 1.19.4.
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

package com.natamus.tntbreaksbedrock.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class Util {
	public static List<BlockPos> getBedrocks(Level world, BlockPos p) {
		List<BlockPos> bedrocks = new ArrayList<BlockPos>();

		for (BlockPos bp : BlockPos.betweenClosed(p.getX(), p.getY() - 1, p.getZ(), p.getX(), p.getY() + 1, p.getZ())) {
			if (world.getBlockState(bp).getBlock().equals(Blocks.BEDROCK)) {
				bedrocks.add(bp.immutable());
			}
		}
		
		return bedrocks;
	}
}
