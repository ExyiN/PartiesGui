package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import org.bukkit.entity.Player;

public class ClickEventPartyMembers implements ClickEvent {
  private final PartiesGui plugin;

  public ClickEventPartyMembers(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean canExecute(final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return true;
  }

  @Override
  public void execute(final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    plugin.getSoundUtil().playClickSound(whoClicked);
    whoClicked.sendRichMessage("Members");
  }
}
