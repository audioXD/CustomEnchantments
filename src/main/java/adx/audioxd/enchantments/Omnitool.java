package adx.audioxd.enchantments;


import adx.audioxd.customenchantmentapi.enchantment.Enchantment;
import adx.audioxd.customenchantmentapi.enchantment.event.EnchantmentEventHandler;
import adx.audioxd.customenchantmentapi.enums.ItemType;
import adx.audioxd.customenchantmentapi.events.damage.EOwnerDamagesEntityEvent;
import adx.audioxd.customenchantmentapi.events.world.EInteractEvent;
import adx.audioxd.customenchantmentapi.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Omnitool extends Enchantment {
	// Constructor
	public Omnitool() {
		super("Omnitool", ItemType.TOOLS, 1);
	}

	@EnchantmentEventHandler
	public void change(EInteractEvent event) {
		Material newMaterial = getMaterial(event);
		if(newMaterial != null) {
			if(!newMaterial.equals(event.getItem().getType())) {
				event.getItem().setType(newMaterial);
				if(event.getOwner() instanceof Player) {
					Player player = (Player) event.getOwner();
					player.updateInventory();
				}
			}
		}
	}

	private static Material getMaterial(EInteractEvent event) {
		if(ItemUtil.isEmpty(event.getItem())) return null;

		Material im = event.getItem().getType();
		if(im == null) return null;

		Block b = event.getClickedBlock();
		if(b == null) return getDefault(im);
		if(b.getType() == null) return getDefault(im);

		Material bm = b.getType();
		if(bm == null) return getDefault(im);

		if(Action.LEFT_CLICK_BLOCK.equals(event.getAction())) {
			if(Material.DIRT.equals(bm)
					|| Material.GRASS.equals(bm)
					|| Material.GRAVEL.equals(bm)
					|| Material.SOUL_SAND.equals(bm)
					|| Material.SAND.equals(bm)) {
				return changeOmnitool(im, Material.DIAMOND_SPADE);
			} else if(bm.toString().contains("ORE") || bm.toString().contains("STONE")) {
				return changeOmnitool(im, Material.DIAMOND_PICKAXE);
			} else if(Material.LOG.equals(bm) || bm.toString().contains("WOOD")) {
				return changeOmnitool(im, Material.DIAMOND_AXE);
			}
		} else if(Action.RIGHT_CLICK_BLOCK.equals(event.getAction())) {
			if(Material.DIRT.equals(bm) || Material.GRASS.equals(bm)) {
				return changeOmnitool(im, Material.DIAMOND_HOE);
			}
		}
		return getDefault(im);
	}

	public static Material getDefault(Material im) {
		return changeOmnitool(im, Material.DIAMOND_SWORD);
	}

	private static Material changeOmnitool(Material now, Material target) {
		return Material.getMaterial(now.toString().split("_")[0] + "_" + target.toString().split("_")[1]);
	}

	@EnchantmentEventHandler
	public void changeOnDamage(EOwnerDamagesEntityEvent event) {
		Material newMaterial = getDefault(event.getItem().getType());

		if(!event.getItem().getType().equals(newMaterial)) {
			event.getItem().setType(newMaterial);
		}
	}
}
