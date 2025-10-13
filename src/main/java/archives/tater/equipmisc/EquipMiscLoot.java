package archives.tater.equipmisc;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class EquipMiscLoot {
    private static RegistryKey<LootTable> of(String path) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, EquipMisc.id(path));
    }

    private static RegistryKey<LootTable> injectOf(String subpath) {
        return of("inject/" + subpath);
    }

    public static final RegistryKey<LootTable> OCEAN_RUINS_INJECT = injectOf("chest/ruins");
    public static final RegistryKey<LootTable> NETHER_FORTRESS_INJECT = injectOf("chest/fortress");

    public static void init() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key == LootTables.UNDERWATER_RUIN_BIG_CHEST)
                tableBuilder.pool(LootPool.builder().with(LootTableEntry.builder(OCEAN_RUINS_INJECT)).build());
            if (key == LootTables.NETHER_BRIDGE_CHEST)
                tableBuilder.pool(LootPool.builder().with(LootTableEntry.builder(NETHER_FORTRESS_INJECT)).build());
        });
    }
}
