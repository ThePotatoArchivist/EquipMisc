package archives.tater.equipmisc.mixin.turtle;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @WrapWithCondition(
            method = "updateTurtleHelmet",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z")
    )
    private boolean cancelWaterBreathing(PlayerEntity instance, StatusEffectInstance statusEffectInstance) {
        return false;
    }
}
