package archives.tater.equipmisc;

import archives.tater.equipmisc.datagen.*;
import archives.tater.equipmisc.registry.EquipMiscTrimMaterials;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class EquipMiscDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.TRIM_MATERIAL, EquipMiscTrimMaterials::bootstrap);
    }

    @Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();
        var blockTags = pack.addProvider(BlockTagGenerator::new);
        pack.addProvider((output, registriesFuture) -> new ItemTagGenerator(output, registriesFuture, blockTags));
        pack.addProvider(BlockLootTableGenerator::new);
        pack.addProvider(AdvancementGenerator::new);
        pack.addProvider(RecipeGenerator::new);
        pack.addProvider(LootTableGenerator::new);
        pack.addProvider(PredicateGenerator::new);
        pack.addProvider(TrimMaterialGenerator::new);

        pack.addProvider(ModelGenerator::new);
        pack.addProvider(LangGenerator::new);
        pack.addProvider(AtlasGenerator::new);
	}
}
