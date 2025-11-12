package me.exyin.partiesgui.utils;

import me.exyin.partiesgui.PartiesGui;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;

public class SoundUtil {
  private final PartiesGui plugin;

  public SoundUtil(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  public void playClickSound(final Audience audience) {
    final Sound clickSound = Sound.sound(Key.key(plugin.getConfigUtil().getString("click-sound.sound")),
            Sound.Source.valueOf(plugin.getConfigUtil().getString("click-sound.category")),
            (float) plugin.getConfigUtil().getDouble("click-sound.volume"),
            (float) plugin.getConfigUtil().getDouble("click-sound.pitch"));
    audience.playSound(clickSound);
  }
}
