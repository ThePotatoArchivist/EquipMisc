package archives.tater.equipmisc.mixin.client;

import archives.tater.equipmisc.client.render.item.model.TexturedShieldModelRenderer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.render.item.model.special.ShieldModelRenderer;
import net.minecraft.client.util.SpriteIdentifier;

@Mixin(ShieldModelRenderer.class)
public class ShieldModelRendererMixin {
    @ModifyExpressionValue(
            method = "render(Lnet/minecraft/component/ComponentMap;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;IIZI)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/model/ModelBaker;SHIELD_BASE:Lnet/minecraft/client/util/SpriteIdentifier;")
    )
    private SpriteIdentifier useCustomTexture(SpriteIdentifier original) {
        return (Object) this instanceof TexturedShieldModelRenderer texturedRenderer ? texturedRenderer.baseTexture : original;
    }

    @ModifyExpressionValue(
            method = "render(Lnet/minecraft/component/ComponentMap;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;IIZI)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/model/ModelBaker;SHIELD_BASE_NO_PATTERN:Lnet/minecraft/client/util/SpriteIdentifier;")
    )
    private SpriteIdentifier useCustomTexture2(SpriteIdentifier original) {
        return (Object) this instanceof TexturedShieldModelRenderer texturedRenderer ? texturedRenderer.noPatternBaseTexture : original;
    }
}
