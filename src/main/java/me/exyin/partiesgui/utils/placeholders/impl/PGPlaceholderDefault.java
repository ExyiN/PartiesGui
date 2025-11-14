package me.exyin.partiesgui.utils.placeholders.impl;

import me.exyin.partiesgui.utils.placeholders.interfaces.PGPlaceholder;

public class PGPlaceholderDefault implements PGPlaceholder {
  @Override
  public String getPlaceholderName() {
    return "default";
  }

  @Override
  public String getReplacement() {
    return null;
  }
}
