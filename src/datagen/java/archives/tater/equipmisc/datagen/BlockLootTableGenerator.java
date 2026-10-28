package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.registry.EquipMiscBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;

import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class BlockLootTableGenerator extends FabricBlockLootSubProvider {
    public BlockLootTableGenerator(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(EquipMiscBlocks.BRONZE_BLOCK);
    }
}
