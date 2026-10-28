package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMiscLoot;
import archives.tater.equipmisc.registry.EquipMiscItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class LootTableGenerator extends SimpleFabricLootTableSubProvider {
    public LootTableGenerator(FabricPackOutput output, CompletableFuture<Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, Builder> lootTableBiConsumer) {
        lootTableBiConsumer.accept(EquipMiscLoot.NETHER_FORTRESS_INJECT, new LootTable.Builder()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(EquipMiscItems.CHAINMAIL_UPGRADE_SMITHING_TEMPLATE)
                                .setWeight(1))
                        .add(EmptyLootItem.emptyItem()
                                .setWeight(3))
                )
        );
        lootTableBiConsumer.accept(EquipMiscLoot.OCEAN_RUINS_SMALL_INJECT, new LootTable.Builder()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE)
                                .setWeight(1))
                        .add(EmptyLootItem.emptyItem()
                                .setWeight(1))
                )
        );
        lootTableBiConsumer.accept(EquipMiscLoot.OCEAN_RUINS_BIG_INJECT, new LootTable.Builder()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE))
                )
        );
    }
}
