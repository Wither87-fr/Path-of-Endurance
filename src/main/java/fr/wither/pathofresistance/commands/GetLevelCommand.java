package fr.wither.pathofresistance.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.wither.pathofresistance.Commons;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class GetLevelCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                LiteralArgumentBuilder.<CommandSourceStack>literal("getRes")
                .then(
                        Commands.argument("target", EntityArgument.player()) // Adding level argument
                        .executes(GetLevelCommand::execute)
                )
        );
    }

    private static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        ServerPlayer player = EntityArgument.getPlayer(context, "target"); // Getting the Executing Player
        try {
            Integer level = 0;
            if(Commons.IMMUNITY_LEVEL_PLAYERS.containsKey(player.getUUID())) {
                level = Commons.IMMUNITY_LEVEL_PLAYERS.get(player.getUUID());
                player.sendSystemMessage(Component.literal("Your current resistence level is : " + level.toString()));
            } else {
                player.sendSystemMessage(Component.literal("No resistance explicitly set, default to : 0"));
            }
            return level;
        } catch (Exception e) {
            return 0;
        }
    }
}
