package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.item.EquipMiscSmithingTemplates;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class EquipMiscItems {
    private static Item register(Identifier id, Function<Item.Properties, Item> item, Item.Properties settings) {
        var key = ResourceKey.create(Registries.ITEM, id);
        return Registry.register(BuiltInRegistries.ITEM, key, item.apply(settings.setId(key)));
    }

    private static Item register(String path, Function<Item.Properties, Item> item, Item.Properties settings) {
        return register(EquipMisc.id(path), item, settings);
    }

    private static Item register(String path, Item.Properties settings) {
        return register(path, Item::new, settings);
    }

    private static Item register(String path, Function<Item.Properties, Item> item) {
        return register(path, item, new Item.Properties());
    }

    private static TagKey<Item> tagOf(String path) {
        return TagKey.create(Registries.ITEM, EquipMisc.id(path));
    }

    public static final TagKey<Item> REPAIRS_BRONZE_ARMOR = tagOf("repairs_bronze_armor");
    public static final TagKey<Item> BRONZE_TOOL_MATERIALS = tagOf("bronze_tool_materials");
    public static final TagKey<Item> BRONZE_EQUIPMENT = tagOf("bronze_equipment");
    public static final TagKey<Item> ENCHANTED_INVISIBLE_EQUIPMENT = tagOf("enchanted_invisible_equipment");

    public static final ArmorMaterial BRONZE_ARMOR = new ArmorMaterial(
            25,
            ArmorMaterials.IRON.defense(),
            15,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            REPAIRS_BRONZE_ARMOR,
            ResourceKey.create(EquipmentAssets.ROOT_ID, EquipMisc.id("bronze"))
    );

    public static final int BRONZE_TOOL_DURABILITY = 831;

    public static final ToolMaterial BRONZE_TOOL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            BRONZE_TOOL_DURABILITY,
            6.0F,
            2.0F,
            15,
            BRONZE_TOOL_MATERIALS
    );

    public static final Item BRONZE_UPGRADE_SMITHING_TEMPLATE = register("bronze_upgrade_smithing_template", EquipMiscSmithingTemplates::createBronzeUpgrade);
    public static final Item RAW_BRONZE = register("raw_bronze", new Item.Properties());
    public static final Item BRONZE_INGOT = register("bronze_ingot", new Item.Properties());

    public static final Item BRONZE_HELMET = register("bronze_helmet", new Item.Properties().humanoidArmor(BRONZE_ARMOR, ArmorType.HELMET));
    public static final Item BRONZE_CHESTPLATE = register("bronze_chestplate", new Item.Properties().humanoidArmor(BRONZE_ARMOR, ArmorType.CHESTPLATE));
    public static final Item BRONZE_LEGGINGS = register("bronze_leggings", new Item.Properties().humanoidArmor(BRONZE_ARMOR, ArmorType.LEGGINGS));
    public static final Item BRONZE_BOOTS = register("bronze_boots", new Item.Properties().humanoidArmor(BRONZE_ARMOR, ArmorType.BOOTS));

    public static final Item BRONZE_SWORD = register("bronze_sword", new Item.Properties().sword(BRONZE_TOOL, 3f, -2.4f));
    public static final Item BRONZE_SHOVEL = register("bronze_shovel", settings -> new ShovelItem(BRONZE_TOOL, 1.5f, -3.0f, settings));
    public static final Item BRONZE_PICKAXE = register("bronze_pickaxe", new Item.Properties().pickaxe(BRONZE_TOOL, 1f, -2.8f));
    public static final Item BRONZE_AXE = register("bronze_axe", settings -> new AxeItem(BRONZE_TOOL, 6f, -3.1f, settings));
    public static final Item BRONZE_HOE = register("bronze_hoe", settings -> new HoeItem(BRONZE_TOOL, -2f, -1f, settings));

    public static final Item BRONZE_SHIELD = register("bronze_shield", ShieldItem::new, new Properties()
            .durability(BRONZE_TOOL_DURABILITY)
            .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
            .repairable(ItemTags.WOODEN_TOOL_MATERIALS)
            .equippableUnswappable(EquipmentSlot.OFFHAND)
            .component(DataComponents.BLOCKS_ATTACKS, new BlocksAttacks(
                    0.25F,
                    1.0F,
                    List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                    new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                    Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                    Optional.of(SoundEvents.SHIELD_BLOCK),
                    Optional.of(SoundEvents.SHIELD_BREAK)
            ))
            .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
    );
    public static final Item BRONZE_SHEARS = register("bronze_shears", ShearsItem::new, new Item.Properties()
            .durability(BRONZE_TOOL_DURABILITY)
            .component(DataComponents.TOOL, ShearsItem.createToolProperties())
    );
    public static final Item FLINT_AND_BRONZE = register("flint_and_bronze", FlintAndSteelItem::new, new Item.Properties()
            .durability(BRONZE_TOOL_DURABILITY)
    );
    public static final Item BRONZE_KNIFE = EquipMisc.FARMERS_DELIGHT_INSTALLED ? register("bronze_knife", KnifeItem::new, ModItems.knifeItem(BRONZE_TOOL)) : null;

    public static final Item CHAINMAIL_UPGRADE_SMITHING_TEMPLATE = register("chainmail_upgrade_smithing_template", EquipMiscSmithingTemplates::createChainmailUpgrade);

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.addAfter(Items.IRON_HOE, BRONZE_SHOVEL, BRONZE_PICKAXE, BRONZE_AXE, BRONZE_HOE);
            entries.addAfter(Items.FLINT_AND_STEEL, FLINT_AND_BRONZE);
            entries.addAfter(Items.SHEARS, BRONZE_SHEARS);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.addAfter(Items.IRON_SWORD, BRONZE_SWORD);
            entries.addAfter(Items.IRON_AXE, BRONZE_AXE);
            entries.addAfter(Items.SHIELD, BRONZE_SHIELD);
            entries.addAfter(Items.IRON_BOOTS, BRONZE_HELMET, BRONZE_CHESTPLATE, BRONZE_LEGGINGS, BRONZE_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.IRON_INGOT, BRONZE_INGOT);
            entries.addAfter(Items.RAW_IRON, RAW_BRONZE);
            entries.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, BRONZE_UPGRADE_SMITHING_TEMPLATE, CHAINMAIL_UPGRADE_SMITHING_TEMPLATE);
        });
        if (EquipMisc.FARMERS_DELIGHT_INSTALLED)
            ItemGroupEvents.modifyEntriesEvent(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(EquipMisc.FARMERS_DELIGHT, EquipMisc.FARMERS_DELIGHT))).register(entries -> {
                entries.addAfter(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(EquipMisc.FARMERS_DELIGHT, "copper_knife")), BRONZE_KNIFE);
            });

        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(Items.TURTLE_HELMET, builder -> {
                var attributes = Items.TURTLE_HELMET.components().get(DataComponents.ATTRIBUTE_MODIFIERS);
                if (attributes == null) return;
                builder.set(DataComponents.ATTRIBUTE_MODIFIERS, attributes
                        .withModifierAdded(EquipMiscAttributes.MAX_AIR, new AttributeModifier(EquipMisc.id("turtle_air"), 30.0, Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
                );
            });
        });

        DispenserBlock.registerBehavior(BRONZE_SHEARS, new ShearsDispenseItemBehavior());
    }
}
