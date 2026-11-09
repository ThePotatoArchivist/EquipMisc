package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.registry.EquipMiscItems;
import archives.tater.lootinj.api.LootInj;
import archives.tater.lootinj.api.LootModification;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.storage.loot.entries.EmptyLootItem.emptyItem;
import static net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem;
import static net.minecraft.world.level.storage.loot.functions.SetItemCountFunction.setCount;
import static net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders.between;

public class ModLootModificationProvider extends FabricDynamicRegistryProvider {
    public ModLootModificationProvider(FabricPackOutput output, CompletableFuture<Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    protected void configure(Provider registries, Entries entries) {
        register(entries, BuiltInLootTables.NETHER_BRIDGE, LootModification.builder()
                .pool(LootPool.lootPool()
                        .add(lootTableItem(EquipMiscItems.CHAINMAIL_UPGRADE_SMITHING_TEMPLATE)
                                .setWeight(1))
                        .add(emptyItem()
                                .setWeight(3))));

        register(entries, BuiltInLootTables.UNDERWATER_RUIN_SMALL, LootModification.builder()
                .pool(LootPool.lootPool()
                        .add(lootTableItem(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE)
                                .setWeight(1))
                        .add(emptyItem()
                                .setWeight(1))));

        register(entries, BuiltInLootTables.UNDERWATER_RUIN_BIG, LootModification.builder()
                .pool(LootPool.lootPool()
                        .add(lootTableItem(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE))));

        register(entries, BuiltInLootTables.TURTLE_GROW, LootModification.builder()
                .apply(setCount(between(1, 4), true)));
    }

    private static void register(Entries entries, ResourceKey<LootTable> target, LootModification.Builder builder) {
        entries.add(createKey(target), builder.target(target).build());
    }

    private static ResourceKey<LootModification> createKey(ResourceKey<LootTable> key) {
        return ResourceKey.create(LootInj.LOOT_MODIFICATION, EquipMisc.id(key.identifier().getPath()));
    }

    @Override
    public String getName() {
        return "Loot Modifications";
    }
}
