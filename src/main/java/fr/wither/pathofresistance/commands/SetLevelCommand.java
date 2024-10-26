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
import net.minecraft.server.level.ServerPlayer;

public class SetLevelCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("setRes")
                .then(
                        Commands.argument("target", EntityArgument.player()) // Adding level argument
                                .then(
                                        Commands.argument("level", IntegerArgumentType.integer())
                                                .executes(SetLevelCommand::execute)
                                )
                )
        );
    }

    private static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        try {
            ServerPlayer player = EntityArgument.getPlayer(context, "target"); // Getting the Executing Player
            int level = IntegerArgumentType.getInteger(context, "level"); // Getting the level argument

            Commons.IMMUNITY_LEVEL_PLAYERS.remove(player.getUUID());

            Commons.IMMUNITY_LEVEL_PLAYERS.put(player.getUUID(), level);

            player.sendSystemMessage(Component.literal("Your resistance was set to " + level));

            return 1;
        } catch (CommandSyntaxException e) {
            return 0;
        }
    }
}
