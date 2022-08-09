package me.v1ctu.listener;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.RequiredArgsConstructor;
import me.v1ctu.mask.Mask;
import me.v1ctu.mask.MaskManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class InventoryClickListener implements Listener {

    private final MaskManager maskManager;

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        final ItemStack itemStack = event.getCursor();

        if (itemStack == null || itemStack.getType().isAir()) {
            return;
        }

        NBTItem nbtItem = new NBTItem(itemStack);
        if (!nbtItem.hasKey("mask")) {
            return;
        }

        Mask mask = maskManager.getMask(nbtItem.getString("mask"));

        if (mask == null) {
            return;
        }
        System.out.println("event.getSlotType() = " + event.getSlotType());
        if (event.getSlotType().equals(InventoryType.SlotType.ARMOR)) {
            for (PotionEffectType effect : mask.getEffects()) {
                event.getWhoClicked().addPotionEffect(new PotionEffect(effect, Integer.MAX_VALUE, 0));
            }
        } else {
            for (PotionEffectType effect : mask.getEffects()) {
                event.getWhoClicked().removePotionEffect(effect);
            }
        }
    }
}
