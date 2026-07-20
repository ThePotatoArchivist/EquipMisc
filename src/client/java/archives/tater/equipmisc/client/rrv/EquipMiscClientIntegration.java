package archives.tater.equipmisc.client.rrv;

import archives.tater.equipmisc.recipe.SmithingPatchRecipe;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeType;

import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.client.recipe.ClientRecipeManager;
import cc.cassian.rrv.common.builtin.smithing.SmithingClientRecipe;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;

public class EquipMiscClientIntegration implements ReliableRecipeViewerClientPlugin {
    private static SmithingClientRecipe smithingPatch(Identifier id, SlotContent addition, SlotContent base, SlotContent template, DataComponentPatch patch) {
        return new SmithingPatchClientRecipe(
                id,
                addition,
                SlotContent.of(base.getValidContents().stream().filter(stack -> !addition.hasItem(stack.getItem())).toList()),
                template,
                SlotContent.of(base.getValidContents().stream().map(stack -> SmithingPatchRecipe.withChanges(stack, patch)).toList()),
                patch,
                2
        );
    }

    @Override
    public void onIntegrationInitialize() {
        ItemView.addClientRecipeProvider(recipeList -> {
            ClientRecipeManager.INSTANCE.getRecipesForType(RecipeType.SMITHING).forEach(recipe -> {
                if (recipe.value() instanceof SmithingPatchRecipe smithingPatchRecipe) {
                    recipeList.add(smithingPatch(
                            recipe.id().identifier(),
                            SlotContent.of(smithingPatchRecipe.additionIngredient().orElse(null)),
                            SlotContent.of(smithingPatchRecipe.baseIngredient()),
                            SlotContent.of(smithingPatchRecipe.templateIngredient().orElse(null)),
                            smithingPatchRecipe.patch()
                    ));
                }
            });
        });
    }
}
