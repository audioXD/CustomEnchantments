package adx.audioxd.enchantments;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import adx.audioxd.customenchantmentapi.EnchantmentRegistery;
import adx.audioxd.customenchantmentapi.enchantment.Enchantment;
import adx.audioxd.customenchantmentapi.plugin.CEPLPlugin;

public class CustomEnchantments extends CEPLPlugin {

	public static Enchantment TEST = new Testing();
	public static Enchantment DEBUG = new Debug();

	@Override
	public void Enable() {
		if (!EnchantmentRegistery.register(this, TEST))
			getPluginLogger().severe("Culdn't enable " + TEST.getName() + " enchantmnt");
		if (!EnchantmentRegistery.register(this, DEBUG))
			getPluginLogger().severe("Culdn't enable " + DEBUG.getName() + " enchantmnt");
	}

	@Override
	public void Disable() {
		EnchantmentRegistery.unregisterAll(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("enchantt")) {
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("off")) {
					onDisable();
					sender.sendMessage("disabled");
				} else if (args[0].equalsIgnoreCase("on")) {
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
