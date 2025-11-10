package me.exyin.partiesgui.utils.placeholders.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.utils.placeholders.interfaces.PGPlaceholder;

public class PGPlaceholderPlayerName implements PGPlaceholder {
  private final PartyPlayer partyPlayer;

  public PGPlaceholderPlayerName(final PartyPlayer partyPlayer) {
    this.partyPlayer = partyPlayer;
  }

  @Override
  public String getReplacement() {
    return partyPlayer.getName();
  }
}
