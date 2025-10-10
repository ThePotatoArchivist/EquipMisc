package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMisc;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.impl.resource.conditions.conditions.AllModsLoadedResourceCondition;

import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static archives.tater.equipmisc.registry.EquipMiscItems.*;
import static net.minecraft.item.Items.*;

public class EMRecipeGenerator extends RecipeGenerator {

    private final RecipeExporterProvider exporterProvider;

    protected EMRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter, RecipeExporterProvider exporterProvider) {
        super(registries, exporter);
        this.exporterProvider = exporterProvider;
    }

    private void offerBronzeUpgrade(ItemConvertible input, Item result, RecipeExporter exporter) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItem(BRONZE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.ofItem(input),
                        ingredientFromTag(BRONZE_TOOL_MATERIALS),
                        RecipeCategory.MISC,
                        result
                )
                .criterion(hasItem(BRONZE_INGOT), conditionsFromTag(BRONZE_TOOL_MATERIALS))
                .offerTo(exporter, getItemPath(result) + "_smithing");
    }

    private void offerBronzeUpgrade(ItemConvertible input, Item result) {
        offerBronzeUpgrade(input, result, exporter);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void generate() {
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
        createShapeless(RecipeCategory.TOOLS, FLINT_AND_BRONZE)
                .input(BRONZE_INGOT)
                .input(FLINT)
                .criterion(hasItem(BRONZE_INGOT), conditionsFromItem(BRONZE_INGOT))
                .offerTo(exporter);
        offerBronzeUpgrade(ModItems.IRON_KNIFE.get(), BRONZE_KNIFE, exporterProvider.get(new AllModsLoadedResourceCondition(List.of(EquipMisc.FARMERS_DELIGHT))));
    }

    public static class Provider extends FabricRecipeProvider {

        public Provider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
            return new EMRecipeGenerator(wrapperLookup, recipeExporter, conditions -> withConditions(recipeExporter, conditions));
        }

        @Override
        public String getName() {
            return "Recipes";
        }
    }

    protected interface RecipeExporterProvider {
        RecipeExporter get(ResourceCondition... conditions);
    }
}
