/*
 * This is the latest source code of Name Tag Tweaks.
 * Minecraft version: 1.19.3.
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

package com.natamus.nametagtweaks.forge.events;

import com.natamus.nametagtweaks.cmds.NametagCommand;
import com.natamus.nametagtweaks.config.ConfigHandler;
import com.natamus.nametagtweaks.events.NameTagEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeNameTagEvent {
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
    	if (ConfigHandler.enableNameTagCommand) {
    		NametagCommand.register(e.getDispatcher());
    	}
    }

	@SubscribeEvent
	public void mobItemDrop(LivingDeathEvent e) {
		LivingEntity livingEntity = e.getEntity();
		NameTagEvent.mobItemDrop(livingEntity.level, e.getEntity(), e.getSource());
	}
}
