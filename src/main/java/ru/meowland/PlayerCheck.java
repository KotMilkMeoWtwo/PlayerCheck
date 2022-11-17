package ru.meowland;

import org.bukkit.plugin.java.JavaPlugin;
import ru.meowland.commands.CallForCheckCmds;
import ru.meowland.commands.CancelCheckCmd;
import ru.meowland.commands.CheckCmd;

import java.io.File;
import java.util.Objects;

public final class PlayerCheck extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("callforcheck")).setExecutor(new CallForCheckCmds(this));
        Objects.requireNonNull(getCommand("check")).setExecutor(new CheckCmd(this));
        Objects.requireNonNull(getCommand("canselcheck")).setExecutor(new CancelCheckCmd(this));

        getServer().getPluginManager().registerEvents(new EventListener(), this);
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists())
            saveDefaultConfig();
        reloadConfig();
    }

    @Override
    public void onDisable() {

    }
}
