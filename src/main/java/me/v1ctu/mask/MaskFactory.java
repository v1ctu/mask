package me.v1ctu.mask;

import dev.dbassett.skullcreator.SkullCreator;
import me.v1ctu.util.ItemStackBuilder;
import me.v1ctu.util.PotionEffectAdapter;
import org.bukkit.inventory.ItemStack;

public class MaskFactory {

    public static ItemStack createMaskItem(Mask mask) {
        ItemStackBuilder itemStackBuilder = new ItemStackBuilder(
                mask.getSkin() == null ? SkullCreator.createSkull() : SkullCreator.itemFromUrl(mask.getSkin()));

        itemStackBuilder.name(mask.getDisplayName());
        itemStackBuilder.addLore(
                "§7Price: §a" + mask.getPrice(), "§7Effects: §a" + PotionEffectAdapter.joinEffects(mask.getEffects()));

        itemStackBuilder.nbtValue("mask", mask.getName());

        return itemStackBuilder.build();
    }
}
