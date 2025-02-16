package archives.tater.equipmisc;

import net.minecraft.util.Identifier;

import static net.minecraft.util.Util.createTranslationKey;

public class EquipMiscSmithingTemplates {
    public static final Identifier EMPTY_INGOT_TEXTURE = new Identifier("item/empty_slot_ingot");

    public static final Identifier EMPTY_HOE_TEXTURE = new Identifier("item/empty_slot_hoe");
    public static final Identifier EMPTY_AXE_TEXTURE = new Identifier("item/empty_slot_axe");
    public static final Identifier EMPTY_SWORD_TEXTURE = new Identifier("item/empty_slot_sword");
    public static final Identifier EMPTY_SHOVEL_TEXTURE = new Identifier("item/empty_slot_shovel");
    public static final Identifier EMPTY_PICKAXE_TEXTURE = new Identifier("item/empty_slot_pickaxe");

    public static final Identifier EMPTY_HELMET_TEXTURE = new Identifier("item/empty_armor_slot_helmet");
    public static final Identifier EMPTY_CHESTPLATE_TEXTURE = new Identifier("item/empty_armor_slot_chestplate");
    public static final Identifier EMPTY_LEGGINGS_TEXTURE = new Identifier("item/empty_armor_slot_leggings");
    public static final Identifier EMPTY_BOOTS_TEXTURE = new Identifier("item/empty_armor_slot_boots");

    public static final Identifier EMPTY_CHAIN_HELMET_TEXTURE = EquipMisc.id("item/empty_chain_armor_slot_helmet");
    public static final Identifier EMPTY_CHAIN_CHESTPLATE_TEXTURE = EquipMisc.id("item/empty_chain_armor_slot_chestplate");
    public static final Identifier EMPTY_CHAIN_LEGGINGS_TEXTURE = EquipMisc.id("item/empty_chain_armor_slot_leggings");
    public static final Identifier EMPTY_CHAIN_BOOTS_TEXTURE = EquipMisc.id("item/empty_chain_armor_slot_boots");

    public static final String LEATHER_REINFORCEMENT_APPLIES_TO = createTranslationKey("item",EquipMisc.id("smithing_template.leather_reinforcement.applies_to"));
    public static final String LEATHER_REINFORCEMENT_INGREDIENT = createTranslationKey("item", EquipMisc.id("smithing_template.leather_reinforcement.ingredients"));
    public static final String LEATHER_REINFORCEMENT_TITLE = createTranslationKey("upgrade", EquipMisc.id("leather_reinforcement"));
    public static final String LEATHER_REINFORCEMENT_BASE_DESCRIPTION = createTranslationKey("item", EquipMisc.id("smithing_template.leather_reinforcement.base_slot_description"));
    public static final String LEATHER_REINFORCEMENT_ADD_DESCRIPTION = createTranslationKey("item", EquipMisc.id("smithing_template.leather_reinforcement.additions_slot_description"));

    public static final String BRONZE_UPGRADE_APPLIES_TO = createTranslationKey("item",EquipMisc.id("smithing_template.bronze_upgrade.applies_to"));
    public static final String BRONZE_UPGRADE_INGREDIENT = createTranslationKey("item", EquipMisc.id("smithing_template.bronze_upgrade.ingredients"));
    public static final String BRONZE_UPGRADE_TITLE = createTranslationKey("upgrade", EquipMisc.id("bronze_upgrade"));
    public static final String BRONZE_UPGRADE_BASE_DESCRIPTION = createTranslationKey("item", EquipMisc.id("smithing_template.bronze_upgrade.base_slot_description"));
    public static final String BRONZE_UPGRADE_ADD_DESCRIPTION = createTranslationKey("item", EquipMisc.id("smithing_template.bronze_upgrade.additions_slot_description"));
}
