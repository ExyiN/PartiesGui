package me.exyin.partiesgui.utils.placeholders.impl;

import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyColor;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.utils.placeholders.interfaces.PGPlaceholder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class PGPlaceholderPartyNextColorCode implements PGPlaceholder {
  private final PartiesGui plugin;
  private final PartyPlayer partyPlayer;

  public PGPlaceholderPartyNextColorCode(final PartiesGui plugin, final PartyPlayer partyPlayer) {
    this.plugin = plugin;
    this.partyPlayer = partyPlayer;
  }

  @Override
  public String getPlaceholderName() {
    return "{party-next-color-code}";
  }

  @Override
  public String getReplacement() {
    final List<PartyColor> colors = new ArrayList<>(plugin.getPartiesAPI().getColors());
    final Comparator<PartyColor> comparator = Comparator.comparing(PartyColor::getName);
    colors.sort(comparator);
    final UUID partyUuid = partyPlayer.getPartyId();
    assert partyUuid != null;
    final Party party = plugin.getPartiesAPI().getParty(partyUuid);
    assert party != null;
    final PartyColor partyColor = party.getColor();
    int colorIndex = -1;
    if (partyColor != null) {
      colorIndex = colors.indexOf(partyColor);
    }
    colorIndex++;
    if (colorIndex >= colors.size()) {
      colorIndex = -1;
    }
    return colorIndex == -1 ? "" : colors.get(colorIndex).getCode();
  }
}
