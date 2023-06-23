/*
 * This is the latest source code of Persistent Inventory Search.
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

package com.natamus.persistentinventorysearch.forge.mixin;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CreativeModeInventoryScreen.class, priority = 1001)
public abstract class CreativeModeInventoryScreenMixin {
	@Shadow private static int selectedTab;
	@Shadow private EditBox searchBox;
	@Shadow protected abstract void refreshSearchResults();

	private static String searchQuery = "";

	@Inject(method = "init()V", at = @At(value = "TAIL"))
	private void CreativeModeInventoryScreen_init(CallbackInfo ci) {
		if (CreativeModeTab.TABS[selectedTab].hasSearchBar()) {
			if (!searchQuery.equals("")) {
				searchBox.setValue(searchQuery);
				refreshSearchResults();
			}
		}
	}

	@Inject(method = "keyReleased(III)Z", at = @At(value = "HEAD"))
	public void CreativeModeInventoryScreen_keyReleased(int a, int b, int c, CallbackInfoReturnable<Boolean> cir) {
		if (searchBox.isFocused() && searchBox.isVisible()) {
			searchQuery = searchBox.getValue();
		}
	}

	@Inject(method = "selectTab(Lnet/minecraft/world/item/CreativeModeTab;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/CreativeModeInventoryScreen;refreshSearchResults()V"))
	private void selectTab(CreativeModeTab p_98561_, CallbackInfo ci) {
		if (CreativeModeTab.TABS[selectedTab].hasSearchBar()) {
			if (!searchQuery.equals("")) {
				searchBox.setValue(searchQuery);
			}
		}
	}
}
