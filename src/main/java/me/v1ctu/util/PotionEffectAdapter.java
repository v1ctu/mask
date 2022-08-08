package me.v1ctu.util;

import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

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

    public static String serializeData(List<PotionEffectType> data) {
        final StringBuilder stringBuilder = new StringBuilder();

        int size = 0;
        for (PotionEffectType effectType : data) {
            stringBuilder.append(effectType.getName());
            if (size != data.size()) {
                stringBuilder.append(";");
            }
        }

        return stringBuilder.toString();
    }
}
