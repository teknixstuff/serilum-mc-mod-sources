/*
 * This is the latest source code of Compact Help Command.
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

package com.natamus.compacthelpcommand.cmds;

import com.google.common.collect.Iterables;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import com.natamus.collective.functions.NumberFunctions;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.compacthelpcommand.config.ConfigHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommandHelp {
	private static final SimpleCommandExceptionType ERROR_FAILED = new SimpleCommandExceptionType(Component.translatable("commands.help.failed"));

	private static final ChatFormatting commandcolour = ChatFormatting.getById(ConfigHandler.commandColour);
	private static final ChatFormatting subcommandcolour = ChatFormatting.getById(ConfigHandler.subcommandColour);

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("help")
			.executes((command) -> {
				return processHelpCommands(dispatcher, command, 1, command.getSource());
			})
			.then(Commands.argument("page", IntegerArgumentType.integer(0, 1000))
			.executes((command) -> {
				return processHelpCommands(dispatcher, command, IntegerArgumentType.getInteger(command, "page"), command.getSource());
			}))
			.then(Commands.argument("command", StringArgumentType.greedyString())
			.executes((command) -> {
				String fakecommand = StringArgumentType.getString(command, "command");

				if (NumberFunctions.isNumeric(fakecommand)) {
					return processHelpCommands(dispatcher, command, Integer.parseInt(fakecommand), command.getSource());
				}

	 			ParseResults<CommandSourceStack> parseResults = dispatcher.parse(StringArgumentType.getString(command, "command"), command.getSource());
				if (parseResults.getContext().getNodes().isEmpty()) {
					throw ERROR_FAILED.create();
				} else {
					Map<CommandNode<CommandSourceStack>, String> commandNodeMap = dispatcher.getSmartUsage((Iterables.getLast(parseResults.getContext().getNodes())).getNode(), command.getSource());

					StringFunctions.sendMessage(command.getSource(), " ", ChatFormatting.WHITE);
					for (String nextStringVar : commandNodeMap.values()) {
						String stringCommand = parseResults.getReader().getString();

						String csuffix = nextStringVar;
						if (ConfigHandler.addVerticalBarSpacing) {
							csuffix = csuffix.replace("|", " | ");
						}

						MutableComponent tc = Component.literal("");

						MutableComponent tc0 = Component.literal("/" + stringCommand + " ");
						tc0.withStyle(commandcolour);
						tc.append(tc0);

						MutableComponent tc1 = Component.literal(csuffix);
						tc1.withStyle(subcommandcolour);
						tc.append(tc1);

						command.getSource().sendSuccess(tc, false);
					}

					return commandNodeMap.size();
				}
			}))
		);
	}

	private static Integer processHelpCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandContext<CommandSourceStack> command, Integer page, CommandSourceStack commandSourceStack) {
		List<String> scmds = new ArrayList<String>();
		Map<CommandNode<CommandSourceStack>, String> map = dispatcher.getSmartUsage(dispatcher.getRoot(), command.getSource());
		for(String s : map.values()) {
			String scmd = "/" + s;
			scmds.add(scmd);
		}

		Collections.sort(scmds);

		int amountperpage = ConfigHandler.amountCommandsPerPage;
		int totalcount = scmds.size();
		int totalpages = (int)Math.ceil(totalcount / (float)amountperpage) + 1;

		if (page <= 0) {
			page = 1;
		}
		if (page > totalpages) {
			page = totalpages;
		}

		StringFunctions.sendMessage(commandSourceStack, " ", ChatFormatting.WHITE);

		for (int n = 0; n < ((amountperpage * page)); n++) {
			if (n >= ((amountperpage * page) - amountperpage)) {
				if (scmds.size() < n+1) {
					break;
				}

				String commandline = scmds.get(n);
				String[] cmdlspl = commandline.split(" ");
				String acmd = cmdlspl[0];
				String csuffix = commandline.replaceAll(acmd, "");

				if (ConfigHandler.addVerticalBarSpacing) {
					csuffix = csuffix.replace("|", " | ");
				}

				MutableComponent tc = Component.literal("");

				MutableComponent tc0 = Component.literal(acmd);
				tc0.withStyle(commandcolour);
				tc.append(tc0);

				MutableComponent tc1 = Component.literal(csuffix);
				tc1.withStyle(subcommandcolour);
				tc.append(tc1);

				commandSourceStack.sendSuccess(tc, false);
			}
		}

		StringFunctions.sendMessage(commandSourceStack, " Page " + page + " / " + totalpages + ", /help <page>", ChatFormatting.YELLOW);
		return 1;
	}
}