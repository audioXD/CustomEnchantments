package adx.audioxd.enchantments;


import adx.audioxd.customenchantmentapi.EnchantmentRegistry;
import adx.audioxd.customenchantmentapi.enchantment.Enchantment;
import adx.audioxd.customenchantmentapi.plugin.CEPLPlugin;
import adx.audioxd.enchantments.enchantments.*;
import adx.audioxd.enchantments.event.EHotbarSwapListener;
import adx.audioxd.enchantments.gui.TestGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomEnchantments extends CEPLPlugin {

	// Global fields
	public static Enchantment TEST = new Testing();
	public static Enchantment EXPLOSIVE = new Expolosive();
	public static Enchantment HAMMER = new Hammer();
	public static Enchantment DEBUG = new Debug();
	public static Enchantment OMNI_TOOL = new Omnitool();
// End of Global Fields

	@Override
	public void Enable() {
		if(!EnchantmentRegistry.register(this, TEST))
			getPluginLogger().severe("Culdn't enable " + TEST.getName() + " enchantmnt");
		if(!EnchantmentRegistry.register(this, DEBUG))
			getPluginLogger().severe("Culdn't enable " + DEBUG.getName() + " enchantmnt");
		if(!EnchantmentRegistry.register(this, EXPLOSIVE))
			getPluginLogger().severe("Culdn't enable " + EXPLOSIVE.getName() + " enchantmnt");

		if(!EnchantmentRegistry.register(this, OMNI_TOOL))
			getPluginLogger().severe("Culdn't enable " + OMNI_TOOL.getName() + " enchantmnt");

		if(!EnchantmentRegistry.register(this, HAMMER))
			getPluginLogger().severe("Culdn't enable " + HAMMER.getName() + " enchantmnt");

		Bukkit.getPluginManager().registerEvents(new EHotbarSwapListener(), this);

		TestGUI.get().activate(this);
	}

	public void Disable() {}

	public void enchatWithTest(ItemStack item) {
		EnchantmentRegistry.enchant(item, TEST, 1, false, false);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(label.equalsIgnoreCase("testGUI")) {
			if(sender instanceof Player) {
				TestGUI.get().openGUI((Player) sender);
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "This command must be executed ba a Player!");
			}
		}

		return super.onCommand(sender, command, label, args);
	}
}
