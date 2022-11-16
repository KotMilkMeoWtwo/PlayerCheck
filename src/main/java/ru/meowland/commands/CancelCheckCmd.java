package ru.meowland.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CancelCheckCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        File file = new File("plugins/PlayerCheck/config.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        Player player = (Player) sender;
        Player player1 = Bukkit.getPlayer(Objects.requireNonNull(configuration.get("playerName")).toString());
        if(command.getName().equalsIgnoreCase("canselcheck")){

            Objects.requireNonNull(player1).removePotionEffect(PotionEffectType.SLOW);
            player1.removePotionEffect(PotionEffectType.SLOW_DIGGING);

            configuration.set("adminName", "nul");
            configuration.set("playerName", "null");
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(configuration.get("successful")).toString()));

            return true;
        }

        return false;
    }
}
