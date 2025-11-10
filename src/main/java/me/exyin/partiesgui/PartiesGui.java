package me.exyin.partiesgui;

import com.alessiodp.parties.api.Parties;
import com.alessiodp.parties.api.interfaces.PartiesAPI;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.exyin.partiesgui.commands.PartiesGuiAdminCommands;
import me.exyin.partiesgui.commands.PartiesGuiCommands;
import me.exyin.partiesgui.utils.ConfigUtil;
import me.exyin.partiesgui.utils.MessageConfigUtil;
import me.exyin.partiesgui.utils.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class PartiesGui extends JavaPlugin {
  private PartiesAPI partiesAPI;
  private ConfigUtil configUtil;
  private MessageConfigUtil messageConfigUtil;
  private MessageUtil messageUtil;

  @Override
  public void onEnable() {
    if (getServer().getPluginManager().getPlugin("Parties") != null && getServer().getPluginManager().isPluginEnabled("Parties")) {
      partiesAPI = Parties.getApi();
      getLogger().info("Hooked into Parties plugin.");
    }
    configUtil = new ConfigUtil(this);
    messageConfigUtil = new MessageConfigUtil(this);
    messageUtil = new MessageUtil(this);
    registerCommands();
  }

  public PartiesAPI getPartiesAPI() {
    return partiesAPI;
  }

  public ConfigUtil getConfigUtil() {
    return configUtil;
  }

  public MessageConfigUtil getMessageConfigUtil() {
    return messageConfigUtil;
  }

  public MessageUtil getMessageUtil() {
    return messageUtil;
  }

  private void registerCommands() {
    final PartiesGuiCommands partiesGuiCommands = new PartiesGuiCommands(this);
    final PartiesGuiAdminCommands partiesGuiAdminCommands = new PartiesGuiAdminCommands(this);
    getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
      commands.registrar()
              .register(partiesGuiCommands.constructPartiesGuiCommand(configUtil.getString("command.partygui.command")));
      configUtil.getStringList("command.partygui.aliases").forEach(alias -> commands.registrar()
              .register(partiesGuiCommands.constructPartiesGuiCommand(alias)));
      commands.registrar()
              .register(partiesGuiAdminCommands.constructPartiesGuiCommand(configUtil.getString("command.partyguia.command")));
      configUtil.getStringList("command.partyguia.aliases").forEach(alias -> commands.registrar()
              .register(partiesGuiAdminCommands.constructPartiesGuiCommand(alias)));
    });
  }
}
