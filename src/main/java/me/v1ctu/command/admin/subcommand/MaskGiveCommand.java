package me.v1ctu.command.admin.subcommand;

import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import me.v1ctu.mask.Mask;
import me.v1ctu.mask.MaskFactory;
import me.v1ctu.mask.MaskManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class MaskGiveCommand {

    private final MaskManager maskManager;

    @Command(name = "mask.give", permission = "mask.admin", target = CommandTarget.PLAYER)
    public void handle(Context<Player> context, String maskName, @Optional String target) {

        Mask mask = maskManager.getMask(maskName);

        if (mask == null) {
            context.sendMessage("§cMask not found");
            return;
        }

        final ItemStack itemStack = MaskFactory.createMaskItem(mask);

        context.getSender().getInventory().addItem(itemStack);
    }
}
