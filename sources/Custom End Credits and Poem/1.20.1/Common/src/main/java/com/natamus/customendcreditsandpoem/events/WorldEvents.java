/*
 * This is the latest source code of Custom End Credits and Poem.
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

package com.natamus.customendcreditsandpoem.events;

import com.natamus.customendcreditsandpoem.util.Reference;
import com.natamus.customendcreditsandpoem.util.Util;
import net.minecraft.world.level.Level;

import java.io.IOException;

public class WorldEvents {
	public static void onWorldLoad(Level level) {
		if (level.isClientSide) {
			try {
				Util.createDefaultFiles();
			} catch (IOException ex) {
				System.out.println("[" + Reference.NAME + "] Something went wrong while trying to generate the default files.");
				ex.printStackTrace();
			}
		}
	}
}