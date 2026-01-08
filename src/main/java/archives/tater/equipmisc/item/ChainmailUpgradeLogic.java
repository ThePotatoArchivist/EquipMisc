package archives.tater.equipmisc.item;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.util.Translation;
import java.util.Map;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ChainmailUpgradeLogic {
    private static ItemAttributeModifiers createModifier(EquipmentSlot slot) {
        return ItemAttributeModifiers.builder().add(
                Attributes.ARMOR_TOUGHNESS,
                new AttributeModifier(
                        EquipMisc.id("chainmail." + slot.getName()),
                        2.0,
                        Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.bySlot(slot)
        ).build();
    }

    public static final Map<EquipmentSlot, ItemAttributeModifiers> MODIFIERS = Map.of(
            EquipmentSlot.HEAD, createModifier(EquipmentSlot.HEAD),
            EquipmentSlot.CHEST, createModifier(EquipmentSlot.CHEST),
            EquipmentSlot.LEGS, createModifier(EquipmentSlot.LEGS),
            EquipmentSlot.FEET, createModifier(EquipmentSlot.FEET)
    );

    public static final Translation.Unit CHAINMAIL_UPGRADE = Translation.unit("item.equipmisc.chainmail_upgrade.tooltip", ChatFormatting.GRAY);
}
