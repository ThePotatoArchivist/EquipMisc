package archives.tater.equipmisc.item;

import archives.tater.equipmisc.EquipMisc;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

import static net.minecraft.util.Util.makeDescriptionId;

public class EquipMiscSmithingTemplates {

    private static final ChatFormatting DESCRIPTION_FORMATTING = ChatFormatting.BLUE;

    public static final Component BRONZE_UPRADE_APPLIES_TO = Component.translatable(makeDescriptionId("item", EquipMisc.id("smithing_template.bronze_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING);
    public static final Component BRONZE_UPRADE_INGREDIENTS = Component.translatable(makeDescriptionId("item", EquipMisc.id("smithing_template.bronze_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMATTING);
    public static final Component BRONZE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(makeDescriptionId("item", EquipMisc.id("smithing_template.bronze_upgrade.base_slot_description")));
    public static final Component BRONZE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(makeDescriptionId("item", EquipMisc.id("smithing_template.bronze_upgrade.additions_slot_description")));

    public static final Component CHAINMAIL_UPRADE_APPLIES_TO = Component.translatable(makeDescriptionId("item", EquipMisc.id("smithing_template.chainmail_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING);
    public static final Component CHAINMAIL_UPRADE_INGREDIENTS = Component.translatable(makeDescriptionId("item", EquipMisc.id("smithing_template.chainmail_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMATTING);
    public static final Component CHAINMAIL_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(makeDescriptionId("item", EquipMisc.id("smithing_template.chainmail_upgrade.base_slot_description")));
    public static final Component CHAINMAIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(makeDescriptionId("item", EquipMisc.id("smithing_template.chainmail_upgrade.additions_slot_description")));

    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.withDefaultNamespace("container/slot/helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.withDefaultNamespace("container/slot/chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.withDefaultNamespace("container/slot/leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.withDefaultNamespace("container/slot/boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.withDefaultNamespace("container/slot/hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.withDefaultNamespace("container/slot/axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.withDefaultNamespace("container/slot/sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.withDefaultNamespace("container/slot/shovel");
    private static final Identifier EMPTY_SLOT_SPEAR_TEXTURE = Identifier.withDefaultNamespace("container/slot/spear");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.withDefaultNamespace("container/slot/pickaxe");

    private static final Identifier EMPTY_SLOT_SHIELD_TEXTURE = EquipMisc.id("container/slot/shield");
    private static final Identifier EMPTY_SLOT_SHEARS_TEXTURE = EquipMisc.id("container/slot/shears");
    private static final Identifier EMPTY_SLOT_FLINT_AND_STEEL_TEXTURE = EquipMisc.id("container/slot/flint_and_steel");

    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.withDefaultNamespace("container/slot/ingot");

    private EquipMiscSmithingTemplates() {}

    private static List<Identifier> getArmorSlots() {
        return List.of(
                EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
                EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
                EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
                EMPTY_ARMOR_SLOT_BOOTS_TEXTURE
        );
    }

    public static SmithingTemplateItem createBronzeUpgrade(Item.Properties settings) {
        return new SmithingTemplateItem(
                BRONZE_UPRADE_APPLIES_TO,
                BRONZE_UPRADE_INGREDIENTS,
                BRONZE_UPGRADE_BASE_SLOT_DESCRIPTION,
                BRONZE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                List.of(
                        EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
                        EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
                        EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
                        EMPTY_ARMOR_SLOT_BOOTS_TEXTURE,
                        EMPTY_SLOT_HOE_TEXTURE,
                        EMPTY_SLOT_AXE_TEXTURE,
                        EMPTY_SLOT_SWORD_TEXTURE,
                        EMPTY_SLOT_SHOVEL_TEXTURE,
                        EMPTY_SLOT_SPEAR_TEXTURE,
                        EMPTY_SLOT_PICKAXE_TEXTURE,
                        EMPTY_SLOT_SHIELD_TEXTURE,
                        EMPTY_SLOT_SHEARS_TEXTURE,
                        EMPTY_SLOT_FLINT_AND_STEEL_TEXTURE
                ),
                List.of(EMPTY_SLOT_INGOT_TEXTURE),
                settings
        );
    }

    public static SmithingTemplateItem createChainmailUpgrade(Item.Properties settings) {
        return new SmithingTemplateItem(
                CHAINMAIL_UPRADE_APPLIES_TO,
                CHAINMAIL_UPRADE_INGREDIENTS,
                CHAINMAIL_UPGRADE_BASE_SLOT_DESCRIPTION,
                CHAINMAIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                getArmorSlots(),
                getArmorSlots(),
                settings
        );
    }
}
