package org.meteorfish.money_item.event;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.meteorfish.money_item.ItemManager;

import static org.meteorfish.money_item.ItemManager.*;
import static org.meteorfish.money_item.util.VaultUtils.getEconomy;

public class MoneyEvent implements Listener {

    private Economy econ;

    @EventHandler
    public void rightClickItem(PlayerInteractEvent event) {
        econ = getEconomy();
        Player player = event.getPlayer();
        if(isValidAction(player, event)) {
            ItemStack item = player.getItemInHand();
            identifyItem(item, player);
        }
    }

    private boolean isValidAction(Player player, PlayerInteractEvent event) {
        return event.getAction() == Action.RIGHT_CLICK_AIR && player.isSneaking();
    }

    private void identifyItem(ItemStack item, Player player) {
        if(item.isSimilar(_100_K)) {
            withdraw(_100_K_AMOUNT, player);
        } else if (item.isSimilar(_50_K)) {
            withdraw(_50_K_AMOUNT, player);
        } else if (item.isSimilar(_10_K)) {
            withdraw(_10_K_AMOUNT, player);
        } else if (item.isSimilar(_5_K)) {
            withdraw(_5_K_AMOUNT, player);
        } else if (item.isSimilar(_1_K)) {
            withdraw(_1_K_AMOUNT, player);
        }
        removeCurrentHand(player);
    }

    private void withdraw(int amount, Player player) {
        EconomyResponse r = econ.depositPlayer(player, amount);
        if(r.transactionSuccess()) {
            player.sendMessage(String.format("%s 을 입금되었습니다. 현재 잔액은 %s 입니다.", econ.format(r.amount), econ.format(r.balance)));
        } else {
            player.sendMessage(String.format("입금 과정에서 에러가 발생했습니다: %s", r.errorMessage));
        }
    }

    private void removeCurrentHand(Player player) {
        if(player.getInventory().getItemInHand().getAmount() == 1) {
            player.getInventory().setItemInHand(new ItemStack(Material.AIR));
        } else {
            int currentAmount = player.getInventory().getItemInHand().getAmount();
            player.getInventory().getItemInHand().setAmount(currentAmount - 1);
        }
    }
}
