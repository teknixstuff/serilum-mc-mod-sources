/*
 * This is the latest source code of Superflat World No Slimes.
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

package com.natamus.superflatworldnoslimes.forge.events;

import com.natamus.superflatworldnoslimes.events.SlimeEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeSlimeEvent {
	@SubscribeEvent
	public void onWorldJoin(EntityJoinLevelEvent e) {
		if (!SlimeEvent.onWorldJoin(e.getLevel(), e.getEntity())) {
			e.setCanceled(true);
		}
	}
}
