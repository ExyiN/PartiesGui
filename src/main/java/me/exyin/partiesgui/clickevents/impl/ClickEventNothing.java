package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClickEventNothing implements ClickEvent {
  @Override
  public boolean canExecute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return false;
  }

  @Override
  public void execute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    // Do nothing
  }

  @Override
  public void processInput(final UUID uuid, final String input) {

  }
}
