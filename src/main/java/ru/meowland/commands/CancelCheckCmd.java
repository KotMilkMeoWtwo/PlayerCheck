package ru.meowland.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class CancelCheckCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        File file = new File("plugins/PlayerCheck/config.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        if(command.getName().equalsIgnoreCase("cansellcheck")){


            configuration.set("playerName", "null");
            return true;
        }

        return false;
    }
}
