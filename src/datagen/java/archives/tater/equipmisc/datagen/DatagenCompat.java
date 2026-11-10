package archives.tater.equipmisc.datagen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public interface DatagenCompat {
    private static Item register(Identifier id) {
        var key = ResourceKey.create(Registries.ITEM, id);
        return register(key);
    }

    private static Item register(ResourceKey<Item> key) {
        return Registry.register(BuiltInRegistries.ITEM, key, new Item(new Item.Properties().setId(key)));
    }

    String REARM = "rearm";

    Item NETHERITE_SHIELD = register(Identifier.fromNamespaceAndPath(REARM, "netherite_shield"));

    static void init() {

    }
}
