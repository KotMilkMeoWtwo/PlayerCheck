package playercheck.ru.meowland.player.check;

import org.bukkit.plugin.java.JavaPlugin;
import playercheck.ru.meowland.player.check.commands.CallOnCheckCmd;

public final class PlayerCheck extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("calloncheck").setExecutor(new CallOnCheckCmd(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
