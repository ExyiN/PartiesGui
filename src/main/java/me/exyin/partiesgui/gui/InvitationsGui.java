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
import me.exyin.partiesgui.gui.interfaces.PageableGui;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InvitationsGui implements InventoryHolder, PGGui, PageableGui {
  private final String id = "invitations-gui";
  private final PartiesGui plugin;
  private final PartyPlayer partyPlayer;
  private final Inventory inventory;
  @Getter
  private List<List<PartyPlayer>> pages;
  @Getter
  @Setter
  private int pIndex;

  public InvitationsGui(final PartiesGui plugin, final PartyPlayer partyPlayer) {
    final int slots = 9 * plugin.getGuiUtil().getInt(id, "rows");
    this.plugin = plugin;
    this.partyPlayer = partyPlayer;
    this.inventory = plugin.getServer().createInventory(this,
            slots,
            plugin.getMessageUtil().toMiniMessageComponent(
                    plugin.getPlaceholderUtil().replacePlaceholders(partyPlayer, plugin.getGuiUtil().getString(id, "title"))));
    pIndex = 0;
    pages = new ArrayList<>();
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
    setupContent();
    setupFooter();
  }

  @Override
  public void setupContent() {
    final Set<UUID> members = getMembers();
    List<PartyPlayer> onlinePlayers = plugin.getServer().getOnlinePlayers().stream().map(player -> plugin.getPartiesAPI().getPartyPlayer(player.getUniqueId())).toList();
    onlinePlayers = onlinePlayers.stream().filter(pp -> !members.contains(pp.getPlayerUUID()) && !pp.isInParty()).toList();
    final List<PartyPlayer> players = new ArrayList<>(onlinePlayers);
    if (players.isEmpty()) {
      return;
    }
    final Comparator<PartyPlayer> comparator = Comparator.comparing(PartyPlayer::getName);
    players.sort(comparator);
    pages = Lists.partition(players, inventory.getSize() - 9);
    final List<PartyPlayer> selectedPage = pages.get(pIndex);
    final AtomicInteger slot = new AtomicInteger();
    selectedPage.forEach(player -> {
      final ItemStack item = plugin.getItemUtil().createItemStack(
              plugin.getGuiUtil().getString(id, "invite-item.material"),
              plugin.getGuiUtil().getString(id, "invite-item.name"),
              plugin.getGuiUtil().getStringList(id, "invite-item.lore"),
              plugin.getGuiUtil().getBoolean(id, "invite-item.enchanted"),
              plugin.getGuiUtil().getString(id, "invite-item.custom-model-data"),
              plugin.getGuiUtil().getString(id, "invite-item.skull-owner"),
              player
      );
      inventory.setItem(slot.get(), item);
      slot.getAndIncrement();
    });
  }

  @Override
  public void setupFooter() {
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

  private Set<UUID> getMembers() {
    final UUID partyId = partyPlayer.getPartyId();
    if (partyId != null) {
      final Party party = plugin.getPartiesAPI().getParty(partyId);
      if (party != null) {
        return party.getMembers();
      }
    }
    return Set.of();
  }

  @Override
  public void handleClickEvent(final int slot, final Player whoClicked) {
    final String key;
    if (slot < inventory.getSize() - 9) {
      key = "invite-item.click-event";
    } else {
      key = "footer.items." + (slot - inventory.getSize() + 9) + ".click-event";
    }
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

  @Override
  public @NotNull Inventory getInventory() {
    return inventory;
  }
}
