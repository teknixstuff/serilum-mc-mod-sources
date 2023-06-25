/*
 * This is the latest source code of Quick Right-Click.
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

package com.natamus.quickrightclick.data;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class Constants {
    public static final Component CRAFTING_TABLE_TITLE = new TranslatableComponent("container.crafting");
    public static final Component CARTOGRAPHY_TABLE_TITLE = new TranslatableComponent("container.cartography_table");
    public static final Component ENDER_CHEST_TITLE = new TranslatableComponent("container.enderchest");
    public static final Component GRINDSTONE_TITLE = new TranslatableComponent("container.grindstone_title");
    public static final Component LOOM_TITLE = new TranslatableComponent("container.loom");
    public static final Component SHULKER_BOX_TITLE = new TranslatableComponent("container.shulkerBox");
    public static final Component SMITHING_TABLE_TITLE = new TranslatableComponent("block.minecraft.smithing_table");
    public static final Component STONECUTTER_TITLE = new TranslatableComponent("container.stonecutter");
    public static final String INVISIBLE_CHAR = "⠀ ";
}
