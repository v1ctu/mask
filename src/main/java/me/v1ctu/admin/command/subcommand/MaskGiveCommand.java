package me.v1ctu.admin.command.subcommand;

import dev.dbassett.skullcreator.SkullCreator;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import me.v1ctu.mask.Mask;
import me.v1ctu.mask.MaskManager;
import me.v1ctu.util.ItemStackBuilder;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MaskGiveCommand {

    private final MaskManager maskManager;

    @Command(
        name = "mask.give",
        permission = "mask.admin",
        target = CommandTarget.PLAYER
    )
    public void handle(Context<Player> context, String maskName, @Optional String target) {

        Mask mask = maskManager.getMask(maskName);

        if (mask == null) {
            context.sendMessage("§cMask not found");
            return;
        }

        ItemStackBuilder itemStackBuilder = new ItemStackBuilder(
            mask.getSkin() == null ? SkullCreator.createSkull() : SkullCreator.itemFromUrl(mask.getSkin())
        );

        itemStackBuilder.name(mask.getDisplayName());
        itemStackBuilder.addLore(
            "§7Price: §a" + mask.getPrice(),
            "§7Effects: §a" + joinEffects(mask.getEffects())
        );
        context.getSender().getInventory().addItem(itemStackBuilder.build());
    }

    public String joinEffects(List<PotionEffectType> effects) {
        return effects.stream()
            .map(effect -> String.format("§a%s", effect.getName()))
            .collect(Collectors.joining(", "));
    }


}
