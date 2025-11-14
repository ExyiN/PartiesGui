package me.exyin.partiesgui.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.gui.SettingsGui;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class ChatListener implements Listener {
  private final PartiesGui plugin;

  public ChatListener(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onChat(final AsyncChatEvent event) {
    final UUID playerUuid = event.getPlayer().getUniqueId();
    if (!plugin.getChatInputUtil().playerHasChatInput(playerUuid)) {
      return;
    }
    event.setCancelled(true);
    final String message = plugin.getMessageUtil().serialize(event.message());
    if (message.equals("cancel")) {
      plugin.getMessageUtil().sendMessage(event.getPlayer(), "cancelled-chat-input");
      plugin.getServer().getScheduler().runTask(plugin, () -> event.getPlayer().openInventory(new SettingsGui(plugin, plugin.getPartiesAPI().getPartyPlayer(playerUuid)).getInventory()));
    } else {
      plugin.getChatInputUtil().getClickEvent(playerUuid).processInput(playerUuid, message);
    }
    plugin.getChatInputUtil().removeChatInput(playerUuid);
  }
}
