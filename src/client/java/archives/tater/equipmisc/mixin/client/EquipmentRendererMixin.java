package archives.tater.equipmisc.mixin.client;

import archives.tater.equipmisc.registry.EquipMiscItemTags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo.LayerType;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAsset;

import org.jetbrains.annotations.Nullable;

@Mixin(EquipmentLayerRenderer.class)
public class EquipmentRendererMixin {
    @Inject(
            method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private <S> void invisibleEquipment(LayerType layerType, ResourceKey<EquipmentAsset> equipmentAssetId, Model<? super S> model, S state, ItemStack itemStack, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, @Nullable Identifier playerTextureOverride, int outlineColor, int order, CallbackInfo ci) {
        if (state instanceof LivingEntityRenderState renderState && renderState.isInvisible && itemStack.is(EquipMiscItemTags.ENCHANTED_INVISIBLE_EQUIPMENT) && itemStack.isEnchanted())
            ci.cancel();
    }
}
