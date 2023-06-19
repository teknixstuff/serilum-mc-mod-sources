/*
 * This is the latest source code of Pumpkillager's Quest.
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

package com.natamus.pumpkillagersquest.events;

import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.EntityFunctions;
import com.natamus.collective.functions.MessageFunctions;
import com.natamus.pumpkillagersquest.config.ConfigHandler;
import com.natamus.pumpkillagersquest.pumpkillager.Manage;
import com.natamus.pumpkillagersquest.util.Data;
import com.natamus.pumpkillagersquest.util.QuestData;
import com.natamus.pumpkillagersquest.util.Reference;
import com.natamus.pumpkillagersquest.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PlayerHeadItem;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Set;

public class PkEntityEvents {
    public static boolean onEntityJoin(Entity entity, ServerLevel level) {
        if (entity.getTags().contains(Reference.MOD_ID + ".removed")) {
            entity.remove(Entity.RemovalReason.DISCARDED);
            return false;
        }

        Set<String> entityTags = entity.getTags();

        if (entity instanceof Pig) {
            if (entityTags.contains(Reference.MOD_ID + ".pumpkincheck") || level.isClientSide) {
                return true;
            }
            entity.getTags().add(Reference.MOD_ID + ".pumpkincheck");

            if (GlobalVariables.random.nextDouble() <= ConfigHandler.chanceForPumpkinBlockToSpawnOnPigSpawn) {
                if (!Data.spawnPumpkin.containsKey(level)) {
                    Data.spawnPumpkin.put(level, new ArrayList<LivingEntity>());
                }
                Data.spawnPumpkin.get(level).add((LivingEntity)entity);
            }
        }
        else if (entity instanceof Husk) {
            Husk husk = (Husk)entity;
            if (husk.hasCustomName()) {
                if (entity.getCustomName().getString().equals("The Ghost Knight")) {
                    if (husk.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof PlayerHeadItem) {
                        EntityFunctions.getTargetSelector(husk).removeAllGoals(goal -> true);
                        EntityFunctions.getTargetSelector(husk).addGoal(2, new NearestAttackableTargetGoal<>(husk, Player.class, true));
                    }
                }
            }
        }
        else if (entityTags.contains(Reference.MOD_ID + ".summoned")) {
            if (entityTags.contains(Reference.MOD_ID + ".justadded")) {
                entity.getTags().remove(Reference.MOD_ID + ".justadded");
                return true;
            }
            entity.remove(Entity.RemovalReason.DISCARDED);
            return false;
        }
        else if (Util.isPumpkillager(entity)) {
            Villager pumpkillager = (Villager)entity;

            if (entityTags.contains(Reference.MOD_ID + ".justadded")) {
                entity.getTags().remove(Reference.MOD_ID + ".justadded");

                if (!Data.allPumpkillagers.get(level).contains(pumpkillager)) {
                    Data.allPumpkillagers.get(level).add(pumpkillager);
                }
                return true;
            }

            Manage.resetPlacedBlocks(level, pumpkillager.blockPosition());
            pumpkillager.remove(Entity.RemovalReason.DISCARDED);
            return false;
        }
        else if (Util.isPrisoner(entity)) {
            Villager prisoner = (Villager)entity;

            if (entityTags.contains(Reference.MOD_ID + ".justadded") || entityTags.contains(Reference.MOD_ID + ".persistent")) {
                entity.getTags().remove(Reference.MOD_ID + ".justadded");

                if (!Data.allPrisoners.get(level).contains(prisoner)) {
                    Data.allPrisoners.get(level).add(prisoner);
                }
                return true;
            }

            prisoner.remove(Entity.RemovalReason.DISCARDED);
            return false;
        }
        return true;
    }

    public static void onEntityLeave(Entity entity, Level level) {
        if (entity instanceof Villager) {
            if (Util.isPumpkillager(entity)) {
                Villager pumpkillager = (Villager)entity;

                if (!level.isClientSide) {
                    if (entity.getTags().contains(Reference.MOD_ID + ".finalform")) {
                        ServerLevel serverLevel = (ServerLevel)level;
                        for (Entity serverEntity : serverLevel.getAllEntities()) {
                            if (serverEntity.getTags().contains(Reference.MOD_ID + ".summoned")) {
                                if (serverEntity instanceof LivingEntity) {
                                    Manage.yeetLivingEntityIntoSky(level, (LivingEntity)serverEntity);
                                }
                            }
                        }
                    }
                }

                if (Data.allPumpkillagers.containsKey(level)) {
                    Data.allPumpkillagers.get(level).remove(pumpkillager);
                }

                Data.pumpkillagerPositions.remove(pumpkillager);
                Data.pumpkillagerPlayerTarget.remove(pumpkillager);
                Data.pumpkillagerBossEvents.remove(pumpkillager);
            }
            else if (Util.isPrisoner(entity)) {
                if (Data.allPrisoners.containsKey(level)) {
                    Data.allPrisoners.get(level).remove((Villager) entity);
                }
            }
        }
    }

    public static void onItemPickup(Level level, Player player, ItemStack itemStack) {
        if (level.isClientSide) {
            return;
        }

        if (!itemStack.getItem().equals(Items.PAPER)) {
            return;
        }

        String itemName = itemStack.getHoverName().getString();
        if (!itemName.contains(QuestData.questOneCoordinatePaperPrefix)) {
            return;
        }

        if (itemName.endsWith(".")) {
            MessageFunctions.sendMessage(player, "As you pick up the piece of paper, you see coordinates to a prisoner camp written on it. You feel bad about unleashing the Pumpkillager back into the world. Maybe a prisoner can help you stop him?", ChatFormatting.GRAY, true);
            itemStack.setHoverName(Component.translatable(itemName.replace(".", "")));
        }

        MessageFunctions.sendMessage(player, itemName, ChatFormatting.GRAY, true);
    }

    public static boolean onEntityHitByLightning(Level level, Entity entity, LightningBolt lightningBolt) {
        return !Util.isPumpkillager(entity) && !Util.isPrisoner(entity);
    }
}
