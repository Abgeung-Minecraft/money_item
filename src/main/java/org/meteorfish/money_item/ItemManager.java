package org.meteorfish.money_item;

import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemManager {

    public final static int _100_K_AMOUNT = 100000;
    public final static int _50_K_AMOUNT = 50000;
    public final static int _10_K_AMOUNT = 10000;
    public final static int _5_K_AMOUNT = 5000;
    public final static int _1_K_AMOUNT = 1000;

    private final static String WITHDRAW_DESCRIPTION = "입금하려면 허공에 [SHIFT + 우클릭]";

    private static ItemStack buildItem(Material material, int amount, String displayName, String... lore) {
        ItemStack stack = new ItemStack(material, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));
        stack.setItemMeta(meta);
        return stack;
    }

    public static final ItemStack _100_K = buildItem(
            Material.DIAMOND,
            1,
            ChatColor.AQUA +"100,000 골드",
            "우린 부자가 될 거야!",
            ChatColor.LIGHT_PURPLE + WITHDRAW_DESCRIPTION);

    public static final ItemStack _50_K = buildItem(
            Material.GOLD_INGOT,
            1,
            ChatColor.GOLD +"50,000 골드",
            "50,000 골드 가치의 화폐",
            ChatColor.LIGHT_PURPLE + WITHDRAW_DESCRIPTION);

    public static final ItemStack _10_K = buildItem(
            Material.GOLD_INGOT,
            1,
            ChatColor.GOLD +"10,000 골드",
            "10,000 골드 가치의 화폐",
            ChatColor.LIGHT_PURPLE + WITHDRAW_DESCRIPTION);

    public static final ItemStack _5_K = buildItem(
            Material.GOLD_INGOT,
            1,
            ChatColor.GOLD +"5,000 골드",
            "5,000 골드 가치의 화폐",
            ChatColor.LIGHT_PURPLE + WITHDRAW_DESCRIPTION);

    public static final ItemStack _1_K = buildItem(
            Material.GOLD_INGOT,
            1,
            ChatColor.GOLD +"1,000 골드",
            "1,000 골드 가치의 화폐",
            ChatColor.LIGHT_PURPLE + WITHDRAW_DESCRIPTION);


}
