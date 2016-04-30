package adx.audioxd.enchantments.enchantments;


import adx.audioxd.customenchantmentapi.CustomEnchantmentAPI;
import adx.audioxd.customenchantmentapi.EnchantmentRegistry;
import adx.audioxd.customenchantmentapi.enchantment.Enchantment;
import adx.audioxd.customenchantmentapi.enchantment.event.EnchantmentEventHandler;
import adx.audioxd.customenchantmentapi.enums.ItemType;
import adx.audioxd.customenchantmentapi.events.world.EBlockBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class Expolosive extends Enchantment {

	public Expolosive() {
		super(ChatColor.RED + "Explosive", ItemType.PICKAXE, 1);
	}

	@EnchantmentEventHandler
	public void explode(EBlockBreakEvent event) {
		if(!(event.getOwner() instanceof Player)) return;
		if(!this.getType().matchType(event.getItem())) return;

		Player owner = (Player) event.getOwner();
		// A 70% chance
		if(new Random().nextInt(100) < 70) {
			Location blockLocation = event.getBlock().getLocation();

			int radius = 1;

			EnchantmentRegistry.unenchant(
					CustomEnchantmentAPI.getInstance().getNSM().getItemInMainHand(event.getOwner()),
					this
			);
			{
				for(int x = (blockLocation.getBlockX() - radius); x <= (blockLocation.getBlockX() + radius); x++) {
					for(int y = (blockLocation.getBlockY() - radius); y <= (blockLocation.getBlockY() + radius); y++) {
						for(int z = (blockLocation.getBlockZ() - radius); z <= (blockLocation.getBlockZ() + radius); z++) {
							Block block = blockLocation.getWorld().getBlockAt(x, y, z);

							BlockBreakEvent e = new BlockBreakEvent(block, owner);
							{
								Bukkit.getPluginManager().callEvent(e);
							}

							if(block.getDrops().isEmpty()) {
								block.setType(Material.AIR);
								continue;
							}

							if(!e.isCancelled()) {
								block.breakNaturally();
							}
						}
					}
				}
			}
			EnchantmentRegistry.enchant(
					CustomEnchantmentAPI.getInstance().getNSM().getItemInMainHand(event.getOwner()),
					this,
					event.getLvl(),
					true,
					false
			);

			event.setCancelled(true);
		}
	}

}
