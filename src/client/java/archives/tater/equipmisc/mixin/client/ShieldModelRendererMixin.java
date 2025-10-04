package archives.tater.equipmisc.mixin.client;

import archives.tater.equipmisc.client.render.item.model.TexturedShieldModelRenderer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.render.item.model.special.ShieldModelRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.ItemDisplayContext;

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

    @ModifyVariable(
            method = "render(Lnet/minecraft/component/ComponentMap;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;IIZI)V",
            at = @At("HEAD"),
            argsOnly = true
    )
    private boolean noGlint(boolean glint, @Local(argsOnly = true)ItemDisplayContext displayContext) {
        return glint && displayContext == ItemDisplayContext.GUI;
    }
}
