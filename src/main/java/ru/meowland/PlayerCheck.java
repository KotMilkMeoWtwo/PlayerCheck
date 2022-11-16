package ru.meowland;

import org.bukkit.plugin.java.JavaPlugin;
import ru.meowland.commands.CheckCmds;

public final class PlayerCheck extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("callforcheck").setExecutor(new CheckCmds(this));
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        getConfig();
        reloadConfig();
    }

    @Override
    public void onDisable() {

    }
}
