package archives.tater.equipmisc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static archives.tater.equipmisc.registry.EquipMiscItems.*;
import static net.minecraft.item.Items.*;

public class EMRecipeGenerator extends RecipeGenerator {
    protected EMRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    private void offerBronzeUpgrade(ItemConvertible input, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItem(BRONZE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.ofItem(input),
                        ingredientFromTag(BRONZE_TOOL_MATERIALS),
                        RecipeCategory.MISC,
                        result
                )
                .criterion(hasItem(BRONZE_INGOT), conditionsFromTag(BRONZE_TOOL_MATERIALS))
                .offerTo(this.exporter, getItemPath(result) + "_smithing");
    }

    @Override
    public void generate() {
        var items = registries.getOrThrow(RegistryKeys.ITEM);

        createShapeless(RecipeCategory.MISC, RAW_BRONZE)
                .input(ingredientFromTag(ConventionalItemTags.COPPER_RAW_MATERIALS), 4)
                .input(ingredientFromTag(ConventionalItemTags.IRON_NUGGETS), 4)
                .criterion(hasItem(Items.RAW_COPPER), conditionsFromTag(ConventionalItemTags.COPPER_RAW_MATERIALS))
                .offerTo(exporter);

        offerSmelting(List.of(RAW_BRONZE), RecipeCategory.MISC, BRONZE_INGOT, 1.5f, 200, "");
        offerBlasting(List.of(RAW_BRONZE), RecipeCategory.MISC, BRONZE_INGOT, 1.5f, 100, "");

        offerBronzeUpgrade(IRON_HELMET, BRONZE_HELMET);
        offerBronzeUpgrade(IRON_CHESTPLATE, BRONZE_CHESTPLATE);
        offerBronzeUpgrade(IRON_LEGGINGS, BRONZE_LEGGINGS);
        offerBronzeUpgrade(IRON_BOOTS, BRONZE_BOOTS);
        offerBronzeUpgrade(IRON_SWORD, BRONZE_SWORD);
        offerBronzeUpgrade(IRON_SHOVEL, BRONZE_SHOVEL);
        offerBronzeUpgrade(IRON_PICKAXE, BRONZE_PICKAXE);
        offerBronzeUpgrade(IRON_AXE, BRONZE_AXE);
        offerBronzeUpgrade(IRON_HOE, BRONZE_HOE);
        offerBronzeUpgrade(SHIELD, BRONZE_SHIELD);
        offerBronzeUpgrade(SHEARS, BRONZE_SHEARS);
        offerBronzeUpgrade(FLINT_AND_STEEL, FLINT_AND_BRONZE);
    }

    public static class Provider extends FabricRecipeProvider {

        public Provider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
            return new EMRecipeGenerator(wrapperLookup, recipeExporter);
        }

        @Override
        public String getName() {
            return "Recipes";
        }
    }
}
