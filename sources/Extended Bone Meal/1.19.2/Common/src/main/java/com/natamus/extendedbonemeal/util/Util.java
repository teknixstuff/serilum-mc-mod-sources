/*
 * This is the latest source code of Extended Bone Meal.
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

package com.natamus.extendedbonemeal.util;

import com.natamus.collective.services.Services;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Util {
    public static boolean isBoneMeal(ItemStack itemStack) {
        Item item = itemStack.getItem();

        if (item.equals(Items.BONE_MEAL)) {
			return true;
		}

        if (Services.MODLOADER.isModLoaded("kelpfertilizer")) {
            return item.equals(Items.KELP);
        }

        return false;
    }
}
