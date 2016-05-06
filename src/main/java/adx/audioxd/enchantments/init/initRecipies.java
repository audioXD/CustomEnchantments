package adx.audioxd.enchantments.init;


import adx.audioxd.customenchantmentapi.EnchantmentRegistry;
import adx.audioxd.enchantments.CustomEnchantments;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class initRecipies {
	private static final ItemStack WOODEN_HAMMER = new ItemStack(Material.WOOD_PICKAXE, 1);
	private static final ItemStack STONE_HAMMER = new ItemStack(Material.STONE_PICKAXE, 1);
	private static final ItemStack GOLD_HAMMER = new ItemStack(Material.GOLD_PICKAXE, 1);
	private static final ItemStack IRON_HAMMER = new ItemStack(Material.IRON_PICKAXE, 1);
	private static final ItemStack DIAMOND_HAMMER = new ItemStack(Material.DIAMOND_PICKAXE, 1);

	static {
		EnchantmentRegistry.enchant(WOODEN_HAMMER, CustomEnchantments.HAMMER, 1, true, true);
		EnchantmentRegistry.enchant(STONE_HAMMER, CustomEnchantments.HAMMER, 1, true, true);
		EnchantmentRegistry.enchant(GOLD_HAMMER, CustomEnchantments.HAMMER, 1, true, true);
		EnchantmentRegistry.enchant(IRON_HAMMER, CustomEnchantments.HAMMER, 1, true, true);
		EnchantmentRegistry.enchant(DIAMOND_HAMMER, CustomEnchantments.HAMMER, 1, true, true);
	}

	private static ShapedRecipe wRecipe = new ShapedRecipe(WOODEN_HAMMER);
	private static ShapedRecipe sRecipe = new ShapedRecipe(STONE_HAMMER);
	private static ShapedRecipe gRecipe = new ShapedRecipe(GOLD_HAMMER);
	private static ShapedRecipe iRecipe = new ShapedRecipe(IRON_HAMMER);
	private static ShapedRecipe dRecipe = new ShapedRecipe(DIAMOND_HAMMER);

	static {
		wRecipe.shape("XXX", "XIX", " I ");
		sRecipe.shape("XXX", "XIX", " I ");
		gRecipe.shape("XXX", "XIX", " I ");
		iRecipe.shape("XXX", "XIX", " I ");
		dRecipe.shape("XXX", "XIX", " I ");

		wRecipe.setIngredient('X', Material.WOOD);
		sRecipe.setIngredient('X', Material.COBBLESTONE);
		gRecipe.setIngredient('X', Material.GOLD_INGOT);
		iRecipe.setIngredient('X', Material.IRON_INGOT);
		dRecipe.setIngredient('X', Material.DIAMOND);

		wRecipe.setIngredient('I', Material.STICK);
		sRecipe.setIngredient('I', Material.STICK);
		gRecipe.setIngredient('I', Material.STICK);
		iRecipe.setIngredient('I', Material.STICK);
		dRecipe.setIngredient('I', Material.STICK);
	}


	public static void init() {
		Bukkit.addRecipe(wRecipe);
		Bukkit.addRecipe(sRecipe);
		Bukkit.addRecipe(gRecipe);
		Bukkit.addRecipe(iRecipe);
		Bukkit.addRecipe(dRecipe);

	}
}
