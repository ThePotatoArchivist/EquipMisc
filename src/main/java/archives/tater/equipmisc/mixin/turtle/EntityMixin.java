package archives.tater.equipmisc.mixin.turtle;

import archives.tater.equipmisc.registry.EquipMiscAttributes;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public class EntityMixin {
    @SuppressWarnings("ConstantValue")
    @ModifyReturnValue(
            method = "getMaxAirSupply",
            at = @At("RETURN")
    )
    private int modifyMaxAir(int original) {
        if (!((Object) this instanceof LivingEntity livingEntity)) return original;
        if (livingEntity.getAttributes() == null) return original;
        return (int) (EquipMiscAttributes.TICKS_PER_AIR * livingEntity.getAttributeValue(EquipMiscAttributes.MAX_AIR)) - 300 + original;
    }
}
