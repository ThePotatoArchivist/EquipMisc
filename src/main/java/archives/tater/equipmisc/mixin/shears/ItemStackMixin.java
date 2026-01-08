package archives.tater.equipmisc.mixin.shears;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract boolean is(TagKey<Item> tag);

    @ModifyReturnValue(
            method = "is(Lnet/minecraft/world/item/Item;)Z",
            at = @At("RETURN")
    )
    private boolean shearCheck(boolean original, @Local(argsOnly = true) Item item) {
        return original || item == Items.SHEARS && is(ConventionalItemTags.SHEAR_TOOLS);
    }

    @ModifyReturnValue(
            method = "is(Lnet/minecraft/core/HolderSet;)Z",
            at = @At("RETURN")
    )
    private boolean shearCheck(boolean original, @Local(argsOnly = true) HolderSet<Item> list) {
        return original || list.isBound() && list.size() == 1 && list.get(0).value() == Items.SHEARS && this.is(ConventionalItemTags.SHEAR_TOOLS);
    }
}
