package archives.tater.equipmisc.mixin.client;

import archives.tater.equipmisc.EquipMiscClient;

import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class InGameHudMixin {

    @Shadow
    private static int getCurrentAirSupplyBubble(int air, int maxAir, int delay) {
        throw new AssertionError();
    }

    @ModifyVariable(
            method = "getCurrentAirSupplyBubble",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 1
    )
    private static int renderNormalBubbleRate(int original) {
        return 300; // Scale according to default max air
    }

    @Inject(
            method = "renderAirBubbles",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;getAirBubbleYLine(II)I")
    )
    private void setMaxBubbles(GuiGraphics context, Player player, int heartCount, int top, int left, CallbackInfo ci, @Share("maxBubbles") LocalIntRef maxBubbles) {
        var maxAir = player.getMaxAirSupply();
        maxBubbles.set(getCurrentAirSupplyBubble(maxAir, maxAir, 0));
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
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"),
            index = 3
    )
    private int shiftRows(int x, @Share("posIndex") LocalIntRef posIndex) {
        return x - 8 * (posIndex.get() / 10);
    }

    @ModifyExpressionValue(
            method = "renderAirBubbles",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/Gui;AIR_SPRITE:Lnet/minecraft/resources/Identifier;")
    )
    private Identifier useTurtleTexture1(Identifier original, @Share("posIndex") LocalIntRef posIndex) {
        return posIndex.get() >= 10 ? EquipMiscClient.TURTLE_AIR_TEXTURE : original;
    }

    @ModifyExpressionValue(
            method = "renderAirBubbles",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/Gui;AIR_POPPING_SPRITE:Lnet/minecraft/resources/Identifier;")
    )
    private Identifier useTurtleTexture2(Identifier original, @Share("posIndex") LocalIntRef posIndex) {
        return posIndex.get() >= 10 ? EquipMiscClient.TURTLE_AIR_BURSTING_TEXTURE : original;
    }
}