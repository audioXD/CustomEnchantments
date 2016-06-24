package adx.audioxd.enchantments.gui;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public abstract class GUI implements Listener {

	private final String title;
	private final int ySize;
	private final Map<Integer, GUIOption> options;

	private int largestSlot = 0;

	// Constructor
	public GUI(String title) {
		options = new HashMap<>();
		this.title = title;

		registerGUIOptions();
		largestSlot++;
		ySize = (largestSlot / 9) + (((largestSlot % 9) == 0) ? 0 : 1);
	}

	public abstract void registerGUIOptions();

	public final void openGUI(Player player) {
		Inventory inventory = Bukkit.createInventory(player, ySize * 9, title);
		onCreate(player, inventory);
		player.openInventory(inventory);
	}
	public void onCreate(Player player, Inventory inventory){
		for(int slot : options.keySet()) {
			if(slot <= inventory.getSize() - 1)
				inventory.setItem(slot, options.get(slot).getIcon());
		}
	}

	public final boolean addGUIOption(int slot, ItemStack icon) {
		return addGUIOption(slot, new GUIOption(icon, null));
	}
	public final boolean addGUIOption(int slot, GUIOption option) {
		if(!options.containsKey(slot)) {
			options.put(slot, option);

			if(slot > largestSlot)
				largestSlot = slot;
			return true;
		}
		return false;
	}

	@EventHandler
	public final void executeOnClick(InventoryClickEvent event) {
		Inventory inventory = event.getInventory();
		if(inventory == null) return;
		if(!inventory.getTitle().equals(title)) return;
		if(!(event.getWhoClicked() instanceof Player)) return;

		onClick(event);
	}
	public void onClick(InventoryClickEvent event){
		event.setCancelled(true);

		if(options.containsKey(event.getSlot())) {
			GUIOption option = options.get(event.getSlot());
			if(option == null) return;
			option.onClick(event);
		}
	}

	@EventHandler
	public final void executeOnClose(InventoryClickEvent event){
		Inventory inventory = event.getInventory();
		if(inventory == null) return;
		if(!inventory.getTitle().equals(title)) return;
		if(!(event.getWhoClicked() instanceof Player)) return;
		onClose(event);
	}
	public void onClose(InventoryClickEvent event){}


	public void activate(Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	// The GUI Option calass
	public static class GUIOption {
		private final ItemStack icon;
		private final GUIRunnable onClick;

		// Constructor
		public GUIOption(ItemStack icon, GUIRunnable onClick) {
			this.icon = icon;
			this.onClick = onClick;
		}

		public final void onClick(InventoryClickEvent event) {
			if(onClick != null)
				onClick.run(event);
		}

		// Getters
		public final ItemStack getIcon() {
			return icon;
		}
	}
	public static abstract class GUIRunnable {
		public abstract void run(InventoryClickEvent event);
	}
}
