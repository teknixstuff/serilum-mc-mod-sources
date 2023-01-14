/*
 * This is the latest source code of Stray Spawn.
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

package com.natamus.strayspawn;

import com.natamus.collective.objects.SAMObject;
import com.natamus.strayspawn.config.ConfigHandler;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		new SAMObject(EntityType.SKELETON, EntityType.STRAY, Items.BOW, ConfigHandler.chanceSkeletonIsStray, false, false, false);
	}
}