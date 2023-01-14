/*
 * This is the latest source code of Random Sheep Colours.
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

package com.natamus.randomsheepcolours.util;

import com.natamus.randomsheepcolours.config.ConfigHandler;
import net.minecraft.world.item.DyeColor;

import java.util.ArrayList;
import java.util.List;

public class Util {
	public static List<DyeColor> possibleColours = null;
	
	public static void initColours() {
		possibleColours = new ArrayList<DyeColor>();
		
		String possiblecolours = ConfigHandler.possibleSheepColours;
		String[] pcspl = possiblecolours.toLowerCase().replace(" ",  "").split(",");
		for (String pc : pcspl) {
			DyeColor colour = getColourFromString(pc.trim());
			if (colour == null) {
				if (!pc.contains("jeb")) {
					continue;
				}
			}
			possibleColours.add(colour);
		}
	}
	
	private static DyeColor getColourFromString(String cs) {
		return switch (cs) {
			case "black" -> DyeColor.BLACK;
			case "blue" -> DyeColor.BLUE;
			case "brown" -> DyeColor.BROWN;
			case "cyan" -> DyeColor.CYAN;
			case "gray" -> DyeColor.GRAY;
			case "green" -> DyeColor.GREEN;
			case "light_blue" -> DyeColor.LIGHT_BLUE;
			case "light_gray" -> DyeColor.LIGHT_GRAY;
			case "lime" -> DyeColor.LIME;
			case "magenta" -> DyeColor.MAGENTA;
			case "orange" -> DyeColor.ORANGE;
			case "pink" -> DyeColor.PINK;
			case "purple" -> DyeColor.PURPLE;
			case "red" -> DyeColor.RED;
			case "white" -> DyeColor.WHITE;
			case "yellow" -> DyeColor.YELLOW;
			default -> null;
		};
	}
}
