package archives.tater.equipmisc.recipe;

import archives.tater.equipmisc.registry.EquipMiscRecipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.recipe.display.SmithingRecipeDisplay;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class SmithingPatchRecipe implements SmithingRecipe {
    final Optional<Ingredient> template;
    final Ingredient base;
    final Optional<Ingredient> addition;
    final ComponentChanges result;
    @Nullable
    private IngredientPlacement ingredientPlacement;

    public SmithingPatchRecipe(Optional<Ingredient> template, Ingredient base, Optional<Ingredient> addition, ComponentChanges result) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    public SmithingPatchRecipe(@Nullable Ingredient template, Ingredient base, @Nullable Ingredient addition, ComponentChanges result) {
        this(Optional.ofNullable(template), base, Optional.ofNullable(addition), result);
    }

    @Override
    public boolean matches(SmithingRecipeInput smithingRecipeInput, World world) {
        return SmithingRecipe.super.matches(smithingRecipeInput, world) && addition.map(it -> !it.test(smithingRecipeInput.base())).orElse(true);
    }

    public ItemStack craft(SmithingRecipeInput smithingRecipeInput, WrapperLookup wrapperLookup) {
        var output = smithingRecipeInput.base().copy();
        output.applyChanges(result);
        return output;
    }

    @Override
    public Optional<Ingredient> template() {
        return this.template;
    }

    @Override
    public Ingredient base() {
        return this.base;
    }

    @Override
    public Optional<Ingredient> addition() {
        return this.addition;
    }

    @Override
    public RecipeSerializer<SmithingPatchRecipe> getSerializer() {
        return EquipMiscRecipes.SMITHING_PATCH;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = IngredientPlacement.forMultipleSlots(List.of(this.template, Optional.of(this.base), this.addition));
        }

        return this.ingredientPlacement;
    }

    @Override
    public List<RecipeDisplay> getDisplays() {
        return List.of(
                new SmithingRecipeDisplay(
                        Ingredient.toDisplay(this.template),
                        this.base.toDisplay(),
                        Ingredient.toDisplay(this.addition),
                        this.base.toDisplay(),
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
                                ComponentChanges.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                        )
                        .apply(instance, SmithingPatchRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, SmithingPatchRecipe> PACKET_CODEC = PacketCodec.tuple(
                Ingredient.OPTIONAL_PACKET_CODEC,
                recipe -> recipe.template,
                Ingredient.PACKET_CODEC,
                recipe -> recipe.base,
                Ingredient.OPTIONAL_PACKET_CODEC,
                recipe -> recipe.addition,
                ComponentChanges.PACKET_CODEC,
                recipe -> recipe.result,
                SmithingPatchRecipe::new
        );

        @Override
        public MapCodec<SmithingPatchRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, SmithingPatchRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}
