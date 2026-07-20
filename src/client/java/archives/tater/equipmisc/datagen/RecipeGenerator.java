package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.component.ChainmailUpgrade;
import archives.tater.equipmisc.recipe.SmithingPatchRecipe;
import archives.tater.equipmisc.registry.EquipMiscComponents;
import archives.tater.equipmisc.registry.EquipMiscItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.impl.resource.conditions.conditions.AllModsLoadedResourceCondition;

import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.triggers.RecipeUnlockedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static archives.tater.equipmisc.registry.EquipMiscItems.*;
import static net.minecraft.world.item.Items.*;

@SuppressWarnings("UnstableApiUsage")
public class RecipeGenerator extends FabricRecipeProvider {

    public RecipeGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {

            private void offerBronzeUpgrade(ItemLike input, Item result, RecipeOutput output) {
                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(BRONZE_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.of(input),
                                tag(EquipMiscItemTags.BRONZE_TOOL_MATERIALS),
                                RecipeCategory.MISC,
                                result
                        )
                        .unlocks(getHasName(BRONZE_INGOT), has(EquipMiscItemTags.BRONZE_TOOL_MATERIALS))
                        .save(output, getItemName(result) + "_smithing");
            }

            private void offerBronzeUpgrade(ItemLike input, Item result) {
                offerBronzeUpgrade(input, result, output);
            }

            private void offerChainmailUpgrade(String name, TagKey<Item> input, ItemLike addition) {
                var id = EquipMisc.id("chainmail_" + name + "_upgrade_smithing");
                var recipeKey = ResourceKey.create(Registries.RECIPE, id);

                var advancement = output.advancement()
                        .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeKey))
                        .addCriterion(getHasName(CHAINMAIL_UPGRADE_SMITHING_TEMPLATE), has(CHAINMAIL_UPGRADE_SMITHING_TEMPLATE))
                        .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                        .requirements(AdvancementRequirements.Strategy.OR)
                        .build(id.withPrefix("recipes/" + RecipeCategory.COMBAT.getFolderName() + "/"));

                output.accept(recipeKey, new SmithingPatchRecipe(
                        Ingredient.of(CHAINMAIL_UPGRADE_SMITHING_TEMPLATE),
                        tag(input),
                        Ingredient.of(addition),
                        DataComponentPatch.builder().set(EquipMiscComponents.CHAINMAIL_UPGRADE, ChainmailUpgrade.INSTANCE).build()
                ), advancement);
            }

            @Override
            public void buildRecipes() {
                shapeless(RecipeCategory.MISC, RAW_BRONZE)
                        .requires(tag(ConventionalItemTags.COPPER_RAW_MATERIALS), 4)
                        .requires(tag(ConventionalItemTags.IRON_NUGGETS), 4)
                        .unlockedBy(getHasName(Items.RAW_COPPER), has(ConventionalItemTags.COPPER_RAW_MATERIALS))
                        .save(output);

                oreSmelting(List.of(RAW_BRONZE), RecipeCategory.MISC, CookingBookCategory.MISC, BRONZE_INGOT, 1.5f, 200, "");
                oreBlasting(List.of(RAW_BRONZE), RecipeCategory.MISC, CookingBookCategory.MISC, BRONZE_INGOT, 1.5f, 100, "");

                offerBronzeUpgrade(IRON_HELMET, BRONZE_HELMET);
                offerBronzeUpgrade(IRON_CHESTPLATE, BRONZE_CHESTPLATE);
                offerBronzeUpgrade(IRON_LEGGINGS, BRONZE_LEGGINGS);
                offerBronzeUpgrade(IRON_BOOTS, BRONZE_BOOTS);
                offerBronzeUpgrade(IRON_SWORD, BRONZE_SWORD);
                offerBronzeUpgrade(IRON_SHOVEL, BRONZE_SHOVEL);
                offerBronzeUpgrade(IRON_PICKAXE, BRONZE_PICKAXE);
                offerBronzeUpgrade(IRON_AXE, BRONZE_AXE);
                offerBronzeUpgrade(IRON_HOE, BRONZE_HOE);
                offerBronzeUpgrade(IRON_SPEAR, BRONZE_SPEAR);
                offerBronzeUpgrade(SHIELD, BRONZE_SHIELD);
                offerBronzeUpgrade(SHEARS, BRONZE_SHEARS);
                offerBronzeUpgrade(FLINT_AND_STEEL, FLINT_AND_BRONZE);
                shapeless(RecipeCategory.TOOLS, FLINT_AND_BRONZE)
                        .requires(BRONZE_INGOT)
                        .requires(FLINT)
                        .unlockedBy(getHasName(BRONZE_INGOT), has(BRONZE_INGOT))
                        .save(output);
                offerBronzeUpgrade(ModItems.IRON_KNIFE.get(), BRONZE_KNIFE, withConditions(output, new AllModsLoadedResourceCondition(List.of(EquipMisc.FARMERS_DELIGHT))));

                offerChainmailUpgrade("helmet", ItemTags.HEAD_ARMOR, CHAINMAIL_HELMET);
                offerChainmailUpgrade("chestplate", ItemTags.CHEST_ARMOR, CHAINMAIL_CHESTPLATE);
                offerChainmailUpgrade("leggings", ItemTags.LEG_ARMOR, CHAINMAIL_LEGGINGS);
                offerChainmailUpgrade("boots", ItemTags.FOOT_ARMOR, CHAINMAIL_BOOTS);

                shaped(RecipeCategory.MISC, BRONZE_UPGRADE_SMITHING_TEMPLATE, 2)
                        .define('#', COPPER_INGOT)
                        .define('C', COBBLESTONE)
                        .define('S', BRONZE_UPGRADE_SMITHING_TEMPLATE)
                        .pattern("#S#")
                        .pattern("#C#")
                        .pattern("###")
                        .unlockedBy(getHasName(BRONZE_UPGRADE_SMITHING_TEMPLATE), has(BRONZE_UPGRADE_SMITHING_TEMPLATE))
                        .save(output);
                copySmithingTemplate(CHAINMAIL_UPGRADE_SMITHING_TEMPLATE, NETHER_BRICK);
            }
        };
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}
