package me.exyin.partiesgui.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.exyin.partiesgui.PartiesGui;

public class PartiesGuiAdminCommands {
  private final PartiesGui plugin;

  public PartiesGuiAdminCommands(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  public LiteralCommandNode<CommandSourceStack> constructPartiesGuiCommand(final String commandName) {
    final PartiesGuiReloadCommand partiesGuiReloadCommand = new PartiesGuiReloadCommand(plugin);
    return Commands.literal(commandName)
            .requires(source -> source.getSender().hasPermission("partiesgui.admin"))
            .then(Commands.literal("reload")
                    .executes(partiesGuiReloadCommand::run))
            .build();
  }
}
