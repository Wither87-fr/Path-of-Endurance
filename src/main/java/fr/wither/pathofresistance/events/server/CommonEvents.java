package fr.wither.pathofresistance.events.server;

import fr.wither.pathofresistance.Commons;
import fr.wither.pathofresistance.PathOfResistance;
import fr.wither.pathofresistance.attributes.Resistance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerScoreEntry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Collection;
import java.util.Objects;

@EventBusSubscriber(modid = Commons.MOD_ID)
public class CommonEvents {
    @SubscribeEvent
    public static void onEntyHurt(LivingDamageEvent.Pre event) {
        if(event.getEntity() instanceof Player player) {

            Collection<PlayerScoreEntry> scores = player.getScoreboard().listPlayerScores(player.getScoreboard().getObjective("Res"));
            int scoreboardValue = -490;
            for(PlayerScoreEntry score : scores) {
                if(score.owner() == player.getScoreboardName()) {
                    PathOfResistance.LOGGER.info("==============[TROUVE : " + score.value() + " ]==============" );
                    scoreboardValue = score.value();
                }
            }

            player.setData(Resistance.RESISTANCE, scoreboardValue);

            int resistancePercent = player.getData(Resistance.RESISTANCE);
            float damage = event.getNewDamage();
            damage = damage * ((100 - resistancePercent) / 100f);
            event.setNewDamage(damage);

        }
    }


    @SubscribeEvent
    public static void onPlayerJoin( PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        PathOfResistance.LOGGER.info("==============[ SYNC SCOREBOARD ]==============" );
        Objective objective = player.getScoreboard().getObjective("Res");
        PathOfResistance.LOGGER.info(objective.getName());
        PathOfResistance.LOGGER.info(player.getScoreboardName());
        Collection<PlayerScoreEntry> scores = player.getScoreboard().listPlayerScores(objective);

        PathOfResistance.LOGGER.info(scores.toString());
        int scoreboardValue = -465;
        for(PlayerScoreEntry score : scores) {
            PathOfResistance.LOGGER.info(score.owner());
            PathOfResistance.LOGGER.info(player.getScoreboardName());

            if(score.owner().equals(player.getScoreboardName())) {
                PathOfResistance.LOGGER.info("==============[TROUVE : " + score.value() + " ]==============" );
                scoreboardValue = score.value();
            }
        }

        player.setData(Resistance.RESISTANCE, scoreboardValue);
    }
}
