package adx.audioxd.enchantments.gui;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestGUI extends GUI {
	private static TestGUI i = new TestGUI();

	public static TestGUI get() {return i;}

	public TestGUI() {
		super(ChatColor.GREEN + "Test GUI");
	}

	@Override
	public void registerGUIOptions() {
		addGUIOption(new GUIOption(0, new ItemStack(Material.DIRT), new GUIRunnable() {
			@Override
			public void run(Player owner) {
				owner.getInventory().addItem(new ItemStack(Material.DIRT, 1));
			}
		}));

		addGUIOption(new GUIOption(9, new ItemStack(Material.DIAMOND), new GUIRunnable() {
			@Override
			public void run(Player owner) {
				owner.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
			}
		}));

		addGUIOption(new GUIOption(15, new ItemStack(Material.BARRIER), new GUIRunnable() {
			@Override
			public void run(Player owner) {
				owner.getInventory().addItem(new ItemStack(Material.BARRIER, 1));
			}
		}));
	}
}
