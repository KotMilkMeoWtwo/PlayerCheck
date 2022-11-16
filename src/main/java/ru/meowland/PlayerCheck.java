package ru.meowland;

import org.bukkit.plugin.java.JavaPlugin;
import ru.meowland.commands.CallForCheckCmds;
import ru.meowland.commands.CancelCheckCmd;
import ru.meowland.commands.SendDiscordCmd;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class PlayerCheck extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("callforcheck")).setExecutor(new CallForCheckCmds(this));
        Objects.requireNonNull(getCommand("senddiscord")).setExecutor(new SendDiscordCmd());
        Objects.requireNonNull(getCommand("canselcheck")).setExecutor(new CancelCheckCmd());
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists())
            saveDefaultConfig();
        reloadConfig();
    }

    @Override
    public void onDisable() {

    }
}
