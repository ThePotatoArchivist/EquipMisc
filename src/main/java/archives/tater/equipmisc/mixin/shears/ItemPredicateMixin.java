package archives.tater.equipmisc.mixin.shears;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Items;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Mixin(ItemPredicate.class)
public class ItemPredicateMixin {
    @Shadow
    @Final
    private MinMaxBounds.Ints count;

    @Shadow
    @Final
    private DataComponentMatchers components;

    @Shadow
    @Final
    private Optional<HolderSet<Item>> items;

    @ModifyReturnValue(
            method = "test(Lnet/minecraft/world/item/ItemInstance;)Z",
            at = @At("RETURN")
    )
    private boolean checkOtherShears(boolean original, ItemInstance itemStack) {
        if (original) return true;
        if (!count.isAny() || !components.isEmpty()) return false;
        var items = this.items.orElse(null);
        if (items == null || !items.isBound() || items.size() != 1 || items.get(0).value() != Items.SHEARS) return false;
        return itemStack.is(ConventionalItemTags.SHEAR_TOOLS);
    }
}
