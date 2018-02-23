package me.nodedigital.olympicheroes.item;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagDouble;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;

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

@SuppressWarnings("unused")
public class OHItems {
	public static ItemStack longBow;
	public static ItemStack recurve;
//	public static ItemStack emeraldInfused;
	public static ItemStack hermesBoots;
	public static ItemStack laminatedStick;
	public static ItemStack aegisShield;
	public static ItemStack aegisCape;
	public static ItemStack silverDrachma;
	public static ItemStack goldenDrachma;
	public static ItemStack hermesElytra;
	public static ItemStack poseidonBoots;
	public static ItemStack emeraldInfusedChest;
	public static ItemStack emeraldInfusedLeggings;
	public static ItemStack emeraldInfusedHelmet;
	public static ItemStack emeraldInfusedBoots;
	public static ItemStack emeraldInfusedSword;
	public static ItemStack emeraldInfusedAxe;
	public static ItemStack starPoweredChest;
	public static ItemStack starPoweredLeggings;
	public static ItemStack starPoweredHelmet;
	public static ItemStack starPoweredBoots;
	public static ItemStack starPoweredSword;
	public static ItemStack starPoweredAxe;
	
	public static ItemStack[] disallowedItems;
	
	private static List<ItemStack> items = new ArrayList<ItemStack>();
	
