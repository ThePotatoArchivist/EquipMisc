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

public class ModLootModificationProvider extends FabricDynamicRegistryProvider {
    public ModLootModificationProvider(FabricPackOutput output, CompletableFuture<Provider> registryLookup) {
        super(output, registryLookup);
    }

    public static ResourceKey<LootModification> createKey(ResourceKey<LootTable> key) {
        return ResourceKey.create(LootInj.LOOT_MODIFICATION, EquipMisc.id(key.identifier().getPath()));
    }

    @Override
    protected void configure(Provider registries, Entries entries) {
        entries.add(createKey(BuiltInLootTables.NETHER_BRIDGE), LootModification.builder()
                .target(BuiltInLootTables.NETHER_BRIDGE)
                .pool(LootPool.lootPool()
                        .add(lootTableItem(EquipMiscItems.CHAINMAIL_UPGRADE_SMITHING_TEMPLATE)
                                .setWeight(1))
                        .add(emptyItem()
                                .setWeight(3)))
                .build());

        entries.add(createKey(BuiltInLootTables.UNDERWATER_RUIN_SMALL), LootModification.builder()
                .target(BuiltInLootTables.UNDERWATER_RUIN_SMALL)
                .pool(LootPool.lootPool()
                        .add(lootTableItem(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE)
                                .setWeight(1))
                        .add(emptyItem()
                                .setWeight(1)))
                .build());

        entries.add(createKey(BuiltInLootTables.UNDERWATER_RUIN_BIG), LootModification.builder()
                .target(BuiltInLootTables.UNDERWATER_RUIN_BIG)
                .pool(LootPool.lootPool()
                        .add(lootTableItem(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE)))
                .build());
    }

    @Override
    public String getName() {
        return "Loot Modifications";
    }
}
