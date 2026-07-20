package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.component.BreakInArea;
import archives.tater.equipmisc.item.EquipMiscSmithingTemplates;
import archives.tater.equipmisc.util.Translation;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;

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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Item.Properties;
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
import java.util.stream.Stream;

import static net.minecraft.util.Util.makeDescriptionId;

public class EquipMiscItems {
    private static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> item, Item.Properties settings) {
        return Registry.register(BuiltInRegistries.ITEM, key, item.apply(settings.setId(key)));
    }

    private static Item register(ResourceKey<Item> key, Item.Properties settings) {
        return register(key, Item::new, settings);
    }

    private static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> item) {
        return register(key, item, new Item.Properties());
    }

    public static final ArmorMaterial BRONZE_ARMOR = new ArmorMaterial(
            25,
            ArmorMaterials.IRON.defense(),
            15,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            EquipMiscItemTags.REPAIRS_BRONZE_ARMOR,
            ResourceKey.create(EquipmentAssets.ROOT_ID, EquipMisc.id("bronze"))
    );

    private static final int BRONZE_TOOL_DURABILITY = 831;

    public static final ToolMaterial BRONZE_TOOL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            BRONZE_TOOL_DURABILITY,
            6.0F,
            2.0F,
            15,
            EquipMiscItemTags.BRONZE_TOOL_MATERIALS
    );

    public static final Item BRONZE_UPGRADE_SMITHING_TEMPLATE = register(EquipMiscItemIds.BRONZE_UPGRADE_SMITHING_TEMPLATE, EquipMiscSmithingTemplates::createBronzeUpgrade);
    public static final Item RAW_BRONZE = register(EquipMiscItemIds.RAW_BRONZE, new Item.Properties());
    public static final Item BRONZE_INGOT = register(EquipMiscItemIds.BRONZE_INGOT, new Item.Properties());

    public static final Item BRONZE_HELMET = register(EquipMiscItemIds.BRONZE_HELMET, new Item.Properties().humanoidArmor(BRONZE_ARMOR, ArmorType.HELMET));
    public static final Item BRONZE_CHESTPLATE = register(EquipMiscItemIds.BRONZE_CHESTPLATE, new Item.Properties().humanoidArmor(BRONZE_ARMOR, ArmorType.CHESTPLATE));
    public static final Item BRONZE_LEGGINGS = register(EquipMiscItemIds.BRONZE_LEGGINGS, new Item.Properties().humanoidArmor(BRONZE_ARMOR, ArmorType.LEGGINGS));
    public static final Item BRONZE_BOOTS = register(EquipMiscItemIds.BRONZE_BOOTS, new Item.Properties().humanoidArmor(BRONZE_ARMOR, ArmorType.BOOTS));

    public static final Item BRONZE_SWORD = register(EquipMiscItemIds.BRONZE_SWORD, new Item.Properties().sword(BRONZE_TOOL, 3f, -2.4f));
    public static final Item BRONZE_SPEAR = register(EquipMiscItemIds.BRONZE_SPEAR, new Item.Properties().spear(BRONZE_TOOL, 0.95F, 0.95F, 0.6F, 2.5F, 8.0F, 6.75F, 5.1F, 11.25F, 4.6F));
    public static final Item BRONZE_SHOVEL = register(EquipMiscItemIds.BRONZE_SHOVEL, settings -> new ShovelItem(BRONZE_TOOL, 1.5f, -3.0f, settings));
    public static final Item BRONZE_PICKAXE = register(EquipMiscItemIds.BRONZE_PICKAXE, new Item.Properties().pickaxe(BRONZE_TOOL, 1f, -2.8f));
    public static final Item BRONZE_AXE = register(EquipMiscItemIds.BRONZE_AXE, settings -> new AxeItem(BRONZE_TOOL, 6f, -3.1f, settings));
    public static final Item BRONZE_HOE = register(EquipMiscItemIds.BRONZE_HOE, settings -> new HoeItem(BRONZE_TOOL, -2f, -1f, settings));

    public static final Item BRONZE_SHIELD = register(EquipMiscItemIds.BRONZE_SHIELD, ShieldItem::new, new Properties()
            .durability(BRONZE_TOOL_DURABILITY)
            .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
            .repairable(ItemTags.WOODEN_TOOL_MATERIALS)
            .equippableUnswappable(EquipmentSlot.OFFHAND)
            .delayedComponent(DataComponents.BLOCKS_ATTACKS, context -> new BlocksAttacks(
                    0.25F,
                    1.0F,
                    List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                    new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                    Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                    Optional.of(SoundEvents.SHIELD_BLOCK),
                    Optional.of(SoundEvents.SHIELD_BREAK)
            ))
            .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
    );
    public static final Item BRONZE_SHEARS = register(EquipMiscItemIds.BRONZE_SHEARS, ShearsItem::new, new Item.Properties()
            .durability(BRONZE_TOOL_DURABILITY)
            .component(DataComponents.TOOL, ShearsItem.createToolProperties())
    );
    public static final Item FLINT_AND_BRONZE = register(EquipMiscItemIds.FLINT_AND_BRONZE, FlintAndSteelItem::new, new Item.Properties()
            .durability(BRONZE_TOOL_DURABILITY)
    );
    public static final Item BRONZE_KNIFE = EquipMisc.FARMERS_DELIGHT_INSTALLED
            ? register(EquipMiscItemIds.BRONZE_KNIFE, KnifeItem::new, ModItems.knifeItem(BRONZE_TOOL))
            : register(EquipMiscItemIds.BRONZE_KNIFE, new Item.Properties());

    public static final Item CHAINMAIL_UPGRADE_SMITHING_TEMPLATE = register(EquipMiscItemIds.CHAINMAIL_UPGRADE_SMITHING_TEMPLATE, EquipMiscSmithingTemplates::createChainmailUpgrade);

    public static final Item RAVAGER_HELMET = register(EquipMiscItemIds.RAVAGER_HELMET, new Item.Properties()
            .humanoidArmor(ArmorMaterials.DIAMOND, ArmorType.HELMET)
            .component(EquipMiscComponents.BREAK_IN_AREA, new BreakInArea(0.2, EquipMiscBlockTags.RAVAGER_HELMET_CAN_DESTROY, EquipmentSlotGroup.HEAD)));

    public static final Identifier EQUIPMISC_TAB_ID = EquipMisc.id(EquipMisc.MOD_ID);
    public static final Translation.Unit EQUIPMISC_TAB_TITLE = Translation.unit(makeDescriptionId("itemGroup", EQUIPMISC_TAB_ID));
    public static final CreativeModeTab EQUIPMISC_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            EQUIPMISC_TAB_ID,
            FabricCreativeModeTab.builder()
                    .icon(BRONZE_PICKAXE::getDefaultInstance)
                    .title(EQUIPMISC_TAB_TITLE.text)
                    .displayItems((_, output) -> {
                        Stream.of(
                                RAW_BRONZE,
                                BRONZE_INGOT,
                                BRONZE_UPGRADE_SMITHING_TEMPLATE,
                                BRONZE_HELMET,
                                BRONZE_CHESTPLATE,
                                BRONZE_LEGGINGS,
                                BRONZE_BOOTS,
                                BRONZE_SWORD,
                                BRONZE_SPEAR,
                                BRONZE_SHOVEL,
                                BRONZE_PICKAXE,
                                BRONZE_AXE,
                                BRONZE_HOE,
                                BRONZE_SHIELD,
                                BRONZE_SHEARS,
                                FLINT_AND_BRONZE
                        ).forEach(output::accept);
                        if (EquipMisc.FARMERS_DELIGHT_INSTALLED)
                            output.accept(BRONZE_KNIFE);
                        Stream.of(
                                CHAINMAIL_UPGRADE_SMITHING_TEMPLATE,
                                RAVAGER_HELMET
                        ).forEach(output::accept);
                    })
                    .build()
    );

    public static void init() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            output.insertAfter(Items.IRON_HOE, BRONZE_SHOVEL, BRONZE_PICKAXE, BRONZE_AXE, BRONZE_HOE);
            output.insertAfter(Items.FLINT_AND_STEEL, FLINT_AND_BRONZE);
            output.insertAfter(Items.SHEARS, BRONZE_SHEARS);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.insertAfter(Items.IRON_SWORD, BRONZE_SWORD);
            output.insertAfter(Items.IRON_AXE, BRONZE_AXE);
            output.insertAfter(Items.IRON_SPEAR, BRONZE_SPEAR);
            output.insertAfter(Items.SHIELD, BRONZE_SHIELD);
            output.insertAfter(Items.IRON_BOOTS, BRONZE_HELMET, BRONZE_CHESTPLATE, BRONZE_LEGGINGS, BRONZE_BOOTS);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.insertAfter(Items.IRON_INGOT, BRONZE_INGOT);
            output.insertAfter(Items.RAW_IRON, RAW_BRONZE);
            output.insertBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, BRONZE_UPGRADE_SMITHING_TEMPLATE, CHAINMAIL_UPGRADE_SMITHING_TEMPLATE);
        });
        if (EquipMisc.FARMERS_DELIGHT_INSTALLED)
            CreativeModeTabEvents.modifyOutputEvent(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(EquipMisc.FARMERS_DELIGHT, EquipMisc.FARMERS_DELIGHT))).register(output -> {
                output.insertAfter(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(EquipMisc.FARMERS_DELIGHT, "copper_knife")), BRONZE_KNIFE);
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
