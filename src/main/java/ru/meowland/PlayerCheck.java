package ru.meowland;

import org.bukkit.plugin.java.JavaPlugin;
import ru.meowland.commands.CallForCheckCmds;

import java.io.File;
import java.io.IOException;

public final class PlayerCheck extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("callforcheck").setExecutor(new CallForCheckCmds(this));
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists())
            saveDefaultConfig();
        reloadConfig();
    }

    @Override
    public void onDisable() {

    }
}
