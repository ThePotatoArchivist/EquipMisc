package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMiscItems;
import archives.tater.equipmisc.EquipMiscSmithingTemplates;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class EquipMiscLangGenerator extends FabricLanguageProvider {
    public EquipMiscLangGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(EquipMiscItems.REINFORCED_LEATHER_HELMET, "Reinforced Leather Helmet");
        builder.add(EquipMiscItems.REINFORCED_LEATHER_CHESTPLATE, "Reinforced Leather Chestplate");
        builder.add(EquipMiscItems.REINFORCED_LEATHER_LEGGINGS, "Reinforced Leather Leggings");
        builder.add(EquipMiscItems.REINFORCED_LEATHER_BOOTS, "Reinforced Leather Boots");

        builder.add(EquipMiscItems.RAW_BRONZE, "Raw Bronze");
        builder.add(EquipMiscItems.BRONZE_INGOT, "Bronze Ingot");
        builder.add(EquipMiscItems.BRONZE_PICKAXE, "Bronze Pickaxe");
        builder.add(EquipMiscItems.BRONZE_AXE, "Bronze Axe");
        builder.add(EquipMiscItems.BRONZE_SHOVEL, "Bronze Shovel");
        builder.add(EquipMiscItems.BRONZE_HOE, "Bronze Hoe");
        builder.add(EquipMiscItems.BRONZE_SWORD, "Bronze Sword");
        builder.add(EquipMiscItems.BRONZE_HELMET, "Bronze Helmet");
        builder.add(EquipMiscItems.BRONZE_CHESTPLATE, "Bronze Chestplate");
        builder.add(EquipMiscItems.BRONZE_LEGGINGS, "Bronze Leggings");
        builder.add(EquipMiscItems.BRONZE_BOOTS, "Bronze Boots");

        builder.add(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_APPLIES_TO, "Leather Armor");
        builder.add(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_INGREDIENT, "Matching Chainmail Armor");
        builder.add(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_TITLE, "Leather Reinforcement");
        builder.add(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_BASE_DESCRIPTION, "Add Leather Armor");
        builder.add(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_ADD_DESCRIPTION, "Add Matching Chainmail Armor");

        builder.add(EquipMiscSmithingTemplates.BRONZE_UPGRADE_APPLIES_TO, "Iron Equipment");
        builder.add(EquipMiscSmithingTemplates.BRONZE_UPGRADE_INGREDIENT, "Bronze Ingot");
        builder.add(EquipMiscSmithingTemplates.BRONZE_UPGRADE_TITLE, "Bronze Upgrade");
        builder.add(EquipMiscSmithingTemplates.BRONZE_UPGRADE_BASE_DESCRIPTION, "Add Iron Equipment");
        builder.add(EquipMiscSmithingTemplates.BRONZE_UPGRADE_ADD_DESCRIPTION, "Add Bronze Ingot");

    }
}
