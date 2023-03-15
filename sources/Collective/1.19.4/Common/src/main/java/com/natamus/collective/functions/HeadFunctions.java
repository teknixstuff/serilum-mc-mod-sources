/*
 * This is the latest source code of Collective.
 * Minecraft version: 1.19.4.
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

package com.natamus.collective.functions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.natamus.collective.data.GlobalVariables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;

public class HeadFunctions {
	public static ItemStack getPlayerHead(String playername, Integer amount) {
		String nameIdJsonString = DataFunctions.readStringFromURL(GlobalVariables.playerdataurl + playername.toLowerCase());
		if (nameIdJsonString.equals("")) {
			return null;
		}

		try {
			Map<String, String> nameIdJson = new Gson().fromJson(nameIdJsonString, new TypeToken<HashMap<String, String>>() {}.getType());

			String headName = nameIdJson.get("name");
			String headUUID = nameIdJson.get("id");

			String profileJsonString = DataFunctions.readStringFromURL(GlobalVariables.skindataurl + headUUID);
			if (profileJsonString.equals("")) {
				return null;
			}

			String[] rawValue = profileJsonString.replaceAll(" ", "").split("value\":\"");

			String texturevalue = rawValue[1].split("\"")[0];
			String d = new String(Base64.getDecoder().decode((texturevalue.getBytes())));

			String texture = Base64.getEncoder().encodeToString((("{\"textures\"" + d.split("\"textures\"")[1]).getBytes()));
			String oldid = new UUID(texture.hashCode(), texture.hashCode()).toString();

			return HeadFunctions.getTexturedHead(headName + "'s Head", texture, oldid, 1);
		}
		catch (ArrayIndexOutOfBoundsException ignored) { }

		return null;
	}
	
	public static ItemStack getTexturedHead(String headname, String texture, String oldid, Integer amount) {
		ItemStack texturedhead = new ItemStack(Items.PLAYER_HEAD, amount);
		
		List<Integer> intarray = UUIDFunctions.oldIdToIntArray(oldid);
		
		CompoundTag skullOwner = new CompoundTag();
		skullOwner.putIntArray("Id", intarray);
		
		CompoundTag properties = new CompoundTag();
		ListTag textures = new ListTag();
		CompoundTag tex = new CompoundTag();
		tex.putString("Value", texture);
		textures.add(tex);

		properties.put("textures", textures);
		skullOwner.put("Properties", properties);
		texturedhead.addTagElement("SkullOwner", skullOwner);
		
		Component tcname = Component.literal(headname);
		texturedhead.setHoverName(tcname);		
		return texturedhead;
	}
	
	public static boolean hasStandardHead(String mobname) {
        return mobname.equals("creeper") || mobname.equals("zombie") || mobname.equals("skeleton");
    }
}
