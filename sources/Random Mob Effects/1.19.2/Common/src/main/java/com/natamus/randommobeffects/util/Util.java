/*
 * This is the latest source code of Random Mob Effects.
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

package com.natamus.randommobeffects.util;

import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.DataFunctions;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
	private static final List<String> defaultblacklist = new ArrayList<String>(Arrays.asList("minecraft:instant_health", "minecraft:instant_damage", "minecraft:invisibility", "minecraft:wither", "minecraft:glowing", "minecraft:levitation", "minecraft:bad_omen", "minecraft:hero_of_the_village"));
	private static final List<MobEffect> potioneffects = new ArrayList<MobEffect>();
	
	private static final String dirpath = DataFunctions.getConfigDirectory() + File.separator + "randommobeffects";
	private static final File dir = new File(dirpath);
	private static final File file = new File(dirpath + File.separator + "blacklist.txt");
	
	public static boolean setupPotionEffects() throws IOException {
		List<String> blacklist = new ArrayList<String>();
		
		PrintWriter writer = null;
		if (!dir.isDirectory() || !file.isFile()) {
			dir.mkdirs();
			writer = new PrintWriter(dirpath + File.separator + "blacklist.txt", StandardCharsets.UTF_8);
		}
		else {
			String blcontent = new String(Files.readAllBytes(Paths.get(dirpath + File.separator + "blacklist.txt", new String[0])));
			for (String effectrl : blcontent.split("," )) {
				String name = effectrl.replace("\n", "").trim();
				if (name.startsWith("!")) {
					blacklist.add(name.replace("!", ""));
				}
			}
		}
		
		for (MobEffect effect : Registry.MOB_EFFECT) {
			ResourceLocation rl = Registry.MOB_EFFECT.getKey(effect);
			if (rl == null) {
				continue;
			}
			
			String name = rl.toString();
			
			if (writer != null) {
				String towrite = name + ",";
				if (defaultblacklist.contains(name)) {
					blacklist.add(name);
					towrite = "!" + towrite;
				}
				
				writer.println(towrite);
			}
			
			if (!blacklist.contains(name)) {
				potioneffects.add(effect);
			}
		}
		
		if (writer != null) {
			writer.close();
		}

		return potioneffects.size() > 0;
	}
	
	public static MobEffect getRandomEffect() {
		int i = GlobalVariables.random.nextInt(potioneffects.size());
		return potioneffects.get(i);
	}
}
