package me.v1ctu.command.regular;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

public class MaskRegularCommand {

    @Command(
        name = "mask",
        target = CommandTarget.PLAYER
    )
    public void handle(Context<Player> context) {
        context.sendMessage(new String[]{
            "§aMask commands:",
            "§a/mask shop"
        });
    }
}
