/*
 * This is the latest source code of Passive Shield.
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

package com.natamus.passiveshield.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.natamus.collective.services.Services;
import com.natamus.passiveshield.config.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class ClientEvent {
	private static final Minecraft mc = Minecraft.getInstance();

	public static boolean onHandRender(InteractionHand hand, PoseStack poseStack, ItemStack itemStack) {
		if (hand.equals(InteractionHand.OFF_HAND)) {
			if (ConfigHandler.hideShieldWhenNotInUse) {
				if (Services.TOOLFUNCTIONS.isShield(itemStack)) {
					ItemStack useItem = mc.player.getUseItem();
					return mc.player.isUsingItem() && Services.TOOLFUNCTIONS.isShield(useItem);
				}
			}
		}

		return true;
	}
}