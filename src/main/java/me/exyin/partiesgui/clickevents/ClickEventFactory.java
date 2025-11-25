package me.exyin.partiesgui.clickevents;

import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.clickevents.enums.ClickEventEnum;
import me.exyin.partiesgui.clickevents.impl.*;
import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;

import java.util.HashMap;
import java.util.Map;

public class ClickEventFactory {
  private final Map<ClickEventEnum, ClickEvent> map;

  public ClickEventFactory(final PartiesGui plugin) {
    map = new HashMap<>();
    map.put(ClickEventEnum.NOTHING, new ClickEventNothing());
    map.put(ClickEventEnum.PARTY_CHANGE_COLOR, new ClickEventPartyChangeColor(plugin));
    map.put(ClickEventEnum.PARTY_CHANGE_DESC, new ClickEventPartyChangeDesc(plugin));
    map.put(ClickEventEnum.PARTY_CHANGE_MOTD, new ClickEventPartyChangeMotd(plugin));
    map.put(ClickEventEnum.PARTY_CHANGE_NAME, new ClickEventPartyChangeName(plugin));
    map.put(ClickEventEnum.PARTY_CHANGE_TAG, new ClickEventPartyChangeTag(plugin));
    map.put(ClickEventEnum.PARTY_HOME, new ClickEventPartyHome(plugin));
    map.put(ClickEventEnum.PARTY_INVITATIONS, new ClickEventPartyInvitations(plugin));
    map.put(ClickEventEnum.PARTY_INVITE, new ClickEventPartyInvite(plugin));
    map.put(ClickEventEnum.PARTY_MEMBERS, new ClickEventPartyMembers(plugin));
    map.put(ClickEventEnum.PARTY_SETTINGS, new ClickEventPartySettings(plugin));
    map.put(ClickEventEnum.GUI_PREVIOUS_PAGE, new ClickEventGuiPreviousPage(plugin));
    map.put(ClickEventEnum.GUI_NEXT_PAGE, new ClickEventGuiNextPage(plugin));
    map.put(ClickEventEnum.GUI_BACK, new ClickEventGuiBack(plugin));
    map.put(ClickEventEnum.GUI_CLOSE, new ClickEventGuiClose(plugin));
  }

  public ClickEvent of(final String event) {
    ClickEventEnum clickEvent;
    try {
      clickEvent = ClickEventEnum.valueOf(event.toUpperCase());
    } catch (final IllegalArgumentException e) {
      clickEvent = ClickEventEnum.NOTHING;
    }
    return map.get(clickEvent);
  }
}
