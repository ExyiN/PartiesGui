package me.exyin.partiesgui.commands;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.gui.MainGui;
import org.bukkit.entity.Player;

public class PartiesGuiOpenCommand {
  private final PartiesGui plugin;

  public PartiesGuiOpenCommand(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  public int run(final CommandContext<CommandSourceStack> ctx) {
    final Player player = (Player) ctx.getSource().getExecutor();
    final PartyPlayer partyPlayer = plugin.getPartiesAPI().getPartyPlayer(player.getUniqueId());
    if (partyPlayer == null || !partyPlayer.isInParty()) {
      plugin.getMessageUtil().sendMessage(player, "error.not-in-party");
    } else {
      final MainGui mainGui = new MainGui(plugin, partyPlayer);
      player.openInventory(mainGui.getInventory());
    }
    return Command.SINGLE_SUCCESS;
  }

  public boolean canRun(final CommandSourceStack commandSourceStack) {
    return commandSourceStack.getExecutor() instanceof final Player player && player.hasPermission("partiesgui.user.gui");
  }
}
