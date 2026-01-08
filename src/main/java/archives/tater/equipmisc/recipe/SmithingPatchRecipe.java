package archives.tater.equipmisc.recipe;

import archives.tater.equipmisc.registry.EquipMiscRecipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.item.crafting.display.SmithingRecipeDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class SmithingPatchRecipe implements SmithingRecipe {
    final Optional<Ingredient> template;
    final Ingredient base;
    final Optional<Ingredient> addition;
    final DataComponentPatch patch;
    @Nullable
    private PlacementInfo ingredientPlacement;

    public SmithingPatchRecipe(Optional<Ingredient> template, Ingredient base, Optional<Ingredient> addition, DataComponentPatch patch) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.patch = patch;
    }

    public SmithingPatchRecipe(@Nullable Ingredient template, Ingredient base, @Nullable Ingredient addition, DataComponentPatch patch) {
        this(Optional.ofNullable(template), base, Optional.ofNullable(addition), patch);
    }

    private static ItemStack withChanges(ItemStack base, DataComponentPatch changes) {
        var stack = base.copy();
        stack.applyComponentsAndValidate(changes);
        return stack;
    }

    @Override
    public boolean matches(SmithingRecipeInput smithingRecipeInput, Level world) {
        return SmithingRecipe.super.matches(smithingRecipeInput, world)
                && addition.map(it -> !it.test(smithingRecipeInput.base())).orElse(true)
                && !ItemStack.isSameItemSameComponents(smithingRecipeInput.base(), withChanges(smithingRecipeInput.base(), patch));
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput smithingRecipeInput, Provider wrapperLookup) {
        return withChanges(smithingRecipeInput.base(), patch);
    }

    @Override
    public Optional<Ingredient> templateIngredient() {
        return this.template;
    }

    @Override
    public Ingredient baseIngredient() {
        return this.base;
    }

    @Override
    public Optional<Ingredient> additionIngredient() {
        return this.addition;
    }

    @Override
    public RecipeSerializer<SmithingPatchRecipe> getSerializer() {
        return EquipMiscRecipes.SMITHING_PATCH;
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(List.of(this.template, Optional.of(this.base), this.addition));
        }

        return this.ingredientPlacement;
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(
                new SmithingRecipeDisplay(
                        Ingredient.optionalIngredientToDisplay(this.template),
                        this.base.display(),
                        Ingredient.optionalIngredientToDisplay(this.addition),
                        this.base.display(),
                        new SlotDisplay.ItemSlotDisplay(Items.SMITHING_TABLE)
                )
        );
    }

    public static class Serializer implements RecipeSerializer<SmithingPatchRecipe> {
        private static final MapCodec<SmithingPatchRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Ingredient.CODEC.optionalFieldOf("template").forGetter(recipe -> recipe.template),
                                Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                                Ingredient.CODEC.optionalFieldOf("addition").forGetter(recipe -> recipe.addition),
                                DataComponentPatch.CODEC.fieldOf("patch").forGetter(recipe -> recipe.patch)
                        )
                        .apply(instance, SmithingPatchRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, SmithingPatchRecipe> PACKET_CODEC = StreamCodec.composite(
                Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC,
                recipe -> recipe.template,
                Ingredient.CONTENTS_STREAM_CODEC,
                recipe -> recipe.base,
                Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC,
                recipe -> recipe.addition,
                DataComponentPatch.STREAM_CODEC,
                recipe -> recipe.patch,
                SmithingPatchRecipe::new
        );

        @Override
        public MapCodec<SmithingPatchRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SmithingPatchRecipe> streamCodec() {
            return PACKET_CODEC;
        }
    }
}
