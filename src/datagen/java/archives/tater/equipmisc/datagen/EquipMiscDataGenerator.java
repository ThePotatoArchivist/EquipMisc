package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.registry.EquipMiscTrimMaterials;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

import org.jspecify.annotations.Nullable;

public class EquipMiscDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public @Nullable String getEffectiveModId() {
        return EquipMisc.MOD_ID;
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.TRIM_MATERIAL, EquipMiscTrimMaterials::bootstrap);
    }

    @Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();
        var blockTags = pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider((output, registriesFuture) -> new ModItemTagProvider(output, registriesFuture, blockTags));
        pack.addProvider(ModBlockLootProvider::new);
        pack.addProvider(ModAdvancementProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModLootModificationProvider::new);
        pack.addProvider(ModPredicateProvider::new);
        pack.addProvider(ModTrimMaterialProvider::new);

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModLangProvider::new);
        pack.addProvider(ModAtlasProvider::new);
        pack.addProvider(ModEquipmentProvider::new);
        pack.addProvider(ModSoundsProvider::new);
	}
}
