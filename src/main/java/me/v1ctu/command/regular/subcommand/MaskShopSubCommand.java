package me.v1ctu.command.regular.subcommand;

import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import me.v1ctu.MaskPlugin;
import me.v1ctu.view.MaskShopView;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class MaskShopSubCommand {

    private final MaskPlugin maskPlugin;

    @Command(
        name = "mask.shop",
        target = CommandTarget.PLAYER
    )
    public void handle(Context<Player> context) {
        maskPlugin.getViewFrame().open(MaskShopView.class, context.getSender());
    }
}
