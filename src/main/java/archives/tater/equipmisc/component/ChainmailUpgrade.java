package archives.tater.equipmisc.component;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.util.Translation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipProvider;

import io.netty.buffer.ByteBuf;

import java.util.Map;
import java.util.function.Consumer;

public class ChainmailUpgrade implements TooltipProvider {
    public static final ChainmailUpgrade INSTANCE = new ChainmailUpgrade();
    public static final Codec<ChainmailUpgrade> CODEC = MapCodec.unitCodec(INSTANCE);
    public static final StreamCodec<ByteBuf, ChainmailUpgrade> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    public static final Map<EquipmentSlot, ItemAttributeModifiers> MODIFIERS = Map.of(
            EquipmentSlot.HEAD, createModifier(EquipmentSlot.HEAD),
            EquipmentSlot.CHEST, createModifier(EquipmentSlot.CHEST),
            EquipmentSlot.LEGS, createModifier(EquipmentSlot.LEGS),
            EquipmentSlot.FEET, createModifier(EquipmentSlot.FEET)
    );

    public static final Translation.Unit TOOLTIP = Translation.unit("item.equipmisc.chainmail_upgrade.tooltip", ChatFormatting.GRAY);

    private static ItemAttributeModifiers createModifier(EquipmentSlot slot) {
        return ItemAttributeModifiers.builder().add(
                Attributes.ARMOR_TOUGHNESS,
                new AttributeModifier(
                        EquipMisc.id("chainmail." + slot.getName()),
                        2.0,
                        AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.bySlot(slot)
        ).build();
    }

    private ChainmailUpgrade() {}

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        consumer.accept(TOOLTIP.text);
    }
}
