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
        builder.add(EquipMiscItems.SMITHING_TEMPLATE_LEATHER_REINFORCEMENT, "Smithing Template");

        builder.add(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_APPLIES_TO, "Leather Armor");
        builder.add(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_INGREDIENT, "Matching Chainmail Armor");
        builder.add(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_TITLE, "Leather Reinforcement");
        builder.add(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_BASE_DESCRIPTION, "Add Leather Armor");
        builder.add(EquipMiscSmithingTemplates.LEATHER_REINFORCEMENT_ADD_DESCRIPTION, "Add Matching Chainmail Armor");
    }
}
