package fr.wither.pathofresistance.events.server;

import fr.wither.pathofresistance.Commons;
import fr.wither.pathofresistance.PathOfResistance;
import fr.wither.pathofresistance.attributes.Resistance;
import fr.wither.pathofresistance.commands.GetLevelCommand;
import fr.wither.pathofresistance.commands.SetLevelCommand;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.PlayerScoreEntry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

import java.util.Collection;

@EventBusSubscriber(modid = Commons.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeServerEvents {
    @SubscribeEvent
    public static void onServerStarting(ServerAboutToStartEvent event) {

        PathOfResistance.LOGGER.info("============================================[ REGISTERING COMMANDS ]=============================================");
        SetLevelCommand.register(event.getServer().getCommands().getDispatcher());
        GetLevelCommand.register(event.getServer().getCommands().getDispatcher());
        PathOfResistance.LOGGER.info("============================================[ END ]=============================================");

    }
}
