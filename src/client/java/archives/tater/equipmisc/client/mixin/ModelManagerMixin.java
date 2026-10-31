package archives.tater.equipmisc.client.mixin;

import archives.tater.equipmisc.client.AdditionalTrimModel;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.resources.model.ClientItemInfoLoader;
import net.minecraft.client.resources.model.ModelManager;

import java.util.concurrent.CompletableFuture;

import static java.util.Map.entry;
import static net.minecraft.util.Util.toMap;

@Mixin(ModelManager.class)
public class ModelManagerMixin {
    @ModifyExpressionValue(
            method = "reload",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/ClientItemInfoLoader;scheduleLoad(Lnet/minecraft/server/packs/resources/ResourceManager;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;")
    )
    private CompletableFuture<ClientItemInfoLoader.LoadedClientInfos> modifyModelsOnLoad(CompletableFuture<ClientItemInfoLoader.LoadedClientInfos> original) {
        return original.thenApply(loadedClientInfos -> new ClientItemInfoLoader.LoadedClientInfos(loadedClientInfos.contents().entrySet().stream()
                .map(entry -> entry(entry.getKey(), AdditionalTrimModel.modifyModel(entry.getKey(), entry.getValue())))
                .collect(toMap())
        ));
    }
}
