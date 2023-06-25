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

import net.minecraft.world.InteractionHand;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Variables {
    // Quick Bed
    public static CopyOnWriteArrayList<String> bedIsSleeping = new CopyOnWriteArrayList<String>();
    public static HashMap<String, InteractionHand> bedUsedHand = new HashMap<String, InteractionHand>();

    // Quick Shulker
    public static CopyOnWriteArrayList<String> shulkerUsed = new CopyOnWriteArrayList<String>();
    public static HashMap<String, InteractionHand> shulkerUsedHand = new HashMap<String, InteractionHand>();
}
