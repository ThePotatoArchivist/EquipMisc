package archives.tater.equipmisc.mixin.client;

import archives.tater.equipmisc.client.render.item.model.TexturedShieldModelRenderer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.client.resources.model.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShieldSpecialRenderer.class)
public class ShieldModelRendererMixin {
    @ModifyExpressionValue(
            method = "submit(Lnet/minecraft/core/component/DataComponentMap;Lnet/minecraft/world/item/ItemDisplayContext;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/resources/model/ModelBakery;SHIELD_BASE:Lnet/minecraft/client/resources/model/Material;")
    )
    private Material useCustomTexture(Material original) {
        return (Object) this instanceof TexturedShieldModelRenderer texturedRenderer ? texturedRenderer.baseTexture : original;
    }

    @ModifyExpressionValue(
            method = "submit(Lnet/minecraft/core/component/DataComponentMap;Lnet/minecraft/world/item/ItemDisplayContext;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/resources/model/ModelBakery;NO_PATTERN_SHIELD:Lnet/minecraft/client/resources/model/Material;")
    )
    private Material useCustomTexture2(Material original) {
        return (Object) this instanceof TexturedShieldModelRenderer texturedRenderer ? texturedRenderer.noPatternBaseTexture : original;
    }
}
