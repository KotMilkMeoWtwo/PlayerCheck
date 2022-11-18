package ru.meowland.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import ru.meowland.PlayerCheck;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CancelCheckCmd implements CommandExecutor {

    Plugin plugin;
    public CancelCheckCmd(PlayerCheck meow){
        plugin = meow;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        File file = new File("plugins/PlayerCheck/config.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        Player player = (Player) sender;
        if(command.getName().equalsIgnoreCase("canselcheck")){
            try {
                configuration.load(file);
            } catch (IOException | InvalidConfigurationException ex) {
                throw new RuntimeException(ex);
            }
            plugin.reloadConfig();
            if(configuration.get("playerName").equals("none")){
                player.sendMessage("проверка не идёт");
                return true;
            }

            Player player1 = Bukkit.getPlayer(Objects.requireNonNull(configuration.get("playerName")).toString());

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&lУспешно"));

            Objects.requireNonNull(player1).removePotionEffect(PotionEffectType.SLOW);
            player1.removePotionEffect(PotionEffectType.SLOW_DIGGING);

            configuration.set("adminName", "none");
            configuration.set("playerName", "none");
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return true;
        }

        return false;
    }
}
