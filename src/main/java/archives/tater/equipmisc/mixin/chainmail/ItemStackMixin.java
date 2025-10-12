package archives.tater.equipmisc.mixin.chainmail;

import archives.tater.equipmisc.item.ChainmailUpgradeLogic;
import archives.tater.equipmisc.registry.EquipMiscComponents;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.component.ComponentHolder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.AttributeModifiersComponent.Display;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;

import org.apache.commons.lang3.function.TriConsumer;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ComponentHolder {
    @WrapOperation(
            method = "applyAttributeModifier",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/component/type/AttributeModifiersComponent;applyModifiers(Lnet/minecraft/component/type/AttributeModifierSlot;Lorg/apache/commons/lang3/function/TriConsumer;)V")
    )
    private void addChainmailModifier(AttributeModifiersComponent instance, AttributeModifierSlot slot, TriConsumer<RegistryEntry<EntityAttribute>, EntityAttributeModifier, Display> attributeConsumer, Operation<Void> original) {
        original.call(instance, slot, attributeConsumer);
        var equippable = this.get(DataComponentTypes.EQUIPPABLE);
        if (equippable != null && this.contains(EquipMiscComponents.CHAINMAIL_UPGRADE)) {
            original.call(ChainmailUpgradeLogic.MODIFIERS.get(equippable.slot()), slot, attributeConsumer);
        }
    }

    @WrapOperation(
            method = "applyAttributeModifiers",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/component/type/AttributeModifiersComponent;applyModifiers(Lnet/minecraft/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V")
    )
    private void addChainmailModifier(AttributeModifiersComponent instance, EquipmentSlot slot, BiConsumer<RegistryEntry<EntityAttribute>, EntityAttributeModifier> attributeConsumer, Operation<Void> original) {
        original.call(instance, slot, attributeConsumer);
        var equippable = this.get(DataComponentTypes.EQUIPPABLE);
        if (equippable != null && this.contains(EquipMiscComponents.CHAINMAIL_UPGRADE)) {
            original.call(ChainmailUpgradeLogic.MODIFIERS.get(equippable.slot()), slot, attributeConsumer);
        }
    }

    @Inject(
            method = "appendTooltip",
            slice = @Slice(
                    from = @At(value = "FIELD", target = "Lnet/minecraft/component/DataComponentTypes;TRIM:Lnet/minecraft/component/ComponentType;")
            ),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;appendComponentTooltip(Lnet/minecraft/component/ComponentType;Lnet/minecraft/item/Item$TooltipContext;Lnet/minecraft/component/type/TooltipDisplayComponent;Ljava/util/function/Consumer;Lnet/minecraft/item/tooltip/TooltipType;)V", ordinal = 0)
    )
    private void addChainmailTooltip(TooltipContext context, TooltipDisplayComponent displayComponent, @Nullable PlayerEntity player, TooltipType type, Consumer<Text> textConsumer, CallbackInfo ci) {
        if (this.contains(EquipMiscComponents.CHAINMAIL_UPGRADE))
            textConsumer.accept(ChainmailUpgradeLogic.CHAINMAIL_UPGRADE.text);
    }
}
