package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import org.bukkit.entity.Player;

public class ClickEventPartySettings implements ClickEvent {
  private final PartiesGui plugin;

  public ClickEventPartySettings(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean canExecute(final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return plugin.getGuiUtil().getInt("main-gui", "items." + slot + ".required-rank-level") < partyPlayer.getRank();
  }

  @Override
  public void execute(final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    plugin.getSoundUtil().playClickSound(whoClicked);
    whoClicked.sendRichMessage("Settings");
  }
}
