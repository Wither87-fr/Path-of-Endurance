package fr.wither.pathofresistance.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.wither.pathofresistance.Commons;
import fr.wither.pathofresistance.PathOfResistance;
import fr.wither.pathofresistance.attributes.Resistance;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerScoreEntry;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.fixes.NeoForgeEntityLegacyAttributesFix;

import java.util.Collection;

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
        ServerPlayer player = EntityArgument.getPlayer(context, "target");
        int level = IntegerArgumentType.getInteger(context, "level"); // Getting the level argument

        try {

            player.setData(Resistance.RESISTANCE, level);
            player.sendSystemMessage(Component.literal("Your resistance was set to " + level));

            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
