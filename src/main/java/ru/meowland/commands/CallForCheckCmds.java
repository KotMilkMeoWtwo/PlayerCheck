package ru.meowland.commands;


import com.destroystokyo.paper.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import ru.meowland.PlayerCheck;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CallForCheckCmds implements CommandExecutor, Listener {

    PlayerCheck plugin;

    public CallForCheckCmds(PlayerCheck nya){
        this.plugin = nya;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        File file = new File("plugins/PlayerCheck/config.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase("callforcheck")){
            Player p1 =  plugin.getServer().getPlayer(args[0]);

            configuration.set("playerName", p1.getName());
            configuration.set("adminName", p.getName());
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Title title = new Title(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("titlerequests"))),
                    ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("subtitlerequests"))));
            Objects.requireNonNull(p1).sendTitle(title);
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(p1.getName() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("callforcheck"))));
                player.sendMessage(p1.getName() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("callforcheck"))));
            }
            return true;
        }



        return false;
    }

}
