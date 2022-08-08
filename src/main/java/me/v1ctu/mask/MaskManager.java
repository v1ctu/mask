package me.v1ctu.mask;

import lombok.RequiredArgsConstructor;
import me.v1ctu.MaskPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class MaskManager {

    private final MaskCache maskCache;
    private final MaskPlugin maskPlugin;

    public void deleteMask(String name) {
        maskPlugin.getConfig().set("masks." + name, null);
        maskCache.remove(name);
    }

    public Mask getMask(String name) {
        final Mask mask = maskCache.get(name);
        return mask;
    }

    public Collection<Mask> getMasks() {
        final Collection<Mask> masks = maskCache.get();
        return masks;
    }

    public void updateMaskName(String name, String newName) {
        Mask mask = getMask(name);
        mask.setName(name);
        maskPlugin.getConfig().set("masks." + name, newName);
        maskCache.put(name, mask);
    }

    public void updateMaskDisplayName(String name, String newName) {
        Mask mask = getMask(name);
        mask.setName(name);
        maskPlugin.getConfig().set("masks." + name + ".display_name", newName);
        maskCache.put(name, mask);
    }

    public void updateMaskEffects(String name, List<PotionEffectType> effects) {
        Mask mask = getMask(name);
        mask.setEffects(effects);
        maskPlugin.getConfig().set("masks." + name + ".effects", effects);
        maskCache.put(name, mask);
    }

    public void updateMaskSkin(String name, String skin) {
        Mask mask = getMask(name);
        mask.setSkin(skin);
        maskPlugin.getConfig().set("masks." + name + ".skin", skin);
        maskCache.put(name, mask);
    }

    public boolean containsMask(String name) {
        return maskCache.contains(name);
    }

}
