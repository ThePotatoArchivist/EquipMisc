package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.item.EquipMiscSmithingTemplates;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.FlintAndSteelDispenseItemBehavior;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface EquipMiscItems {
    private static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> item, Item.Properties settings) {
        return Registry.register(BuiltInRegistries.ITEM, key, item.apply(settings.setId(key)));
    }

    private static Item register(ResourceKey<Item> key, Item.Properties settings) {
        return register(key, Item::new, settings);
    }

    private static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> item) {
        return register(key, item, new Item.Properties());
    }

    private static Item register(BlockItemId id, Block block) {
        return register(id.item(), properties -> new BlockItem(block, properties), new Item.Properties().useBlockDescriptionPrefix());
    }

    Item BRONZE_UPGRADE_SMITHING_TEMPLATE = register(EquipMiscItemIds.BRONZE_UPGRADE_SMITHING_TEMPLATE, EquipMiscSmithingTemplates::createBronzeUpgrade, new Item.Properties()
            .rarity(Rarity.UNCOMMON)
    );
    Item RAW_BRONZE = register(EquipMiscItemIds.RAW_BRONZE, new Item.Properties());
    Item BRONZE_INGOT = register(EquipMiscItemIds.BRONZE_INGOT, new Item.Properties()
            .delayedHolderComponent(DataComponents.PROVIDES_TRIM_MATERIAL, EquipMiscTrimMaterials.BRONZE)
    );
    Item BRONZE_BLOCK = register(EquipMiscBlockItemIds.BRONZE_BLOCK, EquipMiscBlocks.BRONZE_BLOCK);

    Item BRONZE_HELMET = register(EquipMiscItemIds.BRONZE_HELMET, new Item.Properties().humanoidArmor(EquipMiscMaterials.BRONZE_ARMOR, ArmorType.HELMET));
    Item BRONZE_CHESTPLATE = register(EquipMiscItemIds.BRONZE_CHESTPLATE, new Item.Properties().humanoidArmor(EquipMiscMaterials.BRONZE_ARMOR, ArmorType.CHESTPLATE));
    Item BRONZE_LEGGINGS = register(EquipMiscItemIds.BRONZE_LEGGINGS, new Item.Properties().humanoidArmor(EquipMiscMaterials.BRONZE_ARMOR, ArmorType.LEGGINGS));
    Item BRONZE_BOOTS = register(EquipMiscItemIds.BRONZE_BOOTS, new Item.Properties().humanoidArmor(EquipMiscMaterials.BRONZE_ARMOR, ArmorType.BOOTS));

    Item BRONZE_SWORD = register(EquipMiscItemIds.BRONZE_SWORD, new Item.Properties().sword(EquipMiscMaterials.BRONZE_TOOL, 3f, -2.4f));
    Item BRONZE_SPEAR = register(EquipMiscItemIds.BRONZE_SPEAR, new Item.Properties().spear(EquipMiscMaterials.BRONZE_TOOL, 0.95F, 0.95F, 0.6F, 2.5F, 8.0F, 6.75F, 5.1F, 11.25F, 4.6F));
    Item BRONZE_SHOVEL = register(EquipMiscItemIds.BRONZE_SHOVEL, new Item.Properties().shovel(EquipMiscMaterials.BRONZE_TOOL, 1.5f, -3.0f));
    Item BRONZE_PICKAXE = register(EquipMiscItemIds.BRONZE_PICKAXE, new Item.Properties().pickaxe(EquipMiscMaterials.BRONZE_TOOL, 1f, -2.8f));
    Item BRONZE_AXE = register(EquipMiscItemIds.BRONZE_AXE, new Item.Properties().axe(EquipMiscMaterials.BRONZE_TOOL, 6f, -3.1f));
    Item BRONZE_HOE = register(EquipMiscItemIds.BRONZE_HOE, new Item.Properties().hoe(EquipMiscMaterials.BRONZE_TOOL, -2f, -1f));

    Item BRONZE_SHIELD = register(EquipMiscItemIds.BRONZE_SHIELD, ShieldItem::new, new Properties()
            .durability(1110)
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
    Item BRONZE_SHEARS = register(EquipMiscItemIds.BRONZE_SHEARS, ShearsItem::new, new Item.Properties()
            .durability(831)
            .component(DataComponents.TOOL, ShearsItem.createToolProperties())
    );
    Item FLINT_AND_BRONZE = register(EquipMiscItemIds.FLINT_AND_BRONZE, FlintAndSteelItem::new, new Item.Properties()
            .durability(213)
    );
    Item BRONZE_KNIFE = EquipMisc.FARMERS_DELIGHT_INSTALLED
            ? register(EquipMiscItemIds.BRONZE_KNIFE, KnifeItem::new, ModItems.knifeItem(EquipMiscMaterials.BRONZE_TOOL))
            : register(EquipMiscItemIds.BRONZE_KNIFE, new Item.Properties()
                    .stacksTo(1)
                    .durability(EquipMiscMaterials.BRONZE_TOOL.durability()));

    Item CHAINMAIL_UPGRADE_SMITHING_TEMPLATE = register(EquipMiscItemIds.CHAINMAIL_UPGRADE_SMITHING_TEMPLATE, EquipMiscSmithingTemplates::createChainmailUpgrade, new Item.Properties()
            .rarity(Rarity.UNCOMMON)
    );

//    Item RAVAGER_HELMET = register(EquipMiscItemIds.RAVAGER_HELMET, new Item.Properties()
//            .humanoidArmor(ArmorMaterials.DIAMOND, ArmorType.HELMET)
//            .component(EquipMiscComponents.BREAK_IN_AREA, new BreakInArea(0.2, EquipMiscBlockTags.RAVAGER_HELMET_CAN_DESTROY, EquipmentSlotGroup.HEAD)));

    static void init() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(Items.TURTLE_HELMET, builder -> {
                builder.set(DataComponents.ATTRIBUTE_MODIFIERS, builder.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY)
                        .withModifierAdded(EquipMiscAttributes.MAX_AIR, new AttributeModifier(EquipMisc.id("turtle_air"), 30.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
                );
            });
        });

        DispenserBlock.registerBehavior(BRONZE_SHEARS, new ShearsDispenseItemBehavior());
        DispenserBlock.registerBehavior(FLINT_AND_BRONZE, new FlintAndSteelDispenseItemBehavior());
    }
}
