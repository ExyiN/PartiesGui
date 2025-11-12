package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import org.bukkit.entity.Player;

public class ClickEventNothing implements ClickEvent {
  @Override
  public boolean canExecute(final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return false;
  }

  @Override
  public void execute(final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    // Do nothing
  }
}
