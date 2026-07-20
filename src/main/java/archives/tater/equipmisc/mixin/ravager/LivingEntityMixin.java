package archives.tater.equipmisc.mixin.ravager;

import archives.tater.equipmisc.component.BreakInArea;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(
            method = "aiStep",
            at = @At("HEAD")
    )
    private void breakLeaves(CallbackInfo ci) {
        BreakInArea.tick((LivingEntity) (Object) this);
    }
}