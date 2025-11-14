package me.exyin.partiesgui.utils;

import me.exyin.partiesgui.PartiesGui;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiUtil {
  private final PartiesGui plugin;
  private Map<String,YamlConfiguration> configMap;

  public GuiUtil(final PartiesGui plugin) {
    this.plugin = plugin;
    setupConfigFile();
  }

  public void setupConfigFile() {
    configMap = new HashMap<>();
    final File guiDir = new File(plugin.getDataFolder(), "gui");
    File[] configFiles = guiDir.listFiles();
    if (!guiDir.exists() || configFiles == null || configFiles.length == 0) {
      plugin.saveResource("gui" + File.separator + "main-gui.yml", false);
      plugin.saveResource("gui" + File.separator + "members-gui.yml", false);
      plugin.saveResource("gui" + File.separator + "settings-gui.yml", false);
      configFiles = guiDir.listFiles();
      if (configFiles == null || configFiles.length == 0) {
        return;
      }
    }
    for (final File configFile : configFiles) {
      final String guiName = configFile.getName().replace(".yml", "");
      configMap.put(guiName, YamlConfiguration.loadConfiguration(configFile));
    }
  }

  public boolean existsSection(final String gui, final String key) {
    return configMap.get(gui).getConfigurationSection(key) != null;
  }

  public String getString(final String gui, final String key) {
    return configMap.get(gui).getString(key);
  }

  public List<String> getStringList(final String gui, final String key) {
    return configMap.get(gui).getStringList(key);
  }

  public int getInt(final String gui, final String key) {
    return configMap.get(gui).getInt(key);
  }

  public boolean getBoolean(final String gui, final String key) {
    return configMap.get(gui).getBoolean(key);
  }
}
