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
        itemModelGenerator.register(EquipMiscItems.SMITHING_TEMPLATE_LEATHER_REINFORCEMENT, Models.GENERATED);
    }
}
