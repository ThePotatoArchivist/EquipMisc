package archives.tater.equipmisc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootPredicates;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.advancements.predicates.ItemPredicate.Builder.item;
import static net.minecraft.world.level.storage.loot.predicates.MatchTool.toolMatches;

public class PredicateGenerator extends FabricDynamicRegistryProvider {
    public PredicateGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        var items = registries.lookupOrThrow(Registries.ITEM);

        // Hopefully anyone overwriting this condition will do the same
        entries.add(LootPredicates.TOOL_CAN_SHEAR, toolMatches(item().of(items, ConventionalItemTags.SHEAR_TOOLS)).build());
    }

    @Override
    public String getName() {
        return "Predicates";
    }
}
