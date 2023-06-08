/*
 * This is the latest source code of Tree Harvester.
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

package com.natamus.treeharvester.data;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import oshi.util.tuples.Triplet;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Variables {
    public static boolean processedAxeBlacklist = false;

	public static List<Item> allowedAxes = new ArrayList<Item>();

	public static HashMap<BlockPos, Integer> highestleaf = new HashMap<BlockPos, Integer>();
	public static CopyOnWriteArrayList<Triplet<Date, BlockPos, CopyOnWriteArrayList<BlockPos>>> saplingPositions = new CopyOnWriteArrayList<Triplet<Date, BlockPos, CopyOnWriteArrayList<BlockPos>>>();

	public static final HashMap<Level, CopyOnWriteArrayList<BlockPos>> processTickLeaves = new HashMap<Level, CopyOnWriteArrayList<BlockPos>>();
	public static final HashMap<Level, CopyOnWriteArrayList<BlockPos>> processBreakLeaves = new HashMap<Level, CopyOnWriteArrayList<BlockPos>>();
	public static final HashMap<Pair<Level, Player>, Pair<Date, Integer>> harvestSpeedCache = new HashMap<Pair<Level, Player>, Pair<Date, Integer>>();
}
