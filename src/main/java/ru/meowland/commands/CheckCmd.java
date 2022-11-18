package ru.meowland.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ru.meowland.PlayerCheck;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CheckCmd implements CommandExecutor {

    File file = new File("plugins/PlayerCheck/config.yml");
    FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
    Plugin plugin;
    public CheckCmd(PlayerCheck meow){
        plugin = meow;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            throw new RuntimeException(ex);
        }
        Player player = (Player) sender;
        plugin.reloadConfig();

        if(configuration.get("adminName").equals("none")){
            player.sendMessage("хрень какая то");

            return true;
        }
        Player adm = Bukkit.getPlayer(configuration.get("adminName").toString());
        if(command.getName().equalsIgnoreCase("check")){

            adm.sendMessage(player.getName() + " дискорд: " + args[0]);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&lУспешно"));

            return true;
        }

        return false;
    }


}
