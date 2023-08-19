/*
 * This is the latest source code of Extended Creative Inventory.
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

package com.natamus.extendedcreativeinventory.itemgroups;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class ExtendedItemGroup extends CreativeModeTab {

	public ExtendedItemGroup(int index, String label) {
		super(index == -1 ? ((CreativeModeTab) (Object) self).TABS.length : index, label);
	}

	@Override
	public @NotNull ItemStack makeIcon() {
		return new ItemStack(Items.COMMAND_BLOCK_MINECART);
	}

}
