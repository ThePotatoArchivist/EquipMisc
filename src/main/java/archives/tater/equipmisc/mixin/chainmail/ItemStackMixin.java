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
import org.apache.commons.lang3.function.TriConsumer;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemAttributeModifiers.Display;
import net.minecraft.world.item.component.TooltipDisplay;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder {
    @WrapOperation(
            method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/ItemAttributeModifiers;forEach(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V")
    )
    private void addChainmailModifier(ItemAttributeModifiers instance, EquipmentSlotGroup slot, TriConsumer<Holder<Attribute>, AttributeModifier, Display> attributeConsumer, Operation<Void> original) {
        original.call(instance, slot, attributeConsumer);
        var equippable = this.get(DataComponents.EQUIPPABLE);
        if (equippable != null && this.has(EquipMiscComponents.CHAINMAIL_UPGRADE)) {
            original.call(ChainmailUpgradeLogic.MODIFIERS.get(equippable.slot()), slot, attributeConsumer);
        }
    }

    @WrapOperation(
            method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/ItemAttributeModifiers;forEach(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V")
    )
    private void addChainmailModifier(ItemAttributeModifiers instance, EquipmentSlot slot, BiConsumer<Holder<Attribute>, AttributeModifier> attributeConsumer, Operation<Void> original) {
        original.call(instance, slot, attributeConsumer);
        var equippable = this.get(DataComponents.EQUIPPABLE);
        if (equippable != null && this.has(EquipMiscComponents.CHAINMAIL_UPGRADE)) {
            original.call(ChainmailUpgradeLogic.MODIFIERS.get(equippable.slot()), slot, attributeConsumer);
        }
    }

    @Inject(
            method = "addDetailsToTooltip",
            slice = @Slice(
                    from = @At(value = "FIELD", target = "Lnet/minecraft/core/component/DataComponents;TRIM:Lnet/minecraft/core/component/DataComponentType;")
            ),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V", ordinal = 0)
    )
    private void addChainmailTooltip(TooltipContext context, TooltipDisplay displayComponent, @Nullable Player player, TooltipFlag type, Consumer<Component> textConsumer, CallbackInfo ci) {
        if (this.has(EquipMiscComponents.CHAINMAIL_UPGRADE))
            textConsumer.accept(ChainmailUpgradeLogic.CHAINMAIL_UPGRADE.text);
    }
}
