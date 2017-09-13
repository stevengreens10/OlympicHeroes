package me.TehGoldyLockz.OlympicHeroes.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class OHItems {
	public static ItemStack TEST_ITEM;
	
	private static List<ItemStack> items = new ArrayList<ItemStack>();
	
	@SuppressWarnings("deprecation")
	public static void constructItems() {
		TEST_ITEM = createItem(Material.BOW, ChatColor.BLUE + "Test Item", new String[]{"This is a test item"});
		TEST_ITEM.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		ShapedRecipe recipe = new ShapedRecipe(TEST_ITEM);
		recipe.shape("SSA", "sAS", "AsS");
		recipe.setIngredient('S', Material.STICK);
		recipe.setIngredient('s', Material.STRING);
		recipe.setIngredient('A', Material.AIR);
		Bukkit.getServer().addRecipe(recipe);
		items.add(TEST_ITEM);
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
