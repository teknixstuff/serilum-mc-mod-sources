/*
 * This is the latest source code of Hidden Recipe Book.
 * Minecraft version: 1.19.2.
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

package com.natamus.hiddenrecipebook.forge.events;

import com.natamus.hiddenrecipebook.data.Variables;
import com.natamus.hiddenrecipebook.events.BookGUIEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class ForgeBookGUIEvent {
    @SubscribeEvent
    public void onGUIScreen(ScreenEvent.Init.Post e) {
		BookGUIEvent.onGUIScreen(Variables.mc, e.getScreen(), 0, 0);
    }

	@SubscribeEvent
	public void onKey(ScreenEvent.KeyPressed e) {
		if (e.getKeyCode() == Variables.hotkey.getKey().getValue()) {
			BookGUIEvent.onHotkeyPress();
		}
	}
}
