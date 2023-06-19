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

package com.natamus.omegamute.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.omegamute.data.Constants;
import com.natamus.omegamute.events.MuteEvent;
import com.natamus.omegamute.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.List;

public class CommandOmega {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("omegamute")
			.then(Commands.literal("reload")
			.executes((command) -> {
				return reload();
			}))
			.then(Commands.literal("query")
			.executes((command) -> {
				return query();
			}))
			.then(Commands.literal("listen").requires((iCommandSender) -> iCommandSender.getEntity() instanceof Player)
			.executes((command) -> {
				return listen();
			}))
			.then(Commands.literal("mute")
			.then(Commands.argument("string-contains", StringArgumentType.word())
			.executes((command) -> {
				return mute(StringArgumentType.getString(command, "string-contains"));
			})))
			.then(Commands.literal("cull")
			.then(Commands.argument("cull-time", IntegerArgumentType.integer(0, 3600))
			.then(Commands.argument("string-contains", StringArgumentType.word())
			.executes((command) -> {
				return cull(StringArgumentType.getString(command, "string-contains"), IntegerArgumentType.getInteger(command, "cull-time"));
			}))))
			.then(Commands.literal("unmute")
			.then(Commands.argument("string-contains", StringArgumentType.word())
			.executes((command) -> {
				return unmute(StringArgumentType.getString(command, "string-contains"));
			})))
		);
	}

	public static int reload() {
		if (Constants.mc.player == null) {
			return 1;
		}

		StringFunctions.sendMessage(Constants.mc.player, "Reloading the omega mute soundmap file now.", ChatFormatting.DARK_GREEN);
		try {
			if (Util.loadSoundFile()) {
				StringFunctions.sendMessage(Constants.mc.player, "New soundmap changes successfully loaded.", ChatFormatting.DARK_GREEN);
			}
			else {
				StringFunctions.sendMessage(Constants.mc.player, "No soundmap found, a new one has been generated.", ChatFormatting.DARK_GREEN);
			}
		} catch (Exception ex) {
			StringFunctions.sendMessage(Constants.mc.player, "Something went wrong while loading the soundmap file.", ChatFormatting.RED);
		}
		return 1;
	}

	public static int query() {
		HashMap<String, Integer> mutedsounds = Util.getMutedSounds();
		if (mutedsounds.size() > 0) {
			StringBuilder combined = new StringBuilder();
			for (String soundname : mutedsounds.keySet()) {
				if (!combined.toString().equals("")) {
					combined.append(", ");
				}
				Integer mutedvalue = mutedsounds.get(soundname);
				if (mutedvalue > 0) {
					combined.append(soundname).append("(").append(mutedvalue).append(")");
				}
				else {
					combined.append(soundname);
				}
			}

			if (Constants.mc.player == null) {
				return 1;
			}

			StringFunctions.sendMessage(Constants.mc.player, "The following sound events are currently muted:", ChatFormatting.DARK_GREEN);
			StringFunctions.sendMessage(Constants.mc.player, combined.toString(), ChatFormatting.YELLOW);
		}
		else {
			if (Constants.mc.player == null) {
				return 1;
			}

			StringFunctions.sendMessage(Constants.mc.player, "There are currently no sound events muted.", ChatFormatting.DARK_GREEN);
		}

		return 1;
	}

	public static int listen() {
		if (Constants.mc.player == null) {
			return 1;
		}

		String playerName = Constants.mc.player.getName().getString();
		if (MuteEvent.playerIsListening) {
			MuteEvent.playerIsListening = false;
			StringFunctions.sendMessage(Constants.mc.player, "You have stopped listening to the active sounds. To toggle it on use '/omegamute listen' again.", ChatFormatting.DARK_GREEN);
		}
		else {
			MuteEvent.playerIsListening = true;
			StringFunctions.sendMessage(Constants.mc.player, "You are now listening to the active sounds. To toggle it off use '/omegamute listen' again.", ChatFormatting.DARK_GREEN);
		}

		return 1;
	}

	public static int mute(String wildcard) {
		List<String> muted = Util.muteWildcard(wildcard, 0);
		if (Constants.mc.player == null) {
			return 1;
		}

		if (muted.size() > 0) {
			String combined = String.join(", ", muted);
			StringFunctions.sendMessage(Constants.mc.player, "By using the wildcard string '" + wildcard + "', the following " + muted.size() + " sound events have been muted:", ChatFormatting.DARK_GREEN);
			StringFunctions.sendMessage(Constants.mc.player, combined, ChatFormatting.YELLOW);
			StringFunctions.sendMessage(Constants.mc.player, "The soundmap file has been updated.", ChatFormatting.DARK_GREEN);
		}
		else {
			StringFunctions.sendMessage(Constants.mc.player, "No sound events were found by using the wildcard string '" + wildcard + "', try a different query.", ChatFormatting.RED);
		}

		return 1;
	}

	public static int cull(String wildcard, int culltime) {
		List<String> muted = Util.muteWildcard(wildcard, culltime);
		if (Constants.mc.player == null) {
			return 1;
		}

		if (muted.size() > 0) {
			String combined = String.join(", ", muted);
			StringFunctions.sendMessage(Constants.mc.player, "By using the wildcard string '" + wildcard + "', the following " + muted.size() + " sound events have been muted with a cull-time of " + culltime + ":", ChatFormatting.DARK_GREEN);
			StringFunctions.sendMessage(Constants.mc.player, combined, ChatFormatting.YELLOW);
			StringFunctions.sendMessage(Constants.mc.player, "The soundmap file has been updated.", ChatFormatting.DARK_GREEN);
		}
		else {
			StringFunctions.sendMessage(Constants.mc.player, "No sound events were found by using the wildcard string '" + wildcard + "', try a different query.", ChatFormatting.RED);
		}

		return 1;
	}

	public static int unmute(String wildcard) {
		List<String> unmuted = Util.unmuteWildcard(wildcard);
		if (Constants.mc.player == null) {
			return 1;
		}

		if (unmuted.size() > 0) {
			String combined = String.join(", ", unmuted);
			StringFunctions.sendMessage(Constants.mc.player, "By using the wildcard string '" + wildcard + "', the following " + unmuted.size() + " sound events have been unmuted:", ChatFormatting.DARK_GREEN);
			StringFunctions.sendMessage(Constants.mc.player, combined, ChatFormatting.YELLOW);
			StringFunctions.sendMessage(Constants.mc.player, "The soundmap file has been updated.", ChatFormatting.DARK_GREEN);}
		else {
			StringFunctions.sendMessage(Constants.mc.player, "No sound events were found by using the wildcard string '" + wildcard + "', try a different query.", ChatFormatting.RED);
		}

		return 1;
	}
}