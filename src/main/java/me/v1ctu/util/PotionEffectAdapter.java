package me.v1ctu.util;

import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PotionEffectAdapter {

    public static List<PotionEffectType> deserializeData(List<String> data) {
        final List<PotionEffectType> effectTypes = new ArrayList<>();

        for (String string : data) {
            final PotionEffectType effectType = PotionEffectType.getByName(string);
            if (effectType == null) continue;
            effectTypes.add(effectType);
        }

        return effectTypes;
    }

    public static String joinEffects(List<PotionEffectType> effects) {
        return effects.stream()
            .map(effect -> String.format("§a%s", effect.getName()))
            .collect(Collectors.joining(", "));
    }
}
