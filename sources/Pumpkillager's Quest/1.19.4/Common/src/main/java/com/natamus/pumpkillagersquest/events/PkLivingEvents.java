/*
 * This is the latest source code of Pumpkillager's Quest.
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

package com.natamus.pumpkillagersquest.events;

import com.mojang.datafixers.util.Pair;
import com.natamus.collective.functions.EntityFunctions;
import com.natamus.collective.functions.MessageFunctions;
import com.natamus.collective.functions.TaskFunctions;
import com.natamus.pumpkillagersquest.pumpkillager.*;
import com.natamus.pumpkillagersquest.util.Data;
import com.natamus.pumpkillagersquest.util.Reference;
import com.natamus.pumpkillagersquest.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PlayerHeadItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PkLivingEvents {
    public static float onDamagePumpkillager(Level level, Entity entity, DamageSource damageSource, float damageAmount) {
        if (entity.getTags().contains(Reference.MOD_ID + ".summoned")) {
            if (damageSource.getMsgId().equals("lightningBolt")) {
                return 0F;
            }
        }

        if (!(entity instanceof Villager) && !(entity instanceof Player)) {
            return damageAmount;
        }

        Entity sourceEntity = damageSource.getEntity();
        Entity directSourceEntity = damageSource.getDirectEntity();

        if (entity instanceof Player) {
            BlockPos ppos = entity.blockPosition();
            if (damageSource.toString().contains("explosion")) {
                if (Data.allPumpkillagers.get(level).size() > 0 || Data.allPrisoners.get(level).size() > 0) {
                    for (Entity nearbyEntity : level.getEntities(null, new AABB(ppos.getX() - 10, ppos.getY() - 10, ppos.getZ() - 10, ppos.getX() + 10, ppos.getY() + 10, ppos.getZ() + 10))) {
                        if (nearbyEntity instanceof Villager) {
                            if (Util.isPumpkillager(nearbyEntity) || Util.isPrisoner(nearbyEntity)) {
                                return 0F;
                            }
                        }
                    }
                }
            }
            return damageAmount;
        }

        if (!Util.isPumpkillager(entity)) {
            if (Util.isPrisoner(entity)) {
                if (sourceEntity instanceof LivingEntity && !(sourceEntity instanceof Player)) {
                    Manage.yeetLivingEntityIntoSky(level, (LivingEntity)sourceEntity);
                }
                return 0F;
            }
            return damageAmount;
        }

        Villager pumpkillager = (Villager)entity;
        BlockPos pos = pumpkillager.blockPosition();

        Set<String> pumpkillagerTags = pumpkillager.getTags();

        float returnDamage = damageAmount;
        if (!pumpkillagerTags.contains(Reference.MOD_ID + ".isweakened")) {
            returnDamage = 0F;
        }
        else if ((!(sourceEntity instanceof Player))) {
            returnDamage = 0F;
        }

        boolean yeet = !pumpkillagerTags.contains(Reference.MOD_ID + ".nodamage") && !pumpkillagerTags.contains(Reference.MOD_ID + ".isweakened");

        if (sourceEntity instanceof Player) {
            Player player = (Player)sourceEntity;

            if (yeet) {
                MessageFunctions.sendMessage(player, Component.literal(""));
                Conversations.addMessage(level, pumpkillager, player, "You cannot kill me, " + player.getName().getString() + ".", ChatFormatting.WHITE, 0);
                Conversations.addMessage(level, pumpkillager, player, "I will be back.", ChatFormatting.WHITE, 0);

                pumpkillager.getTags().add(Reference.MOD_ID + ".preventactions");

                Manage.initiateCharacterLeave(level, pumpkillager);
            }
            else {
                if (!pumpkillagerTags.contains(Reference.MOD_ID + ".isweakened")) {
                    if (directSourceEntity instanceof SpectralArrow) {
                        pumpkillager.addEffect(new MobEffectInstance(MobEffects.GLOWING, Integer.MAX_VALUE));
                        pumpkillager.getTags().add(Reference.MOD_ID + ".isweakened");
                        pumpkillager.getTags().remove(Reference.MOD_ID + ".nodamage");

                        Actions.startWeakenedBossEvent(level, pumpkillager, player);
                        return returnDamage;
                    }

                    if (!player.isCreative()) {
                        Conversations.addMessageWithoutPrefix(level, pumpkillager, player, "Your damage is reflected back to you!", ChatFormatting.GRAY, 0);

                        float playerDamage = damageAmount;
                        if (playerDamage > 3.0F) {
                            playerDamage = 3.0F;
                        }

                        player.hurt(level.damageSources().mobAttack(pumpkillager), playerDamage);
                    }

                    if (pumpkillagerTags.contains(Reference.MOD_ID + ".finalform")) {
                        Conversations.addEmptyMessage(level, pumpkillager, player, 0);
                        Conversations.addMessageWithoutPrefix(level, pumpkillager, player, "It doesn't look like you did any damage!", ChatFormatting.GRAY, 10);
                        Conversations.addMessageWithoutPrefix(level, pumpkillager, player, "Perhaps " + Data.prisonerNameKnown + "'s spectral arrows could weaken him?", ChatFormatting.GRAY, 20);

                        Prisoner.checkForSpectralArrows(level, pumpkillager, player);
                    }
                }
                else {
                    ServerBossEvent serverBossEvent = Data.pumpkillagerBossEvents.get(pumpkillager);

                    float currentHealth = pumpkillager.getHealth();
                    float newHealth = currentHealth-damageAmount;

                    returnDamage = 0F;
                    EntityFunctions.forceSetHealth(pumpkillager, newHealth);

                    if (serverBossEvent != null) {
                        serverBossEvent.setProgress(newHealth / Data.pumpkillagerMaxHealth);
                    }

                    if (!pumpkillager.getTags().contains(Reference.MOD_ID + ".saidouch")) {
                        Conversations.addMessage(level, pumpkillager, player, "Ouch!", ChatFormatting.GRAY, 0);
                        pumpkillager.getTags().add(Reference.MOD_ID + ".saidouch");
                    }

                    Summon.checkForNewSummon(level, pumpkillager, player, newHealth);
                }
            }
        }
        else if (sourceEntity instanceof LivingEntity) {
            if (!sourceEntity.getTags().contains(Reference.MOD_ID + ".summoned")) {
                Manage.yeetLivingEntityIntoSky(level, (LivingEntity) sourceEntity);
            }
        }

        return returnDamage;
    }

    public static boolean onLivingDeath(LivingEntity livingEntity, DamageSource damageSource, float damageAmount) {
        if (livingEntity instanceof Player) {
            if (Data.pumpkillagerPlayerTarget.size() > 0) {
                Player player = (Player)livingEntity;
                Level level = player.level;

                for (Villager pumpkillager : Data.pumpkillagerPlayerTarget.keySet()) {
                    if (!pumpkillager.getTags().contains(Reference.MOD_ID + ".finalform")) {
                        continue;
                    }

                    Player targetPlayer = Data.pumpkillagerPlayerTarget.get(pumpkillager);

                    if (targetPlayer.getName().getString().equals(player.getName().getString())) {
                        targetPlayer.getTags().add(Reference.MOD_ID + ".diedonce");
                        targetPlayer.getTags().remove(Reference.MOD_ID + ".aimforfeet");

                        Conversations.addEmptyMessage(level, pumpkillager, targetPlayer, 0);
                        Conversations.addMessage(level, pumpkillager, player, "I knew you couldn't defeat me, " + targetPlayer.getName().getString() + ".", ChatFormatting.WHITE, 1);
                        Conversations.addMessage(level, pumpkillager, player, "Bye.", ChatFormatting.WHITE, 20);

                        Manage.initiateCharacterLeave(level, pumpkillager);
                        return true;
                    }
                }
            }
        }
        else if (Util.isPumpkillager(livingEntity)) {
            Villager pumpkillager = (Villager) livingEntity;

            pumpkillager.setHealth(1000F);

            Actions.shrinkAndKillPumpkillager(pumpkillager.level, pumpkillager, Data.pumpkillagerPlayerTarget.get(pumpkillager));
            return false;
        }
        return true;
    }

    public static void onEntityItemDrop(Level level, LivingEntity livingEntity, DamageSource damageSource) {
        if (level.isClientSide) {
            return;
        }

        if (!livingEntity.hasCustomName()) {
            return;
        }

        String entityName = livingEntity.getName().getString();
        if (!entityName.contains("Ghost")) {
            return;
        }

        ItemStack headStack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        Item headItem = headStack.getItem();
        if (headItem instanceof PlayerHeadItem) {
            BlockPos epos = livingEntity.blockPosition();
            TaskFunctions.enqueueTask(level, () -> {
                List<ItemEntity> dropEntities = new ArrayList<ItemEntity>();
                for (Entity ea : level.getEntities(null, new AABB(epos.getX()-1, epos.getY()-1, epos.getZ()-1, epos.getX()+1, epos.getY()+1, epos.getZ()+1))) {
                    if (ea instanceof ItemEntity) {
                        dropEntities.add((ItemEntity)ea);
                    }
                }

                boolean foundStack = false;
                for (ItemEntity dropEntity : dropEntities) {
                    if (dropEntity.getItem().getItem().equals(headItem)) {
                        foundStack = true;
                        break;
                    }
                }

                Vec3 vec = livingEntity.position();
                if (!foundStack) {
                    level.addFreshEntity(new ItemEntity(level, vec.x, vec.y+0.5, vec.z, headStack));

                    Entity sourceEntity = damageSource.getEntity();
                    if (sourceEntity instanceof Player) {
                        Player player = (Player)sourceEntity;

                        String deathMessageString = "You'll never get away with this!";
                        if (entityName.contains("Rider")) {
                            deathMessageString = "No! This was supposed to be a new age!";
                        }

                        Data.messagesToSend.get(level).add(new Pair<Player, MutableComponent>(player, Data.addCharacterPrefix(entityName, player, Component.translatable(deathMessageString))));
                    }
                }
		    }, 0);
        }
    }
}
