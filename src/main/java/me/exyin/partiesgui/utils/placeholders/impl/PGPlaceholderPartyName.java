package me.exyin.partiesgui.utils.placeholders.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.utils.placeholders.interfaces.PGPlaceholder;

public class PGPlaceholderPartyName implements PGPlaceholder {
  private final PartyPlayer partyPlayer;

  public PGPlaceholderPartyName(final PartyPlayer partyPlayer) {
    this.partyPlayer = partyPlayer;
  }

  @Override
  public String getPlaceholderName() {
    return "{party-name}";
  }

  @Override
  public String getReplacement() {
    return partyPlayer.getPartyName() == null ? "" : partyPlayer.getPartyName();
  }
}
