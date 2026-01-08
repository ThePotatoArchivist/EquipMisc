package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;

public class EquipMiscComponents {
    private static <T> DataComponentType<T> register(Identifier id, Codec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> packetCodec, boolean cache) {
        var builder = DataComponentType.<T>builder().persistent(codec).networkSynchronized(packetCodec);
        if (cache) builder.cacheEncoding();
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id, builder.build());
    }

    public static final DataComponentType<Unit> CHAINMAIL_UPGRADE = register(EquipMisc.id("chainmail_upgrade"), Unit.CODEC, Unit.STREAM_CODEC, false);

    public static void init() {

    }
}
