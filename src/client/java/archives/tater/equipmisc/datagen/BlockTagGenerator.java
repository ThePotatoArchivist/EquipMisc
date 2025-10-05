package archives.tater.equipmisc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {
    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.NETHER_CARVER_REPLACEABLES).add(Blocks.GILDED_BLACKSTONE, Blocks.NETHER_GOLD_ORE, Blocks.RAW_GOLD_BLOCK);
    }
}
