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

import java.util.function.BiFunction;
import java.util.function.Consumer;

import static archives.tater.equipmisc.EquipMiscUtil.weakMemoize;
import static java.lang.Math.min;

public class ChainmailUpgrade implements TooltipProvider {
    public static final ChainmailUpgrade INSTANCE = new ChainmailUpgrade();
    public static final Codec<ChainmailUpgrade> CODEC = MapCodec.unitCodec(INSTANCE);
    public static final StreamCodec<ByteBuf, ChainmailUpgrade> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    public static final Translation.Unit TOOLTIP = Translation.unit("item.equipmisc.chainmail_upgrade.tooltip", ChatFormatting.GRAY);

    // TODO dehardcode
    private static int getMaxArmor(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD, FEET -> 3;
            case CHEST -> 8;
            case LEGS -> 6;
            default -> 0;
        };
    }

    public static final BiFunction<EquipmentSlot, ItemAttributeModifiers, ItemAttributeModifiers> MODIFIERS = weakMemoize((slot, attributes) -> {
        var baseArmor = attributes.compute(Attributes.ARMOR, 0, slot);
        var bonus = 2;
        var armorBonus = min(bonus, getMaxArmor(slot) - baseArmor);
        var toughnessBonus = bonus - armorBonus;
        var builder = ItemAttributeModifiers.builder();
        if (armorBonus > 0) builder.add(Attributes.ARMOR, new AttributeModifier(EquipMisc.id("chainmail." + slot.getName()), armorBonus, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(slot));
        if (toughnessBonus > 0) builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(EquipMisc.id("chainmail." + slot.getName()), toughnessBonus, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(slot));
        return builder.build();
    });

    private ChainmailUpgrade() {}

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        consumer.accept(TOOLTIP.text);
    }
}
