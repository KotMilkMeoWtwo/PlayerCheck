package ru.meowland;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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


import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class EventListener implements Listener {


    File file = new File("plugins/PlayerCheck/config.yml");
    FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

    @EventHandler(priority = EventPriority.HIGHEST)
    public void leaveEvent(PlayerQuitEvent e){
        Player p = e.getPlayer();
        try {
            configuration.load(file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InvalidConfigurationException ex) {
            throw new RuntimeException(ex);
        }
        Player p1 = Bukkit.getPlayer(Objects.requireNonNull(configuration.get("playerName")).toString());
        p.sendMessage(Objects.requireNonNull(p1).getName());
        if(p.getName().equals(Objects.requireNonNull(p1).getName())){
            p.banPlayerFull(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(configuration.get("banreason")).toString()));
        }
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        try {
            configuration.load(file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InvalidConfigurationException ex) {
            throw new RuntimeException(ex);
        }
        Player p = event.getPlayer();
        Player p1 = Bukkit.getPlayer(configuration.get("playerName").toString());
        Location q = event.getFrom();
        Location w = event.getTo();
        if (p.getName().equals(Objects.requireNonNull(p1).getName())) {
            if (q.getBlockX() != w.getBlockX() || q.getBlockY() != w.getBlockY() || q.getBlockZ() != w.getBlockZ()) {
                event.setTo(q);
            }
        }
    }
}
