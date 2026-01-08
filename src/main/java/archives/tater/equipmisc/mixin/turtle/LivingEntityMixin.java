package archives.tater.equipmisc.mixin.turtle;

import archives.tater.equipmisc.registry.EquipMiscAttributes;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, Level world) {
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
            method = "stopLocationBasedEffects",
            at = @At("TAIL")
    )
    private void fixAir(ItemStack removedEquipment, EquipmentSlot slot, AttributeMap container, CallbackInfo ci) {
        if (getAirSupply() > getMaxAirSupply())
            setAirSupply(getMaxAirSupply());
    }
}