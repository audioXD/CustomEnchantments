package adx.audioxd.enchantments.event;


import adx.audioxd.customenchantmentapi.EnchantmentRegistry;
import adx.audioxd.customenchantmentapi.enchantment.Enchanted;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class EHotbarSwapListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void hotbarSwap(PlayerItemHeldEvent event) {
		for(Enchanted en : EnchantmentRegistry.getEnchantments(event.getPlayer().getInventory().getItem(event.getPreviousSlot()))) {
			EHotbarSwapEvent e = new EHotbarSwapEvent(
					en.getLvl(),
					event.getPlayer(),
					event.getPlayer().getInventory().getItem(event.getPreviousSlot()),
					event.getPreviousSlot(),
					event.getNewSlot()
			);
			e.setCancelled(event.isCancelled());
			{
				en.fireEvent(e);
			}
			event.setCancelled(e.isCancelled());
		}
	}

}
