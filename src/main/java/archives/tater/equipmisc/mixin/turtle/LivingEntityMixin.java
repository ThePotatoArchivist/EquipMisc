package archives.tater.equipmisc.mixin.turtle;

import archives.tater.equipmisc.registry.EquipMiscAttributes;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyReturnValue(
            method = "createLivingAttributes",
            at = @At("RETURN")
    )
    private static Builder addAttributes(Builder original) {
        return original.add(EquipMiscAttributes.MAX_AIR);
    }

    @Inject(
            method = "onEquipmentRemoved",
            at = @At("TAIL")
    )
    private void fixAir(ItemStack removedEquipment, EquipmentSlot slot, AttributeContainer container, CallbackInfo ci) {
        if (getAir() > getMaxAir())
            setAir(getMaxAir());
    }
}