/*
 * This is the latest source code of Areas.
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

package com.natamus.areas.functions;

import com.natamus.collective.functions.NumberFunctions;
import com.natamus.collective.functions.SignFunctions;
import net.minecraft.world.level.block.entity.SignBlockEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZoneFunctions {
    public static final List<String> zonePrefixes = new ArrayList<String>(Arrays.asList("[na]", "[area]", "[region]", "[zone]"));

	public static boolean hasZonePrefix(String line) {
		for (String prefix : zonePrefixes) {
			if (line.toLowerCase().startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasZonePrefix(SignBlockEntity signentity) {
		int i = -1;
		for (String line : SignFunctions.getSignText(signentity)) {
			i += 1;

			if (i == 0 && hasZonePrefix(line)) {
				return true;
			}
			break;
		}

		return false;
	}

	public static Integer getZonePrefixgetRadius(String line) {
		for (String prefix : zonePrefixes) {
			if (line.startsWith(prefix)) {
				String[] linespl = line.split("]");
				if (linespl.length < 2) {
					return -1;
				}

				String rest = linespl[1].trim();
				if (NumberFunctions.isNumeric(rest)) {
					return Integer.parseInt(rest);
				}
			}
		}

		return -1;
	}

	public static String getZoneRGB(String line) {
		String prefix = "[rgb]";
		if (line.startsWith(prefix)) {
			String[] linespl = line.split("]");
			if (linespl.length < 2) {
				return "";
			}

			String rest = linespl[1].replace(" ", "");
			String[] restspl = rest.split(",");
			if (restspl.length != 3) {
				return "";
			}

			for (String value : restspl) {
				if (!NumberFunctions.isNumeric(value)) {
					return "";
				}
			}
			return rest;
		}
		return "";
	}
}
