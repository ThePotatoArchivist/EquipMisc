package archives.tater.equipmisc.mixin.client;

import archives.tater.equipmisc.EquipMiscItems;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin {
	@Inject(
			method = "renderArmor",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;copyBipedStateTo(Lnet/minecraft/client/render/entity/model/BipedEntityModel;)V"),
			cancellable = true)
	private <T extends LivingEntity, A extends BipedEntityModel<T>> void init(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model, CallbackInfo ci, @Local ItemStack itemStack) {
		if (entity.isInvisible() && itemStack.isIn(EquipMiscItems.INVISIBLE_ARMOR)) ci.cancel();
	}
}
