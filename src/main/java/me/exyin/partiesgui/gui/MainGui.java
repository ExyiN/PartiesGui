package me.exyin.partiesgui.gui;

import me.exyin.partiesgui.PartiesGui;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class MainGui implements InventoryHolder {
  private final Inventory inventory;

  public MainGui(final PartiesGui plugin) {
    this.inventory = plugin.getServer().createInventory(this, 9);
  }

  @Override
  public @NotNull Inventory getInventory() {
    return inventory;
  }
}
