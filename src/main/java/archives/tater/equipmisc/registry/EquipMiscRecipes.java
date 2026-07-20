package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.recipe.SmithingPatchRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class EquipMiscRecipes {
    public static RecipeSerializer<SmithingPatchRecipe> SMITHING_PATCH = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            EquipMisc.id("smithing_patch"),
            new RecipeSerializer<>(SmithingPatchRecipe.CODEC, SmithingPatchRecipe.STREAM_CODEC)
    );

    public static void init() {

    }
}
