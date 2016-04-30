package adx.audioxd.enchantments.enchantments;


import adx.audioxd.customenchantmentapi.enchantment.Enchantment;
import adx.audioxd.customenchantmentapi.enchantment.event.EnchantmentEventHandler;
import adx.audioxd.customenchantmentapi.enums.ItemType;
import adx.audioxd.enchantments.event.EHotbarSwapEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Omnitool extends Enchantment {
	// Global fields
	private static Material[] iArray = new Material[] {
			Material.DIAMOND_SWORD,
			Material.DIAMOND_AXE,
			Material.DIAMOND_PICKAXE,
			Material.DIAMOND_SPADE,
			Material.DIAMOND_HOE
	};
	private static Map<Material, Integer> iMap = toMap(iArray);
// End of Global Fields

	// Constructor
	public Omnitool() {
		super("Omnitool", ItemType.TOOLS, 1);
	}

	public static Map<Material, Integer> toMap(Material[] array) {
		Map<Material, Integer> res = new HashMap<>();
		for(int i = 0; i < array.length; i++) {
			res.put(array[i], i);
		}
		return res;
	}

	@EnchantmentEventHandler
	public void hotbarChange(EHotbarSwapEvent event) {
		if(event.getPrewSlot() - event.getNewSlot() == 0) return;
		if(!((Player) event.getOwner()).isSneaking()) return;

		//78 012345678 012
		int i = event.getPrewSlot(), j = event.getNewSlot();

		boolean next = (j - i) > 0;
		if(i == 0 && j == 8) {
			next = false;
		} else if(j == 0 && i == 8) {
			next = true;
		}

		event.getItem().setType(
				changeOmnitool(
						event.getItem().getType(),
						getNextPrewMaterial(
								next,
								event.getItem().getType()
						)
				)
		);

		event.setCancelled(true);
	}

	private static Material changeOmnitool(Material now, Material target) {
		return Material.getMaterial(now.toString().split("_")[0] + "_" + target.toString().split("_")[1]);
	}

	public static Material getNextPrewMaterial(boolean next, Material now) {
		int n = iMap.get(now);

		final int i = next ?
				((n + 1) % (iArray.length))
				: (((n - 1) < 0) ? (iArray.length - 1) : (n - 1));
		return iArray[i];
	}
}
