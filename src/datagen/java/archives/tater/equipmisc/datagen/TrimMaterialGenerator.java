package archives.tater.equipmisc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

public class TrimMaterialGenerator extends FabricDynamicRegistryProvider {
    public TrimMaterialGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.TRIM_MATERIAL));
    }

    @Override
    public String getName() {
        return "Trim Materials";
    }
}
