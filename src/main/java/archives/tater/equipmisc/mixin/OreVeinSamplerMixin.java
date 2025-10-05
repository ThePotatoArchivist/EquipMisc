package archives.tater.equipmisc.mixin;

import archives.tater.equipmisc.registry.EquipMiscWorldgen;

import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.world.gen.OreVeinSampler;
import net.minecraft.world.gen.OreVeinSampler.VeinType;

@Mixin(OreVeinSampler.class)
public class OreVeinSamplerMixin {
    @ModifyVariable(
            method = "method_40547",
            at = @At("STORE")
    )
    private static VeinType useGoldVein(VeinType value, @Local(ordinal = 0) double d) {
        return d == EquipMiscWorldgen.GOLD_VEIN_VALUE ? EquipMiscWorldgen.getGoldVein() : value;
    }
}
