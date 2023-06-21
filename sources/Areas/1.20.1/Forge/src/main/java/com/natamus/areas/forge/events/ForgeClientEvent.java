/*
 * This is the latest source code of Areas.
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

package com.natamus.areas.forge.events;

import com.natamus.areas.cmds.ClientCommandAreas;
import com.natamus.areas.data.ClientConstants;
import com.natamus.areas.events.ClientEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT)
public class ForgeClientEvent {
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent e) {
		if (e.phase.equals(TickEvent.Phase.START)) {
			ClientEvent.onClientTick(ClientConstants.mc);
		}
	}

	@SubscribeEvent
	public void registerCommands(RegisterClientCommandsEvent e) {
		ClientCommandAreas.register(e.getDispatcher());
	}
}