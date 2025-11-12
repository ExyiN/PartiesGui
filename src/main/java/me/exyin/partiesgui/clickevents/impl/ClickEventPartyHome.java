package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class ClickEventPartyHome implements ClickEvent {
  private final PartiesGui plugin;

  public ClickEventPartyHome(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean canExecute(final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return true;
  }

  @Override
  public void execute(final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    final String partyCommand = plugin.getConfigUtil().getString("parties-commands.party", "party");
    final String homeSubCommand = plugin.getConfigUtil().getString("parties-commands.sub-commands.home", "home");
    plugin.getSoundUtil().playClickSound(whoClicked);
    whoClicked.performCommand(MessageFormat.format("{0} {1}", partyCommand, homeSubCommand));
    whoClicked.closeInventory();
  }
}
