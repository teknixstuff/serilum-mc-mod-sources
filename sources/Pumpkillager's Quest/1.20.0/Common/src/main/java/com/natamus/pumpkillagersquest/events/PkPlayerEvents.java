/*
 * This is the latest source code of Pumpkillager's Quest.
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

package com.natamus.pumpkillagersquest.events;

import com.natamus.collective.functions.MessageFunctions;
import com.natamus.pumpkillagersquest.pumpkillager.Actions;
import com.natamus.pumpkillagersquest.pumpkillager.Conversations;
import com.natamus.pumpkillagersquest.pumpkillager.Manage;
import com.natamus.pumpkillagersquest.util.Data;
import com.natamus.pumpkillagersquest.util.QuestData;
import com.natamus.pumpkillagersquest.util.Reference;
import com.natamus.pumpkillagersquest.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class PkPlayerEvents {
    public static InteractionResult onCharacterInteract(Player player, Level level, InteractionHand hand, Entity target, EntityHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.PASS;
        }

        if (!hand.equals(InteractionHand.MAIN_HAND)) {
            return InteractionResult.PASS;
        }

        if (!(target instanceof Villager)) {
            return InteractionResult.PASS;
        }

        if (Util.isPumpkillager(target)) {
            Villager pumpkillager = (Villager)target;
            Vec3 pumpkillagerVec = Data.pumpkillagerPositions.get(pumpkillager);

            if (pumpkillager.position().equals(pumpkillagerVec)) {
                Manage.pumpkillagerMovedWrongly(level, pumpkillager, player);
            }

            if ((pumpkillager.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof WrittenBookItem)) {
                Actions.givePlayerQuestbook(level, pumpkillager, player);
            }
        }
        else if (Util.isPrisoner(target)) {
            Villager prisoner = (Villager)target;

            if (!Util.prisonerIsKnown(prisoner, player)) {
                prisoner.getBrain().removeAllBehaviors();
                prisoner.setDeltaMovement(0, 0, 0);
                prisoner.setPos(prisoner.position());
                prisoner.lookAt(EntityAnchorArgument.Anchor.EYES, player.position());
                prisoner.getTags().add(Reference.MOD_ID + ".lookingatplayer");

                Conversations.startTalking(level, prisoner, player, 5);
            }
        }
        else {
            return InteractionResult.PASS;
        }

        return InteractionResult.SUCCESS;
    }

    public static InteractionResultHolder<ItemStack> onRightClickItem(Player player, Level level, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (level.isClientSide) {
            return InteractionResultHolder.pass(itemStack);
        }

        Item handItem = itemStack.getItem();
        if (handItem.equals(Items.PAPER)) {
            String itemName = itemStack.getHoverName().getString();
            if (!itemName.contains(QuestData.questOneCoordinatePaperPrefix)) {
                return InteractionResultHolder.pass(itemStack);
            }

            MessageFunctions.sendMessage(player, itemName, ChatFormatting.GRAY);
        }
        else if (handItem instanceof WrittenBookItem) {
            String bookName = itemStack.getDisplayName().getString();
            if (bookName.contains("Pumpkillager")) {
                Conversations.addMessageWithoutPrefix(level, null, player, "You feel the book somehow whispering to you: \"Right-click me on the ground to see a glimpse of the ritual.\"", ChatFormatting.GRAY, 10);
            }
        }

        return InteractionResultHolder.pass(itemStack);
    }
}
