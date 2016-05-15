package adx.audioxd.enchantments.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CEAPICommadRegistery implements TabExecutor {
	private Set<CEAPICommand> commands = new HashSet<>();
	private volatile CEAPICommand[] backedCommands = null;

	public synchronized boolean addCommand(CEAPICommand command) {
		if(commands.add(command)) {
			backedCommands = null;
			return true;
		}
		return false;
	}

	public void reset() {
		synchronized(commands) {
			commands = new HashSet<>();
			backedCommands = null;
		}
	}

	private CEAPICommand[] bake() {
		CEAPICommand[] baked = backedCommands;
		if(baked == null) {
			synchronized(commands) {
				if((baked = backedCommands) == null) {
					baked = commands.toArray(new CEAPICommand[commands.size()]);
					Arrays.sort(baked);
					backedCommands = baked;
				}
			}
		}
		return baked;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		for(CEAPICommand cmd : bake()) {
			if(label.equalsIgnoreCase(cmd.getLabel())){
			}
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return null;
	}
}
