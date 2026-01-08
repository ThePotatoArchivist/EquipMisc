package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.registry.EquipMiscBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {
    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(EquipMiscBlocks.RAVAGER_HELMET_CAN_DESTROY)
                .add(
                        Blocks.BAMBOO,
                        Blocks.BAMBOO_SAPLING,
                        Blocks.VINE
                )
                .forceAddTag(BlockTags.LEAVES);
    }
}
