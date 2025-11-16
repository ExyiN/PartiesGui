package me.exyin.partiesgui.utils;

import me.exyin.partiesgui.PartiesGui;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class ConfigUtil {
  private final PartiesGui plugin;
  private YamlConfiguration config;

  public ConfigUtil(final PartiesGui plugin) {
    this.plugin = plugin;
    setupConfigFile();
  }

  public void setupConfigFile() {
    final File configFile = new File(plugin.getDataFolder(), "config.yml");
    if (!configFile.exists()) {
      plugin.saveResource("config.yml", false);
    }
    config = YamlConfiguration.loadConfiguration(configFile);
  }

  public String getString(final String key) {
    return config.getString(key);
  }

  public String getString(final String key, final String defaultValue) {
    return config.getString(key, defaultValue);
  }

  public List<String> getStringList(final String key) {
    return config.getStringList(key);
  }

  public double getDouble(final String key) {
    return config.getDouble(key);
  }

  public int getInt(final String key) {
    return config.getInt(key);
  }
}
