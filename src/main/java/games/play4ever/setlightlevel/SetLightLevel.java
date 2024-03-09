package games.play4ever.setlightlevel;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adds commands to set the light level of a block.
 *
 * @author Marcel Schoen
 */
public final class SetLightLevel extends JavaPlugin implements CommandExecutor, TabCompleter {
    private final String CONFIG_FILENAME = "config.yml";

    @Override
    public void onEnable() {
        // Plugin startup logic
        readConfig();
    }

    public static void logInfo(String message) {
        PluginLogger.getLogger(SetLightLevel.class.getName()).info("[SetLightLevel] " + message);
    }

    public static void logWarn(String message) {
        PluginLogger.getLogger(SetLightLevel.class.getName()).warning("[SetLightLevel] " + message);
    }

    public static void logError(String message) {
        PluginLogger.getLogger(SetLightLevel.class.getName()).severe("[SetLightLevel] " + message);
    }

    private void readConfig() {
        try {

            // TBD - no config yet

            // logInfo("Configuration loaded.");

        } catch (Exception e) {
            SetLightLevel.logError("Failed to load configuration: " + e);
            throw new RuntimeException(e);
        }
    }

    private static boolean isConsoleOrOP(CommandSender sender) {
        return (!(sender instanceof Player) || ((Player)sender).isOp());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        boolean consoleOrOP = isConsoleOrOP(sender);
        if(args != null && args.length > 0) {
            if(args.length == 1) {
                if(consoleOrOP) {
                    return Arrays.asList("help", "setlightlevel");
                }
            } else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("setlightlevel") && consoleOrOP) {
                    return Arrays.asList("x y z level" );
                }

                // TBD - handle every number of parameters

            }
        }
        return new ArrayList<>();
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        boolean consoleOrOP = isConsoleOrOP(sender);

        final String cmd = command.getName().toLowerCase();
        if (!cmd.equals("setlightlevel") && !cmd.equals("sl")) {
            return false;
        }

        if (args[0].equalsIgnoreCase("help")) {
            showHelp(sender);
            return true;
        } else if (args[0].equalsIgnoreCase("setlightlevel") && consoleOrOP) {
            if(args.length != 4) {
                sender.sendMessage(ChatColor.RED + "Invalid arguments for 'setlightlevel' command, must be 4, see help:");
                showHelp(sender);
            } else {




            }
            return true;
        }
        return false;
    }

    private void showHelp(final CommandSender sender) {
        sender.sendMessage(ChatColor.GREEN + "SetLightLevel commands:");
        if(isConsoleOrOP(sender)) {
            sender.sendMessage(ChatColor.GREEN + "help - shows this help");
            sender.sendMessage(ChatColor.GREEN + "setlightlevel <x> <y> <z> <level> - sets the light level of a block");
        }
    }

    @Override
    public void onDisable() {
    }
}
