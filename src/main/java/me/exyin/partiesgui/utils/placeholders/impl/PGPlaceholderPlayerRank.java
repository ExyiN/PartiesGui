package me.exyin.partiesgui.utils.placeholders.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import com.alessiodp.parties.api.interfaces.PartyRank;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.utils.placeholders.interfaces.PGPlaceholder;

import java.util.Set;

public class PGPlaceholderPlayerRank implements PGPlaceholder {
  private final PartiesGui plugin;
  private final PartyPlayer partyPlayer;

  public PGPlaceholderPlayerRank(final PartiesGui plugin, final PartyPlayer partyPlayer) {
    this.plugin = plugin;
    this.partyPlayer = partyPlayer;
  }

  @Override
  public String getReplacement() {
    final Set<PartyRank> partyRanks = plugin.getPartiesAPI().getRanks();
    final PartyRank rank = partyRanks.stream().filter(partyRank -> partyRank.getLevel() == partyPlayer.getRank()).toList().getFirst();
    return rank.getName();
  }
}
