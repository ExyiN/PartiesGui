package me.exyin.partiesgui.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.exyin.partiesgui.PartiesGui;

public class PartiesGuiReloadCommand {
  private final PartiesGui plugin;

  public PartiesGuiReloadCommand(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  public int run(final CommandContext<CommandSourceStack> ctx) {
    plugin.getConfigUtil().setupConfigFile();
    plugin.getMessageConfigUtil().setupConfigFile();
    plugin.getGuiUtil().setupConfigFile();
    plugin.getMessageUtil().sendMessage(ctx.getSource().getSender(), "reload");
    return Command.SINGLE_SUCCESS;
  }
}
