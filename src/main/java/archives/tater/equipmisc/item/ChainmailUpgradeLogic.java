package archives.tater.equipmisc.item;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.util.Translation;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Formatting;

import java.util.Map;

public class ChainmailUpgradeLogic {
    private static AttributeModifiersComponent createModifier(EquipmentSlot slot) {
        return AttributeModifiersComponent.builder().add(
                EntityAttributes.ARMOR_TOUGHNESS,
                new EntityAttributeModifier(
                        EquipMisc.id("chainmail." + slot.getName()),
                        2.0,
                        Operation.ADD_VALUE
                ),
                AttributeModifierSlot.forEquipmentSlot(slot)
        ).build();
    }

    public static final Map<EquipmentSlot, AttributeModifiersComponent> MODIFIERS = Map.of(
            EquipmentSlot.HEAD, createModifier(EquipmentSlot.HEAD),
            EquipmentSlot.CHEST, createModifier(EquipmentSlot.CHEST),
            EquipmentSlot.LEGS, createModifier(EquipmentSlot.LEGS),
            EquipmentSlot.FEET, createModifier(EquipmentSlot.FEET)
    );

    public static final Translation.Unit CHAINMAIL_UPGRADE = Translation.unit("item.equipmisc.chainmail_upgrade.tooltip", Formatting.GRAY);
}
