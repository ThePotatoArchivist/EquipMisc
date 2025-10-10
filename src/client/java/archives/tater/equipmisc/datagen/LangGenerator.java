package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.item.EquipMiscSmithingTemplates;
import archives.tater.equipmisc.registry.EquipMiscAttributes;
import archives.tater.equipmisc.registry.EquipMiscItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import net.minecraft.registry.RegistryWrapper.WrapperLookup;

import java.util.concurrent.CompletableFuture;

public class LangGenerator extends FabricLanguageProvider {
    public LangGenerator(FabricDataOutput dataOutput, CompletableFuture<WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(EquipMiscAttributes.MAX_AIR, "Max Air");

        translationBuilder.add(EquipMiscItems.BRONZE_TOOL_MATERIALS, "Bronze Tool Materials");
        translationBuilder.add(EquipMiscItems.REPAIRS_BRONZE_ARMOR, "Repairs Bronze Armor");
        translationBuilder.add(EquipMiscItems.BRONZE_EQUIPMENT, "Bronze Equipment");
        translationBuilder.add(EquipMiscItems.ENCHANTED_INVISIBLE_EQUIPMENT, "Works with Invisibility when Enchanted");

        translationBuilder.add(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, "Bronze Upgrade");
        translationBuilder.add(EquipMiscSmithingTemplates.BRONZE_UPRADE_APPLIES_TO_KEY, "Iron Equipment");
        translationBuilder.add(EquipMiscSmithingTemplates.BRONZE_UPRADE_INGREDIENTS_KEY, "Bronze Ingot");
        translationBuilder.add(EquipMiscSmithingTemplates.BRONZE_UPGRADE_BASE_SLOT_DESCRIPTION_KEY, "Add iron armor, weapon, or tool");
        translationBuilder.add(EquipMiscSmithingTemplates.BRONZE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_KEY, "Add Bronze Ingot");

        translationBuilder.add(EquipMiscItems.RAW_BRONZE, "Raw Bronze");
        translationBuilder.add(EquipMiscItems.BRONZE_INGOT, "Bronze Ingot");
        translationBuilder.add(EquipMiscItems.BRONZE_HELMET, "Bronze Helmet");
        translationBuilder.add(EquipMiscItems.BRONZE_CHESTPLATE, "Bronze Chestplate");
        translationBuilder.add(EquipMiscItems.BRONZE_LEGGINGS, "Bronze Leggings");
        translationBuilder.add(EquipMiscItems.BRONZE_BOOTS, "Bronze Boots");
        translationBuilder.add(EquipMiscItems.BRONZE_SWORD, "Bronze Sword");
        translationBuilder.add(EquipMiscItems.BRONZE_SHOVEL, "Bronze Shovel");
        translationBuilder.add(EquipMiscItems.BRONZE_PICKAXE, "Bronze Pickaxe");
        translationBuilder.add(EquipMiscItems.BRONZE_AXE, "Bronze Axe");
        translationBuilder.add(EquipMiscItems.BRONZE_HOE, "Bronze Hoe");
        translationBuilder.add(EquipMiscItems.BRONZE_SHIELD, "Bronze Shield");
        translationBuilder.add(EquipMiscItems.BRONZE_SHEARS, "Bronze Shears");
        translationBuilder.add(EquipMiscItems.FLINT_AND_BRONZE, "Flint and Bronze");
        translationBuilder.add(EquipMiscItems.BRONZE_KNIFE, "Bronze Knife");

        translationBuilder.add(AdvancementGenerator.BRONZE_TOOLS_TITLE, "Incremental Upgrades");
        translationBuilder.add(AdvancementGenerator.BRONZE_TOOLS_DESCRIPTION, "Improve the durability of your iron tools by upgrading them with bronze");
    }
}
