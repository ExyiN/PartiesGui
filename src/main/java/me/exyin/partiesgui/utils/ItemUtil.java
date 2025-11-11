package me.exyin.partiesgui.utils;

import com.alessiodp.parties.api.interfaces.PartyPlayer;
import com.destroystokyo.paper.profile.PlayerProfile;
import me.exyin.partiesgui.PartiesGui;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;

import java.util.List;
import java.util.UUID;

public class ItemUtil {
  private final PartiesGui plugin;

  public ItemUtil(final PartiesGui plugin) {
    this.plugin = plugin;
  }

  public ItemStack createItemStack(final String material, final String name, final List<String> lore, final boolean isEnchanted, final String customModelData, final String skullOwner, final PartyPlayer partyPlayer) {
    final Component clearText = plugin.getMessageUtil().toMiniMessageComponent("<!i><white>");
    Material itemMat;
    try {
      itemMat = Material.valueOf(material);
    } catch (final IllegalArgumentException e) {
      plugin.getLogger().severe("Wrong item material: " + material);
      itemMat = Material.AIR;
    }
    final Component itemName = name == null ? null : clearText.append(plugin.getMessageUtil()
            .toMiniMessageComponent(plugin.getPlaceholderUtil().replacePlaceholders(partyPlayer, name)));
    final List<Component> itemLore = lore.stream().map(line -> plugin.getPlaceholderUtil().replacePlaceholders(partyPlayer, line))
            .map(line -> clearText.append(plugin.getMessageUtil().toMiniMessageComponent(line))).toList();
    final UUID skullUuid = skullOwner == null ? null : skullOwner.equals("self")
            ? partyPlayer.getPlayerUUID() : UUID.fromString(skullOwner);
    return getItemStack(
            itemMat,
            itemName,
            itemLore,
            isEnchanted,
            customModelData,
            skullUuid
    );
  }

  private ItemStack getItemStack(final Material material, final Component name, final List<Component> lore, final boolean isEnchanted, final String customModelData, final UUID skullUuid) {
    final ItemStack item = new ItemStack(material, 1);
    item.editMeta(itemMeta -> {
      if (itemMeta instanceof final SkullMeta skullMeta && skullUuid != null) {
        final PlayerProfile playerProfile = plugin.getServer().createProfile(skullUuid);
        skullMeta.setPlayerProfile(playerProfile);
      }
      itemMeta.displayName(name);
      if (name == null) {
        itemMeta.setHideTooltip(true);
      }
      if (!lore.isEmpty()) {
        itemMeta.lore(lore);
      }
      if (customModelData != null) {
        final CustomModelDataComponent customModelDataComponent = itemMeta.getCustomModelDataComponent();
        try {
          final float floatData = Float.parseFloat(customModelData);
          customModelDataComponent.setFloats(List.of(floatData));
        } catch (final NumberFormatException e) {
          customModelDataComponent.setStrings(List.of(customModelData));
        }
        itemMeta.setCustomModelDataComponent(customModelDataComponent);
      }
      itemMeta.setEnchantmentGlintOverride(isEnchanted);
      itemMeta.addAttributeModifier(Attribute.LUCK, new AttributeModifier(new NamespacedKey(plugin, "hide"), 0, AttributeModifier.Operation.ADD_NUMBER));
      itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    });
    return item;
  }
}
