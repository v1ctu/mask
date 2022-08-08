package me.v1ctu;

import lombok.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.ViewFrame;
import me.v1ctu.command.admin.MaskAdminCommand;
import me.v1ctu.command.admin.subcommand.MaskGiveCommand;
import me.v1ctu.command.regular.subcommand.MaskShopSubCommand;
import me.v1ctu.listener.InventoryClickListener;
import me.v1ctu.mask.Mask;
import me.v1ctu.mask.MaskCache;
import me.v1ctu.mask.MaskManager;
import me.v1ctu.util.PotionEffectAdapter;
import me.v1ctu.view.MaskShopView;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class MaskPlugin extends JavaPlugin {

    private MaskCache maskCache;
    private MaskManager maskManager;

    @Getter
    private ViewFrame viewFrame;

    @Getter
    private Economy economy;

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void onEnable() {
        maskCache = new MaskCache();
        maskManager = new MaskManager(maskCache, this);

        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        saveDefaultConfig();
        loadCache();

        viewFrame = new ViewFrame(this);
        viewFrame.register(
            new MaskShopView(maskManager, this)
        );

        registerCommands();

        final PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new InventoryClickListener(maskManager), this);
    }

    @Override
    public void onDisable() {

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    private void registerCommands() {
        BukkitFrame bukkitFrame = new BukkitFrame(this);
        bukkitFrame.registerCommands(
            new MaskAdminCommand(),
            new MaskGiveCommand(maskManager),
            new MaskShopSubCommand(this));
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
    }
}
