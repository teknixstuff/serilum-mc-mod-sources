/*
 * This is the latest source code of Omega Mute.
 * Minecraft version: 1.20.1.
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

package com.natamus.omegamute.events;

import com.natamus.collective.functions.StringFunctions;
import com.natamus.omegamute.data.Constants;
import com.natamus.omegamute.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;

import java.util.Date;
import java.util.HashMap;

public class MuteEvent {
	public static boolean playerIsListening = false;
	public static HashMap<String, Integer> ismutedsoundmap = new HashMap<String, Integer>();
	public static HashMap<String, Date> lastplayedsound = new HashMap<String, Date>();
	
	public static boolean onSoundEvent(SoundEngine soundEngine, SoundInstance soundInstance) {	
		String name = soundInstance.getLocation().toString();
		if (name.contains(":")) {
			name = name.split(":")[1];
		}
		
		boolean canplay = true;
		if (ismutedsoundmap.containsKey(name)) {
			int mutedvalue = ismutedsoundmap.get(name);
			if (mutedvalue >= 0) {
				if (mutedvalue == 0) {
					name = "(muted) " + name;
					canplay = false;
				}
				else {
					Date now = new Date();
					
					boolean replace = true;
					if (lastplayedsound.containsKey(name)) {
						Date then = lastplayedsound.get(name);
						long ms = (now.getTime()-then.getTime());
						if (ms < mutedvalue * 1000L) {
							replace = false;
							name = "(" + mutedvalue + "-culled-muted) " + name;
							canplay = false;
						}
					}
					
					if (replace) {
						lastplayedsound.put(name, now);
						name = "(" + mutedvalue + "-culled-allowed) " + name;
					}
				}
			}
		}

		if (playerIsListening && Constants.mc.player != null) {
			StringFunctions.sendMessage(Constants.mc.player, name, ChatFormatting.WHITE);
		}

		return canplay;
	}

	public static void onHotkeyPress() {
		if (Constants.mc.screen instanceof ChatScreen) {
			return;
		}
		
		try {
			Util.loadSoundFile();
		} catch (Exception ex) { return; }

		LocalPlayer localPlayer = Constants.mc.player;
		if (localPlayer != null) {
			StringFunctions.sendMessage(localPlayer, "Reloading the omega mute soundmap file now.", ChatFormatting.DARK_GREEN);
			try {
				if (Util.loadSoundFile()) {
					StringFunctions.sendMessage(localPlayer, "New soundmap changes successfully loaded.", ChatFormatting.DARK_GREEN);
				}
				else {
					StringFunctions.sendMessage(localPlayer, "No soundmap found, a new one has been generated.", ChatFormatting.DARK_GREEN);
				}
			} catch (Exception ex) {
				StringFunctions.sendMessage(localPlayer, "Something went wrong while loading the soundmap file.", ChatFormatting.RED);
			}	
		}
	}
}