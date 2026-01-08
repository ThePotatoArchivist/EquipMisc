package archives.tater.equipmisc;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public class EquipMiscLoot {
    private static ResourceKey<LootTable> of(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, EquipMisc.id(path));
    }

    private static ResourceKey<LootTable> injectOf(String subpath) {
        return of("inject/" + subpath);
    }

    public static final ResourceKey<LootTable> OCEAN_RUINS_SMALL_INJECT = injectOf("chest/ruins_small");
    public static final ResourceKey<LootTable> OCEAN_RUINS_BIG_INJECT = injectOf("chest/ruins_big");
    public static final ResourceKey<LootTable> NETHER_FORTRESS_INJECT = injectOf("chest/fortress");

    public static void init() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key == BuiltInLootTables.UNDERWATER_RUIN_SMALL)
                tableBuilder.pool(LootPool.lootPool().add(NestedLootTable.lootTableReference(OCEAN_RUINS_SMALL_INJECT)).build());
            if (key == BuiltInLootTables.UNDERWATER_RUIN_BIG)
                tableBuilder.pool(LootPool.lootPool().add(NestedLootTable.lootTableReference(OCEAN_RUINS_BIG_INJECT)).build());
            if (key == BuiltInLootTables.NETHER_BRIDGE)
                tableBuilder.pool(LootPool.lootPool().add(NestedLootTable.lootTableReference(NETHER_FORTRESS_INJECT)).build());
        });
    }
}
