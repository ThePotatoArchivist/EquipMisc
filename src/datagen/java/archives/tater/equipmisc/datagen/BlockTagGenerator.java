package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.registry.EquipMiscBlockItemIds;
import archives.tater.equipmisc.registry.EquipMiscBlockItemTags;
import archives.tater.equipmisc.registry.EquipMiscBlockTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockItemTags;

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

        builder(EquipMiscBlockItemTags.STORAGE_BLOCKS_BRONZE)
                .add(EquipMiscBlockItemIds.BRONZE_BLOCK);

        builder(ConventionalBlockItemTags.STORAGE_BLOCKS)
                .addTag(EquipMiscBlockItemTags.STORAGE_BLOCKS_BRONZE.block());

        builder(BlockTags.MINEABLE_WITH_PICKAXE).add(EquipMiscBlockItemIds.BRONZE_BLOCK);
        builder(BlockTags.NEEDS_STONE_TOOL).add(EquipMiscBlockItemIds.BRONZE_BLOCK);
        builder(BlockTags.BEACON_BASE_BLOCKS).add(EquipMiscBlockItemIds.BRONZE_BLOCK);
        builder(BlockTags.BLOCKS_MOTION_NO_LEAVES).add(EquipMiscBlockItemIds.BRONZE_BLOCK);
    }
}
