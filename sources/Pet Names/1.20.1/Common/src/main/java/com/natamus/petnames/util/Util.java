/*
 * This is the latest source code of Pet Names.
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

package com.natamus.petnames.util;

import com.natamus.petnames.config.ConfigHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Mule;

public class Util {
	public static boolean isNamable(Entity entity) {
		if (entity instanceof Wolf) {
			return ConfigHandler.nameWolves;
		}
		else if (entity instanceof Cat) {
			return ConfigHandler.nameCats;
		}
		else if (entity instanceof Horse) {
			return ConfigHandler.nameHorses;
		}
		else if (entity instanceof Donkey) {
			return ConfigHandler.nameDonkeys;
		}
		else if (entity instanceof Mule) {
			return ConfigHandler.nameMules;
		}
		else if (entity instanceof Llama) {
			return ConfigHandler.nameLlamas;
		}
		else {
			return false;
		}
	}
}
