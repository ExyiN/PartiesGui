package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import me.exyin.partiesgui.gui.SettingsGui;
import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.UUID;

public class ClickEventPartyChangeMotd implements ClickEvent {
  private final PartiesGui plugin;

  public ClickEventPartyChangeMotd(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean canExecute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return plugin.getGuiUtil().getInt("settings-gui", "items." + slot + ".required-rank-level") <= partyPlayer.getRank();
  }

  @Override
  public void execute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    plugin.getChatInputUtil().addChatInput(whoClicked.getUniqueId(), this);
    plugin.getSoundUtil().playClickSound(whoClicked);
    whoClicked.closeInventory();
    plugin.getMessageUtil().sendMessage(whoClicked, "settings.motd");
  }

  @Override
  public void processInput(final UUID uuid, final String input) {
    final Player player = plugin.getServer().getPlayer(uuid);
    if (player == null) {
      return;
    }
    final String partyCommand = plugin.getConfigUtil().getString("parties-commands.party", "party");
    final String subCommand = plugin.getConfigUtil().getString("parties-commands.sub-commands.motd", "motd");
    plugin.getServer().getScheduler().runTask(plugin, () -> player.performCommand(MessageFormat.format("{0} {1} {2}", partyCommand, subCommand, input)));
    plugin.getServer().getScheduler().runTaskLater(plugin, () -> player.openInventory(new SettingsGui(plugin, plugin.getPartiesAPI().getPartyPlayer(uuid)).getInventory()), 5L);
  }
}
