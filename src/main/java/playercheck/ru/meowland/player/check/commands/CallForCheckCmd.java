package playercheck.ru.meowland.player.check.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import playercheck.ru.meowland.player.check.PlayerCheck;

import java.util.Objects;
import java.util.PriorityQueue;

public class CallForCheckCmd implements CommandExecutor, Listener {

    PlayerCheck plugin;
    private Boolean meow;
    private Player player;
    public CallForCheckCmd(PlayerCheck nya){
        this.plugin = nya;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase("callforcheck")){
            Player p1 =  plugin.getServer().getPlayer(args[0]);
            player = p1;
            meow = true;
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(p1.getName() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("callforcheck"))));
                player.sendMessage(p1.getName() + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("callforcheck"))));
            }
            return true;
        }

        return false;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void leaveEvent(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(p == player && meow){
            p.banPlayerFull("Leave in check time");
        }

    }

}
