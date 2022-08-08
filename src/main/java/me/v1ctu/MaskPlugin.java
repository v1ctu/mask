package me.v1ctu;

import lombok.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.v1ctu.admin.command.MaskCommand;
import me.v1ctu.admin.command.subcommand.MaskGiveCommand;
import me.v1ctu.mask.Mask;
import me.v1ctu.mask.MaskCache;
import me.v1ctu.mask.MaskManager;
import me.v1ctu.util.PotionEffectAdapter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class MaskPlugin extends JavaPlugin {

    private MaskCache maskCache;
    private MaskManager maskManager;

    @Override
    public void onEnable() {
        maskCache = new MaskCache();
        maskManager = new MaskManager(maskCache, this);

        saveDefaultConfig();
        loadCache();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        BukkitFrame bukkitFrame = new BukkitFrame(this);
        bukkitFrame.registerCommands(
            new MaskCommand(),
            new MaskGiveCommand(maskManager));
    }

    private void loadCache() {
        final ConfigurationSection maskSection = getConfig().getConfigurationSection("masks");
        if (maskSection == null) return;

        for (String maskName : maskSection.getKeys(false)) {
            String displayName = maskSection.getString(maskName + ".display_name").replace("&", "§");
            if (displayName == null) continue;

            double price = maskSection.getDouble(maskName + ".price");

            List<String> effects = maskSection.getStringList(maskName + ".effects");
            List<PotionEffectType> effectsType = PotionEffectAdapter.deserializeData(effects);

            String skin = maskSection.getString(maskName + ".skin");
            if (skin == null) continue;

            final Mask mask = new Mask(
                maskName,
                displayName,
                price,
                effectsType,
                skin
            );
            maskCache.put(mask.getName(), mask);
        }
        System.out.println("maskCache.get() = " + maskCache.get());
    }
}
