package me.NodeDigital.OlympicHeroes.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagDouble;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;

@SuppressWarnings("unused")
public class OHItems {
	public static ItemStack LONG_BOW;
	public static ItemStack RECURVE;
	public static ItemStack FIRE_STICK;
	public static ItemStack HERMES_BOOTS;
	public static ItemStack LAMINATED_STICK;
	public static ItemStack AEGIS_SHIELD;
	public static ItemStack AEGIS_CAPE;
	public static ItemStack SILVER_DRACHMA;
	public static ItemStack GOLDEN_DRACHMA;
	public static ItemStack HERMES_ELYTRA;
	public static ItemStack POSEIDON_BOOTS;
	//public static MaterialData LAMINATED_STICK_MAT;
	//public static ItemStack LAMINATED_LONGBOW;
	//public static ItemStack LAMINATED_RECURVE;
	
	public static ItemStack[] DISALLOWED_ITEMS;
	
	private static List<ItemStack> items = new ArrayList<ItemStack>();
	
	@SuppressWarnings("deprecation")
	public static void constructItems() {
		
		AEGIS_SHIELD = createItem(Material.SHIELD, ChatColor.AQUA + "The Aegis", new String[] {"The Shield of Athena Herself"});
		AEGIS_SHIELD.addEnchantment(Enchantment.DURABILITY, 2);
		AEGIS_SHIELD.addUnsafeEnchantment(Enchantment.THORNS, 1);
		ItemMeta meta = AEGIS_SHIELD.getItemMeta();
        BlockStateMeta AEGIS_META = (BlockStateMeta) meta;
        Banner banner = (Banner) AEGIS_META.getBlockState();
        banner.setBaseColor(DyeColor.YELLOW);
        banner.addPattern(new Pattern(DyeColor.SILVER, PatternType.STRIPE_DOWNLEFT));
        banner.addPattern(new Pattern(DyeColor.SILVER, PatternType.STRIPE_DOWNRIGHT));
        banner.addPattern(new Pattern(DyeColor.LIME, PatternType.FLOWER));
        banner.addPattern(new Pattern(DyeColor.GREEN, PatternType.CROSS));
        banner.addPattern(new Pattern(DyeColor.GREEN, PatternType.FLOWER));
        banner.addPattern(new Pattern(DyeColor.ORANGE, PatternType.BORDER));
        AEGIS_META.setBlockState(banner);
        AEGIS_SHIELD.setItemMeta(AEGIS_META);
		
		LONG_BOW = createItem(Material.BOW, ChatColor.YELLOW + "LongBow", new String[]{"a Heavier Bow"});
		LONG_BOW.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		ShapedRecipe longbowRecipe = new ShapedRecipe(LONG_BOW);
		longbowRecipe.shape("SSa", "saS", "asS");
		longbowRecipe.setIngredient('S', Material.STICK);
		longbowRecipe.setIngredient('s', Material.STRING);
		longbowRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(longbowRecipe);
		items.add(LONG_BOW);
		
		FIRE_STICK = createItem(Material.STICK, ChatColor.RED + "Fire stick thing", new String[] {"This is a fire stick"});
		FIRE_STICK.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		// Put attributes after changing name and adding enchants
		FIRE_STICK = addAttribute(FIRE_STICK, "generic.attackDamage", 2, "mainhand");
		// Have to put recipe after modifying attributes
		ShapelessRecipe shapelessRecipe = new ShapelessRecipe(FIRE_STICK);
		shapelessRecipe.addIngredient(Material.BLAZE_POWDER);
		shapelessRecipe.addIngredient(Material.GOLD_NUGGET);
		shapelessRecipe.addIngredient(Material.STICK);
		Bukkit.getServer().addRecipe(shapelessRecipe);
		items.add(FIRE_STICK);
		
		HERMES_BOOTS = createItem(Material.LEATHER_BOOTS, ChatColor.GOLD + "Hermes Boots", new String[] {"Boots with a slight amount of Hermes's speed"});
		HERMES_BOOTS = addAttribute(HERMES_BOOTS, "generic.movementSpeed", 0.1, "feet");
		ShapedRecipe hermesRecipe = new ShapedRecipe(HERMES_BOOTS);
		hermesRecipe.shape("lgl","lal","faf");
		hermesRecipe.setIngredient('g', Material.GLOWSTONE_DUST);
		hermesRecipe.setIngredient('l', Material.LEATHER);
		hermesRecipe.setIngredient('f', Material.FEATHER);
		hermesRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(hermesRecipe);
		items.add(HERMES_BOOTS);
		
		LAMINATED_STICK = createItem(Material.STICK, "Laminated Stick", new String[]{"This is a laminated stick used for bowmaking"});
		ShapedRecipe laminateRecipe = new ShapedRecipe(LAMINATED_STICK);
		laminateRecipe.shape("psp","psp","aaa");
		laminateRecipe.setIngredient('p', Material.LOG);
		laminateRecipe.setIngredient('s', Material.STICK);
		laminateRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(laminateRecipe);
		items.add(LAMINATED_STICK);
		
		RECURVE = createItem(Material.BOW, ChatColor.YELLOW +  "Recurve", new String[]{"a Lightweight bow that gives mobility"});
		RECURVE = addAttribute(RECURVE, "generic.movementSpeed", .05, "mainhand");
		ShapedRecipe recurveRecipe = new ShapedRecipe(RECURVE);
		recurveRecipe.shape("sSa", "sal", "sSa");
		recurveRecipe.setIngredient('S', Material.STICK);
		recurveRecipe.setIngredient('s', Material.STRING);
		recurveRecipe.setIngredient('a', Material.AIR);
		recurveRecipe.setIngredient('l', Material.LEATHER);
		Bukkit.getServer().addRecipe(recurveRecipe);
		items.add(RECURVE);
		
		SILVER_DRACHMA = createItem(Material.IRON_NUGGET, ChatColor.GRAY + "Silver Drachma", new String[] {"The common currency of the greek world."});
		GOLDEN_DRACHMA = createItem(Material.GOLD_NUGGET, ChatColor.GOLD + "Golden Drachma", new String[] {"The advanced currency of the greek world"});
		
		HERMES_ELYTRA = createItem(Material.ELYTRA, ChatColor.DARK_PURPLE + "Hermes Elytra", new String[] {});
		POSEIDON_BOOTS = createItem(Material.LEATHER_BOOTS, ChatColor.AQUA + "Poseidon Boots", new String[] {});
		
		DISALLOWED_ITEMS = new ItemStack[]{AEGIS_SHIELD,HERMES_ELYTRA,POSEIDON_BOOTS};
	}
	
