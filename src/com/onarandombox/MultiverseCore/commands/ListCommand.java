package com.onarandombox.MultiverseCore.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.onarandombox.MultiverseCore.MVWorld;
import com.onarandombox.MultiverseCore.MultiverseCore;

public class ListCommand extends MultiverseCommand {

    public ListCommand(MultiverseCore plugin) {
        super(plugin);
        this.commandName = "World Listing";
        this.commandDesc = "Displays a listing of all worlds that you can enter";
        this.commandUsage = "/mvlist";
        this.minimumArgLength = 0;
        this.maximumArgLength = 0;
        this.commandKeys.add("mvlist");
        this.commandKeys.add("mvl");
        this.commandKeys.add("mv list");
        this.permission = "multiverse.world.list";
        this.opRequired = false;
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;
        }

        String output = ChatColor.LIGHT_PURPLE + "Worlds which you can view:\n";
        for (MVWorld world : this.plugin.getMVWorlds()) {

            if (p != null && (!this.plugin.ph.canEnterWorld(p, world))) {
                continue;
            }

            ChatColor color = ChatColor.GOLD;
            Environment env = world.getEnvironment();
            if (env == Environment.NETHER) {
                color = ChatColor.RED;
            } else if (env == Environment.NORMAL) {
                color = ChatColor.GREEN;
            } else if (env == Environment.SKYLANDS) {
                color = ChatColor.AQUA;
            }
            output += world.getColoredWorldString() + ChatColor.WHITE + " - " + color + world.getEnvironment() + " \n";

        }
        String[] response = output.split("\n");
        for (String msg : response) {
            sender.sendMessage(msg);
        }
    }
}