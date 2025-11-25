package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import me.exyin.partiesgui.gui.SettingsGui;
import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClickEventPartySettings implements ClickEvent {
  private final PartiesGui plugin;

  public ClickEventPartySettings(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean canExecute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return plugin.getGuiUtil().getInt("main-gui", "items." + slot + ".required-rank-level") <= partyPlayer.getRank();
  }

  @Override
  public void execute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    plugin.getSoundUtil().playClickSound(whoClicked);
    final SettingsGui settingsGui = new SettingsGui(plugin, partyPlayer);
    whoClicked.openInventory(settingsGui.getInventory());
  }

  @Override
  public void processInput(final UUID uuid, final String input) {
    // Nothing
  }
}
