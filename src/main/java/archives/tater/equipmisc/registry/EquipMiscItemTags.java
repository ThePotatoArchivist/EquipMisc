package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EquipMiscItemTags {
    private static TagKey<Item> create(String path) {
        return TagKey.create(Registries.ITEM, EquipMisc.id(path));
    }

    public static final TagKey<Item> REPAIRS_BRONZE_ARMOR = create("repairs_bronze_armor");
    public static final TagKey<Item> BRONZE_TOOL_MATERIALS = create("bronze_tool_materials");
    public static final TagKey<Item> BRONZE_EQUIPMENT = create("bronze_equipment");

    public static final TagKey<Item> ENCHANTED_INVISIBLE_EQUIPMENT = create("enchanted_invisible_equipment");
}
