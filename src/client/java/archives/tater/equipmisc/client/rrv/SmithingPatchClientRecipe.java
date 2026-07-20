package archives.tater.equipmisc.client.rrv;

import archives.tater.equipmisc.recipe.SmithingPatchRecipe;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import cc.cassian.rrv.common.builtin.smithing.SmithingClientRecipe;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;

import java.util.Objects;
import java.util.function.BiPredicate;

public class SmithingPatchClientRecipe extends SmithingClientRecipe {
    private final SlotContent template;
    private final SlotContent base;
    private final SlotContent additionIngredient;
    private final SlotContent result;
    private final DataComponentPatch patch;
    private final BiPredicate<ItemStack, ItemStack> patchCheck;

    public SmithingPatchClientRecipe(Identifier id, SlotContent additionIngredient, SlotContent base, SlotContent template, SlotContent result, DataComponentPatch patch, int priority) {
        super(id, additionIngredient, base, template, result, null, priority);
        this.template = template;
        this.base = base;
        this.additionIngredient = additionIngredient;
        this.result = result;
        this.patch = patch;
        this.patchCheck = (stack1, stack2) -> patch.entrySet().stream().allMatch(entry ->
                Objects.equals(stack1.get(entry.getKey()), stack2.get(entry.getKey()))
        );
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindOptionalSlot(0, template, RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
        slotFillContext.bindOptionalSlot(1, base, RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
        slotFillContext.bindOptionalSlot(2, additionIngredient, RecipeViewMenu.OptionalSlotRenderer.DEFAULT);

        slotFillContext.bindDependentSlot(3, () -> result.getNextMatching(
                SmithingPatchRecipe.withChanges(base.current(), patch),
                patchCheck
        ), result);
    }
}
