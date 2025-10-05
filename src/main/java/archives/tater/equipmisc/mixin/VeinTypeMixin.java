package archives.tater.equipmisc.mixin;

import archives.tater.equipmisc.registry.EquipMiscWorldgen;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.OreVeinSampler.VeinType;

import java.util.Arrays;

@Mixin(VeinType.class)
public class VeinTypeMixin {
    @Mutable
    @Shadow
    @Final
    private static VeinType[] field_33609;

    @Invoker("<init>")
    private static VeinType newVeinType(String name, int ordinal, final BlockState ore, final BlockState rawOreBlock, final BlockState stone, final int minY, final int maxY) {
        throw new AssertionError();
    }

    @Inject(
            method = "<clinit>",
            at = @At("TAIL")
    )
    private static void addGoldVeinType(CallbackInfo ci) {
        var length = field_33609.length;
        field_33609 = Arrays.copyOf(field_33609, length + 1);
        var goldVein = newVeinType("EQUIPMISC_GOLD", length, Blocks.NETHER_GOLD_ORE.getDefaultState(), Blocks.GILDED_BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), 30, 128);
        EquipMiscWorldgen.setGoldVein(goldVein);
        field_33609[length] = goldVein;
    }
}
