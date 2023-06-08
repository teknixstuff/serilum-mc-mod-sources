/*
 * This is the latest source code of Campfire Spawn and Tweaks.
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

package com.natamus.campfirespawnandtweaks.util;

import com.mojang.datafixers.util.Pair;
import com.natamus.campfirespawnandtweaks.config.ConfigHandler;
import com.natamus.campfirespawnandtweaks.events.CampfireEvent;
import com.natamus.collective.functions.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Util {
	public static void loadCampfireSpawnsFromWorld(Level level) {
		String campfirespawnfolder = WorldFunctions.getWorldPath((ServerLevel)level) + File.separator + "data" + File.separator + "campfirespawn" + File.separator + DimensionFunctions.getSimpleDimensionString(level);
		File dir = new File(campfirespawnfolder);
		dir.mkdirs();
		
		File[] listOfFiles = dir.listFiles();
		if (listOfFiles == null) {
			return;
		}

		for (File listOfFile : listOfFiles) {
			String filename = listOfFile.getName();
			if (filename.endsWith(".spawnpoint")) {
				String playername = filename.toLowerCase().replace(".spawnpoint", "");

				String filecontent;
				try {
					filecontent = new String(Files.readAllBytes(Paths.get(campfirespawnfolder + File.separator + filename, new String[0])));
				} catch (IOException e) {
					System.out.println("[Error] Campfire Spawn: Something went wrong while loading a campfire spawn location for player " + playername + ".");
					continue;
				}

				if (StringFunctions.sequenceCount(filecontent, "_") < 2) {
					continue;
				}

				String[] cs = filecontent.trim().split("_");
				if (cs.length == 3) {
					if (NumberFunctions.isNumeric(cs[0]) && NumberFunctions.isNumeric(cs[1]) && NumberFunctions.isNumeric(cs[2])) {
						int x = Integer.parseInt(cs[0]);
						int y = Integer.parseInt(cs[1]);
						int z = Integer.parseInt(cs[2]);

						BlockPos spawnpos = new BlockPos(x, y, z);
						CampfireEvent.playercampfires.put(playername, new Pair<Level, BlockPos>(level, spawnpos.immutable()));
					}

				}
			}
		}
	}
	
	public static boolean setCampfireSpawn(Level level, String playername, BlockPos campfirepos) {
		String campfirespawnfolder = WorldFunctions.getWorldPath((ServerLevel)level) + File.separator + "data" + File.separator + "campfirespawn" + File.separator + DimensionFunctions.getSimpleDimensionString(level);
		File dir = new File(campfirespawnfolder);
		dir.mkdirs();
		
		String filename = playername.toLowerCase() + ".spawnpoint";
		try {
			PrintWriter writer = new PrintWriter(campfirespawnfolder + File.separator + filename, StandardCharsets.UTF_8);
			writer.println(campfirepos.getX() + "_" + campfirepos.getY() + "_" + campfirepos.getZ());
			writer.close();
		} catch (Exception e) {
			System.out.println("[Error] Campfire Spawn: Something went wrong while saving a campfire spawn location for player " + playername + ".");
			return false;
		}
		
		CampfireEvent.playercampfires.put(playername.toLowerCase(), new Pair<Level, BlockPos>(level, campfirepos.immutable()));
		return true;
	}
	
	public static boolean checkForCampfireSpawnRemoval(Level level, String playername, BlockPos campfirepos) {
		List<String> fromplayernames = new ArrayList<String>();
		for (String pname : CampfireEvent.playercampfires.keySet()) {
			Pair<Level, BlockPos> pair = CampfireEvent.playercampfires.get(pname);
			if (campfirepos.equals(pair.getSecond())) {
				fromplayernames.add(pname);
			}
		}
		
		boolean removed = false;
		for (String fromplayername : fromplayernames) {
			if (CampfireEvent.playercampfires.containsKey(fromplayername)) {
				for (Level looplevel : CampfireEvent.playerstorespawn.keySet()) {
					String campfirespawnfolder = WorldFunctions.getWorldPath((ServerLevel)looplevel) + File.separator + "data" + File.separator + "campfirespawn" + File.separator + DimensionFunctions.getSimpleDimensionString(looplevel);
					File dir = new File(campfirespawnfolder);
					dir.mkdirs();
					
					String filename = fromplayername.toLowerCase() + ".spawnpoint";
					try {
						PrintWriter writer = new PrintWriter(campfirespawnfolder + File.separator + filename, StandardCharsets.UTF_8);
						writer.print("");
						writer.close();
					} catch (Exception e) {
						System.out.println("[Error] Campfire Spawn: Something went wrong while removing a campfire spawn location for player " + playername + ".");
					}
				}
				
				CampfireEvent.playercampfires.remove(fromplayername);
				removed = true;
				
				if (ConfigHandler.sendMessageOnCampfireSpawnUnset) {
					if (fromplayername.equalsIgnoreCase(playername)) {
						continue;
					}
					
					Player target = PlayerFunctions.matchPlayer(level, fromplayername);
					if (target == null) {
						continue;
					}
					
					StringFunctions.sendMessage(target, "Campfire spawn point removed.", ChatFormatting.DARK_GRAY);
				}
			}
		}

		return removed && fromplayernames.contains(playername.toLowerCase());
	}
}
