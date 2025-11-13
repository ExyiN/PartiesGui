package me.exyin.partiesgui.utils.placeholders.impl;

import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.utils.placeholders.interfaces.PGPlaceholder;

import java.util.UUID;

public class PGPlaceholderPartyOnlineMembers implements PGPlaceholder {
  private final PartiesGui plugin;
  private final PartyPlayer partyPlayer;

  public PGPlaceholderPartyOnlineMembers(final PartiesGui plugin, final PartyPlayer partyPlayer) {
    this.plugin = plugin;
    this.partyPlayer = partyPlayer;
  }

  @Override
  public String getPlaceholderName() {
    return "{party-online-members}";
  }

  @Override
  public String getReplacement() {
    final UUID partyUuid = partyPlayer.getPartyId();
    assert partyUuid != null;
    final Party party = plugin.getPartiesAPI().getParty(partyUuid);
    assert party != null;
    return String.valueOf(party.getOnlineMembers().size());
  }
}
