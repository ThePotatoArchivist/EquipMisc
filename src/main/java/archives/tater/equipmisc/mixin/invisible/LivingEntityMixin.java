package archives.tater.equipmisc.mixin.invisible;

import archives.tater.equipmisc.EquipMisc;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Function;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @WrapOperation(
            method = "getArmorCoverPercentage",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z")
    )
    private boolean invisibleChainmail(ItemStack instance, Operation<Boolean> original) {
        return original.call(instance) || EquipMisc.isInvisibleEquipment(instance);
    }

    @SuppressWarnings("unchecked")
    @ModifyArg(
            method = "updateSynchronizedMobEffectParticles",
            at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;map(Ljava/util/function/Function;)Ljava/util/stream/Stream;")
    )
    private <T, R> Function<? super T, ? extends R> invisibleOpacity(Function<? super T, ? extends R> function) {
        if (!isInvisible()) return function;
        return t -> {
            var r = function.apply(t);
            if (!(r instanceof ColorParticleOption particle)) return r;
            return (R) ColorParticleOption.create(particle.getType(), ARGB.color(38, ARGB.colorFromFloat(1f, particle.getRed(), particle.getGreen(), particle.getBlue())));
        };
    }
}
