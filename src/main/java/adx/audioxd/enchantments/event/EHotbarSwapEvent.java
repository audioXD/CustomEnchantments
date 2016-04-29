package adx.audioxd.enchantments.event;


import adx.audioxd.customenchantmentapi.enchantment.event.extra.EnchantmentEventWithOwnerAndItem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

public class EHotbarSwapEvent extends EnchantmentEventWithOwnerAndItem implements Cancellable {

	private boolean cancelled = false;
	private final int prewSlot, newSlot;

	public EHotbarSwapEvent(int lvl, LivingEntity owner, ItemStack item, int prewSlot, int newSlot) {
		super(lvl, owner, item);
		this.prewSlot = prewSlot;
		this.newSlot = newSlot;
	}

	public int getPrewSlot() {
		return prewSlot;
	}

	public int getNewSlot() {
		return newSlot;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}
