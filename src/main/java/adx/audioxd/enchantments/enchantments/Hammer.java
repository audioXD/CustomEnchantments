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
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class Hammer extends Enchantment {

	public Hammer() {
		super(ChatColor.GREEN + "Hammer", ItemType.PICKAXE, 2);
	}

	@EnchantmentEventHandler
	public void explode(EBlockBreakEvent event) {
		if(!(event.getOwner() instanceof Player)) return;
		if(!this.getType().matchType(event.getItem())) return;

		Player owner = (Player) event.getOwner();
		if(owner.isSneaking()) return;

		Location blockLocation = event.getBlock().getLocation();

		final int xRadius, yRadius, zRadius, radius = event.getLvl();
		{
			Location loc = owner.getLocation();
			int yaw = (int) loc.getYaw();
			yaw = yaw / 180 >= 1 ? -(yaw % 180) : yaw;

			int pitch = (int) loc.getPitch();
			if((pitch <= -45 && pitch >= -90) || (pitch >= 45 && pitch <= 90)) {
				//Up down
				xRadius = radius;
				yRadius = 0;
				zRadius = radius;
			} else if((yaw >= -135 && yaw <= -45) || (yaw >= 45 && yaw <= 135)) {
				// east west
				xRadius = 0;
				yRadius = radius;
				zRadius = radius;
			} else {
				// north south
				xRadius = radius;
				yRadius = radius;
				zRadius = 0;
			}
		}
		EnchantmentRegistry.unenchant(
				CustomEnchantmentAPI.getInstance().getNSM().getItemInMainHand(event.getOwner()),
				this
		);
		{
			for(int xOff = -xRadius; xOff <= xRadius; ++xOff) {
				for(int yOff = -yRadius; yOff <= yRadius; ++yOff) {
					for(int zOff = -zRadius; zOff <= zRadius; ++zOff) {
						Block block = event.getBlock().getRelative(xOff, yOff, zOff);

						BlockBreakEvent e = new BlockBreakEvent(block, owner);
						{
							Bukkit.getPluginManager().callEvent(e);
						}

						if(!e.isCancelled()) {
							block.breakNaturally(event.getItem());
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