	public static List<ItemStack> getItems(){
		if(items.size() == 0) {
			constructItems();
		}
		
		return items;
	}
	
	private static ItemStack createItem(Material mat, String name, String[] lore) {
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		List<String> loreList = new ArrayList<String>();
		
		for(String s : lore) {
			loreList.add(s);
		}
		
		meta.setLore(loreList);
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static boolean isItemSimilarTo(ItemStack item1, ItemStack item2, boolean checkLore) {
		if(item1 != null && item2 != null) {
			if(item1.getType() == item2.getType()) {
				if(item1.hasItemMeta() && item2.hasItemMeta()) {
					if(item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName())) {
						if(!checkLore || item1.getItemMeta().hasLore() && item2.getItemMeta().hasLore()) {
							if(!checkLore || item1.getItemMeta().getLore().equals(item2.getItemMeta().getLore())) {
								return true;
							}
						}else {
							return (item1.getItemMeta().hasLore() == false && item2.getItemMeta().hasLore() == false);
						}
					}
				}else {
					return (item1.hasItemMeta() == false && item2.hasItemMeta() == false);
				}
			}
		}else {
			return (item1 == null && item2 == null);
		}
		return false;
	}
	/**
	 * Adds an attribute to an item (will add to existing value)
	 * @param item The item to which to add the attribute
	 * @param attributeName the name of the attribute (e.g. generic.attackDamage)
	 * @param value the value for the attribute
	 * @param slot the slot for the attribute to apply (mainhand, head, etc.). Use "all" for all slots.
	 * @return the modified ItemStack
	 */
	private static ItemStack addAttribute(ItemStack item, String attributeName, double value, String slot) {
		net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
		NBTTagList modifiers = compound.getList("AttributeModifiers",10);
		NBTTagCompound tag = new NBTTagCompound();
		tag.set("AttributeName", new NBTTagString(attributeName));
		tag.set("Name", new NBTTagString(attributeName));
		tag.set("Amount", new NBTTagDouble(value));
		// 0 is the additive operation. 1 and 2 are multiplicative
		tag.set("Operation", new NBTTagInt(0));
		tag.set("UUIDLeast", new NBTTagInt(894654));
		tag.set("UUIDMost", new NBTTagInt(2872));
		if(!slot.equalsIgnoreCase("all"))
			tag.set("Slot", new NBTTagString(slot));
		
		modifiers.add(tag);
		compound.set("AttributeModifiers", modifiers);
		nmsStack.setTag(compound);
		
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
}