package archives.tater.equipmisc.datagen;

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
        translationBuilder.add(EquipMiscItems.BRONZE_TOOL_MATERIALS, "Bronze Tool Materials");
        translationBuilder.add(EquipMiscItems.REPAIRS_BRONZE_ARMOR, "Repairs Bronze Armor");

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
    }
}
