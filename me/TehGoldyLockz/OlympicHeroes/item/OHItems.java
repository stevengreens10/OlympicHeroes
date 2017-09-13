package me.TehGoldyLockz.OlympicHeroes.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class OHItems {
	public static ItemStack POWER_BOW;
	public static ItemStack FIRE_STICK;
	
	private static List<ItemStack> items = new ArrayList<ItemStack>();
	
	@SuppressWarnings("deprecation")
	public static void constructItems() {
		POWER_BOW = createItem(Material.BOW, ChatColor.BLUE + "Test Item", new String[]{"This is a test item"});
		POWER_BOW.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		ShapedRecipe shapedRecipe = new ShapedRecipe(POWER_BOW);
		shapedRecipe.shape("SSA", "sAS", "AsS");
		shapedRecipe.setIngredient('S', Material.STICK);
		shapedRecipe.setIngredient('s', Material.STRING);
		shapedRecipe.setIngredient('A', Material.AIR);
		Bukkit.getServer().addRecipe(shapedRecipe);
		items.add(POWER_BOW);
		
		FIRE_STICK = createItem(Material.DIAMOND_SWORD, ChatColor.RED + "Fire stick thing", new String[] {"This is a fire stick"});
		FIRE_STICK.addEnchantment(Enchantment.FIRE_ASPECT, 1);
		ShapelessRecipe shapelessRecipe = new ShapelessRecipe(FIRE_STICK);
		shapelessRecipe.addIngredient(Material.STICK);
		shapelessRecipe.addIngredient(Material.STICK);
		Bukkit.getServer().addRecipe(shapelessRecipe);
		items.add(FIRE_STICK);
		
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
}
