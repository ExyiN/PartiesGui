package me.exyin.partiesgui.utils;

import me.exyin.partiesgui.PartiesGui;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class MessageConfigUtil {
  private final PartiesGui plugin;
  private YamlConfiguration config;

  public MessageConfigUtil(final PartiesGui plugin) {
    this.plugin = plugin;
    setupConfigFile();
  }

  public void setupConfigFile() {
    final File configFile = new File(plugin.getDataFolder(), "messages.yml");
    if (!configFile.exists()) {
      plugin.saveResource("messages.yml", false);
    }
    config = YamlConfiguration.loadConfiguration(configFile);
  }

  public String getString(final String key) {
    return config.getString(key);
  }

  public List<String> getStringList(final String key) {
    return config.getStringList(key);
  }
}
