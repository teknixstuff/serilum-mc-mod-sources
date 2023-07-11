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

package com.natamus.collective.implementations.event;

import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
	Original code used from https://github.com/FabricMC/fabric.
 */
class EventPhaseData<T> {
	final ResourceLocation id;
	T[] listeners;
	final List<EventPhaseData<T>> subsequentPhases = new ArrayList<>();
	final List<EventPhaseData<T>> previousPhases = new ArrayList<>();
	int visitStatus = 0; // 0: not visited, 1: visiting, 2: visited

	@SuppressWarnings("unchecked")
	EventPhaseData(ResourceLocation id, Class<?> listenerClass) {
		this.id = id;
		this.listeners = (T[]) Array.newInstance(listenerClass, 0);
	}

	void addListener(T listener) {
		int oldLength = listeners.length;
		listeners = Arrays.copyOf(listeners, oldLength + 1);
		listeners[oldLength] = listener;
	}
}
