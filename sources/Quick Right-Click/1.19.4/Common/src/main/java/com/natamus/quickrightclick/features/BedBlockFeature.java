/*
 * This is the latest source code of Quick Right-Click.
 * Minecraft version: 1.19.4.
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

package com.natamus.quickrightclick.features;

import com.natamus.collective.functions.MessageFunctions;
import com.natamus.quickrightclick.config.ConfigHandler;
import com.natamus.quickrightclick.data.Variables;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BedPart;

import java.util.concurrent.atomic.AtomicBoolean;

public class BedBlockFeature {
    public static boolean init(Level level, Player player, BlockPos playerPos, ItemStack handStack, InteractionHand hand, Block block) {
        if (!ConfigHandler.enableQuickBeds) {
            return false;
        }

        BedBlock bedBlock = (BedBlock)block;
        if (!BedBlock.canSetSpawn(level)) {
            return false;
        }

        BlockPos bedPos = playerPos.immutable();
        if (!level.getBlockState(bedPos).getBlock().equals(Blocks.AIR)) {
            bedPos = bedPos.above().immutable();

            if (!level.getBlockState(bedPos).getBlock().equals(Blocks.AIR) || !level.getBlockState(bedPos.north()).getBlock().equals(Blocks.AIR)) {
                if (level.isClientSide) {
                    MessageFunctions.sendMessage(player, "Unable to sleep, location obstructed.", ChatFormatting.DARK_GRAY);
                }
                return false;
            }
        }

        level.setBlock(bedPos.north(), block.defaultBlockState().setValue(BedBlock.PART, BedPart.HEAD), 3);
        level.setBlock(bedPos, block.defaultBlockState().setValue(BedBlock.PART, BedPart.FOOT), 3);

        if (!level.isClientSide) {
            String playerName = player.getName().getString();
            Variables.bedIsSleeping.add(playerName);

            AtomicBoolean canSleep = new AtomicBoolean(true);
            player.startSleepInBed(bedPos.north()).ifLeft((bedSleepingProblem) -> {
                if (bedSleepingProblem.getMessage() != null) {
                    player.displayClientMessage(bedSleepingProblem.getMessage(), true);
                    canSleep.set(false);
                }
            });

            if (!canSleep.get()) {
                level.setBlock(bedPos.north(), Blocks.AIR.defaultBlockState(), 3);
                level.setBlock(bedPos, Blocks.AIR.defaultBlockState(), 3);
                Variables.bedIsSleeping.remove(playerName);
                return false;
            }

            if (!player.isCreative()) {
                handStack.shrink(1);
            }

            Variables.bedUsedHand.put(playerName, hand);
        }
        return true;
    }
}
