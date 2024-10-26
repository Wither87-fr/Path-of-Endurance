package fr.wither.pathofresistance.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
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
import net.minecraft.world.scores.PlayerScoreEntry;

import java.util.Collection;
import java.util.List;

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

        ServerPlayer player = EntityArgument.getPlayer(context, "target");


        try {

            Collection<PlayerScoreEntry> scores = player.getScoreboard().listPlayerScores(player.getScoreboard().getObjective("Res"));
            int scoreboardValue = -490;
            for(PlayerScoreEntry score : scores) {
                if(score.owner() == player.getScoreboardName()) {
                    PathOfResistance.LOGGER.info("==============[TROUVE : " + score.value() + " ]==============" );
                    scoreboardValue = score.value();
                }
            }

            player.setData(Resistance.RESISTANCE, scoreboardValue);

            Integer playerResistance = player.getData(Resistance.RESISTANCE);


            player.sendSystemMessage(Component.literal("Your current resistence level is : " + playerResistance));

            return playerResistance;
        } catch (Exception e) {
            return 0;
        }
    }
}
