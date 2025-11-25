package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import me.exyin.partiesgui.gui.InvitationsGui;
import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.UUID;

public class ClickEventPartyInvite implements ClickEvent {
  private final PartiesGui plugin;

  public ClickEventPartyInvite(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean canExecute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return plugin.getGuiUtil().getInt("invitations-gui", "invite-items.required-rank-level") <= partyPlayer.getRank();
  }

  @Override
  public void execute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    final String partyCommand = plugin.getConfigUtil().getString("parties-commands.party", "party");
    final String subCommand = plugin.getConfigUtil().getString("parties-commands.sub-commands.invite", "invite");
    if (gui instanceof final InvitationsGui invitationsGui) {
      if (slot >= invitationsGui.getPages().get(invitationsGui.getPIndex()).size()) {
        return;
      }
      final String playerName = invitationsGui.getPages().get(invitationsGui.getPIndex()).get(slot).getName();
      plugin.getSoundUtil().playClickSound(whoClicked);
      whoClicked.performCommand(MessageFormat.format("{0} {1} {2}", partyCommand, subCommand, playerName));
    }
  }

  @Override
  public void processInput(final UUID uuid, final String input) {
    // Nothing
  }
}
