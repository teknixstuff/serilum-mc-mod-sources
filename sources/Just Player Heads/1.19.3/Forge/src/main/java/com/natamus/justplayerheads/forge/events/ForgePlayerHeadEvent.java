/*
 * This is the latest source code of Just Player Heads.
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

package com.natamus.justplayerheads.forge.events;

import com.natamus.justplayerheads.cmds.CommandJph;
import com.natamus.justplayerheads.events.PlayerHeadEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgePlayerHeadEvent {
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
        CommandJph.register(e.getDispatcher());
    }
	
	@SubscribeEvent
	public void entityDeath(LivingDeathEvent e) {
		Entity entity = e.getEntity();
		Level level = entity.getCommandSenderWorld();
        if (level.isClientSide) {
            return;
        }

        if (!(entity instanceof Player)) {
            return;
        }

        PlayerHeadEvent.onPlayerDeath((ServerLevel)level, (ServerPlayer)entity);
	}
}
