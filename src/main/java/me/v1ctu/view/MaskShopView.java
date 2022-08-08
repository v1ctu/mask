package me.v1ctu.view;

import dev.dbassett.skullcreator.SkullCreator;
import me.saiintbrisson.minecraft.*;
import me.v1ctu.MaskPlugin;
import me.v1ctu.mask.Mask;
import me.v1ctu.mask.MaskFactory;
import me.v1ctu.mask.MaskManager;
import me.v1ctu.util.ItemStackBuilder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static me.v1ctu.util.PotionEffectAdapter.joinEffects;

public class MaskShopView extends PaginatedView<Mask> {

    private final MaskPlugin maskPlugin;

    public MaskShopView(MaskManager maskManager, MaskPlugin maskPlugin) {
        super(5, "§aMask Shop");
        this.maskPlugin = maskPlugin;

        setSource(maskManager.getMasks().stream().toList());

        setLayout("XXXXXXXXX", "XOOOOOOOX", "XOOOOOOOX", "<OOOOOOO>", "XXXXXXXXX");

        setCancelOnClick(true);
    }

    @Override
    protected void onItemRender(
            @NotNull PaginatedViewSlotContext<Mask> context, @NotNull ViewItem viewItem, @NotNull Mask value) {
        ItemStack itemStack = MaskFactory.createMaskItem(value);
        ItemStack iconItem =
                new ItemStackBuilder(itemStack).addLore(" ", "§7Click to buy").build();
        viewItem.withItem(iconItem).onClick(slotClickContext -> {
            if (maskPlugin.getEconomy().getBalance(context.getPlayer()) < value.getPrice()) {
                context.getPlayer().sendMessage("§cYou don't have enough money to buy this mask!");
                return;
            }
            maskPlugin.getEconomy().withdrawPlayer(context.getPlayer(), value.getPrice());
            context.getPlayer().getInventory().addItem(itemStack);
            context.getPlayer().closeInventory();
        });
    }
}
