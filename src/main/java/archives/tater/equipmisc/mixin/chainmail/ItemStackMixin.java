package archives.tater.equipmisc.mixin.chainmail;

import archives.tater.equipmisc.component.ChainmailUpgrade;
import archives.tater.equipmisc.registry.EquipMiscComponents;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemAttributeModifiers.Display;

import org.apache.commons.lang3.function.TriConsumer;

import java.util.function.BiConsumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder {
    @WrapOperation(
            method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/ItemAttributeModifiers;forEach(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V")
    )
    private void addChainmailModifier(ItemAttributeModifiers instance, EquipmentSlotGroup slot, TriConsumer<Holder<Attribute>, AttributeModifier, Display> consumer, Operation<Void> original) {
        original.call(instance, slot, consumer);
        var equippable = this.get(DataComponents.EQUIPPABLE);
        if (equippable != null && this.has(EquipMiscComponents.CHAINMAIL_UPGRADE)) {
            original.call(ChainmailUpgrade.MODIFIERS.get(equippable.slot()), slot, consumer);
        }
    }

    @WrapOperation(
            method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/ItemAttributeModifiers;forEach(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V")
    )
    private void addChainmailModifier(ItemAttributeModifiers instance, EquipmentSlot slot, BiConsumer<Holder<Attribute>, AttributeModifier> consumer, Operation<Void> original) {
        original.call(instance, slot, consumer);
        var equippable = this.get(DataComponents.EQUIPPABLE);
        if (equippable != null && this.has(EquipMiscComponents.CHAINMAIL_UPGRADE)) {
            original.call(ChainmailUpgrade.MODIFIERS.get(equippable.slot()), slot, consumer);
        }
    }
}
