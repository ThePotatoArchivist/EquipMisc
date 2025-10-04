package archives.tater.equipmisc.mixin;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.entry.RegistryEntryList;

// This is really cursed I'm sorry

/**
 * Modifies the ItemPredicate codec so that for the items list, a single list containing shears is replaced with a list of {@code #c:tools/shear}
 */
@Mixin(ItemPredicate.class)
public class ItemPredicateMixin {
    @ModifyExpressionValue(
            method = "method_57298",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/RegistryCodecs;entryList(Lnet/minecraft/registry/RegistryKey;)Lcom/mojang/serialization/Codec;")
    )
    private static Codec<RegistryEntryList<Item>> modifyShearsPredicate(Codec<RegistryEntryList<Item>> original) {
        return new Codec<>() {
            @Override
            public <T> DataResult<Pair<RegistryEntryList<Item>, T>> decode(DynamicOps<T> ops, T input) {
                var originalResult = original.decode(ops, input);
                if (!(ops instanceof RegistryOps<T> registryOps)) return originalResult;

                return originalResult.map(pair -> pair.mapFirst(list -> {
                    if (!list.isBound() || list.size() != 1 || list.get(0).value() != Items.SHEARS) return list;
                    return registryOps.getEntryLookup(RegistryKeys.ITEM)
                            .<RegistryEntryList<Item>>flatMap(it -> it.getOptional(ConventionalItemTags.SHEAR_TOOLS))
                            .orElse(list);
                }));
            }

            @Override
            public <T> DataResult<T> encode(RegistryEntryList<Item> input, DynamicOps<T> ops, T prefix) {
                return original.encode(input, ops, prefix);
            }
        };
    }
}
