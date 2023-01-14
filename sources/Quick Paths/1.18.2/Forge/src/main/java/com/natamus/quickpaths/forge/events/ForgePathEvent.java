/*
 * This is the latest source code of Quick Paths.
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

package com.natamus.quickpaths.forge.events;

import com.natamus.quickpaths.events.PathEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgePathEvent {
	@SubscribeEvent
	public void onServerTick(ServerTickEvent e) {
		if (!e.phase.equals(Phase.END)) {
			return;
		}

		PathEvent.onServerTick(null);
	}
	
	@SubscribeEvent
	public void onRightClickGrass(PlayerInteractEvent.RightClickBlock e) {
		if (!PathEvent.onRightClickGrass(e.getWorld(), e.getPlayer(), e.getHand(), e.getPos(), e.getHitVec())) {
			e.setCanceled(true);
		}
	}
}
