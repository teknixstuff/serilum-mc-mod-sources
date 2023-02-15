/*
 * This is the latest source code of Crying Portals.
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

package com.natamus.cryingportals.forge.events;

import com.natamus.cryingportals.forge.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Iterator;
import java.util.Optional;

@EventBusSubscriber
public class ForgePortalEvent {
	@SubscribeEvent
	public void onClick(PlayerInteractEvent.RightClickBlock e) {
		Level level = e.getLevel();
		if (level.isClientSide) {
			return;
		}
		
		ItemStack itemstack = e.getItemStack();
		if (itemstack.getItem().equals(Items.FLINT_AND_STEEL)) {
        	BlockPos clickpos = e.getPos();
        	
        	int obsidiancount = 0;
        	Iterator<BlockPos> it = BlockPos.betweenClosedStream(clickpos.getX()-3, clickpos.getY()-3, clickpos.getZ()-3, clickpos.getX()+3, clickpos.getY()+3, clickpos.getZ()+3).iterator();
        	while (it.hasNext()) {
        		BlockPos np = it.next();
        		if (level.getBlockState(np).getBlock().equals(Blocks.CRYING_OBSIDIAN)) {
        			obsidiancount+=1;
        		}
        	}
			
        	if (obsidiancount >= 6) {
				new Thread(() -> {
					try  { Thread.sleep( 10 ); }
					catch (InterruptedException ignored)  {}

					BlockPos topos = clickpos;

					boolean foundportal = false;
					for (BlockPos np : BlockPos.betweenClosed(clickpos.getX() - 1, clickpos.getY() - 1, clickpos.getZ() - 1, clickpos.getX() + 1, clickpos.getY() + 1, clickpos.getZ() + 1)) {
						Block bsblock = level.getBlockState(np).getBlock();
						if (bsblock instanceof NetherPortalBlock) {
							foundportal = true;
						} else if (bsblock.equals(Blocks.FIRE)) {
							if (Util.isAir(level.getBlockState(np.below(1)))) {
								topos = np.below(1).immutable();
							} else if (Util.isAir(level.getBlockState(np.below(2)))) {
								topos = np.below(2).immutable();
							} else {
								topos = np.immutable();
							}
						}
					}

					if (!foundportal) {
						if (Util.isAir(level.getBlockState(topos))) {
							if (level.dimension() == Level.OVERWORLD || level.dimension() == Level.NETHER) {
								Optional<PortalShape> optional = PortalShape.findEmptyPortalShape(level, topos, Direction.Axis.X);
								optional = ForgeEventFactory.onTrySpawnPortal(level, topos, optional);
								if (optional.isPresent()) {
									(optional.get()).createPortalBlocks();
								}
							}
						}
					}
				}).start();
        	}
		}
	}
}
