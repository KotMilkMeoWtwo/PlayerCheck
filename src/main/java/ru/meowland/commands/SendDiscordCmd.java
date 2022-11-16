package ru.meowland.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class SendDiscordCmd implements CommandExecutor, Listener {

    File file = new File("plugins/PlayerCheck/config.yml");
    FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;
        Player adm = Bukkit.getPlayer(String.valueOf(Objects.requireNonNull(configuration.get("adminName"))));
        if(command.getName().equalsIgnoreCase("senddiscord")){

            Objects.requireNonNull(adm).sendMessage(player.getName() + " дискорд: " + args[0]);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(configuration.get("successful")).toString()));

            return true;
        }

        return false;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void leaveEvent(PlayerQuitEvent e){
        Player p = e.getPlayer();
        Player p1 = Bukkit.getPlayer(Objects.requireNonNull(configuration.get("playerName")).toString());
        if(p == p1){
            p.banPlayerFull(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(configuration.get("banreson")).toString()));
        }
    }
}