	@SuppressWarnings("deprecation")
	public static void constructItems() {
		
		aegisShield = createItem(Material.SHIELD, ChatColor.AQUA + "The Aegis", new String[] {"The Shield of Athena Herself"});
		aegisShield.addEnchantment(Enchantment.DURABILITY, 2);
		aegisShield.addUnsafeEnchantment(Enchantment.THORNS, 1);
		ItemMeta meta = aegisShield.getItemMeta();
        BlockStateMeta aegisMeta = (BlockStateMeta) meta;
        Banner banner = (Banner) aegisMeta.getBlockState();
        banner.setBaseColor(DyeColor.YELLOW);
        banner.addPattern(new Pattern(DyeColor.SILVER, PatternType.STRIPE_DOWNLEFT));
        banner.addPattern(new Pattern(DyeColor.SILVER, PatternType.STRIPE_DOWNRIGHT));
        banner.addPattern(new Pattern(DyeColor.LIME, PatternType.FLOWER));
        banner.addPattern(new Pattern(DyeColor.GREEN, PatternType.CROSS));
        banner.addPattern(new Pattern(DyeColor.GREEN, PatternType.FLOWER));
        banner.addPattern(new Pattern(DyeColor.ORANGE, PatternType.BORDER));
        aegisMeta.setBlockState(banner);
        aegisShield.setItemMeta(aegisMeta);
		
		longBow = createItem(Material.BOW, ChatColor.YELLOW + "LongBow", new String[]{"a Heavier Bow"});
		longBow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		ShapedRecipe longbowRecipe = new ShapedRecipe(longBow);
		longbowRecipe.shape("SSa", "saS", "asS");
		longbowRecipe.setIngredient('S', Material.STICK);
		longbowRecipe.setIngredient('s', Material.STRING);
		longbowRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(longbowRecipe);
		items.add(longBow);
		
//		emeraldInfused = createItem(Material.STICK, ChatColor.RED + "Fire stick thing", new String[] {"This is a fire stick"});
//		emeraldInfused.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		// Put attributes after changing name and adding enchants
//		emeraldInfused = addAttribute(fireStick, "generic.attackDamage", 2, "mainhand", 3123, 5941);
		// Have to put recipe after modifying attributes
//		ShapelessRecipe shapelessRecipe = new ShapelessRecipe(emeraldInfused);
//		shapelessRecipe.addIngredient(Material.BLAZE_POWDER);
//		shapelessRecipe.addIngredient(Material.GOLD_NUGGET);
//		shapelessRecipe.addIngredient(Material.STICK);
//		Bukkit.getServer().addRecipe(shapelessRecipe);
//		items.add(emeraldInfused);
		
		hermesBoots = createItem(Material.LEATHER_BOOTS, ChatColor.GOLD + "Hermes Boots", new String[] {"Boots with a slight amount of Hermes's speed"});
		hermesBoots = addAttribute(hermesBoots, "generic.movementSpeed", 0.1, "feet", 9213, 4192);
		ShapedRecipe hermesRecipe = new ShapedRecipe(hermesBoots);
		hermesRecipe.shape("lgl","lal","faf");
		hermesRecipe.setIngredient('g', Material.GLOWSTONE_DUST);
		hermesRecipe.setIngredient('l', Material.LEATHER);
		hermesRecipe.setIngredient('f', Material.FEATHER);
		hermesRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(hermesRecipe);
		items.add(hermesBoots);
		
		recurve = createItem(Material.BOW, ChatColor.YELLOW +  "Recurve", new String[]{"a Lightweight bow that gives mobility"});
		recurve = addAttribute(recurve, "generic.movementSpeed", .05, "mainhand", 5694, 40985);
		recurve = addAttribute(recurve, "generic.movementSpeed", .05, "offhand", 10923, 581267);
		ShapedRecipe recurveRecipe = new ShapedRecipe(recurve);
		recurveRecipe.shape("sSa", "sal", "sSa");
		recurveRecipe.setIngredient('S', Material.STICK);
		recurveRecipe.setIngredient('s', Material.STRING);
		recurveRecipe.setIngredient('a', Material.AIR);
		recurveRecipe.setIngredient('l', Material.LEATHER);
		Bukkit.getServer().addRecipe(recurveRecipe);
		items.add(recurve);
		
		/*Emerald Infused*/
		
		emeraldInfusedChest = createItem(Material.DIAMOND_CHESTPLATE, ChatColor.GREEN + "Emerald Infused ChestPiece", new String[]{"An Emerald Strengthened Diamond Chest Piece"});
		emeraldInfusedChest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		//emeraldInfusedChest = addAttribute(emeraldInfusedChest, "generic.armor", 10, "chest");
		//emeraldInfusedChest = addAttribute(emeraldInfusedChest, "generic.armorToughness", 4, "chest");
		ShapedRecipe emeraldInfusedChestRecipe = new ShapedRecipe(emeraldInfusedChest);
		emeraldInfusedChestRecipe.shape("EaE", "DED", "DDD");
		emeraldInfusedChestRecipe.setIngredient('E', Material.EMERALD);
		emeraldInfusedChestRecipe.setIngredient('D', Material.DIAMOND);
		emeraldInfusedChestRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(emeraldInfusedChestRecipe);
		items.add(emeraldInfusedChest);
		
		emeraldInfusedLeggings = createItem(Material.DIAMOND_LEGGINGS, ChatColor.GREEN + "Emerald Infused Leggings", new String[]{"An Emerald Strengthened Set Of Diamond Leggings"});
		emeraldInfusedLeggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		//emeraldInfusedLeggings = addAttribute(emeraldInfusedLeggings, "generic.armor", 7, "legs");
		//emeraldInfusedLeggings = addAttribute(emeraldInfusedLeggings, "generic.armorToughness", 4, "legs");
		ShapedRecipe emeraldInfusedLeggingsRecipe = new ShapedRecipe(emeraldInfusedLeggings);
		emeraldInfusedLeggingsRecipe.shape("EDE", "DaD", "DaD");
		emeraldInfusedLeggingsRecipe.setIngredient('E', Material.EMERALD);
		emeraldInfusedLeggingsRecipe.setIngredient('D', Material.DIAMOND);
		emeraldInfusedLeggingsRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(emeraldInfusedLeggingsRecipe);
		items.add(emeraldInfusedLeggings);
		
		emeraldInfusedHelmet = createItem(Material.DIAMOND_HELMET, ChatColor.GREEN + "Emerald Infused Helmet", new String[]{"An Emerald Strengthened Diamond Helm"});
		emeraldInfusedHelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		//emeraldInfusedHelmet = addAttribute(emeraldInfusedHelmet, "generic.armor", 4, "head");
		//emeraldInfusedHelmet = addAttribute(emeraldInfusedHelmet, "generic.armorToughness", 4, "head");
		ShapedRecipe emeraldInfusedHelmetRecipe = new ShapedRecipe(emeraldInfusedHelmet);
		emeraldInfusedHelmetRecipe.shape("DED", "DaD", "aaa");
		emeraldInfusedHelmetRecipe.setIngredient('E', Material.EMERALD);
		emeraldInfusedHelmetRecipe.setIngredient('D', Material.DIAMOND);
		emeraldInfusedHelmetRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(emeraldInfusedHelmetRecipe);
		
		ShapedRecipe emeraldInfusedHelmetRecipeBot = new ShapedRecipe(emeraldInfusedHelmet);
		emeraldInfusedHelmetRecipeBot.shape("aaa", "DED", "DaD");
		emeraldInfusedHelmetRecipeBot.setIngredient('E', Material.EMERALD);
		emeraldInfusedHelmetRecipeBot.setIngredient('D', Material.DIAMOND);
		emeraldInfusedHelmetRecipeBot.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(emeraldInfusedHelmetRecipeBot);
		items.add(emeraldInfusedHelmet);
		
		emeraldInfusedBoots = createItem(Material.DIAMOND_BOOTS, ChatColor.GREEN + "Emerald Infused Boots", new String[]{"An Emerald Strengthened Pair Of Diamond Boots"});
		emeraldInfusedBoots.addEnchantment(Enchantment.PROTECTION_FALL, 1);
		emeraldInfusedBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		//emeraldInfusedBoots = addAttribute(emeraldInfusedBoots, "generic.armor", 4, "feet");
		//emeraldInfusedBoots = addAttribute(emeraldInfusedBoots, "generic.armorToughness", 4, "feet");
		ShapedRecipe emeraldInfusedBootsRecipe = new ShapedRecipe(emeraldInfusedBoots);
		emeraldInfusedBootsRecipe.shape("DaD", "EaE", "aaa");
		emeraldInfusedBootsRecipe.setIngredient('E', Material.EMERALD);
		emeraldInfusedBootsRecipe.setIngredient('D', Material.DIAMOND);
		emeraldInfusedBootsRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(emeraldInfusedBootsRecipe);
		
		ShapedRecipe emeraldInfusedBootsRecipeBot = new ShapedRecipe(emeraldInfusedBoots);
		emeraldInfusedBootsRecipeBot.shape("aaa", "DaD", "EaE");
		emeraldInfusedBootsRecipeBot.setIngredient('E', Material.EMERALD);
		emeraldInfusedBootsRecipeBot.setIngredient('D', Material.DIAMOND);
		emeraldInfusedBootsRecipeBot.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(emeraldInfusedBootsRecipeBot);
		items.add(emeraldInfusedBoots);
		
		emeraldInfusedSword = createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Emerald Infused Sword", new String[]{"An Emerald Strengthened Sword"});
		emeraldInfusedSword = addAttribute(emeraldInfusedSword, "generic.attackDamage", 8, "mainhand", 756, 3410);
		emeraldInfusedSword = addAttribute(emeraldInfusedSword, "generic.attackSpeed", -2.4, "mainhand", 9857, 6741);
		ShapedRecipe emeraldInfusedSwordRecipe = new ShapedRecipe(emeraldInfusedSword);
		emeraldInfusedSwordRecipe.shape("aDa", "sDs", "aEa");
		emeraldInfusedSwordRecipe.setIngredient('E', Material.EMERALD);
		emeraldInfusedSwordRecipe.setIngredient('D', Material.DIAMOND);
		emeraldInfusedSwordRecipe.setIngredient('a', Material.AIR);
		emeraldInfusedSwordRecipe.setIngredient('s',Material.STICK);
		Bukkit.getServer().addRecipe(emeraldInfusedSwordRecipe);
		items.add(emeraldInfusedSword);
		
		emeraldInfusedAxe = createItem(Material.DIAMOND_AXE, ChatColor.GREEN + "Emerald Infused Axe", new String[]{"An Emerald Strengthened Axe"});
		emeraldInfusedAxe.addEnchantment(Enchantment.DIG_SPEED, 2);
		emeraldInfusedAxe = addAttribute(emeraldInfusedAxe, "generic.attackDamage", 10, "mainhand", 68731, 91723);
		emeraldInfusedAxe = addAttribute(emeraldInfusedAxe, "generic.attackSpeed", -3, "mainhand", 58712, 21735);
		ShapedRecipe emeraldInfusedAxeRecipe = new ShapedRecipe(emeraldInfusedAxe);
		emeraldInfusedAxeRecipe.shape("aDD", "aED", "asa");
		emeraldInfusedAxeRecipe.setIngredient('E', Material.EMERALD);
		emeraldInfusedAxeRecipe.setIngredient('D', Material.DIAMOND);
		emeraldInfusedAxeRecipe.setIngredient('a', Material.AIR);
		emeraldInfusedAxeRecipe.setIngredient('s', Material.STICK);
		Bukkit.getServer().addRecipe(emeraldInfusedAxeRecipe);
		items.add(emeraldInfusedAxe);
		
		/*Star Powered*/
		
		starPoweredChest = createItem(Material.DIAMOND_CHESTPLATE, ChatColor.DARK_AQUA + "Star Powered ChestPiece", new String[]{"A Star Imbued Emerald Chest Piece"});
		starPoweredChest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
		//starPoweredChest = addAttribute(starPoweredChest, "generic.armor", 12, "chest");
		//starPoweredChest = addAttribute(starPoweredChest, "generic.armorToughness", 6, "chest");
		ShapedRecipe starPoweredChestRecipe = new ShapedRecipe(starPoweredChest);
		starPoweredChestRecipe.shape("DaD", "DED", "DDD");
		starPoweredChestRecipe.setIngredient('E', Material.NETHER_STAR);
		starPoweredChestRecipe.setIngredient('D', Material.EMERALD);
		starPoweredChestRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(starPoweredChestRecipe);
		items.add(starPoweredChest);
		
		starPoweredLeggings = createItem(Material.DIAMOND_LEGGINGS, ChatColor.DARK_AQUA + "Star Powered Leggings", new String[]{"A Star Imbued Set Of Emerald Leggings"});
		starPoweredLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
		//starPoweredLeggings = addAttribute(starPoweredLeggings, "generic.armor", 8, "legs");
		//starPoweredLeggings = addAttribute(starPoweredLeggings, "generic.armorToughness", 6, "legs");
		ShapedRecipe starPoweredLeggingsRecipe = new ShapedRecipe(starPoweredLeggings);
		starPoweredLeggingsRecipe.shape("DED", "DaD", "DaD");
		starPoweredLeggingsRecipe.setIngredient('E', Material.NETHER_STAR);
		starPoweredLeggingsRecipe.setIngredient('D', Material.EMERALD);
		starPoweredLeggingsRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(starPoweredLeggingsRecipe);
		items.add(starPoweredLeggings);
		
		starPoweredHelmet = createItem(Material.DIAMOND_HELMET, ChatColor.DARK_AQUA + "Star Powered Helmet", new String[]{"A Star Imbued Emerald Helm"});
		starPoweredHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
		//starPoweredHelmet = addAttribute(starPoweredHelmet, "generic.armor", 5, "head");
		//starPoweredHelmet = addAttribute(starPoweredHelmet, "generic.armorToughness", 6, "head");
		ShapedRecipe starPoweredHelmetRecipe = new ShapedRecipe(starPoweredHelmet);
		starPoweredHelmetRecipe.shape("DED", "DaD", "aaa");
		starPoweredHelmetRecipe.setIngredient('E', Material.NETHER_STAR);
		starPoweredHelmetRecipe.setIngredient('D', Material.EMERALD);
		starPoweredHelmetRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(starPoweredHelmetRecipe);
		
		ShapedRecipe starPoweredHelmetRecipeBot = new ShapedRecipe(starPoweredHelmet);
		starPoweredHelmetRecipeBot.shape("aaa", "DED", "DaD");
		starPoweredHelmetRecipeBot.setIngredient('E', Material.NETHER_STAR);
		starPoweredHelmetRecipeBot.setIngredient('D', Material.EMERALD);
		starPoweredHelmetRecipeBot.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(starPoweredHelmetRecipeBot);
		items.add(starPoweredHelmet);
		
		starPoweredBoots = createItem(Material.DIAMOND_BOOTS, ChatColor.DARK_AQUA + "Star Powered Boots", new String[]{"A Star Imbued Pair Of Emerald Boots"});
		starPoweredBoots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
		starPoweredBoots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
		//starPoweredBoots = addAttribute(starPoweredBoots, "generic.armor", 5, "feet");
		//starPoweredBoots = addAttribute(starPoweredBoots, "generic.armorToughness", 6, "feet");
		ShapedRecipe starPoweredBootsRecipe = new ShapedRecipe(starPoweredBoots);
		starPoweredBootsRecipe.shape("DaD", "EaE", "aaa");
		starPoweredBootsRecipe.setIngredient('E', Material.NETHER_STAR);
		starPoweredBootsRecipe.setIngredient('D', Material.EMERALD);
		starPoweredBootsRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(starPoweredBootsRecipe);
		
		ShapedRecipe starPoweredBootsRecipeBot = new ShapedRecipe(starPoweredBoots);
		starPoweredBootsRecipeBot.shape("aaa", "DaD", "EaE");
		starPoweredBootsRecipeBot.setIngredient('E', Material.NETHER_STAR);
		starPoweredBootsRecipeBot.setIngredient('D', Material.EMERALD);
		starPoweredBootsRecipeBot.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(starPoweredBootsRecipeBot);
		items.add(starPoweredBoots);
		
		starPoweredSword = createItem(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + "Star Powered Sword", new String[]{"A Star Imbued Sword"});
		starPoweredSword = addAttribute(starPoweredSword, "generic.attackDamage", 10, "mainhand", 876, 452109);
		starPoweredSword = addAttribute(starPoweredSword, "generic.attackSpeed", -2.4, "mainhand", 8761, 71293);
		ShapedRecipe starPoweredSwordRecipe = new ShapedRecipe(starPoweredSword);
		starPoweredSwordRecipe.shape("aDa", "sDs", "aEa");
		starPoweredSwordRecipe.setIngredient('E', Material.NETHER_STAR);
		starPoweredSwordRecipe.setIngredient('D', Material.EMERALD);
		starPoweredSwordRecipe.setIngredient('a', Material.AIR);
		starPoweredSwordRecipe.setIngredient('s',Material.BLAZE_ROD);
		Bukkit.getServer().addRecipe(starPoweredSwordRecipe);
		items.add(starPoweredSword);
		
		starPoweredAxe = createItem(Material.DIAMOND_AXE, ChatColor.DARK_AQUA + "Star Powered Axe", new String[]{"A Star Imbued Axe"});
		starPoweredAxe.addEnchantment(Enchantment.DIG_SPEED, 5);
		starPoweredAxe = addAttribute(starPoweredAxe, "generic.attackDamage", 12, "mainhand", 59871, 509812);
		starPoweredAxe = addAttribute(starPoweredAxe, "generic.attackSpeed", -3, "mainhand", 120981, 959123);
		ShapedRecipe starPoweredAxeRecipe = new ShapedRecipe(starPoweredAxe);
		starPoweredAxeRecipe.shape("aDD", "aED", "asa");
		starPoweredAxeRecipe.setIngredient('E', Material.NETHER_STAR);
		starPoweredAxeRecipe.setIngredient('D', Material.EMERALD);
		starPoweredAxeRecipe.setIngredient('a', Material.AIR);
		starPoweredAxeRecipe.setIngredient('s', Material.BLAZE_ROD);
		Bukkit.getServer().addRecipe(starPoweredAxeRecipe);
		items.add(starPoweredAxe);
		
		/*Other OH Items*/
		
		silverDrachma = createItem(Material.IRON_NUGGET, ChatColor.GRAY + "Silver Drachma", new String[] {"The common currency of the greek world."});
		goldenDrachma = createItem(Material.GOLD_NUGGET, ChatColor.GOLD + "Golden Drachma", new String[] {"The advanced currency of the greek world"});
		
		hermesElytra = createItem(Material.ELYTRA, ChatColor.DARK_PURPLE + "Hermes Elytra", new String[] {});
		poseidonBoots = createItem(Material.LEATHER_BOOTS, ChatColor.AQUA + "Poseidon Boots", new String[] {});
		
		disallowedItems = new ItemStack[]{aegisShield,hermesElytra,poseidonBoots};
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
						} else {
							return (item1.getItemMeta().hasLore() == false && item2.getItemMeta().hasLore() == false);
						}
					}
				} else {
					return (item1.hasItemMeta() == false && item2.hasItemMeta() == false);
				}
			}
		} else {
			return (item1 == null && item2 == null);
		}
		return false;
	}
	/**
	 * Adds an attribute to an item (will add to existing value)
	 * Ensure that the uuidLeast and uuidMost are a unique pair
	 * @param item The item to which to add the attribute
	 * @param attributeName the name of the attribute (e.g. generic.attackDamage)
	 * @param value the value for the attribute
	 * @param slot the slot for the attribute to apply (mainhand, head, etc.). Use "all" for all slots.
	 * @param uuidLeast The least significant bits of the UUID
	 * @param uuidMost The most significant bits of the UUID 
	 * @return the modified ItemStack
	 */
	private static ItemStack addAttribute(ItemStack item, String attributeName, double value, String slot, int uuidLeast, int uuidMost) {
		net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
		NBTTagList modifiers = compound.getList("AttributeModifiers", 10);
		NBTTagCompound tag = new NBTTagCompound();
		tag.set("AttributeName", new NBTTagString(attributeName));
		tag.set("Name", new NBTTagString(attributeName));
		tag.set("Amount", new NBTTagDouble(value));
		// 0 is the additive operation. 1 and 2 are multiplicative
		tag.set("Operation", new NBTTagInt(0));
		tag.set("UUIDLeast", new NBTTagInt(uuidLeast));
		tag.set("UUIDMost", new NBTTagInt(uuidMost));
		if(!slot.equalsIgnoreCase("all"))
			tag.set("Slot", new NBTTagString(slot));
		
		modifiers.add(tag);
		compound.set("AttributeModifiers", modifiers);
		nmsStack.setTag(compound);
		
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
}
