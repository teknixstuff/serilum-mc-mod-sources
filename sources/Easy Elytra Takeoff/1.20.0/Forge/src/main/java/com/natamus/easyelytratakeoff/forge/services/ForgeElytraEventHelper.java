/*
 * This is the latest source code of Easy Elytra Takeoff.
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

package com.natamus.easyelytratakeoff.forge.services;

import com.natamus.easyelytratakeoff.services.helpers.ElytraEventHelper;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class ForgeElytraEventHelper implements ElytraEventHelper {
    @Override
    public boolean isWearingAnElytra(Player player) {
        boolean foundelytra = false;
		for (ItemStack nis : player.getArmorSlots()) {
			if (nis.getItem() instanceof ElytraItem) {
				foundelytra = true;
				break;
			}
		}

		if (!foundelytra) {
			Collection<AttributeInstance> atrb = player.getAttributes().getSyncableAttributes();
			for (AttributeInstance ai : atrb) {
				for (AttributeModifier m : ai.getModifiers()) {
					String name = m.getName().toLowerCase();
					if (name.equals("flight modifier") || name.equals("elytra curio modifier")) {
						if (m.getAmount() >= 1.0) {
							foundelytra = true;
							break;
						}
					}
				}
				if (foundelytra) {
					break;
				}
			}
		}
        return foundelytra;
    }
}