package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMiscItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EquipMiscRecipeGenerator extends FabricRecipeProvider {
    public EquipMiscRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    public static void offerUpgrade(Item template, Item input, Item addition, Item result, Consumer<RecipeJsonProvider> exporter) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(template), Ingredient.ofItems(input), Ingredient.ofItems(addition), RecipeCategory.COMBAT, result
                )
                .criterion("has_" + getItemPath(addition), conditionsFromItem(addition))
                .offerTo(exporter, getItemPath(result) + "_smithing");
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerUpgrade(EquipMiscItems.LEATHER_REINFORCEMENT_SMITHING_TEMPLATE, Items.LEATHER_HELMET, Items.CHAINMAIL_HELMET, EquipMiscItems.REINFORCED_LEATHER_HELMET, exporter);
        offerUpgrade(EquipMiscItems.LEATHER_REINFORCEMENT_SMITHING_TEMPLATE, Items.LEATHER_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE, EquipMiscItems.REINFORCED_LEATHER_CHESTPLATE, exporter);
        offerUpgrade(EquipMiscItems.LEATHER_REINFORCEMENT_SMITHING_TEMPLATE, Items.LEATHER_LEGGINGS, Items.CHAINMAIL_LEGGINGS, EquipMiscItems.REINFORCED_LEATHER_LEGGINGS, exporter);
        offerUpgrade(EquipMiscItems.LEATHER_REINFORCEMENT_SMITHING_TEMPLATE, Items.LEATHER_BOOTS, Items.CHAINMAIL_BOOTS, EquipMiscItems.REINFORCED_LEATHER_BOOTS, exporter);
        offerSmithingTemplateCopyingRecipe(exporter, EquipMiscItems.LEATHER_REINFORCEMENT_SMITHING_TEMPLATE, Items.BEDROCK); // TODO choose material

        Map.of(
                Items.IRON_PICKAXE, EquipMiscItems.BRONZE_PICKAXE,
                Items.IRON_AXE, EquipMiscItems.BRONZE_AXE,
                Items.IRON_SHOVEL, EquipMiscItems.BRONZE_SHOVEL,
                Items.IRON_HOE, EquipMiscItems.BRONZE_HOE,
                Items.IRON_SWORD, EquipMiscItems.BRONZE_SWORD,
                Items.IRON_HELMET, EquipMiscItems.BRONZE_HELMET,
                Items.IRON_CHESTPLATE, EquipMiscItems.BRONZE_CHESTPLATE,
                Items.IRON_LEGGINGS, EquipMiscItems.BRONZE_LEGGINGS,
                Items.IRON_BOOTS, EquipMiscItems.BRONZE_BOOTS
        ).forEach((ironEquipment, bronzeEquipment) ->
                offerUpgrade(EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, ironEquipment, EquipMiscItems.BRONZE_INGOT, bronzeEquipment, exporter));
        offerSmithingTemplateCopyingRecipe(exporter, EquipMiscItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, Items.BEDROCK); // TODO choose material
        new ShapelessRecipeJsonBuilder(RecipeCategory.MISC, EquipMiscItems.RAW_BRONZE, 1)
                .input(Items.RAW_COPPER, 4)
                .input(Items.IRON_NUGGET, 4)
                .criterion(hasItem(Items.RAW_COPPER), conditionsFromItem(Items.RAW_COPPER))
                .offerTo(exporter);
        offerBlasting(exporter, List.of(EquipMiscItems.RAW_BRONZE), RecipeCategory.MISC, EquipMiscItems.BRONZE_INGOT, 2.8f, 200, null);
    }
}
