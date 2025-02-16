package archives.tater.equipmisc;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
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
            24,
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

    public static Item SMITHING_TEMPLATE_LEATHER_REINFORCEMENT = register("smithing_template_leather_reinforcement", new SmithingTemplateItem(
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

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.CHAINMAIL_HELMET, REINFORCED_LEATHER_HELMET, REINFORCED_LEATHER_CHESTPLATE, REINFORCED_LEATHER_LEGGINGS, REINFORCED_LEATHER_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, SMITHING_TEMPLATE_LEATHER_REINFORCEMENT);
        });
    }
}
