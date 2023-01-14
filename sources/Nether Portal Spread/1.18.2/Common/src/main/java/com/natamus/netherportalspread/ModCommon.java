/*
 * This is the latest source code of Nether Portal Spread.
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

package com.natamus.netherportalspread;

import com.natamus.netherportalspread.config.ConfigHandler;
import com.natamus.netherportalspread.util.Reference;
import com.natamus.netherportalspread.util.Util;

import java.io.IOException;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		try {
			Util.loadSpreadBlocks();
		} catch (IOException ex) {
			System.out.println("[" + Reference.NAME + "] Something went wrong loading the nether spread block config.");
		}
	}
}