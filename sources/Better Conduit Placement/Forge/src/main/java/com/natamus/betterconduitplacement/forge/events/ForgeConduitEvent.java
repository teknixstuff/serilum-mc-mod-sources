/*
 * This is the latest source code of Better Conduit Placement.
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

package com.natamus.betterconduitplacement.forge.events;

import com.natamus.betterconduitplacement.events.ConduitEvent;
import com.natamus.collective.functions.WorldFunctions;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeConduitEvent {
	@SubscribeEvent
	public void onWaterClick(PlayerInteractEvent.RightClickItem e) {
		ConduitEvent.onWaterClick(e.getEntity(), e.getLevel(), e.getHand());
	}
	
	@SubscribeEvent
	public void onConduitClick(PlayerInteractEvent.RightClickBlock e) {
		if (!ConduitEvent.onConduitClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec())) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
		if (level == null) {
			return;
		}
		
		ConduitEvent.onBlockBreak(level, e.getPlayer(), e.getPos(), e.getState(), null);
	}
}
