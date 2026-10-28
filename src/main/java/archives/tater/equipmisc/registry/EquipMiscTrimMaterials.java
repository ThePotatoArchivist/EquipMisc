package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;

import static net.minecraft.util.Util.makeDescriptionId;

public interface EquipMiscTrimMaterials {
    private static ResourceKey<TrimMaterial> create(String path) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, EquipMisc.id(path));
    }

    ResourceKey<TrimMaterial> BRONZE = create("bronze");

    static void bootstrap(BootstrapContext<TrimMaterial> registry) {
        registry.register(BRONZE, new TrimMaterial(
                TrimMaterials.Palette.EQUIPMISC_BRONZE.id(),
                Component.translatable(makeDescriptionId("trim_material", BRONZE.identifier()))
                        .withColor(0xD8A039)
        ));
    }
}
