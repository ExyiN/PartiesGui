package me.exyin.partiesgui.utils;

import me.exyin.partiesgui.PartiesGui;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.Objects;

public class MessageUtil {
  private final PartiesGui plugin;
  private final MiniMessage miniMessage;

  public MessageUtil(final PartiesGui plugin) {
    this.plugin = plugin;
    miniMessage = MiniMessage.miniMessage();
  }

  public void sendMessage(final Audience audience, final String key) {
    final String message = plugin.getMessageConfigUtil().getString(key);
    audience.sendMessage(toMiniMessageComponent(plugin.getMessageConfigUtil().getString("prefix") + message));
  }

  private String convertLegacy(String text) {
    Component legacy = LegacyComponentSerializer.legacyAmpersand().deserialize(Objects.requireNonNullElse(text, ""));
    text = miniMessage.serialize(legacy).replace("\\", "");
    legacy = LegacyComponentSerializer.legacySection().deserialize(text);
    return miniMessage.serialize(legacy).replace("\\", "");
  }

  public Component toMiniMessageComponent(final String text) {
    final String transformedText = convertLegacy(text);
    return miniMessage.deserialize(transformedText);
  }
}
