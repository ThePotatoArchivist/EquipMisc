package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMiscLoot;
import archives.tater.equipmisc.registry.EquipMiscItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class LootTableGenerator extends SimpleFabricLootTableProvider {
    public LootTableGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.CHEST);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, Builder> lootTableBiConsumer) {
        lootTableBiConsumer.accept(EquipMiscLoot.NETHER_FORTRESS_INJECT, new LootTable.Builder()
                .pool(LootPool.builder()
                        .with(ItemEntry.builder(EquipMiscItems.CHAINMAIL_UPGRADE_SMITHING_TEMPLATE)
                                .weight(1))
                        .with(EmptyEntry.builder()
                                .weight(3))
                )
        );
        lootTableBiConsumer.accept(EquipMiscLoot.OCEAN_RUINS_SMALL_INJECT, new LootTable.Builder()
                .pool(LootPool.builder()
                        .with(ItemEntry.builder(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE)
                                .weight(1))
                        .with(EmptyEntry.builder()
                                .weight(1))
                )
        );
        lootTableBiConsumer.accept(EquipMiscLoot.OCEAN_RUINS_BIG_INJECT, new LootTable.Builder()
                .pool(LootPool.builder()
                        .with(ItemEntry.builder(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE))
                )
        );
    }
}
