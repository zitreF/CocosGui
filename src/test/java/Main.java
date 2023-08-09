import command.TestCommand;
import me.cocos.gui.CocosGui;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        CocosGui.initialize();
        this.getCommand("test").setExecutor(new TestCommand());
    }
}