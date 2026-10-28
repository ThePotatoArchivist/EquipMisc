package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.client.EquipMiscClient;
import archives.tater.equipmisc.client.render.item.model.TexturedShieldModelRenderer.Unbaked;
import archives.tater.equipmisc.registry.EquipMiscBlocks;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.trim.TrimMaterials;

import java.util.Map;

import static archives.tater.equipmisc.registry.EquipMiscItems.*;
import static net.minecraft.client.data.models.ItemModelGenerators.*;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricPackOutput output) {
        super(output);
    }

    public static final Identifier BRONZE_SHIELD_BASE = EquipMisc.id("entity/bronze_shield_base");
    public static final Identifier BRONZE_SHIELD_BASE_NO_PATTERN = EquipMisc.id("entity/bronze_shield_base_nopattern");

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(EquipMiscBlocks.BRONZE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(BRONZE_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RAW_BRONZE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BRONZE_INGOT, ModelTemplates.FLAT_ITEM);

        var bronzeOverrides = Map.of(TrimMaterials.Palette.EQUIPMISC_BRONZE, TrimMaterials.Palette.EQUIPMISC_BRONZE_DARKER);
        itemModelGenerator.generateTrimmableItem(BRONZE_HELMET, TRIM_PREFIX_HELMET, false, bronzeOverrides);
        itemModelGenerator.generateTrimmableItem(BRONZE_CHESTPLATE, TRIM_PREFIX_CHESTPLATE, false, bronzeOverrides);
        itemModelGenerator.generateTrimmableItem(BRONZE_LEGGINGS, TRIM_PREFIX_LEGGINGS, false, bronzeOverrides);
        itemModelGenerator.generateTrimmableItem(BRONZE_BOOTS, TRIM_PREFIX_BOOTS, false, bronzeOverrides);

        for (var model : EquipMiscClient.BRONZE_TRIM_MODELS.values()) {
            generateTrimModel(itemModelGenerator, model.modelId(), model.item(), model.prefix(), "_equipmisc_bronze", model.dyed());
        }

        itemModelGenerator.generateFlatItem(BRONZE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(BRONZE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(BRONZE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(BRONZE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(BRONZE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateSpear(BRONZE_SPEAR);

        var shieldSpecial = new Unbaked(BRONZE_SHIELD_BASE, BRONZE_SHIELD_BASE_NO_PATTERN);
        var shield = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(Items.SHIELD), shieldSpecial);
        var shieldBlocking = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(Items.SHIELD, "_blocking"), shieldSpecial);
        itemModelGenerator.itemModelOutput.accept(BRONZE_SHIELD, ItemModelUtils.conditional(ShieldSpecialRenderer.DEFAULT_TRANSFORMATION, ItemModelUtils.isUsingItem(), shieldBlocking, shield));

        itemModelGenerator.generateFlatItem(BRONZE_SHEARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FLINT_AND_BRONZE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BRONZE_KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(CHAINMAIL_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
    }

    private static void generateTrimModel(ItemModelGenerators itemModelGenerator, Identifier modelId, Item item, Identifier prefix, String suffix, boolean dyed) {
        if (dyed)
            itemModelGenerator.generateLayeredItem(modelId, TextureMapping.getItemTexture(item), TextureMapping.getItemTexture(item, "_overlay"), new Material(prefix.withSuffix(suffix)));
        else
            itemModelGenerator.generateLayeredItem(modelId, TextureMapping.getItemTexture(item), new Material(prefix.withSuffix(suffix)));
    }
}
