package archives.tater.equipmisc.mixin.client;

import archives.tater.equipmisc.client.render.item.model.TexturedShieldModelRenderer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import org.objectweb.asm.Opcodes;

import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.client.resources.model.sprite.SpriteId;

@Mixin(ShieldSpecialRenderer.class)
public class ShieldModelRendererMixin {
    @ModifyExpressionValue(
            method = "submit(Lnet/minecraft/core/component/DataComponentMap;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/Sheets;SHIELD_BASE:Lnet/minecraft/client/resources/model/sprite/SpriteId;", opcode = Opcodes.GETSTATIC)
    )
    private SpriteId useCustomTexture(SpriteId original) {
        return (Object) this instanceof TexturedShieldModelRenderer texturedRenderer ? texturedRenderer.baseTexture : original;
    }

    @ModifyExpressionValue(
            method = "submit(Lnet/minecraft/core/component/DataComponentMap;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/Sheets;SHIELD_BASE_NO_PATTERN:Lnet/minecraft/client/resources/model/sprite/SpriteId;", opcode = Opcodes.GETSTATIC)
    )
    private SpriteId useCustomTexture2(SpriteId original) {
        return (Object) this instanceof TexturedShieldModelRenderer texturedRenderer ? texturedRenderer.noPatternBaseTexture : original;
    }
}
