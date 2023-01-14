/*
 * This is the latest source code of Hand Over Your Items.
 * Minecraft version: 1.18.2.
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

package com.natamus.handoveryouritems.forge.events;

import com.natamus.handoveryouritems.events.HandOverEvent;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeHandOverEvent {
	@SubscribeEvent
	public void onPlayerClick(PlayerInteractEvent.EntityInteract e) {
		if (HandOverEvent.onPlayerClick(e.getPlayer(), e.getWorld(), e.getHand(), e.getTarget(), null).equals(InteractionResult.FAIL)) {
			e.setCanceled(true);
			e.setResult(Result.DENY);
		}
	}
}
