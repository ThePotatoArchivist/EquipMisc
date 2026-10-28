package archives.tater.equipmisc.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.equipment.trim.TrimMaterials;

@Mixin(TrimMaterials.Palette.class)
public enum TrimMaterialsPaletteMixin {
    EQUIPMISC_BRONZE("equipmisc:bronze"),
    EQUIPMISC_BRONZE_DARKER("equipmisc:bronze_darker");

    @Shadow
    TrimMaterialsPaletteMixin(String name) {
    }

    @Definition(id = "suffix", field = "Lnet/minecraft/world/item/equipment/trim/TrimMaterials$Palette;suffix:Ljava/lang/String;")
    @Expression("?.suffix = @(?)")
    @ModifyExpressionValue(
            method = "<init>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private String namespaceSuffix(String original) {
        return original.replace(":", "_");
    }

    @WrapOperation(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/resources/Identifier;withDefaultNamespace(Ljava/lang/String;)Lnet/minecraft/resources/Identifier;")
    )
    private Identifier namespaceId(String path, Operation<Identifier> original, String enumName, int ordinal, String name) {
        return name.contains(":") ? Identifier.parse(name).withPrefix("trim/") : original.call(path);
    }
}
