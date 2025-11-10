package me.exyin.partiesgui.utils.placeholders;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.utils.placeholders.impl.*;
import me.exyin.partiesgui.utils.placeholders.interfaces.PGPlaceholder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PGPlaceholderFactory {
  private final Map<String, PGPlaceholder> map;
  private final List<String> placeholders = List.of("{party-name}", "{party-desc}", "{party-leader}", "{player-name}", "{player-rank}");

  public PGPlaceholderFactory(final PartiesGui plugin, final PartyPlayer partyPlayer) {
    map = new HashMap<>();
    map.put("{party-name}", new PGPlaceholderPartyName(partyPlayer));
    map.put("{party-desc}", new PGPlaceholderPartyDesc(plugin, partyPlayer));
    map.put("{party-leader}", new PGPlaceholderPartyLeader(plugin, partyPlayer));
    map.put("{player-name}", new PGPlaceholderPlayerName(partyPlayer));
    map.put("{player-rank}", new PGPlaceholderPlayerRank(plugin, partyPlayer));
    map.put("default", new PGPlaceholderDefault());
  }

  public PGPlaceholder of(final String placeholder) {
    if (!placeholders.contains(placeholder)) {
      return map.get("default");
    }
    return map.get(placeholder);
  }
}
