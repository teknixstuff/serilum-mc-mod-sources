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
import com.natamus.pumpkillagersquest.data.Constants;
import com.natamus.pumpkillagersquest.util.Util;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

public class PkSoundEvents {
    public static boolean onSoundEvent(SoundEngine soundEngine, SoundInstance soundInstance) {
        String soundString = soundInstance.getLocation().getPath();
        if (soundString.contains("entity.villager")) {
            BlockPos pos = BlockPos.containing(soundInstance.getX(), soundInstance.getY(), soundInstance.getZ());

            for (Entity entity : Constants.mc.level.getEntities(null, new AABB(pos.getX()-1, pos.getY()-1, pos.getZ()-1, pos.getX()+1, pos.getY()+1, pos.getZ()+1))) {
                if (Util.isPumpkillager(entity)) {
                    Villager pumpkillager = (Villager)entity;
                    ItemStack headStack = pumpkillager.getItemBySlot(EquipmentSlot.HEAD);
                    String headName = headStack.getHoverName().getString();

                    SoundEvent soundEvent = SoundEvents.ENDER_DRAGON_GROWL;
                    SoundSource soundSource = SoundSource.HOSTILE;
                    if (headName.equals("Jack o'Lantern")) {
                        soundEvent = SoundEvents.EVOKER_AMBIENT;
                        soundSource = SoundSource.NEUTRAL;
                    }

                    SimpleSoundInstance simplesoundinstance = new SimpleSoundInstance(soundEvent, soundSource, 1.0F, (GlobalVariables.randomSource.nextFloat() - GlobalVariables.randomSource.nextFloat()) * 0.2F + 1.0F, GlobalVariables.randomSource, pos.getX(), pos.getY(), pos.getZ());
                    soundEngine.play(simplesoundinstance);
                    return false;
                }
            }
        }
        return true;
    }
}