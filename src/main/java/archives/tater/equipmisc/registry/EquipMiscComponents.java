package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.component.BreakInAreaComponent;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;

public class EquipMiscComponents {
    private static <T> DataComponentType<T> register(Identifier id, Codec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec, boolean cache) {
        var builder = DataComponentType.<T>builder().persistent(codec).networkSynchronized(streamCodec);
        if (cache) builder.cacheEncoding();
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id, builder.build());
    }

    private static <T> DataComponentType<T> register(String path, Codec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec, boolean cache) {
        return register(EquipMisc.id(path), codec, streamCodec, cache);
    }

    private static <T> DataComponentType<T> register(String path, Codec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        return register(path, codec, streamCodec, true);
    }

    public static final DataComponentType<Unit> CHAINMAIL_UPGRADE = register("chainmail_upgrade", Unit.CODEC, Unit.STREAM_CODEC, false);

    public static final DataComponentType<BreakInAreaComponent> BREAK_IN_AREA = register("break_in_area", BreakInAreaComponent.CODEC, BreakInAreaComponent.STREAM_CODEC);

    public static void init() {

    }
}
