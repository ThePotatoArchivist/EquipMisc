package archives.tater.equipmisc.mixin.client;

import archives.tater.equipmisc.registry.EquipMiscTrimMaterials;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.equipment.trim.TrimMaterials;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

@Mixin(ItemModelGenerators.class)
public class ItemModelGeneratorsMixin {
    @SuppressWarnings("unchecked")
    @Definition(id = "TRIM_MATERIAL_MODELS", field = "Lnet/minecraft/client/data/models/ItemModelGenerators;TRIM_MATERIAL_MODELS:Ljava/util/List;")
    @Expression("TRIM_MATERIAL_MODELS = @(?)")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static <E> List<E> addBronze(@Unmodifiable List<E> original) {
        var builder = ImmutableList.<E>builder();
        builder.addAll(original);
        builder.add((E) new ItemModelGenerators.TrimMaterialData(TrimMaterials.Palette.EQUIPMISC_BRONZE, EquipMiscTrimMaterials.BRONZE));
        return builder.build();
    }
}
