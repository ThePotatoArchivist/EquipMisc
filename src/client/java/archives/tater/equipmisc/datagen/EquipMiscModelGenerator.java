package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMiscItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class EquipMiscModelGenerator extends FabricModelProvider {

    public EquipMiscModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.registerArmor(EquipMiscItems.REINFORCED_LEATHER_HELMET);
        itemModelGenerator.registerArmor(EquipMiscItems.REINFORCED_LEATHER_CHESTPLATE);
        itemModelGenerator.registerArmor(EquipMiscItems.REINFORCED_LEATHER_LEGGINGS);
        itemModelGenerator.registerArmor(EquipMiscItems.REINFORCED_LEATHER_BOOTS);
        itemModelGenerator.register(EquipMiscItems.LEATHER_REINFORCEMENT_SMITHING_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(EquipMiscItems.RAW_BRONZE, Models.GENERATED);
        itemModelGenerator.register(EquipMiscItems.BRONZE_INGOT, Models.GENERATED);
        itemModelGenerator.register(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(EquipMiscItems.BRONZE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EquipMiscItems.BRONZE_AXE, Models.HANDHELD);
        itemModelGenerator.register(EquipMiscItems.BRONZE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EquipMiscItems.BRONZE_HOE, Models.HANDHELD);
        itemModelGenerator.register(EquipMiscItems.BRONZE_SWORD, Models.HANDHELD);
        itemModelGenerator.registerArmor(EquipMiscItems.BRONZE_HELMET);
        itemModelGenerator.registerArmor(EquipMiscItems.BRONZE_CHESTPLATE);
        itemModelGenerator.registerArmor(EquipMiscItems.BRONZE_LEGGINGS);
        itemModelGenerator.registerArmor(EquipMiscItems.BRONZE_BOOTS);
    }
}
