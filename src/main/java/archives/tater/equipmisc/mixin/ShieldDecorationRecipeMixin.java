package archives.tater.equipmisc.mixin;

import archives.tater.equipmisc.registry.EquipMiscItems;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShieldDecorationRecipe;

@Mixin(ShieldDecorationRecipe.class)
public class ShieldDecorationRecipeMixin {
    @WrapOperation(
            method = {
                    "matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z",
                    "craft(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;"
            },
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z")
    )
    private boolean allowBronzeShield(ItemStack instance, Item item, Operation<Boolean> original) {
        return original.call(instance, item) || original.call(instance, EquipMiscItems.BRONZE_SHIELD);
    }
}
