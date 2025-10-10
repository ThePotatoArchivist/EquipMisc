package archives.tater.equipmisc.mixin.client;

import archives.tater.equipmisc.EquipMiscClient;

import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow
    private static int getAirBubbles(int air, int maxAir, int delay) {
        throw new AssertionError();
    }

    @ModifyVariable(
            method = "getAirBubbles",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 1
    )
    private static int renderNormalBubbleRate(int original) {
        return 300; // Scale according to default max air
    }

    @Inject(
            method = "renderAirBubbles",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;getAirBubbleY(II)I")
    )
    private void setMaxBubbles(DrawContext context, PlayerEntity player, int heartCount, int top, int left, CallbackInfo ci, @Share("maxBubbles") LocalIntRef maxBubbles) {
        var maxAir = player.getMaxAir();
        maxBubbles.set(getAirBubbles(maxAir, maxAir, 0));
    }

    @ModifyExpressionValue(
            method = "renderAirBubbles",
            at = @At(value = "CONSTANT", args = "intValue=10")
    )
    private int loopAll(int original, @Share("maxBubbles") LocalIntRef maxBubbles) {
        return maxBubbles.get();
    }

    @Expression("? - 1")
    @ModifyExpressionValue(
            method = "renderAirBubbles",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private int wrapX(int original, @Share("posIndex") LocalIntRef posIndex) {
        posIndex.set(original);
        return original % 10;
    }

    @ModifyArg(
            method = "renderAirBubbles",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 3
    )
    private int shiftRows(int x, @Share("posIndex") LocalIntRef posIndex) {
        return x - 8 * (posIndex.get() / 10);
    }

    @ModifyExpressionValue(
            method = "renderAirBubbles",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;AIR_TEXTURE:Lnet/minecraft/util/Identifier;")
    )
    private Identifier useTurtleTexture1(Identifier original, @Share("posIndex") LocalIntRef posIndex) {
        return posIndex.get() >= 10 ? EquipMiscClient.TURTLE_AIR_TEXTURE : original;
    }

    @ModifyExpressionValue(
            method = "renderAirBubbles",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;AIR_BURSTING_TEXTURE:Lnet/minecraft/util/Identifier;")
    )
    private Identifier useTurtleTexture2(Identifier original, @Share("posIndex") LocalIntRef posIndex) {
        return posIndex.get() >= 10 ? EquipMiscClient.TURTLE_AIR_BURSTING_TEXTURE : original;
    }
}