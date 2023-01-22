/*
 * This is the latest source code of Creative Block Replacer.
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

package com.natamus.creativeblockreplacer.events;

import com.natamus.collective.functions.StringFunctions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.HashMap;
import java.util.Map;

public class ReplaceEvent {
	private static final Map<String, Boolean> replacingplayers = new HashMap<String, Boolean>();
	private static final Map<String, Integer> sneaktotal = new HashMap<String, Integer>();
	private static final Map<String, Integer> sneakcurrent = new HashMap<String, Integer>();
	private static final Map<String, BlockPos> lastpos = new HashMap<String, BlockPos>();

	public static void onPlayerTick(ServerLevel level, ServerPlayer player) {
		if (!player.isCreative()) {
			return;
		}
		
		String playername = player.getName().getString();
		BlockPos playerpos = player.blockPosition();
		if (lastpos.containsKey(playername)) {
			if (sneaktotal.containsKey(playername)) {
				if (!lastpos.get(playername).equals(playerpos)) {
					sneaktotal.remove(playername);
					sneakcurrent.remove(playername);
				}
			}
		}
		lastpos.put(playername, playerpos);
		
		if (player.isShiftKeyDown()) {
			if (sneakcurrent.containsKey(playername)) {
				return;
			}
			int totalsneak = 0;
			if (sneaktotal.containsKey(playername)) {
				totalsneak = sneaktotal.get(playername);
			}
			sneaktotal.put(playername, totalsneak+1);
			sneakcurrent.put(playername, totalsneak+1);
			return;
		}
		
		if (!sneakcurrent.containsKey(playername)) {
			return;
		}
		
		int current = sneakcurrent.get(playername);
		if (current > 2) {
			sneaktotal.put(playername, 0);
			sneakcurrent.remove(playername);
			
			if (replacingplayers.containsKey(playername)) {
				boolean isreplacing = replacingplayers.get(playername);
				if (isreplacing) {
					replacingplayers.put(playername, false);
					StringFunctions.sendMessage(player, "Replacing block mode disabled.", ChatFormatting.YELLOW);
					return;
				}
			}
			
			replacingplayers.put(playername, true);
			StringFunctions.sendMessage(player, "Replacing block mode enabled", ChatFormatting.YELLOW);
			return;
		}
		sneakcurrent.remove(playername);
	}

	public static boolean onBlockClick(Level level, Player player, InteractionHand hand, BlockPos pos, BlockHitResult hitVec) {
		if (!hand.equals(InteractionHand.MAIN_HAND)) {
			return true;
		}

		if (!player.isCreative()) {
			return true;
		}

		String playername = player.getName().getString();
		if (!replacingplayers.containsKey(playername)) {
			return true;
		}
		
		boolean isreplacing = replacingplayers.get(playername);
		if (!isreplacing) {
			return true;
		}

		ItemStack itemStack = player.getItemInHand(hand);

		Block toblock = Block.byItem(itemStack.getItem());
		BlockState tostate = toblock.defaultBlockState();
		
		level.setBlockAndUpdate(pos, tostate);
		return false;
	}
}
