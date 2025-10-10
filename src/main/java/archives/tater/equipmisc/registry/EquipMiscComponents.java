package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

public class EquipMiscComponents {
    private static <T> ComponentType<T> register(Identifier id, Codec<T> codec, PacketCodec<? super RegistryByteBuf, T> packetCodec, boolean cache) {
        var builder = ComponentType.<T>builder().codec(codec).packetCodec(packetCodec);
        if (cache) builder.cache();
        return Registry.register(Registries.DATA_COMPONENT_TYPE, id, builder.build());
    }

    public static final ComponentType<Unit> CHAINMAIL_UPGRADE = register(EquipMisc.id("chainmail_upgrade"), Unit.CODEC, Unit.PACKET_CODEC, false);

    public static void init() {

    }
}
