package fr.wither.pathofresistance.events.server;

import fr.wither.pathofresistance.Commons;
import fr.wither.pathofresistance.commands.GetLevelCommand;
import fr.wither.pathofresistance.commands.SetLevelCommand;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

@EventBusSubscriber(modid = Commons.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeServerEvents {
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        SetLevelCommand.register(event.getServer().getCommands().getDispatcher());
        GetLevelCommand.register(event.getServer().getCommands().getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        if(!Commons.IMMUNITY_LEVEL_PLAYERS.containsKey(player.getUUID())) {
            Commons.IMMUNITY_LEVEL_PLAYERS.put(player.getUUID(), -490);
        }
    }
}
