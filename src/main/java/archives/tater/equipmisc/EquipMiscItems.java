package archives.tater.equipmisc;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class EquipMiscItems {
    public static <T extends Item> T register(Identifier id, T item) {
        return Registry.register(Registries.ITEM, id, item);
    }

    public static <T extends Item> T register(String path, T item) {
        return register(EquipMisc.id(path), item);
    }

    public static ArmorMaterial REINFORCED_LEATHER = FabricLoader.getInstance().getGameDir().endsWith("datagen") ? ArmorMaterials.LEATHER : new RArmorMaterial(
            "equipmisc_reinforced_leather",
            20,
            RArmorMaterial.armorTypeMap(2, 6, 5, 2),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
            2f,
            0f,
            () -> Ingredient.ofItems(Items.LEATHER, Items.IRON_INGOT)
    );

    public static ArmorItem REINFORCED_LEATHER_HELMET = register("reinforced_leather_helmet",
            new DyeableArmorItem(REINFORCED_LEATHER, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static ArmorItem REINFORCED_LEATHER_CHESTPLATE = register("reinforced_leather_chestplate",
            new DyeableArmorItem(REINFORCED_LEATHER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static ArmorItem REINFORCED_LEATHER_LEGGINGS = register("reinforced_leather_leggings",
            new DyeableArmorItem(REINFORCED_LEATHER, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static ArmorItem REINFORCED_LEATHER_BOOTS = register("reinforced_leather_boots",
            new DyeableArmorItem(REINFORCED_LEATHER, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static Item LEATHER_REINFORCEMENT_SMITHING_TEMPLATE = register("leather_reinforcement_smithing_template", new SmithingTemplateItem(
            Text.translatable(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_APPLIES_TO),
            Text.translatable(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_INGREDIENT),
            Text.translatable(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_TITLE),
            Text.translatable(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_BASE_DESCRIPTION),
            Text.translatable(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_ADD_DESCRIPTION),
            List.of(EquipMiscSmithingTemplates.EMPTY_HELMET_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_CHESTPLATE_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_LEGGINGS_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_BOOTS_TEXTURE),
            List.of(EquipMiscSmithingTemplates.EMPTY_CHAIN_HELMET_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_CHAIN_CHESTPLATE_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_CHAIN_LEGGINGS_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_CHAIN_BOOTS_TEXTURE)
    ));

    public static Item RAW_BRONZE = register("raw_bronze", new Item(new FabricItemSettings()));
    public static Item BRONZE_INGOT = register("bronze_ingot", new Item(new FabricItemSettings()));
    public static Item BRONZE_UPGRADE_SMITHING_TEMPLATE = register("bronze_upgrade_smithing_template", new SmithingTemplateItem(
            Text.translatable(EquipMiscSmithingTemplates.BRONZE_UPGRADE_APPLIES_TO),
            Text.translatable(EquipMiscSmithingTemplates.BRONZE_UPGRADE_INGREDIENT),
            Text.translatable(EquipMiscSmithingTemplates.BRONZE_UPGRADE_TITLE),
            Text.translatable(EquipMiscSmithingTemplates.BRONZE_UPGRADE_BASE_DESCRIPTION),
            Text.translatable(EquipMiscSmithingTemplates.BRONZE_UPGRADE_ADD_DESCRIPTION),
            List.of(EquipMiscSmithingTemplates.EMPTY_HELMET_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_CHESTPLATE_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_LEGGINGS_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_BOOTS_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_PICKAXE_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_AXE_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_SHOVEL_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_HOE_TEXTURE,
                    EquipMiscSmithingTemplates.EMPTY_SWORD_TEXTURE),
            List.of(EquipMiscSmithingTemplates.EMPTY_INGOT_TEXTURE)
    ));

    public static ArmorMaterial BRONZE_ARMOR = new RArmorMaterial(
            "equipmisc_bronze",
            24,
            RArmorMaterial.armorTypeMap(2, 6, 5, 2),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, // TODO sound?
            0f,
            0.05f,
            () -> Ingredient.ofItems(BRONZE_INGOT)
    );
    public static ToolMaterial BRONZE_TOOL = new RToolMaterial(
            MiningLevels.IRON,
            700,
            6.0F,
            2.0F,
            14,
            () -> Ingredient.ofItems(BRONZE_INGOT)
    );

    public static Item BRONZE_PICKAXE = register("bronze_pickaxe",
            new PickaxeItem(BRONZE_TOOL, 1, -2.8f, new FabricItemSettings()));
    public static Item BRONZE_AXE = register("bronze_axe",
            new AxeItem(BRONZE_TOOL, 1, -2.8f, new FabricItemSettings()));
    public static Item BRONZE_SHOVEL = register("bronze_shovel",
            new ShovelItem(BRONZE_TOOL, 1, -2.8f, new FabricItemSettings()));
    public static Item BRONZE_HOE = register("bronze_hoe",
            new HoeItem(BRONZE_TOOL, 1, -2.8f, new FabricItemSettings()));
    public static Item BRONZE_SWORD = register("bronze_sword",
            new SwordItem(BRONZE_TOOL, 1, -2.8f, new FabricItemSettings()));

    public static ArmorItem BRONZE_HELMET = register("bronze_helmet",
            new ArmorItem(BRONZE_ARMOR, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static ArmorItem BRONZE_CHESTPLATE = register("bronze_chestplate",
            new ArmorItem(BRONZE_ARMOR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static ArmorItem BRONZE_LEGGINGS = register("bronze_leggings",
            new ArmorItem(BRONZE_ARMOR, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static ArmorItem BRONZE_BOOTS = register("bronze_boots",
            new ArmorItem(BRONZE_ARMOR, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.CHAINMAIL_BOOTS, REINFORCED_LEATHER_HELMET, REINFORCED_LEATHER_CHESTPLATE, REINFORCED_LEATHER_LEGGINGS, REINFORCED_LEATHER_BOOTS);
            entries.addAfter(Items.IRON_BOOTS, BRONZE_HELMET, BRONZE_CHESTPLATE, BRONZE_LEGGINGS, BRONZE_BOOTS);
            entries.addAfter(Items.IRON_SWORD, BRONZE_SWORD);
            entries.addAfter(Items.IRON_AXE, BRONZE_AXE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAfter(Items.IRON_HOE, BRONZE_SHOVEL, BRONZE_PICKAXE, BRONZE_AXE, BRONZE_HOE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, LEATHER_REINFORCEMENT_SMITHING_TEMPLATE, BRONZE_UPGRADE_SMITHING_TEMPLATE);
            entries.addAfter(Items.RAW_GOLD, RAW_BRONZE);
            entries.addAfter(Items.GOLD_INGOT, BRONZE_INGOT);
        });
    }
}
