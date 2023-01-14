/*
 * This is the latest source code of Omega Mute.
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

package com.natamus.omegamute.fabric.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.natamus.omegamute.cmds.CommandOmega;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.world.entity.player.Player;

public class FabricCommandOmega {
	public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		dispatcher.register(ClientCommandManager.literal("omegamute")
			.then(ClientCommandManager.literal("reload")
			.executes((command) -> {
                return CommandOmega.reload();
			}))
			.then(ClientCommandManager.literal("query")
			.executes((command) -> {
				return CommandOmega.query();
			}))
			.then(ClientCommandManager.literal("listen").requires((iCommandSender) -> iCommandSender.getEntity() instanceof Player)
			.executes((command) -> {
				return CommandOmega.listen();
			}))
			.then(ClientCommandManager.literal("mute")
			.then(ClientCommandManager.argument("string-contains", StringArgumentType.word())
			.executes((command) -> {
				return CommandOmega.mute(StringArgumentType.getString(command, "string-contains"));
			})))
			.then(ClientCommandManager.literal("cull")
			.then(ClientCommandManager.argument("cull-time", IntegerArgumentType.integer(0, 3600))
			.then(ClientCommandManager.argument("string-contains", StringArgumentType.word())
			.executes((command) -> {
				return CommandOmega.cull(StringArgumentType.getString(command, "string-contains"), IntegerArgumentType.getInteger(command, "cull-time"));
			}))))
			.then(ClientCommandManager.literal("unmute")
			.then(ClientCommandManager.argument("string-contains", StringArgumentType.word())
			.executes((command) -> {
                return CommandOmega.unmute(StringArgumentType.getString(command, "string-contains"));
			})))
		);
	}
}
