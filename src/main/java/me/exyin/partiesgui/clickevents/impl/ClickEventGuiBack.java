package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import me.exyin.partiesgui.gui.MainGui;
import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClickEventGuiBack implements ClickEvent {
  private final PartiesGui plugin;

  public ClickEventGuiBack(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean canExecute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return true;
  }

  @Override
  public void execute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    plugin.getSoundUtil().playClickSound(whoClicked);
    final MainGui mainGui = new MainGui(plugin, partyPlayer);
    whoClicked.openInventory(mainGui.getInventory());
  }

  @Override
  public void processInput(final UUID uuid, final String input) {
    // Nothing
  }
}
