package archives.tater.equipmisc.mixin;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.TagKey;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract boolean isIn(TagKey<Item> tag);

    @ModifyReturnValue(
            method = "isOf",
            at = @At("RETURN")
    )
    private boolean shearCheck(boolean original, @Local(argsOnly = true) Item item) {
        return original || item == Items.SHEARS && isIn(ConventionalItemTags.SHEAR_TOOLS);
    }
}
