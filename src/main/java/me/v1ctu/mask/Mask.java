package me.v1ctu.mask;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

@AllArgsConstructor
@Data
public class Mask {

    private String name;
    private String displayName;
    private double price;
    private List<PotionEffectType> effects;
    private String skin;

}
