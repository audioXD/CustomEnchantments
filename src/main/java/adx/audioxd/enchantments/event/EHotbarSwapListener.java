package adx.audioxd.enchantments.event;


import adx.audioxd.customenchantmentapi.EnchantmentRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class EHotbarSwapListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void hotbarSwap(PlayerItemHeldEvent event) {
		EHotbarSwapEvent e = new EHotbarSwapEvent(
				event.getPlayer(),
				event.getPlayer().getInventory().getItem(event.getPreviousSlot()),
				event.getPreviousSlot(),
				event.getNewSlot()
		);
		e.setCancelled(event.isCancelled());
		{
			EnchantmentRegistry.fireEvents(EnchantmentRegistry.getEnchantments(event.getPlayer().getInventory().getItem(event.getPreviousSlot())), e);
		}
		event.setCancelled(e.isCancelled());
	}

}
