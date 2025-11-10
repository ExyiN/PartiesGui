package me.exyin.partiesgui;

import com.alessiodp.parties.api.Parties;
import com.alessiodp.parties.api.interfaces.PartiesAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class PartiesGui extends JavaPlugin {
  private PartiesAPI partiesAPI;

  @Override
  public void onEnable() {
    if(getServer().getPluginManager().getPlugin("Parties") != null && getServer().getPluginManager().isPluginEnabled("Parties")) {
      partiesAPI = Parties.getApi();
      getLogger().info("Hooked into Parties plugin.");
    }
  }

  public PartiesAPI getPartiesAPI() {
    return partiesAPI;
  }
}
