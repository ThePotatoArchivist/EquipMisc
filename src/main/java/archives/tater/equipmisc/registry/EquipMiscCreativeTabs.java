package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

import vectorwing.farmersdelight.common.registry.ModCreativeTabs;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.awt.*;

import static net.minecraft.util.Util.makeDescriptionId;

public interface EquipMiscCreativeTabs {
    Identifier EQUIPMISC_TAB_ID = EquipMisc.id(EquipMisc.MOD_ID);
    Component EQUIPMISC_TAB_TITLE = Component.translatable(makeDescriptionId("itemGroup", EQUIPMISC_TAB_ID));
    CreativeModeTab EQUIPMISC_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            EQUIPMISC_TAB_ID,
            FabricCreativeModeTab.builder()
                    .icon(EquipMiscItems.BRONZE_PICKAXE::getDefaultInstance)
                    .title(EQUIPMISC_TAB_TITLE)
                    .displayItems((_, output) -> {
                        output.accept(EquipMiscItems.RAW_BRONZE);
                        output.accept(EquipMiscItems.BRONZE_INGOT);
                        output.accept(EquipMiscItems.BRONZE_BLOCK);
                        output.accept(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE);
                        output.accept(EquipMiscItems.BRONZE_HELMET);
                        output.accept(EquipMiscItems.BRONZE_CHESTPLATE);
                        output.accept(EquipMiscItems.BRONZE_LEGGINGS);
                        output.accept(EquipMiscItems.BRONZE_BOOTS);
                        output.accept(EquipMiscItems.BRONZE_SWORD);
                        output.accept(EquipMiscItems.BRONZE_SPEAR);
                        output.accept(EquipMiscItems.BRONZE_SHOVEL);
                        output.accept(EquipMiscItems.BRONZE_PICKAXE);
                        output.accept(EquipMiscItems.BRONZE_AXE);
                        output.accept(EquipMiscItems.BRONZE_HOE);
                        output.accept(EquipMiscItems.BRONZE_SHIELD);
                        output.accept(EquipMiscItems.BRONZE_SHEARS);
                        output.accept(EquipMiscItems.FLINT_AND_BRONZE);
                        if (EquipMisc.FARMERS_DELIGHT_INSTALLED)
                            output.accept(EquipMiscItems.BRONZE_KNIFE);
                        output.accept(EquipMiscItems.CHAINMAIL_UPGRADE_SMITHING_TEMPLATE);
                        output.accept(Items.CHAINMAIL_HELMET);
                        output.accept(Items.CHAINMAIL_CHESTPLATE);
                        output.accept(Items.CHAINMAIL_LEGGINGS);
                        output.accept(Items.CHAINMAIL_BOOTS);
                        output.accept(Items.TURTLE_HELMET);
//                        output.accept(RAVAGER_HELMET);
                    })
                    .build()
    );

    static void init() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            output.insertAfter(Items.IRON_HOE, EquipMiscItems.BRONZE_SHOVEL, EquipMiscItems.BRONZE_PICKAXE, EquipMiscItems.BRONZE_AXE, EquipMiscItems.BRONZE_HOE);
            output.insertAfter(Items.FLINT_AND_STEEL, EquipMiscItems.FLINT_AND_BRONZE);
            output.insertAfter(Items.SHEARS, EquipMiscItems.BRONZE_SHEARS);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.insertAfter(Items.IRON_SWORD, EquipMiscItems.BRONZE_SWORD);
            output.insertAfter(Items.IRON_AXE, EquipMiscItems.BRONZE_AXE);
            output.insertAfter(Items.IRON_SPEAR, EquipMiscItems.BRONZE_SPEAR);
            output.insertAfter(Items.SHIELD, EquipMiscItems.BRONZE_SHIELD);
            output.insertAfter(Items.IRON_BOOTS, EquipMiscItems.BRONZE_HELMET, EquipMiscItems.BRONZE_CHESTPLATE, EquipMiscItems.BRONZE_LEGGINGS, EquipMiscItems.BRONZE_BOOTS);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.insertAfter(Items.IRON_INGOT, EquipMiscItems.BRONZE_INGOT);
            output.insertAfter(Items.RAW_IRON, EquipMiscItems.RAW_BRONZE);
            output.insertBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, EquipMiscItems.CHAINMAIL_UPGRADE_SMITHING_TEMPLATE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
            output.insertAfter(Items.IRON_BLOCK, EquipMiscItems.BRONZE_BLOCK);
        });
        if (EquipMisc.FARMERS_DELIGHT_INSTALLED)
            CreativeModeTabEvents.modifyOutputEvent(BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(ModCreativeTabs.TAB_FARMERS_DELIGHT.get()).orElseThrow()).register(output -> {
                output.insertAfter(ModItems.COPPER_KNIFE.get(), EquipMiscItems.BRONZE_KNIFE);
            });
    }
}
