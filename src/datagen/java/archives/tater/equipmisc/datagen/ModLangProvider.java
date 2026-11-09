package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.component.ChainmailUpgrade;
import archives.tater.equipmisc.item.EquipMiscSmithingTemplates;
import archives.tater.equipmisc.registry.*;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.util.Util.makeDescriptionId;

public class ModLangProvider extends FabricLanguageProvider {
    public ModLangProvider(FabricPackOutput dataOutput, CompletableFuture<Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.addAttribute(EquipMiscAttributes.MAX_AIR, "Max Air");

        translationBuilder.add(EquipMiscItemTags.BRONZE_TOOL_MATERIALS, "Bronze Tool Materials");
        translationBuilder.add(EquipMiscItemTags.REPAIRS_BRONZE_ARMOR, "Repairs Bronze Armor");
        translationBuilder.add(EquipMiscItemTags.BRONZE_EQUIPMENT, "Bronze Equipment");
        translationBuilder.add(EquipMiscItemTags.ENCHANTED_INVISIBLE_EQUIPMENT, "Works with Invisibility when Enchanted");

        translationBuilder.add(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, "Bronze Upgrade");
        translationBuilder.add(getKey(EquipMiscSmithingTemplates.BRONZE_UPRADE_APPLIES_TO), "Iron Equipment");
        translationBuilder.add(getKey(EquipMiscSmithingTemplates.BRONZE_UPRADE_INGREDIENTS), "Bronze Ingot");
        translationBuilder.add(getKey(EquipMiscSmithingTemplates.BRONZE_UPGRADE_BASE_SLOT_DESCRIPTION), "Add iron armor, weapon, or tool");
        translationBuilder.add(getKey(EquipMiscSmithingTemplates.BRONZE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION), "Add Bronze Ingot");

        translationBuilder.add(EquipMiscItems.CHAINMAIL_UPGRADE_SMITHING_TEMPLATE, "Chainmail Upgrade");
        translationBuilder.add(getKey(EquipMiscSmithingTemplates.CHAINMAIL_UPRADE_APPLIES_TO), "Armor");
        translationBuilder.add(getKey(EquipMiscSmithingTemplates.CHAINMAIL_UPRADE_INGREDIENTS), "Chainmail Armor");
        translationBuilder.add(getKey(EquipMiscSmithingTemplates.CHAINMAIL_UPGRADE_BASE_SLOT_DESCRIPTION), "Add a piece of armor");
        translationBuilder.add(getKey(EquipMiscSmithingTemplates.CHAINMAIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION), "Add matching chainmail armor");

        translationBuilder.add(EquipMiscItems.RAW_BRONZE, "Raw Bronze");
        translationBuilder.add(EquipMiscItems.BRONZE_INGOT, "Bronze Ingot");
        translationBuilder.add(EquipMiscBlocks.BRONZE_BLOCK, "Block of Bronze");
        translationBuilder.add(EquipMiscItems.BRONZE_HELMET, "Bronze Helmet");
        translationBuilder.add(EquipMiscItems.BRONZE_CHESTPLATE, "Bronze Chestplate");
        translationBuilder.add(EquipMiscItems.BRONZE_LEGGINGS, "Bronze Leggings");
        translationBuilder.add(EquipMiscItems.BRONZE_BOOTS, "Bronze Boots");
        translationBuilder.add(EquipMiscItems.BRONZE_SWORD, "Bronze Sword");
        translationBuilder.add(EquipMiscItems.BRONZE_SHOVEL, "Bronze Shovel");
        translationBuilder.add(EquipMiscItems.BRONZE_PICKAXE, "Bronze Pickaxe");
        translationBuilder.add(EquipMiscItems.BRONZE_AXE, "Bronze Axe");
        translationBuilder.add(EquipMiscItems.BRONZE_HOE, "Bronze Hoe");
        translationBuilder.add(EquipMiscItems.BRONZE_SPEAR, "Bronze Spear");
        translationBuilder.add(EquipMiscItems.BRONZE_SHIELD, "Bronze Shield");
        translationBuilder.add(EquipMiscItems.BRONZE_SHEARS, "Bronze Shears");
        translationBuilder.add(EquipMiscItems.FLINT_AND_BRONZE, "Flint and Bronze");
        translationBuilder.add(EquipMiscItems.BRONZE_KNIFE, "Bronze Knife");

        translationBuilder.add(getKey(EquipMiscCreativeTabs.EQUIPMISC_TAB_TITLE), "EquipMisc");

        translationBuilder.add(getKey(ChainmailUpgrade.TOOLTIP), "Chainmail Upgrade");

        translationBuilder.add(ModAdvancementProvider.BRONZE_TOOLS_TITLE, "Incremental Upgrades");
        translationBuilder.add(ModAdvancementProvider.BRONZE_TOOLS_DESCRIPTION, "Improve the durability of your iron tools by upgrading them with bronze");

        translationBuilder.add(makeDescriptionId("trim_material", EquipMiscTrimMaterials.BRONZE.identifier()), "Bronze Material");

        translationBuilder.add(EquipMiscSounds.TURTLE_BRUSH, "Scute is brushed off");
    }

    private static String getKey(Component translatable) {
        return ((TranslatableContents) translatable.getContents()).getKey();
    }
}
