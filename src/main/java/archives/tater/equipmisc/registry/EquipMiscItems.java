package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class EquipMiscItems {
    private static Item register(Identifier id, Function<Item.Settings, Item> item, Item.Settings settings) {
        var key = RegistryKey.of(RegistryKeys.ITEM, id);
        return Registry.register(Registries.ITEM, key, item.apply(settings.registryKey(key)));
    }

    private static Item register(String path, Function<Item.Settings, Item> item, Item.Settings settings) {
        return register(EquipMisc.id(path), item, settings);
    }

    private static Item register(String path, Item.Settings settings) {
        return register(path, Item::new, settings);
    }

    private static Item register(String path, Function<Item.Settings, Item> item) {
        return register(path, item, new Item.Settings());
    }

    private static TagKey<Item> tagOf(String path) {
        return TagKey.of(RegistryKeys.ITEM, EquipMisc.id(path));
    }

    public static final TagKey<Item> REPAIRS_BRONZE_ARMOR = tagOf("repairs_bronze_armor");
    public static final TagKey<Item> BRONZE_TOOL_MATERIALS = tagOf("bronze_tool_materials");

    public static final ArmorMaterial BRONZE_ARMOR = new ArmorMaterial(
            25,
            ArmorMaterials.IRON.defense(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            REPAIRS_BRONZE_ARMOR,
            RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, EquipMisc.id("bronze"))
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

    public static final Item BRONZE_INGOT = register("bronze_ingot", new Item.Settings());

    public static final Item BRONZE_HELMET = register("bronze_helmet", new Item.Settings().armor(BRONZE_ARMOR, EquipmentType.HELMET));
    public static final Item BRONZE_CHESTPLATE = register("bronze_chestplate", new Item.Settings().armor(BRONZE_ARMOR, EquipmentType.CHESTPLATE));
    public static final Item BRONZE_LEGGINGS = register("bronze_leggings", new Item.Settings().armor(BRONZE_ARMOR, EquipmentType.LEGGINGS));
    public static final Item BRONZE_BOOTS = register("bronze_boots", new Item.Settings().armor(BRONZE_ARMOR, EquipmentType.BOOTS));

    public static final Item BRONZE_SWORD = register("bronze_sword", new Item.Settings().sword(BRONZE_TOOL, 3f, -2.4f));
    public static final Item BRONZE_SHOVEL = register("bronze_shovel", settings -> new ShovelItem(BRONZE_TOOL, 1.5f, -3.0f, settings));
    public static final Item BRONZE_PICKAXE = register("bronze_pickaxe", new Item.Settings().pickaxe(BRONZE_TOOL, 1f, -2.8f));
    public static final Item BRONZE_AXE = register("bronze_axe", settings -> new AxeItem(BRONZE_TOOL, 6f, -3.1f, settings));
    public static final Item BRONZE_HOE = register("bronze_hoe", settings -> new HoeItem(BRONZE_TOOL, -2f, -1f, settings));

    public static final Item BRONZE_SHIELD = register("bronze_shield", ShieldItem::new, new Settings()
            .maxDamage(BRONZE_TOOL_DURABILITY)
            .component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)
            .repairable(ItemTags.WOODEN_TOOL_MATERIALS)
            .equippableUnswappable(EquipmentSlot.OFFHAND)
            .component(DataComponentTypes.BLOCKS_ATTACKS, new BlocksAttacksComponent(
                    0.25F,
                    1.0F,
                    List.of(new BlocksAttacksComponent.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                    new BlocksAttacksComponent.ItemDamage(3.0F, 1.0F, 1.0F),
                    Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                    Optional.of(SoundEvents.ITEM_SHIELD_BLOCK),
                    Optional.of(SoundEvents.ITEM_SHIELD_BREAK)
            ))
            .component(DataComponentTypes.BREAK_SOUND, SoundEvents.ITEM_SHIELD_BREAK)
    );
    public static final Item BRONZE_SHEARS = register("bronze_shears", ShearsItem::new, new Item.Settings()
            .maxDamage(BRONZE_TOOL_DURABILITY)
            .component(DataComponentTypes.TOOL, ShearsItem.createToolComponent())
    );
    public static final Item FLINT_AND_BRONZE = register("flint_and_bronze", FlintAndSteelItem::new, new Item.Settings()
            .maxDamage(BRONZE_TOOL_DURABILITY)
    );

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAfter(Items.IRON_HOE, BRONZE_SHOVEL, BRONZE_PICKAXE, BRONZE_AXE, BRONZE_HOE);
            entries.addAfter(Items.FLINT_AND_STEEL, FLINT_AND_BRONZE);
            entries.addAfter(Items.SHEARS, BRONZE_SHEARS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.IRON_SWORD, BRONZE_SWORD);
            entries.addAfter(Items.IRON_AXE, BRONZE_AXE);
            entries.addAfter(Items.SHIELD, BRONZE_SHIELD);
            entries.addAfter(Items.IRON_BOOTS, BRONZE_HELMET, BRONZE_CHESTPLATE, BRONZE_LEGGINGS, BRONZE_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.IRON_INGOT, BRONZE_INGOT);
        });

        DispenserBlock.registerBehavior(BRONZE_SHEARS, new ShearsDispenserBehavior());
    }
}
