/*
 * This is the latest source code of Full Brightness Toggle.
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

package com.natamus.fullbrightnesstoggle.forge.events;

import com.natamus.fullbrightnesstoggle.data.Constants;
import com.natamus.fullbrightnesstoggle.events.ToggleEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class ForgeToggleEvent {
	@SubscribeEvent
	public void onKey(InputEvent.Key e) {
		if (e.getAction() != 1) {
			return;
		}

		if (Constants.hotkey == null) {
			return;
		}

		if (e.getKey() == Constants.hotkey.getKey().getValue()) {
			ToggleEvent.onHotkeyPress();
		}
	}
}
