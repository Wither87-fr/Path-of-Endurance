package fr.wither.pathofresistance.attributes;

import com.mojang.serialization.Codec;
import fr.wither.pathofresistance.Commons;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class Resistance {

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Commons.MOD_ID);



    public static final Supplier<AttachmentType<Integer>> RESISTANCE = ATTACHMENT_TYPES.register("resistance", () -> AttachmentType.<Integer>builder(() -> -490).serialize(Codec.INT).build());


    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }

}
