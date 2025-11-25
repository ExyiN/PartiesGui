package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import me.exyin.partiesgui.gui.MembersGui;
import me.exyin.partiesgui.gui.interfaces.PGGui;
import me.exyin.partiesgui.gui.interfaces.PageableGui;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClickEventGuiNextPage implements ClickEvent {
  private final PartiesGui plugin;

  public ClickEventGuiNextPage(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean canExecute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return gui instanceof final MembersGui membersGui && membersGui.getPIndex() < (membersGui.getPages().size() - 1);
  }

  @Override
  public void execute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    if (!(gui instanceof final PageableGui pageableGui)) {
      return;
    }
    plugin.getSoundUtil().playClickSound(whoClicked);
    pageableGui.setPIndex(pageableGui.getPIndex() + 1);
    gui.setup();
  }

  @Override
  public void processInput(final UUID uuid, final String input) {
    // Nothing
  }
}
