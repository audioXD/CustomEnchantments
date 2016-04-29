package adx.audioxd.enchantments.enchantments;


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
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Expolosive extends Enchantment {

	public Expolosive() {
		super(ChatColor.RED + "Explosive", ItemType.PICKAXE, 1);
	}

	@EnchantmentEventHandler
	public void explode(EBlockBreakEvent event) {
		if(!(event.getOwner() instanceof Player)) return;

		Player owner = (Player) event.getOwner();
		// A 70% chance
		if(new Random().nextInt(100) < 70) {
			Location blockLocation = event.getBlock().getLocation();

			int radius = 1;
			List<Block> blocks = new ArrayList<>();
			for(int x = (blockLocation.getBlockX() - radius); x <= (blockLocation.getBlockX() + radius); x++) {
				for(int y = (blockLocation.getBlockY() - radius); y <= (blockLocation.getBlockY() + radius); y++) {
					for(int z = (blockLocation.getBlockZ() - radius); z <= (blockLocation.getBlockZ() + radius); z++) {
						Block block = blockLocation.getWorld().getBlockAt(x, y, z);
						blocks.add(block);
					}
				}
			}

			EntityExplodeEvent e = new EntityExplodeEvent(null, blockLocation, blocks, 0);
			Bukkit.getPluginManager().callEvent(e);
			if(!e.isCancelled()) {
				// Explosion effect does 0 damage.
				event.getOwner().getWorld().createExplosion(blockLocation, 0);

				for(Block block : e.blockList()) {
					if(block.isLiquid()
							|| block.getType().equals(Material.BEDROCK)
							|| block.getType().equals(Material.BARRIER)) continue;

					/*for(ItemStack item : block.getDrops(event.getItem())) {
						owner.getInventory().addItem(item);
					}*/
					block.breakNaturally(event.getItem());
				}
				owner.updateInventory();
			}
		}

	}

}
