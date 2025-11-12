package me.exyin.partiesgui;

import com.alessiodp.parties.api.Parties;
import com.alessiodp.parties.api.interfaces.PartiesAPI;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import lombok.Getter;
import me.exyin.partiesgui.commands.PartiesGuiAdminCommands;
import me.exyin.partiesgui.commands.PartiesGuiCommands;
import me.exyin.partiesgui.listeners.InventoryListener;
import me.exyin.partiesgui.utils.*;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class PartiesGui extends JavaPlugin {
  private PartiesAPI partiesAPI;
  private ConfigUtil configUtil;
  private MessageConfigUtil messageConfigUtil;
  private MessageUtil messageUtil;
  private GuiUtil guiUtil;
  private PlaceholderUtil placeholderUtil;
  private ItemUtil itemUtil;
  private SoundUtil soundUtil;

  @Override
  public void onEnable() {
    if (getServer().getPluginManager().getPlugin("Parties") != null && getServer().getPluginManager().isPluginEnabled("Parties")) {
      partiesAPI = Parties.getApi();
      getLogger().info("Hooked into Parties plugin.");
    }
    configUtil = new ConfigUtil(this);
    messageConfigUtil = new MessageConfigUtil(this);
    messageUtil = new MessageUtil(this);
    guiUtil = new GuiUtil(this);
    placeholderUtil = new PlaceholderUtil(this);
    itemUtil = new ItemUtil(this);
    soundUtil = new SoundUtil(this);
    registerCommands();
    registerListeners();
  }

  private void registerCommands() {
    final PartiesGuiCommands partiesGuiCommands = new PartiesGuiCommands(this);
    final PartiesGuiAdminCommands partiesGuiAdminCommands = new PartiesGuiAdminCommands(this);
    getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
      commands.registrar()
              .register(partiesGuiCommands.constructPartiesGuiCommand(configUtil.getString("commands.partygui.command")));
      configUtil.getStringList("commands.partygui.aliases").forEach(alias -> commands.registrar()
              .register(partiesGuiCommands.constructPartiesGuiCommand(alias)));
      commands.registrar()
              .register(partiesGuiAdminCommands.constructPartiesGuiCommand(configUtil.getString("commands.partyguia.command")));
      configUtil.getStringList("commands.partyguia.aliases").forEach(alias -> commands.registrar()
              .register(partiesGuiAdminCommands.constructPartiesGuiCommand(alias)));
    });
  }

  private void registerListeners() {
    getServer().getPluginManager().registerEvents(new InventoryListener(), this);
  }
}
