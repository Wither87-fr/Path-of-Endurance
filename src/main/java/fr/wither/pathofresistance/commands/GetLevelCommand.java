package fr.wither.pathofresistance.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.wither.pathofresistance.Commons;
import fr.wither.pathofresistance.PathOfResistance;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.RandomCommand;
import net.minecraft.server.level.ServerPlayer;

public class GetLevelCommand extends RandomCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("getRes")
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
