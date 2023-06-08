/*
 * This is the latest source code of Hidden Recipe Book.
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

package com.natamus.hiddenrecipebook;

import com.mojang.blaze3d.platform.InputConstants;
import com.natamus.hiddenrecipebook.data.Variables;
import com.natamus.hiddenrecipebook.events.BookGUIEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() { 
		registerEvents();
	}
	
	private void registerEvents() {
		Variables.hotkey = KeyBindingHelper.registerKeyBinding(new KeyMapping("hiddenrecipebook.key.togglebook", InputConstants.Type.KEYSYM, 258, "key.categories.misc"));

		ScreenEvents.AFTER_INIT.register((Minecraft client, Screen screen, int scaledWidth, int scaledHeight) -> {
			BookGUIEvent.onGUIScreen(client, screen, scaledWidth, scaledHeight);

			if (screen != null) {
				String guiname = screen.getTitle().getString().toLowerCase();
				if (guiname.equalsIgnoreCase("crafting") || guiname.equalsIgnoreCase("furnace")) {
					ScreenKeyboardEvents.afterKeyPress(screen).register((Screen eventScreen, int key, int scancode, int modifiers) -> {
						if (Variables.hotkey.matches(key, scancode)) {
							BookGUIEvent.onHotkeyPress();
						}
					});
				}
			}
		});
	}
}
