package org.meteorfish.money_item;

import org.bukkit.plugin.java.JavaPlugin;
import org.meteorfish.money_item.commands.Deposit;
import org.meteorfish.money_item.event.MoneyEvent;

import static org.meteorfish.money_item.util.VaultUtils.setupEconomy;

public final class Money_item extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("money item 플러그인 활성화");
        getServer().getPluginCommand("tdeposit").setExecutor(new Deposit());
        getServer().getPluginCommand("tdeposit").setTabCompleter(new Deposit());

        getServer().getPluginManager().registerEvents(new MoneyEvent(), this);

        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("money item 플러그인 비활성화");
    }
}
