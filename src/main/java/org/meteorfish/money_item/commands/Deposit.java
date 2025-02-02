package org.meteorfish.money_item.commands;

import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.meteorfish.money_item.util.VaultUtils.getEconomy;

public class Deposit implements CommandExecutor {

    private static final List<Integer> CURRENCY_UNIT = new ArrayList<>(Arrays.asList(1000, 5000, 10000, 50000, 100000));
    private static Economy econ;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        try {
            Player player = (Player) sender;
            econ = getEconomy();

            isValidParameter(strings);

            int unit = Integer.parseInt(strings[0]);
            int count = Integer.parseInt(strings[1]);
            int totalAmount = unit * count;
            int userBalance = (int) econ.getBalance(player.getName());

            isValidUnit(unit);
            isValidMoneyAmount(totalAmount, userBalance);

            deposit(player, totalAmount);
            return false;
        } catch(Exception e) {
            sender.sendMessage(e.getMessage());
            return false;
        }
    }

    private void isValidParameter(String[] strings) {
        if(strings.length != 2) {
            throw new IllegalArgumentException("명령어를 확인해주세요!");
        }
    }

    private void isValidUnit(int unit) {
        if (!CURRENCY_UNIT.contains(unit)) {
            throw new NumberFormatException("화폐 단위를 확인해주세요!");
        }
    }

    private void isValidMoneyAmount(int totalAmount, int userBalance) {
        if(totalAmount > userBalance) {
            throw new IllegalArgumentException("유저의 잔액이 부족합니다.");
        }
    }

    private void deposit(Player player, int totalAmount) {
        // 시스템 입장에서 withdraw 되므로 player는 deposit 됨
        EconomyResponse r = econ.withdrawPlayer(player, totalAmount);
        if(r.transactionSuccess()) {
            player.sendMessage(String.format("%s 을 인출했습니다. 현재 잔액은 %s 입니다.", econ.format(r.amount), econ.format(r.balance)));
        } else {
            player.sendMessage(String.format("인출 과정에서 에러가 발생했습니다: %s", r.errorMessage));
        }
    }
}
