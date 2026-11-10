package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public interface EquipMiscItemTags {
    private static TagKey<Item> create(String path) {
        return TagKey.create(Registries.ITEM, EquipMisc.id(path));
    }

    private static TagKey<Item> createCommon(String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TagUtil.C_TAG_NAMESPACE, path));
    }

    TagKey<Item> REPAIRS_BRONZE_ARMOR = create("repairs_bronze_armor");
    TagKey<Item> BRONZE_TOOL_MATERIALS = create("bronze_tool_materials");
    TagKey<Item> BRONZE_EQUIPMENT = create("bronze_equipment");

    TagKey<Item> ENCHANTED_INVISIBLE_EQUIPMENT = create("enchanted_invisible_equipment");

    TagKey<Item> BRONZE_INGOTS = createCommon("ingots/bronze");
    TagKey<Item> BRONZE_RAW_MATERIALS = createCommon("raw_materials/bronze");

    interface Menderman {
        private static TagKey<Item> createMM(String path) {
            return TagKey.create(Registries.ITEM, EquipMisc.id("menderman/" + path));
        }

        TagKey<Item> REPAIRS_BRONZE_SHIELDS = createMM("repairs_bronze_shields");
        TagKey<Item> REPAIRS_BRONZE_SHEARS = createMM("repairs_bronze_shears");
        TagKey<Item> REPAIRS_FLINT_AND_BRONZES = createMM("repairs_flint_and_bronzes");
    }
}
