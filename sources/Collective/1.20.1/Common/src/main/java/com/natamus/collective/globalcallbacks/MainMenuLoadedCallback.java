/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.globalcallbacks;

import com.natamus.collective.implementations.event.Event;
import com.natamus.collective.implementations.event.EventFactory;

public class MainMenuLoadedCallback {
	private MainMenuLoadedCallback() { }

    public static final Event<On_Main_Menu_Loaded> MAIN_MENU_LOADED = EventFactory.createArrayBacked(On_Main_Menu_Loaded.class, callbacks -> () -> {
        for (On_Main_Menu_Loaded callback : callbacks) {
        	callback.onMainMenuLoaded();
        }
    });

	@FunctionalInterface
	public interface On_Main_Menu_Loaded {
		 void onMainMenuLoaded();
	}
}
