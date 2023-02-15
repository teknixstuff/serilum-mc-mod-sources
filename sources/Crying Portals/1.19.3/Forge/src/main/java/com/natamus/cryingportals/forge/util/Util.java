/*
 * This is the latest source code of Crying Portals.
 * Minecraft version: 1.19.3.
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

package com.natamus.cryingportals.forge.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class Util {
	public static Boolean isObsidian(BlockState bs) {
		Block block = bs.getBlock();
		return block.equals(Blocks.OBSIDIAN);
	}
	public static Boolean isObsidian(Block block) {
		return block.equals(Blocks.OBSIDIAN);
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
		return block.equals(Blocks.NETHER_PORTAL) || block.equals(Blocks.OBSIDIAN);
	}
	
	public static Boolean isPortal(BlockState bs) {
		Block block = bs.getBlock();
		return block.equals(Blocks.NETHER_PORTAL);
	}
}
