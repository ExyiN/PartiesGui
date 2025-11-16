package me.exyin.partiesgui.clickevents.impl;

import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyColor;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class ClickEventPartyChangeColor implements ClickEvent {
  private final PartiesGui plugin;

  public ClickEventPartyChangeColor(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean canExecute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    return plugin.getGuiUtil().getInt("settings-gui", "items." + slot + ".required-rank-level") <= partyPlayer.getRank();
  }

  @Override
  public void execute(final PGGui gui, final Player whoClicked, final PartyPlayer partyPlayer, final int slot) {
    final List<PartyColor> colors = new ArrayList<>(plugin.getPartiesAPI().getColors());
    final Comparator<PartyColor> comparator = Comparator.comparing(PartyColor::getName);
    colors.sort(comparator);
    final UUID partyUuid = partyPlayer.getPartyId();
    assert partyUuid != null;
    final Party party = plugin.getPartiesAPI().getParty(partyUuid);
    assert party != null;
    final PartyColor partyColor = party.getColor();
    int colorIndex = -1;
    if (partyColor != null) {
      colorIndex = colors.indexOf(partyColor);
    }
    colorIndex++;
    if (colorIndex >= colors.size()) {
      colorIndex = -1;
    }
    String newColorCommand = plugin.getConfigUtil().getString("parties-commands.misc-commands.remove", "remove");
    if (colorIndex > -1) {
      final PartyColor newColor = colors.get(colorIndex);
      newColorCommand = newColor.getCommand();
    }
    final String finalColorCommand = newColorCommand;
    final String partyCommand = plugin.getConfigUtil().getString("parties-commands.party", "party");
    final String subCommand = plugin.getConfigUtil().getString("parties-commands.sub-commands.color", "color");
    plugin.getServer().getScheduler().runTask(plugin, () -> {
      whoClicked.performCommand(MessageFormat.format("{0} {1} {2}", partyCommand, subCommand, finalColorCommand));
      gui.setup();
    });
  }

  @Override
  public void processInput(final UUID uuid, final String input) {

  }
}
