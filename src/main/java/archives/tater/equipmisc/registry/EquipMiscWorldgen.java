package archives.tater.equipmisc.registry;

import net.minecraft.world.gen.OreVeinSampler.VeinType;

import org.jetbrains.annotations.ApiStatus.Internal;

public class EquipMiscWorldgen {
    private static VeinType GOLD_VEIN;
    public static final double GOLD_VEIN_VALUE = -32768;

    public static VeinType getGoldVein() {
        return GOLD_VEIN;
    }

    @Internal
    public static void setGoldVein(VeinType goldVein) {
        if (GOLD_VEIN != null)
            throw new AssertionError("GOLD_VEIN was already set");
        GOLD_VEIN = goldVein;
    }
}
