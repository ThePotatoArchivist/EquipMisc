package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.component.ChainmailUpgrade;
import archives.tater.equipmisc.recipe.SmithingPatchRecipe;
import archives.tater.equipmisc.registry.EquipMiscComponents;
import archives.tater.equipmisc.registry.EquipMiscItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.impl.resource.conditions.conditions.AllModsLoadedResourceCondition;

import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShieldDecorationRecipe;
import net.minecraft.world.level.ItemLike;

import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static archives.tater.equipmisc.registry.EquipMiscItemTags.BRONZE_INGOTS;
import static archives.tater.equipmisc.registry.EquipMiscItems.*;
import static net.minecraft.world.item.Items.*;

@SuppressWarnings("UnstableApiUsage")
public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {

            private void offerBronzeUpgrade(ItemLike input, Item result, RecipeCategory recipeCategory, RecipeOutput output) {
                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(BRONZE_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.of(input),
                                tag(EquipMiscItemTags.BRONZE_TOOL_MATERIALS),
                                recipeCategory,
                                result
                        )
                        .unlocks(getHasName(BRONZE_INGOT), has(EquipMiscItemTags.BRONZE_TOOL_MATERIALS))
                        .save(output, getItemName(result) + "_smithing");
            }

            private void offerBronzeUpgrade(ItemLike input, Item result, RecipeCategory recipeCategory) {
                offerBronzeUpgrade(input, result, recipeCategory, output);
            }

            private void offerChainmailUpgrade(String name, TagKey<Item> input, ItemLike addition) {
                var id = EquipMisc.id("chainmail_" + name + "_upgrade_smithing");
                var recipeKey = ResourceKey.create(Registries.RECIPE, id);

                var advancement = new RecipeUnlockAdvancementBuilder();
                advancement.unlockedBy(getHasName(CHAINMAIL_UPGRADE_SMITHING_TEMPLATE), has(CHAINMAIL_UPGRADE_SMITHING_TEMPLATE));

                output.accept(recipeKey, new SmithingPatchRecipe(
                        Ingredient.of(CHAINMAIL_UPGRADE_SMITHING_TEMPLATE),
                        tag(input),
                        Ingredient.of(addition),
                        DataComponentPatch.builder().set(EquipMiscComponents.CHAINMAIL_UPGRADE, ChainmailUpgrade.INSTANCE).build()
                ), advancement.build(output, recipeKey, RecipeCategory.COMBAT));
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

                nineBlockStorageRecipes(RecipeCategory.MISC, BRONZE_INGOT, RecipeCategory.BUILDING_BLOCKS, BRONZE_BLOCK);

                offerBronzeUpgrade(IRON_HELMET, BRONZE_HELMET, RecipeCategory.COMBAT);
                offerBronzeUpgrade(IRON_CHESTPLATE, BRONZE_CHESTPLATE, RecipeCategory.COMBAT);
                offerBronzeUpgrade(IRON_LEGGINGS, BRONZE_LEGGINGS, RecipeCategory.COMBAT);
                offerBronzeUpgrade(IRON_BOOTS, BRONZE_BOOTS, RecipeCategory.COMBAT);
                offerBronzeUpgrade(IRON_SWORD, BRONZE_SWORD, RecipeCategory.COMBAT);
                offerBronzeUpgrade(IRON_SHOVEL, BRONZE_SHOVEL, RecipeCategory.TOOLS);
                offerBronzeUpgrade(IRON_PICKAXE, BRONZE_PICKAXE, RecipeCategory.TOOLS);
                offerBronzeUpgrade(IRON_AXE, BRONZE_AXE, RecipeCategory.TOOLS);
                offerBronzeUpgrade(IRON_HOE, BRONZE_HOE, RecipeCategory.TOOLS);
                offerBronzeUpgrade(IRON_SPEAR, BRONZE_SPEAR, RecipeCategory.COMBAT);
                offerBronzeUpgrade(SHIELD, BRONZE_SHIELD, RecipeCategory.COMBAT);
                offerBronzeUpgrade(SHEARS, BRONZE_SHEARS, RecipeCategory.TOOLS);
                offerBronzeUpgrade(FLINT_AND_STEEL, FLINT_AND_BRONZE, RecipeCategory.TOOLS);
                shapeless(RecipeCategory.TOOLS, FLINT_AND_BRONZE)
                        .requires(BRONZE_INGOTS)
                        .requires(FLINT)
                        .unlockedBy(getHasName(BRONZE_INGOT), has(BRONZE_INGOTS))
                        .save(output);
                offerBronzeUpgrade(ModItems.IRON_KNIFE.get(), BRONZE_KNIFE, RecipeCategory.TOOLS, withConditions(output, new AllModsLoadedResourceCondition(List.of(EquipMisc.FARMERS_DELIGHT))));

                offerChainmailUpgrade("helmet", ItemTags.HEAD_ARMOR, CHAINMAIL_HELMET);
                offerChainmailUpgrade("chestplate", ItemTags.CHEST_ARMOR, CHAINMAIL_CHESTPLATE);
                offerChainmailUpgrade("leggings", ItemTags.LEG_ARMOR, CHAINMAIL_LEGGINGS);
                offerChainmailUpgrade("boots", ItemTags.FOOT_ARMOR, CHAINMAIL_BOOTS);

                shaped(RecipeCategory.MISC, BRONZE_UPGRADE_SMITHING_TEMPLATE, 2)
                        .define('#', COPPER_INGOT)
                        .define('C', SANDSTONE)
                        .define('S', BRONZE_UPGRADE_SMITHING_TEMPLATE)
                        .pattern("#S#")
                        .pattern("#C#")
                        .pattern("###")
                        .unlockedBy(getHasName(BRONZE_UPGRADE_SMITHING_TEMPLATE), has(BRONZE_UPGRADE_SMITHING_TEMPLATE))
                        .save(output);
                copySmithingTemplate(CHAINMAIL_UPGRADE_SMITHING_TEMPLATE, NETHER_BRICKS);

                SpecialRecipeBuilder.special(() -> new ShieldDecorationRecipe(tag(ItemTags.BANNERS), Ingredient.of(BRONZE_SHIELD), new ItemStackTemplate(BRONZE_SHIELD)))
                        .save(output, "bronze_shield_decoration");

                SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(BRONZE_SHIELD),
                        tag(ItemTags.NETHERITE_TOOL_MATERIALS),
                        RecipeCategory.COMBAT,
                        DatagenCompat.NETHERITE_SHIELD
                )
                        .unlocks(getHasName(NETHERITE_INGOT), has(ItemTags.NETHERITE_TOOL_MATERIALS))
                        .save(withConditions(output, ResourceConditions.allModsLoaded(DatagenCompat.REARM)), "netherite_shield_from_smithing_bronze_shield");
            }
        };
    }
}
