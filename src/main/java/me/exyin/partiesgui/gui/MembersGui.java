package me.exyin.partiesgui.gui;

import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.ClickEventFactory;
import me.exyin.partiesgui.clickevents.enums.ClickEventEnum;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;
import me.exyin.partiesgui.gui.interfaces.PGGui;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MembersGui implements InventoryHolder, PGGui {
  private final String id = "members-gui";
  private final PartiesGui plugin;
  private final PartyPlayer partyPlayer;
  private final Inventory inventory;
  @Getter
  private List<List<PartyPlayer>> pages;
  @Getter
  @Setter
  private int pIndex;

  public MembersGui(final PartiesGui plugin, final PartyPlayer partyPlayer) {
    final int slots = 9 * plugin.getGuiUtil().getInt(id, "rows");
    this.plugin = plugin;
    this.partyPlayer = partyPlayer;
    this.inventory = plugin.getServer().createInventory(this,
            slots,
            plugin.getMessageUtil().toMiniMessageComponent(
                    plugin.getPlaceholderUtil().replacePlaceholders(partyPlayer, plugin.getGuiUtil().getString(id, "title"))));
    pIndex = 0;
    setup();
  }

  @Override
  public void setup() {
    final ItemStack emptySlot = plugin.getItemUtil().createItemStack(
            plugin.getGuiUtil().getString(id, "empty"),
            null,
            List.of(),
            false,
            null,
            null,
            partyPlayer

    );
    for (int i = 0; i < inventory.getSize(); i++) {
      inventory.setItem(i, emptySlot);
    }
    setupMembers();
    setupFooter();
  }

  private void setupMembers() {
    final List<PartyPlayer> members = getMembers();
    pages = Lists.partition(members, inventory.getSize() - 9);
    final List<PartyPlayer> selectedPage = pages.get(pIndex);
    final AtomicInteger slot = new AtomicInteger();
    selectedPage.forEach(member -> {
      final ItemStack item = plugin.getItemUtil().createItemStack(
              plugin.getGuiUtil().getString(id, "member-item.material"),
              plugin.getGuiUtil().getString(id, "member-item.name"),
              plugin.getGuiUtil().getStringList(id, "member-item.lore"),
              plugin.getGuiUtil().getBoolean(id, "member-item.enchanted"),
              plugin.getGuiUtil().getString(id, "member-item.custom-model-data"),
              plugin.getGuiUtil().getString(id, "member-item.skull-owner"),
              member
      );
      inventory.setItem(slot.get(), item);
      slot.getAndIncrement();
    });
  }

  private void setupFooter() {
    final ItemStack emptySlot = plugin.getItemUtil().createItemStack(
            plugin.getGuiUtil().getString(id, "footer.empty"),
            null,
            List.of(),
            false,
            null,
            null,
            partyPlayer
    );
    final int startIndex = inventory.getSize() - 9;
    for (int i = 0; i < 9; i++) {
      final String sectionKey = "footer.items." + i;
      if (!plugin.getGuiUtil().existsSection(id, sectionKey)
              || (plugin.getGuiUtil().getString(id, sectionKey + ".click-event")
              .toUpperCase()
              .equals(ClickEventEnum.GUI_PREVIOUS_PAGE.name())
              && pIndex <= 0)
              || (plugin.getGuiUtil().getString(id, sectionKey + ".click-event")
              .toUpperCase()
              .equals(ClickEventEnum.GUI_NEXT_PAGE.name())
              && pIndex >= (pages.size() - 1))
      ) {
        inventory.setItem(startIndex + i, emptySlot);
      } else {
        final ItemStack item = plugin.getItemUtil().createItemStack(
                plugin.getGuiUtil().getString(id, sectionKey + ".material"),
                plugin.getGuiUtil().getString(id, sectionKey + ".name"),
                plugin.getGuiUtil().getStringList(id, sectionKey + ".lore"),
                plugin.getGuiUtil().getBoolean(id, sectionKey + ".enchanted"),
                plugin.getGuiUtil().getString(id, sectionKey + ".custom-model-data"),
                plugin.getGuiUtil().getString(id, sectionKey + ".skull-owner"),
                partyPlayer
        );
        inventory.setItem(startIndex + i, item);
      }
    }
  }

  private List<PartyPlayer> getMembers() {
    final UUID partyId = partyPlayer.getPartyId();
    if (partyId != null) {
      final Party party = plugin.getPartiesAPI().getParty(partyId);
      if (party != null) {
        final Set<UUID> membersUuid = party.getMembers();
        final List<PartyPlayer> members = new ArrayList<>();
        for (final UUID memberUuid : membersUuid) {
          final PartyPlayer member = plugin.getPartiesAPI().getPartyPlayer(memberUuid);
          members.add(member);
        }
        final Comparator<PartyPlayer> comparator = Comparator.comparing((final PartyPlayer pp) -> {
                  final OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(pp.getPlayerUUID());
                  return offlinePlayer.isOnline();
                })
                .thenComparingInt(PartyPlayer::getRank)
                .reversed()
                .thenComparing(pp -> pp.getName().toLowerCase());
        members.sort(comparator);
        return members;
      }
    }
    return List.of();
  }

  @Override
  public @NotNull Inventory getInventory() {
    return inventory;
  }

  @Override
  public void handleClickEvent(final int slot, final Player whoClicked) {
    if (slot < inventory.getSize() - 9) {
      return;
    }
    final String key = "footer.items." + (slot - inventory.getSize() + 9) + ".click-event";
    final String clickEventName = plugin.getGuiUtil().getString(id, key);
    if (clickEventName == null) {
      return;
    }
    final ClickEventFactory clickEventFactory = new ClickEventFactory(plugin);
    final ClickEvent clickEvent = clickEventFactory.of(clickEventName);
    if (clickEvent.canExecute(this, whoClicked, partyPlayer, slot)) {
      clickEvent.execute(this, whoClicked, partyPlayer, slot);
    }
  }
}
