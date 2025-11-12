package me.exyin.partiesgui.listeners;

import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {
  @EventHandler
  public void onInventoryClick(final InventoryClickEvent event) {
    Inventory inventory = event.getInventory();
    if (!(inventory.getHolder(false) instanceof PGGui)) {
      return;
    }
    event.setCancelled(true);
    inventory = event.getClickedInventory();
    if (inventory != null && inventory.getHolder() instanceof final PGGui pgGui) {
      pgGui.handleClickEvent(event.getSlot(), (Player) event.getWhoClicked());
    }
  }
}
