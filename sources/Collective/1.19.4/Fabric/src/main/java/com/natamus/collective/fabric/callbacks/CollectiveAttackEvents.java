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

package com.natamus.collective.fabric.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CollectiveAttackEvents {
	private CollectiveAttackEvents() { }
	 
    public static final Event<On_Arrow_Nock> ON_ARROW_NOCK = EventFactory.createArrayBacked(On_Arrow_Nock.class, callbacks -> (item, level, player, hand, hasAmmo) -> {
        for (On_Arrow_Nock callback : callbacks) {
			InteractionResultHolder<ItemStack> resultHolder = callback.onArrowNock(item, level, player, hand, hasAmmo);
        	if (!resultHolder.getResult().equals(InteractionResult.PASS)) {
        		return resultHolder;
        	}
        }
        
        return InteractionResultHolder.pass(item);
    });
    
	@FunctionalInterface
	public interface On_Arrow_Nock {
		 InteractionResultHolder<ItemStack> onArrowNock(ItemStack item, Level level, Player player, InteractionHand hand, boolean hasAmmo);
	}
}
