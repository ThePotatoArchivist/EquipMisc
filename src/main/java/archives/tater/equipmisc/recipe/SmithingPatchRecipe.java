package archives.tater.equipmisc.recipe;

import archives.tater.equipmisc.registry.EquipMiscRecipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.item.crafting.display.SmithingRecipeDisplay;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class SmithingPatchRecipe extends SimpleSmithingRecipe {

    public static final MapCodec<SmithingPatchRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            CommonInfo.MAP_CODEC.forGetter(recipe -> recipe.commonInfo),
            Ingredient.CODEC.optionalFieldOf("template").forGetter(recipe -> recipe.template),
            Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
            Ingredient.CODEC.optionalFieldOf("addition").forGetter(recipe -> recipe.addition),
            DataComponentPatch.CODEC.fieldOf("patch").forGetter(recipe -> recipe.patch)
    ).apply(instance, SmithingPatchRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SmithingPatchRecipe> STREAM_CODEC = StreamCodec.composite(
            CommonInfo.STREAM_CODEC, recipe -> recipe.commonInfo,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, recipe -> recipe.template,
            Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.base,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, recipe -> recipe.addition,
            DataComponentPatch.STREAM_CODEC, recipe -> recipe.patch,
            SmithingPatchRecipe::new
    );

    private final Optional<Ingredient> template;
    private final Ingredient base;
    private final Optional<Ingredient> addition;
    private final DataComponentPatch patch;

    public SmithingPatchRecipe(CommonInfo commonInfo, Optional<Ingredient> template, Ingredient base, Optional<Ingredient> addition, DataComponentPatch patch) {
        super(commonInfo);
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.patch = patch;
    }

    public SmithingPatchRecipe(@Nullable Ingredient template, Ingredient base, @Nullable Ingredient addition, DataComponentPatch patch) {
        this(new CommonInfo(true), Optional.ofNullable(template), base, Optional.ofNullable(addition), patch);
    }

    public static ItemStack withChanges(ItemStack base, DataComponentPatch changes) {
        var stack = base.copy();
        stack.applyComponentsAndValidate(changes);
        return stack;
    }

    @Override
    public boolean matches(SmithingRecipeInput smithingRecipeInput, Level world) {
        return super.matches(smithingRecipeInput, world)
                && addition.map(it -> !it.test(smithingRecipeInput.base())).orElse(true)
                && !ItemStack.isSameItemSameComponents(smithingRecipeInput.base(), withChanges(smithingRecipeInput.base(), patch));
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input) {
        return withChanges(input.base(), patch);
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

    public DataComponentPatch patch() {
        return patch;
    }

    @Override
    public RecipeSerializer<SmithingPatchRecipe> getSerializer() {
        return EquipMiscRecipes.SMITHING_PATCH;
    }

    @Override
    protected PlacementInfo createPlacementInfo() {
        return PlacementInfo.createFromOptionals(List.of(this.template, Optional.of(this.base), this.addition));
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
}
