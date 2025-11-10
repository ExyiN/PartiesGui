package me.exyin.partiesgui.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.exyin.partiesgui.PartiesGui;

import java.util.Objects;

public record PartiesGuiReloadCommand(PartiesGui plugin) {
  public int run(final CommandContext<CommandSourceStack> ctx) {
    plugin.getConfigUtil().setupConfigFile();
    plugin.getMessageConfigUtil().setupConfigFile();
    plugin.getMessageUtil().sendMessage(Objects.requireNonNull(ctx.getSource().getExecutor()), "reload");
    return Command.SINGLE_SUCCESS;
  }
}
