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

package com.natamus.collective.fabric.services;

import com.natamus.collective.services.helpers.ToolFunctionsHelper;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.world.item.*;

public class FabricToolFunctionsHelper implements ToolFunctionsHelper {
    @Override
    public boolean isTool(ItemStack itemstack) {
		return isPickaxe(itemstack) || isAxe(itemstack) || isShovel(itemstack) || isHoe(itemstack) || isShears(itemstack);
    }

    @Override
	public boolean isSword(ItemStack itemStack) {
        return itemStack.getItem() instanceof SwordItem || itemStack.is(ConventionalItemTags.SWORDS);
    }

    @Override
	public boolean isShield(ItemStack itemStack) {
		return itemStack.getItem() instanceof ShieldItem || itemStack.is(ConventionalItemTags.SHIELDS);
	}

    @Override
	public boolean isPickaxe(ItemStack itemStack) {
        return itemStack.getItem() instanceof PickaxeItem || itemStack.is(ConventionalItemTags.PICKAXES);
    }

    @Override
	public boolean isAxe(ItemStack itemStack) {
        return itemStack.getItem() instanceof AxeItem || itemStack.is(ConventionalItemTags.AXES);
    }

    @Override
	public boolean isShovel(ItemStack itemStack) {
        return itemStack.getItem() instanceof ShovelItem || itemStack.is(ConventionalItemTags.SHOVELS);
    }

    @Override
	public boolean isHoe(ItemStack itemStack) {
        return itemStack.getItem() instanceof HoeItem || itemStack.is(ConventionalItemTags.HOES);
    }

    @Override
	public boolean isShears(ItemStack itemStack) {
		return itemStack.getItem() instanceof ShearsItem || itemStack.is(ConventionalItemTags.SHEARS);
	}

    @Override
    public boolean isFlintAndSteel(ItemStack itemStack) {
        return itemStack.getItem() instanceof FlintAndSteelItem;
    }
}
