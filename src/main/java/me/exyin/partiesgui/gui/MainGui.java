package me.exyin.partiesgui.gui;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.ClickEventFactory;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainGui implements InventoryHolder, PGGui {
  private final String id = "main-gui";
  private final PartiesGui plugin;
  private final PartyPlayer partyPlayer;
  private final Inventory inventory;
  private final int slots;

  public MainGui(final PartiesGui plugin, final PartyPlayer partyPlayer) {
    slots = 9 * plugin.getGuiUtil().getInt(id, "rows");
    this.plugin = plugin;
    this.partyPlayer = partyPlayer;
    this.inventory = plugin.getServer().createInventory(this,
            slots,
            plugin.getMessageUtil().toMiniMessageComponent(
                    plugin.getPlaceholderUtil().replacePlaceholders(partyPlayer, plugin.getGuiUtil().getString(id, "title"))));
    setup();
  }

  private void setup() {
    final ItemStack emptySlot = plugin.getItemUtil().createItemStack(
            plugin.getGuiUtil().getString(id, "empty"),
            null,
            List.of(),
            false,
            null,
            null,
            partyPlayer

    );
    for (int i = 0; i < slots; i++) {
      final String sectionKey = "items." + i;
      if (!plugin.getGuiUtil().existsSection(id, sectionKey)
              || plugin.getGuiUtil().getInt(id, sectionKey + ".required-rank-level") > partyPlayer.getRank()) {
        inventory.setItem(i, emptySlot);
      } else {
        final ItemStack item = plugin.getItemUtil().createItemStack(
                plugin.getGuiUtil().getString(id, sectionKey + ".material"),
                plugin.getGuiUtil().getString(id, sectionKey + ".name"),
                plugin.getGuiUtil().getStringList(id, sectionKey + ".lore"),
                plugin.getGuiUtil().getBoolean(id, sectionKey + ".enchanted"),
                plugin.getGuiUtil().getString(id, sectionKey + ".custom-model-data"),
                plugin.getGuiUtil().getString(id, sectionKey + ".skullOwner"),
                partyPlayer
        );
        inventory.setItem(i, item);
      }
    }
  }

  @Override
  public @NotNull Inventory getInventory() {
    return inventory;
  }

  @Override
  public void handleClickEvent(final int slot, final Player whoClicked) {
    final String key = "items." + slot + ".click-event";
    final String clickEventName = plugin.getGuiUtil().getString(id, key);
    if (clickEventName == null) {
      return;
    }
    final ClickEventFactory clickEventFactory = new ClickEventFactory(plugin);
    final ClickEvent clickEvent = clickEventFactory.of(clickEventName);
    if (clickEvent.canExecute(whoClicked, partyPlayer, slot)) {
      clickEvent.execute(whoClicked, partyPlayer, slot);
    }
  }
}
