package archives.tater.equipmisc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSources;
import net.minecraft.client.renderer.texture.atlas.sources.PalettedPermutations;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.AtlasIds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.equipment.trim.TrimMaterials;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class AtlasGenerator extends FabricCodecDataProvider<List<SpriteSource>> {
    public AtlasGenerator(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture, PackOutput.Target.RESOURCE_PACK, "atlases", SpriteSources.FILE_CODEC);
    }

    @Override
    protected void configure(BiConsumer<Identifier, List<SpriteSource>> provider, HolderLookup.Provider registryLookup) {
        provider.accept(AtlasIds.ITEMS, List.of(
                new PalettedPermutations(
                        List.of(
                                ItemModelGenerators.TRIM_PREFIX_HELMET,
                                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
                                ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
                                ItemModelGenerators.TRIM_PREFIX_BOOTS
                        ),
                        Identifier.withDefaultNamespace("trim_base"),
                        Map.of(
                                TrimMaterials.Palette.EQUIPMISC_BRONZE.suffix(), TrimMaterials.Palette.EQUIPMISC_BRONZE.id(),
                                TrimMaterials.Palette.EQUIPMISC_BRONZE_DARKER.suffix(), TrimMaterials.Palette.EQUIPMISC_BRONZE_DARKER.id()
                        )
                )
        ));
    }

    @Override
    public String getName() {
        return "Atlases";
    }
}
