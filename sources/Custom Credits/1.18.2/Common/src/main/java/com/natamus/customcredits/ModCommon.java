/*
 * This is the latest source code of Custom Credits.
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

package com.natamus.customcredits;

import com.natamus.collective.globalcallbacks.MainMenuLoadedCallback;
import com.natamus.customcredits.config.ConfigHandler;
import com.natamus.customcredits.util.Reference;
import com.natamus.customcredits.util.Util;

import java.io.IOException;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		MainMenuLoadedCallback.MAIN_MENU_LOADED.register(() -> {
			try {
				Util.createDefaultFiles();
			} catch (IOException ex) {
				System.out.println("[" + Reference.NAME + "] Something went wrong while trying to generate the default files.");
				ex.printStackTrace();
			}
		});
	}
}