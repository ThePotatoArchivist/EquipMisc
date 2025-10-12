package archives.tater.equipmisc.item;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.util.Translation;

import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.minecraft.util.Util.createTranslationKey;

public class EquipMiscSmithingTemplates {

    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;

    public static final Translation.Unit BRONZE_UPRADE_APPLIES_TO = Translation.unit(createTranslationKey("item", EquipMisc.id("smithing_template.bronze_upgrade.applies_to")), DESCRIPTION_FORMATTING);
    public static final Translation.Unit BRONZE_UPRADE_INGREDIENTS = Translation.unit(createTranslationKey("item", EquipMisc.id("smithing_template.bronze_upgrade.ingredients")), DESCRIPTION_FORMATTING);
    public static final Translation.Unit BRONZE_UPGRADE_BASE_SLOT_DESCRIPTION = Translation.unit(createTranslationKey("item", EquipMisc.id("smithing_template.bronze_upgrade.base_slot_description")));
    public static final Translation.Unit BRONZE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Translation.unit(createTranslationKey("item", EquipMisc.id("smithing_template.bronze_upgrade.additions_slot_description")));

    public static final Translation.Unit CHAINMAIL_UPRADE_APPLIES_TO = Translation.unit(createTranslationKey("item", EquipMisc.id("smithing_template.chainmail_upgrade.applies_to")), DESCRIPTION_FORMATTING);
    public static final Translation.Unit CHAINMAIL_UPRADE_INGREDIENTS = Translation.unit(createTranslationKey("item", EquipMisc.id("smithing_template.chainmail_upgrade.ingredients")), DESCRIPTION_FORMATTING);
    public static final Translation.Unit CHAINMAIL_UPGRADE_BASE_SLOT_DESCRIPTION = Translation.unit(createTranslationKey("item", EquipMisc.id("smithing_template.chainmail_upgrade.base_slot_description")));
    public static final Translation.Unit CHAINMAIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Translation.unit(createTranslationKey("item", EquipMisc.id("smithing_template.chainmail_upgrade.additions_slot_description")));

    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.ofVanilla("container/slot/helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.ofVanilla("container/slot/chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.ofVanilla("container/slot/leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.ofVanilla("container/slot/boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.ofVanilla("container/slot/hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.ofVanilla("container/slot/axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.ofVanilla("container/slot/sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.ofVanilla("container/slot/shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.ofVanilla("container/slot/pickaxe");

    private static final Identifier EMPTY_SLOT_SHIELD_TEXTURE = EquipMisc.id("container/slot/shield");
    private static final Identifier EMPTY_SLOT_SHEARS_TEXTURE = EquipMisc.id("container/slot/shears");
    private static final Identifier EMPTY_SLOT_FLINT_AND_STEEL_TEXTURE = EquipMisc.id("container/slot/flint_and_steel");

    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.ofVanilla("container/slot/ingot");

    private EquipMiscSmithingTemplates() {}

    private static @NotNull List<Identifier> getArmorSlots() {
        return List.of(
                EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
                EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
                EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
                EMPTY_ARMOR_SLOT_BOOTS_TEXTURE
        );
    }

    public static SmithingTemplateItem createBronzeUpgrade(Item.Settings settings) {
        return new SmithingTemplateItem(
                BRONZE_UPRADE_APPLIES_TO.text,
                BRONZE_UPRADE_INGREDIENTS.text,
                BRONZE_UPGRADE_BASE_SLOT_DESCRIPTION.text,
                BRONZE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.text,
                List.of(
                        EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
                        EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
                        EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
                        EMPTY_ARMOR_SLOT_BOOTS_TEXTURE,
                        EMPTY_SLOT_HOE_TEXTURE,
                        EMPTY_SLOT_AXE_TEXTURE,
                        EMPTY_SLOT_SWORD_TEXTURE,
                        EMPTY_SLOT_SHOVEL_TEXTURE,
                        EMPTY_SLOT_PICKAXE_TEXTURE,
                        EMPTY_SLOT_SHIELD_TEXTURE,
                        EMPTY_SLOT_SHEARS_TEXTURE,
                        EMPTY_SLOT_FLINT_AND_STEEL_TEXTURE
                ),
                List.of(EMPTY_SLOT_INGOT_TEXTURE),
                settings
        );
    }

    public static SmithingTemplateItem createChainmailUpgrade(Item.Settings settings) {
        return new SmithingTemplateItem(
                CHAINMAIL_UPRADE_APPLIES_TO.text,
                CHAINMAIL_UPRADE_INGREDIENTS.text,
                CHAINMAIL_UPGRADE_BASE_SLOT_DESCRIPTION.text,
                CHAINMAIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.text,
                getArmorSlots(),
                getArmorSlots(),
                settings
        );
    }
}
