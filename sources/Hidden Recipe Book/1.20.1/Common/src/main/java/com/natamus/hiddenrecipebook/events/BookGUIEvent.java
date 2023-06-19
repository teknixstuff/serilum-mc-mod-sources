/*
 * This is the latest source code of Hidden Recipe Book.
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

package com.natamus.hiddenrecipebook.events;

import com.natamus.collective.functions.StringFunctions;
import com.natamus.hiddenrecipebook.config.ConfigHandler;
import com.natamus.hiddenrecipebook.data.Variables;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BookGUIEvent {
    private static Date lastpress = null;
    private static Screen lastScreen = null;
    
	private static final HashMap<String, ImageButton> recipe_buttons = new HashMap<String, ImageButton>();
	private static boolean showbook = !ConfigHandler.shouldHideRecipeBook;

    public static void onGUIScreen(Minecraft client, Screen screen, int scaledWidth, int scaledHeight) {
    	String guiname = screen.getTitle().getString().toLowerCase();
    	if (guiname.equals("crafting") || guiname.equals("furnace")) {
    		lastScreen = screen;

    		@SuppressWarnings("unchecked")
			List<GuiEventListener> widgets = (List<GuiEventListener>)screen.children();
    		
    		ImageButton imagebutton = null;
    		for (GuiEventListener widget : widgets) {
    			if (widget instanceof ImageButton) {
    				imagebutton = (ImageButton)widget;
    				try {
						int height = imagebutton.getHeight();
						int width = imagebutton.getWidth();
    					if (width == 20 && height == 18) {
    	    				recipe_buttons.put(guiname, imagebutton);
    	    				break;
    					}
    				} catch (Exception ex) {
						ex.printStackTrace();
					}
    			}
    		}
    		
    		if (showbook) {
    			if (imagebutton == null) {
					if (recipe_buttons.containsKey(guiname)) {
						ImageButton recipe_button = recipe_buttons.get(guiname);
						if (!widgets.contains(recipe_button)) {
							 widgets.add(recipe_button);
						}
						
						recipe_button.visible = showbook;
					}
					return;
    			}
    			
    			imagebutton.visible = showbook;
    			return;
    		}
    		
    		if (imagebutton != null) {
    			imagebutton.visible = showbook;
    		}
    	}
    }

	public static void onHotkeyPress() {
		if (!ConfigHandler.allowRecipeBookToggleHotkey) {
			return;
		}

		if (Variables.mc.screen instanceof ChatScreen) {
			return;
		}

		if (Variables.mc.player != null) {
			Date now = new Date();
			if (lastpress != null) {
				long ms = (now.getTime()-lastpress.getTime());
				if (ms < 1000) {
					return;
				}
			}

			lastpress = now;

			String message;
			if (showbook) {
				showbook = false;
				message = "Now hiding recipe book button.";
			}
			else {
				showbook = true;
				message = "Now showing recipe book button.";
			}

			if (lastScreen != null) {
				String guiname = lastScreen.getTitle().getString().toLowerCase();
				if (recipe_buttons.containsKey(guiname)) {
					ImageButton button = recipe_buttons.get(guiname);
					button.visible = showbook;
				}
			}

			if (ConfigHandler.showMessageOnRecipeBookToggle) {
				StringFunctions.sendMessage(Variables.mc.player, message, ChatFormatting.DARK_GREEN);
			}
		}
	}
}
