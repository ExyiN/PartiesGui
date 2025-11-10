package me.exyin.partiesgui.utils;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import me.exyin.partiesgui.PartiesGui;
import me.exyin.partiesgui.utils.placeholders.PGPlaceholderFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderUtil {
  private final PartiesGui plugin;

  public PlaceholderUtil(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  public String replacePlaceholders(final PartyPlayer partyPlayer, final String text) {
    String replacement;
    String replacedText = text;
    final Pattern pattern = Pattern.compile("([{]([^}]+)})");
    final Matcher matcher = pattern.matcher(replacedText);
    final PGPlaceholderFactory placeholderFactory = new PGPlaceholderFactory(plugin, partyPlayer);

    while (matcher.find()) {
      final String placeholder = matcher.group(1);
      replacement = placeholderFactory.of(placeholder).getReplacement();
      if (!replacement.isBlank()) {
        replacedText = replacedText.replace(placeholder, replacement);
      }
    }
    return replacedText;
  }
}
