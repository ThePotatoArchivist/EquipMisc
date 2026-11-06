package archives.tater.equipmisc.mixin.invisible;

import archives.tater.equipmisc.EquipMisc;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @WrapOperation(
            method = "getArmorCoverPercentage",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z")
    )
    private boolean invisibleChainmail(ItemStack instance, Operation<Boolean> original) {
        return original.call(instance) || EquipMisc.isInvisibleEquipment(instance);
    }
}
