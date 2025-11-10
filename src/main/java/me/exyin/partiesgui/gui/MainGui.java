package me.exyin.partiesgui.gui;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MainGui implements InventoryHolder {
  private final String id = "main-gui";
  private final Inventory inventory;

  public MainGui(final PartiesGui plugin, final PartyPlayer partyPlayer) {
    this.inventory = plugin.getServer().createInventory(this,
            9 * plugin.getGuiUtil().getInt(id, "rows"),
            plugin.getMessageUtil().toMiniMessageComponent(
                    plugin.getPlaceholderUtil().replacePlaceholders(partyPlayer, plugin.getGuiUtil().getString(id, "title"))));
  }

  @Override
  public @NotNull Inventory getInventory() {
    return inventory;
  }
}
