package me.exyin.partiesgui.utils;

import me.exyin.partiesgui.clickevents.interfaces.ClickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatInputUtil {
  private final Map<UUID, ClickEvent> chatInputs;

  public ChatInputUtil() {
    chatInputs = new HashMap<>();
  }

  public boolean playerHasChatInput(final UUID uuid) {
    return chatInputs.get(uuid) != null;
  }

  public ClickEvent getClickEvent(final UUID uuid) {
    return chatInputs.get(uuid);
  }

  public void addChatInput(final UUID uuid, final ClickEvent clickEvent) {
    chatInputs.put(uuid, clickEvent);
  }

  public void removeChatInput(final UUID uuid) {
    chatInputs.remove(uuid);
  }
}
