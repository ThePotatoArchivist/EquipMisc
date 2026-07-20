package archives.tater.equipmisc.mixin.turtle;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

@Mixin(Player.class)
public class PlayerEntityMixin {
    @WrapOperation(
            method = "turtleHelmetTick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z")
    )
    private boolean cancelWaterBreathing(Player instance, MobEffectInstance mobEffectInstance, Operation<Boolean> original) {
        return false;
    }
}
