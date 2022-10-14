package playercheck.ru.meowland.player.check;

import org.bukkit.plugin.java.JavaPlugin;
import playercheck.ru.meowland.player.check.commands.CallForCheckCmd;

public final class PlayerCheck extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("callforcheck").setExecutor(new CallForCheckCmd(this));

    }

    @Override
    public void onDisable() {

    }
}
