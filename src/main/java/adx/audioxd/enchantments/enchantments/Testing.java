package adx.audioxd.enchantments.enchantments;


import adx.audioxd.customenchantmentapi.enchantment.Enchantment;
import adx.audioxd.customenchantmentapi.enchantment.event.EnchantmentEventHandler;
import adx.audioxd.customenchantmentapi.enums.ItemType;
import adx.audioxd.customenchantmentapi.events.bow.EArrowHitEvent;
import adx.audioxd.customenchantmentapi.events.damage.EOwnerDamagedEvent;
import adx.audioxd.customenchantmentapi.events.enchant.EEnchantEvent;
import adx.audioxd.customenchantmentapi.events.enchant.EUnenchantEvent;
import adx.audioxd.customenchantmentapi.events.inventory.EEquipEvent;
import adx.audioxd.customenchantmentapi.events.inventory.EUnequipEvent;
import adx.audioxd.customenchantmentapi.events.inventory.hand.EItemInMainHandEvent;
import adx.audioxd.customenchantmentapi.events.inventory.hand.EItemInOffHandEvent;
import adx.audioxd.customenchantmentapi.events.inventory.hand.EItemNotInMainHandEvent;
import adx.audioxd.customenchantmentapi.events.inventory.hand.EItemNotInOffHandEvent;
import adx.audioxd.customenchantmentapi.events.world.EBlockBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Testing extends Enchantment {

// Constructor
	public Testing() {
		super(ChatColor.GOLD + "Traveler" + ChatColor.RESET, ItemType.ALL_OFF_THE_ABOVE, 40);
	}

	@EnchantmentEventHandler
	public void enchant(EEnchantEvent event) {
		Bukkit.broadcastMessage(ChatColor.GREEN + " enchanted" + event.getItem() + " and at level" + event.getLvl());
	}

	@EnchantmentEventHandler
	public void unenchant(EUnenchantEvent event) {
		Bukkit.broadcastMessage(ChatColor.GREEN + " unenchanted" + event.getItem());
	}

	@EnchantmentEventHandler
	public void onEquip(EEquipEvent event) {
		if(event.getOwner() instanceof Player) {
			Player player = (Player) event.getOwner();
			player.setAllowFlight(true);
		}
		event.getOwner().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 720000, 1));
		event.getOwner().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 720000, event.getLvl()));
		event.getOwner().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 720000, event.getLvl()));
	}

	@EnchantmentEventHandler
	public void onUnequip(EUnequipEvent event) {
		if(event.getOwner() instanceof Player) {
			Player player = (Player) event.getOwner();
			player.setAllowFlight(false);
		}
		event.getOwner().removePotionEffect(PotionEffectType.NIGHT_VISION);
		event.getOwner().removePotionEffect(PotionEffectType.SATURATION);
		event.getOwner().removePotionEffect(PotionEffectType.SPEED);
	}

	@EnchantmentEventHandler
	public void onItemInHand(EItemInOffHandEvent event) {
		event.getOwner().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 720000, event.getLvl()));
	}

	@EnchantmentEventHandler
	public void onItemNotInHand(EItemNotInOffHandEvent event) {
		event.getOwner().removePotionEffect(PotionEffectType.FAST_DIGGING);
	}

	@EnchantmentEventHandler
	public void onItemInHand2(EItemInMainHandEvent event) {
		event.getOwner().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 720000, event.getLvl()));
	}

	@EnchantmentEventHandler
	public void onItemNotInHand2(EItemNotInMainHandEvent event) {
		event.getOwner().removePotionEffect(PotionEffectType.ABSORPTION);
	}

	@EnchantmentEventHandler
	public void onArrowHit(EArrowHitEvent event) {
		event.getArrow().getWorld().createExplosion(event.getArrow().getLocation(), event.getLvl());
		event.getArrow().remove();
	}

	@EnchantmentEventHandler
	public void whenOwnerIsDamaged(EOwnerDamagedEvent event) {
		event.setDamage(0);
	}

	@EnchantmentEventHandler
	public void onBlockBreak(EBlockBreakEvent event) {
		event.getItem().setDurability((short) 0);
	}

}
