package me.exyin.partiesgui.gui.interfaces;

import org.bukkit.entity.Player;

public interface PGGui {
  void setup();
  void handleClickEvent(int slot, Player whoClicked);
}
