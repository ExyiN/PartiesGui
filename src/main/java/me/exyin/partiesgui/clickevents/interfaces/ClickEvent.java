package me.exyin.partiesgui.clickevents.interfaces;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.bukkit.entity.Player;

public interface ClickEvent {
  boolean canExecute(Player whoClicked, PartyPlayer partyPlayer, int slot);
  void execute(Player whoClicked, PartyPlayer partyPlayer, int slot);
}
