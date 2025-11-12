package me.exyin.partiesgui.clickevents.interfaces;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.entity.Player;

public interface ClickEvent {
  boolean canExecute(PGGui gui, Player whoClicked, PartyPlayer partyPlayer, int slot);
  void execute(PGGui gui, Player whoClicked, PartyPlayer partyPlayer, int slot);
}
