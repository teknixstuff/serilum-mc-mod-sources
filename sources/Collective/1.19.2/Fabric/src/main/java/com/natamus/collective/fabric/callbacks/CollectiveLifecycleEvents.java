/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.fabric.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public class CollectiveLifecycleEvents {
	private CollectiveLifecycleEvents() { }
	 
    public static final Event<Minecraft_Loaded> MINECRAFT_LOADED = EventFactory.createArrayBacked(Minecraft_Loaded.class, callbacks -> (isclient) -> {
        for (Minecraft_Loaded callback : callbacks) {
        	callback.onMinecraftLoad(isclient);
        }
    });

	public static final Event<Default_Language_Loaded> DEFAULT_LANGUAGE_LOADED = EventFactory.createArrayBacked(Default_Language_Loaded.class, callbacks -> () -> {
		for (Default_Language_Loaded callback : callbacks) {
			callback.onLanguageLoad();
		}
	});
    
	@FunctionalInterface
	public interface Minecraft_Loaded {
		 void onMinecraftLoad(boolean isclient);
	}

	public interface Default_Language_Loaded {
		void onLanguageLoad();
	}
}
