package me.v1ctu.command.admin;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

public class MaskAdminCommand {

    @Command(
        name = "mask",
        permission = "mask.admin",
        target = CommandTarget.PLAYER
    )
    public void handle(Context<Player> context) {
        context.sendMessage(new String[]{
            "§aMask commands:",
            "§a/mask give <mask> [player]",
            "§a/mask delete <mask>",
            "§a/mask list",
            "§a/mask info <mask>"
        });
    }
}
