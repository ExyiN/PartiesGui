package me.exyin.partiesgui.utils.placeholders;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.utils.placeholders.impl.*;
import me.exyin.partiesgui.utils.placeholders.interfaces.PGPlaceholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PGPlaceholderFactory {
  private final PartiesGui plugin;
  private final PartyPlayer partyPlayer;
  private final Map<String, PGPlaceholder> map;
  private List<PGPlaceholder> placeholders;

  public PGPlaceholderFactory(final PartiesGui plugin, final PartyPlayer partyPlayer) {
    this.plugin = plugin;
    this.partyPlayer = partyPlayer;
    map = new HashMap<>();
    getPlaceholders();
    placeholders.forEach(pgPlaceholder -> map.put(pgPlaceholder.getPlaceholderName(), pgPlaceholder));
  }

  private void getPlaceholders() {
    placeholders = new ArrayList<>();
    placeholders.add(new PGPlaceholderPartyDesc(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPartyLeader(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPartyLevel(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPartyLevelUpCurrent(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPartyLevelUpNecessary(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPartyName(partyPlayer));
    placeholders.add(new PGPlaceholderPartyNameColored(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPartyOnlineMembers(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPartyTag(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPartyTotalMembers(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPlayerName(partyPlayer));
    placeholders.add(new PGPlaceholderPlayerOnlineStatus(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPlayerRank(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderPlayerRankChat(plugin, partyPlayer));
    placeholders.add(new PGPlaceholderDefault());
  }

  public PGPlaceholder of(final String placeholder) {
    if (!placeholders.stream().map(PGPlaceholder::getPlaceholderName).toList().contains(placeholder)) {
      return map.get("default");
    }
    return map.get(placeholder);
  }
}
