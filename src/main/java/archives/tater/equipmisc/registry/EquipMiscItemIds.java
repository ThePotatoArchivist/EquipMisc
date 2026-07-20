package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public interface EquipMiscItemIds {
    private static ResourceKey<Item> create(String path) {
        return ResourceKey.create(Registries.ITEM, EquipMisc.id(path));
    }

    ResourceKey<Item> BRONZE_UPGRADE_SMITHING_TEMPLATE = create("bronze_upgrade_smithing_template");
    ResourceKey<Item> RAW_BRONZE = create("raw_bronze");
    ResourceKey<Item> BRONZE_INGOT = create("bronze_ingot");

    ResourceKey<Item> BRONZE_HELMET = create("bronze_helmet");
    ResourceKey<Item> BRONZE_CHESTPLATE = create("bronze_chestplate");
    ResourceKey<Item> BRONZE_LEGGINGS = create("bronze_leggings");
    ResourceKey<Item> BRONZE_BOOTS = create("bronze_boots");

    ResourceKey<Item> BRONZE_SWORD = create("bronze_sword");
    ResourceKey<Item> BRONZE_SPEAR = create("bronze_spear");
    ResourceKey<Item> BRONZE_SHOVEL = create("bronze_shovel");
    ResourceKey<Item> BRONZE_PICKAXE = create("bronze_pickaxe");
    ResourceKey<Item> BRONZE_AXE = create("bronze_axe");
    ResourceKey<Item> BRONZE_HOE = create("bronze_hoe");

    ResourceKey<Item> BRONZE_SHIELD = create("bronze_shield");
    ResourceKey<Item> BRONZE_SHEARS = create("bronze_shears");
    ResourceKey<Item> FLINT_AND_BRONZE = create("flint_and_bronze");
    ResourceKey<Item> BRONZE_KNIFE = create("bronze_knife");

    ResourceKey<Item> CHAINMAIL_UPGRADE_SMITHING_TEMPLATE = create("chainmail_upgrade_smithing_template");
    ResourceKey<Item> RAVAGER_HELMET = create("ravager_helmet");
}
