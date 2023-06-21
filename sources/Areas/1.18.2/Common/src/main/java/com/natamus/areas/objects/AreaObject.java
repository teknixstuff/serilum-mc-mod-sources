/*
 * This is the latest source code of Areas.
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

package com.natamus.areas.objects;

import com.natamus.areas.data.AreaVariables;
import com.natamus.collective.functions.HashMapFunctions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.List;

public class AreaObject {
	public Level level;
	public BlockPos location;
	public String areaName;
	public int radius;
	public String customRGB;
	public List<String> signLines;

	public AreaObject(Level levelIn, BlockPos locationIn, String areaNameIn, int radiusIn, String customRGBIn, List<String> signLinesIn) {
		level = levelIn;
		location = locationIn;
		areaName = areaNameIn;
		radius = radiusIn;
		customRGB = customRGBIn;
		signLines = signLinesIn;

		HashMapFunctions.computeIfAbsent(AreaVariables.areaObjects, level, k -> new HashMap<BlockPos, AreaObject>()).put(location, this);
	}
}
