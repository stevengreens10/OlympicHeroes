package me.TehGoldyLockz.OlympicHeroes.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagDouble;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;

public class OHItems {
	public static ItemStack LONG_BOW;
	public static ItemStack FIRE_STICK;
	public static ItemStack HERMES_BOOTS;
	
	private static List<ItemStack> items = new ArrayList<ItemStack>();
	
	@SuppressWarnings("deprecation")
	public static void constructItems() {
		LONG_BOW = createItem(Material.BOW, "LONG_BOW", new String[]{"This is a test item"});
		LONG_BOW.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		ShapedRecipe shapedRecipe = new ShapedRecipe(LONG_BOW);
		shapedRecipe.shape("SSA", "sAS", "AsS");
		shapedRecipe.setIngredient('S', Material.STICK);
		shapedRecipe.setIngredient('s', Material.STRING);
		shapedRecipe.setIngredient('A', Material.AIR);
		Bukkit.getServer().addRecipe(shapedRecipe);
		items.add(LONG_BOW);
		
		FIRE_STICK = createItem(Material.STICK, ChatColor.RED + "Fire stick thing", new String[] {"This is a fire stick"});
		FIRE_STICK.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		// Put attributes after changing name and adding enchants
		FIRE_STICK = addAttribute(FIRE_STICK, "generic.attackSpeed", 1024, "mainhand");
		FIRE_STICK = addAttribute(FIRE_STICK, "generic.attackDamage", 10000, "mainhand");
		// Have to put recipe after modifying attributes
		ShapelessRecipe shapelessRecipe = new ShapelessRecipe(FIRE_STICK);
		shapelessRecipe.addIngredient(Material.STICK);
		shapelessRecipe.addIngredient(Material.STICK);
		Bukkit.getServer().addRecipe(shapelessRecipe);
		items.add(FIRE_STICK);
		
		HERMES_BOOTS = createItem(Material.DIAMOND_BOOTS, ChatColor.GOLD + "Hermes Boots", new String[] {"sanic boots"});
		HERMES_BOOTS = addAttribute(HERMES_BOOTS, "generic.movementSpeed", 0.3, "feet");
		shapedRecipe = new ShapedRecipe(HERMES_BOOTS);
		shapedRecipe.shape("dfd","dad","aaa");
		shapedRecipe.setIngredient('d', Material.DIAMOND);
		shapedRecipe.setIngredient('f', Material.FEATHER);
		shapedRecipe.setIngredient('a', Material.AIR);
		Bukkit.getServer().addRecipe(shapedRecipe);
		items.add(HERMES_BOOTS);
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
