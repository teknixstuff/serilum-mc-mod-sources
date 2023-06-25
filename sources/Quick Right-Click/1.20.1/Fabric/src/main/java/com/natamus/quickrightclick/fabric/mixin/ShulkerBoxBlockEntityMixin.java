/*
 * This is the latest source code of Quick Right-Click.
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

package com.natamus.quickrightclick.fabric.mixin;

import com.natamus.collective.functions.ItemFunctions;
import com.natamus.quickrightclick.data.Constants;
import com.natamus.quickrightclick.data.Variables;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ShulkerBoxBlockEntity.class, priority = 1001)
public class ShulkerBoxBlockEntityMixin {
    @Inject(method = "stopOpen(Lnet/minecraft/world/entity/player/Player;)V", at = @At(value = "HEAD"))
    public void stopOpen(Player player, CallbackInfo ci) {
        String playerName = player.getName().getString();
        if (Variables.shulkerUsed.contains(playerName) && Variables.shulkerUsedHand.containsKey(playerName)) {
            ShulkerBoxBlockEntity shulkerBoxBlockEntity = (ShulkerBoxBlockEntity)(Object)this;
            Component shulkerEntityCustomName = shulkerBoxBlockEntity.getCustomName();
            String shulkerEntityCustomNameString = shulkerEntityCustomName.getString();
            if (!shulkerEntityCustomNameString.startsWith(Constants.INVISIBLE_CHAR)) {
                return;
            }

            Level level = shulkerBoxBlockEntity.getLevel();
            BlockPos shulkerPos = shulkerBoxBlockEntity.getBlockPos();
            BlockState blockState = shulkerBoxBlockEntity.getBlockState();
            Block block = blockState.getBlock();

            if (!(block instanceof ShulkerBoxBlock)) {
                return;
            }

            ShulkerBoxBlock shulkerBoxBlock = (ShulkerBoxBlock)block;
            ItemStack shulkerStack = shulkerBoxBlock.getCloneItemStack(level, shulkerPos, blockState);

            Style nameStyle = shulkerEntityCustomName.getStyle();
            shulkerStack.setHoverName(Component.literal(shulkerEntityCustomNameString.replace(Constants.INVISIBLE_CHAR, "")).withStyle(nameStyle));

            InteractionHand hand = Variables.shulkerUsedHand.get(playerName);
            if (player.getItemInHand(hand).isEmpty()) {
                player.setItemInHand(hand, shulkerStack);
            }
            else {
                ItemFunctions.giveOrDropItemStack(player, shulkerStack);
            }

            level.removeBlockEntity(shulkerPos);
            level.setBlock(shulkerPos, Blocks.AIR.defaultBlockState(), 3);

            Variables.shulkerUsed.remove(playerName);
            Variables.shulkerUsedHand.remove(playerName);
        }
    }
}