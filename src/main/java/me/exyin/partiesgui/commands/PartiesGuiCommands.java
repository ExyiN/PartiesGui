package me.exyin.partiesgui.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.exyin.partiesgui.PartiesGui;

public class PartiesGuiCommands {
  private final PartiesGui plugin;

  public PartiesGuiCommands(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  public LiteralCommandNode<CommandSourceStack> constructPartiesGuiCommand(final String commandName) {
    final PartiesGuiOpenCommand partiesGuiOpenCommand = new PartiesGuiOpenCommand(plugin);
    return Commands.literal(commandName)
            .requires(partiesGuiOpenCommand::canRun)
            .executes(partiesGuiOpenCommand::run)
            .build();
  }
}
