package me.exyin.partiesgui.utils.placeholders.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.utils.placeholders.interfaces.PGPlaceholder;
import org.bukkit.OfflinePlayer;

public class PGPlaceholderPlayerOnlineStatus implements PGPlaceholder {
  private final PartiesGui plugin;
  private final PartyPlayer partyPlayer;

  public PGPlaceholderPlayerOnlineStatus(final PartiesGui plugin, final PartyPlayer partyPlayer) {
    this.plugin = plugin;
    this.partyPlayer = partyPlayer;
  }

  @Override
  public String getReplacement() {
    final OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(partyPlayer.getPlayerUUID());
    return offlinePlayer.isOnline() ? plugin.getMessageConfigUtil().getString("player.online")
            : plugin.getMessageConfigUtil().getString("player.offline");
  }
}
