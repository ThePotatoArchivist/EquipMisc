package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public interface EquipMiscLootTables {
    private static ResourceKey<LootTable> create(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, EquipMisc.id(path));
    }

    ResourceKey<LootTable> TURTLE_BRUSH = create("brush/turtle");
}
