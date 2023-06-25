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
import com.natamus.quickrightclick.data.Constants;
import com.natamus.quickrightclick.data.Variables;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ShulkerBoxFeature {
    public static boolean init(Level level, Player player, BlockPos playerPos, ItemStack handStack, InteractionHand hand, Block block) {
        if (!ConfigHandler.enableQuickShulkerBoxes) {
            return false;
        }

        ShulkerBoxBlock shulkerBoxBlock = (ShulkerBoxBlock)block;

        Direction playerDirection = player.getDirection();
        BlockPos shulkerPos = playerPos.above(2).relative(playerDirection, 0).immutable();
        if (!level.getBlockState(shulkerPos).getBlock().equals(Blocks.AIR)) {
            if (level.isClientSide) {
                MessageFunctions.sendMessage(player, "Unable to open shulker box, location obstructed.", ChatFormatting.DARK_GRAY);
            }
            return false;
        }

        BlockState blockState = block.defaultBlockState().setValue(ShulkerBoxBlock.FACING, playerDirection);

        ShulkerBoxBlockEntity shulkerBoxBlockEntity = new ShulkerBoxBlockEntity(shulkerBoxBlock.getColor(), shulkerPos, blockState);
        shulkerBoxBlockEntity.setLevel(level);

        level.setBlock(shulkerPos, blockState, 3);
        level.setBlockEntity(shulkerBoxBlockEntity);

        CompoundTag compoundTag = BlockItem.getBlockEntityData(handStack);
        if (compoundTag != null) {
            if (compoundTag.contains("Items", 9)) {
                shulkerBoxBlockEntity.loadFromTag(compoundTag);
            }
        }

        shulkerBoxBlockEntity.setCustomName(Component.literal(Constants.INVISIBLE_CHAR).append(handStack.getHoverName()));

        handStack.shrink(1);

        String playerName = player.getName().getString();
        Variables.shulkerUsed.add(playerName);
        Variables.shulkerUsedHand.put(playerName, hand);

        player.openMenu(shulkerBoxBlockEntity);
        return true;
    }
}
