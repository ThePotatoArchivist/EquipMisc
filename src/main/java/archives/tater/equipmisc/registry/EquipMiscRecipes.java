package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.recipe.SmithingPatchRecipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EquipMiscRecipes {
    public static RecipeSerializer<SmithingPatchRecipe> SMITHING_PATCH = Registry.register(
            Registries.RECIPE_SERIALIZER,
            EquipMisc.id("smithing_patch"),
            new SmithingPatchRecipe.Serializer()
    );

    public static void init() {

    }
}
