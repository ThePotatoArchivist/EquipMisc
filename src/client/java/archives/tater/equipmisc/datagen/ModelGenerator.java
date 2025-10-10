package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.client.render.item.model.TexturedShieldModelRenderer.Unbaked;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import net.minecraft.client.data.*;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import static archives.tater.equipmisc.registry.EquipMiscItems.*;
import static net.minecraft.client.data.ItemModelGenerator.*;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    public static final Identifier BRONZE_SHIELD_BASE = EquipMisc.id("entity/bronze_shield_base");
    public static final Identifier BRONZE_SHIELD_BASE_NO_PATTERN = EquipMisc.id("entity/bronze_shield_base_nopattern");

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(BRONZE_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(RAW_BRONZE, Models.GENERATED);
        itemModelGenerator.register(BRONZE_INGOT, Models.GENERATED);

        itemModelGenerator.registerArmor(BRONZE_HELMET, BRONZE_ARMOR.assetId(), HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(BRONZE_CHESTPLATE, BRONZE_ARMOR.assetId(), CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(BRONZE_LEGGINGS, BRONZE_ARMOR.assetId(), LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(BRONZE_BOOTS, BRONZE_ARMOR.assetId(), BOOTS_TRIM_ID_PREFIX, false);

        itemModelGenerator.register(BRONZE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(BRONZE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(BRONZE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(BRONZE_AXE, Models.HANDHELD);
        itemModelGenerator.register(BRONZE_HOE, Models.HANDHELD);

        var shieldSpecial = new Unbaked(BRONZE_SHIELD_BASE, BRONZE_SHIELD_BASE_NO_PATTERN);
        var shield = ItemModels.special(ModelIds.getItemModelId(Items.SHIELD), shieldSpecial);
        var shieldBlocking = ItemModels.special(ModelIds.getItemSubModelId(Items.SHIELD, "_blocking"), shieldSpecial);
        itemModelGenerator.registerCondition(BRONZE_SHIELD, ItemModels.usingItemProperty(), shieldBlocking, shield);

        itemModelGenerator.register(BRONZE_SHEARS, Models.GENERATED);
        itemModelGenerator.register(FLINT_AND_BRONZE, Models.GENERATED);
        itemModelGenerator.register(BRONZE_KNIFE, Models.HANDHELD);
    }
}
