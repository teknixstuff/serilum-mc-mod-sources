/*
 * This is the latest source code of Easy Elytra Takeoff.
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

package com.natamus.easyelytratakeoff.fabric.services;

import com.natamus.easyelytratakeoff.services.helpers.ElytraEventHelper;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;

public class FabricElytraEventHelper implements ElytraEventHelper {
    @Override
    public boolean isWearingAnElytra(Player player) {
        boolean foundelytra = EntityElytraEvents.CUSTOM.invoker().useCustomElytra(player, false);
		if (!foundelytra) {
			for (ItemStack nis : player.getArmorSlots()) {
				if (nis.getItem() instanceof ElytraItem) {
					foundelytra = true;
					break;
				}
			}
		}
        return foundelytra;
    }
}