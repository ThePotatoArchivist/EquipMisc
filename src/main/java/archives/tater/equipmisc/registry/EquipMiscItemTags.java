package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EquipMiscItemTags {
    private static TagKey<Item> create(String path) {
        return TagKey.create(Registries.ITEM, EquipMisc.id(path));
    }

    private static TagKey<Item> createCommon(String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TagUtil.C_TAG_NAMESPACE, path));
    }

    public static final TagKey<Item> REPAIRS_BRONZE_ARMOR = create("repairs_bronze_armor");
    public static final TagKey<Item> BRONZE_TOOL_MATERIALS = create("bronze_tool_materials");
    public static final TagKey<Item> BRONZE_EQUIPMENT = create("bronze_equipment");

    public static final TagKey<Item> ENCHANTED_INVISIBLE_EQUIPMENT = create("enchanted_invisible_equipment");

    public static final TagKey<Item> BRONZE_INGOTS = createCommon("ingots/bronze");
    public static final TagKey<Item> BRONZE_RAW_MATERIALS = createCommon("raw_materials/bronze");
}
