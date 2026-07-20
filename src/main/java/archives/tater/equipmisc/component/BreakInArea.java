package archives.tater.equipmisc.component;

import archives.tater.equipmisc.registry.EquipMiscComponents;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gamerules.GameRules;

import static java.util.Objects.requireNonNullElse;
import static net.minecraft.util.Mth.floor;

public record BreakInArea(
        double hitboxInflation,
        TagKey<Block> canBreak,
        EquipmentSlotGroup slots
) {
    public static final Codec<BreakInArea> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.doubleRange(0, 2.0).fieldOf("hitbox_inflate").forGetter(BreakInArea::hitboxInflation),
            TagKey.codec(Registries.BLOCK).fieldOf("can_break").forGetter(BreakInArea::canBreak),
            EquipmentSlotGroup.CODEC.fieldOf("slots").forGetter(BreakInArea::slots)
    ).apply(instance, BreakInArea::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BreakInArea> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, BreakInArea::hitboxInflation,
            TagKey.streamCodec(Registries.BLOCK), BreakInArea::canBreak,
            EquipmentSlotGroup.STREAM_CODEC, BreakInArea::slots,
            BreakInArea::new
    );

    public void breakBlocks(LivingEntity entity) {
        var box = entity.getBoundingBox().inflate(hitboxInflation);

        for (var pos : BlockPos.betweenClosed(
                floor(box.minX), floor(box.minY), floor(box.minZ),
                floor(box.maxX), floor(box.maxY), floor(box.maxZ)
        )) {
            if (entity.level().getBlockState(pos).is(canBreak))
                entity.level().destroyBlock(pos, true, entity);
        }
    }

    public static void tick(LivingEntity entity) {
        if (!entity.isAlive()
//                || !entity.horizontalCollision
                || (!(getController(entity) instanceof Player) && entity.level() instanceof ServerLevel level && !level.getGameRules().get(GameRules.MOB_GRIEFING)))
            return;

        for (var slot : EquipmentSlot.VALUES) {
            var component = entity.getItemBySlot(slot).get(EquipMiscComponents.BREAK_IN_AREA);
            if (component == null || !component.slots.test(slot)) continue;
            component.breakBlocks(entity);
        }
    }

    private static LivingEntity getController(LivingEntity entity) {
        return requireNonNullElse(entity.getControllingPassenger(), entity);
    }
}
