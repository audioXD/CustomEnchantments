package adx.audioxd.enchantments;


import adx.audioxd.customenchantmentapi.EnchantmentRegistry;
import adx.audioxd.customenchantmentapi.enchantment.Enchantment;
import adx.audioxd.customenchantmentapi.plugin.CEPLPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CustomEnchantments extends CEPLPlugin {

// Global fields
	public static Enchantment TEST = new Testing();
	public static Enchantment DEBUG = new Debug();
// End of Global Fields

	@Override
	public void Enable() {
		if(!EnchantmentRegistry.register(this, TEST))
			getPluginLogger().severe("Culdn't enable " + TEST.getName() + " enchantmnt");
		if(!EnchantmentRegistry.register(this, DEBUG))
			getPluginLogger().severe("Culdn't enable " + DEBUG.getName() + " enchantmnt");
	}

	@Override
	public void Disable() {
		EnchantmentRegistry.unregisterAll(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("enchantt")) {
			if(args.length >= 1) {
				if(args[0].equalsIgnoreCase("off")) {
					onDisable();
					sender.sendMessage("disabled");
				} else if(args[0].equalsIgnoreCase("on")) {
					onEnable();
					sender.sendMessage("enabled");
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return super.onCommand(sender, command, label, args);
	}
}
