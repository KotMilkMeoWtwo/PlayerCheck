package playercheck.ru.meowland.player.check.commands;

import com.destroystokyo.paper.event.player.PlayerSetSpawnEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import playercheck.ru.meowland.player.check.PlayerCheck;

public class CallOnCheckCmd implements CommandExecutor {

    PlayerCheck plugin;

    public CallOnCheckCmd(PlayerCheck nya){
        this.plugin = nya;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("calloncheck")){
            Player p1 =  plugin.getServer().getPlayer(args[0]);
            return true;
        }

        return false;
    }
}
