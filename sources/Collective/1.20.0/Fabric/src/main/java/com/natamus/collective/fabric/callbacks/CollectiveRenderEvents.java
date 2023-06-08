/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.fabric.callbacks;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class CollectiveRenderEvents {
	private CollectiveRenderEvents() { }

	public static final Event<Render_Specific_Hand> RENDER_SPECIFIC_HAND = EventFactory.createArrayBacked(Render_Specific_Hand.class, callbacks -> (interactionHand, poseStack, itemStack) -> {
		for (Render_Specific_Hand callback : callbacks) {
			if (!callback.onRenderSpecificHand(interactionHand, poseStack, itemStack)) {
				return false;
			}
		}

		return true;
	});

	@FunctionalInterface
	public interface Render_Specific_Hand {
		boolean onRenderSpecificHand(InteractionHand interactionHand, PoseStack poseStack, ItemStack itemStack);
	}
}
