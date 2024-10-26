package fr.wither.pathofresistance.events.server;

import fr.wither.pathofresistance.Commons;
import fr.wither.pathofresistance.PathOfResistance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = Commons.MOD_ID)
public class CommonEvents {
    @SubscribeEvent
    public static void onEntyHurt(LivingDamageEvent.Pre event) {
        if(event.getEntity() instanceof Player player) {
            if(Commons.IMMUNITY_LEVEL_PLAYERS.containsKey(player.getUUID())) {
                float damage = event.getNewDamage();
                damage = damage * ((100 - Commons.IMMUNITY_LEVEL_PLAYERS.get(player.getUUID())) / 100f);
                event.setNewDamage(damage);
            }
        }
    }
}
