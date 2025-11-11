package me.exyin.partiesgui.listeners;

import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {
  @EventHandler
  public void onInventoryClick(final InventoryClickEvent event) {
    final Inventory inventory = event.getInventory();
    if (!(inventory.getHolder(false) instanceof PGGui)) {
      return;
    }
    event.setCancelled(true);
  }
}
