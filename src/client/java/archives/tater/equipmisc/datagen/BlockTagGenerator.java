package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.registry.EquipMiscBlockTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockIds;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagsProvider.BlockTagsProvider {
    public BlockTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        builder(EquipMiscBlockTags.RAVAGER_HELMET_CAN_DESTROY)
                .add(
                        BlockItemIds.BAMBOO,
                        BlockItemIds.VINE
                )
                .add(BlockIds.BAMBOO_SAPLING)
                .forceAddTag(BlockTags.LEAVES);
    }
}
