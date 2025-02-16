package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMiscItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class EquipMiscRecipeGenerator extends FabricRecipeProvider {
    public EquipMiscRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    public static void offerLeatherReinforcement(Item input, Item addition, Item result, Consumer<RecipeJsonProvider> exporter) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(EquipMiscItems.SMITHING_TEMPLATE_LEATHER_REINFORCEMENT), Ingredient.ofItems(input), Ingredient.ofItems(addition), RecipeCategory.COMBAT, result
                )
                .criterion("has_" + getItemPath(addition), conditionsFromItem(addition))
                .offerTo(exporter, getItemPath(result) + "_smithing");
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerLeatherReinforcement(Items.LEATHER_HELMET, Items.CHAINMAIL_HELMET, EquipMiscItems.REINFORCED_LEATHER_HELMET, exporter);
        offerLeatherReinforcement(Items.LEATHER_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE, EquipMiscItems.REINFORCED_LEATHER_CHESTPLATE, exporter);
        offerLeatherReinforcement(Items.LEATHER_LEGGINGS, Items.CHAINMAIL_LEGGINGS, EquipMiscItems.REINFORCED_LEATHER_LEGGINGS, exporter);
        offerLeatherReinforcement(Items.LEATHER_BOOTS, Items.CHAINMAIL_BOOTS, EquipMiscItems.REINFORCED_LEATHER_BOOTS, exporter);
        offerSmithingTemplateCopyingRecipe(exporter, EquipMiscItems.SMITHING_TEMPLATE_LEATHER_REINFORCEMENT, Items.BEDROCK); // TODO choose material
    }
}
