package archives.tater.equipmisc;

import archives.tater.equipmisc.client.render.item.model.TexturedShieldModelRenderer;
import archives.tater.equipmisc.registry.EquipMiscComponents;
import archives.tater.equipmisc.registry.EquipMiscTrimMaterials;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.Map;

import static net.minecraft.client.data.models.model.ItemModelUtils.*;

public class EquipMiscClient implements ClientModInitializer {

    public static final Identifier TURTLE_AIR_TEXTURE = EquipMisc.id("hud/turtle_air");
    public static final Identifier TURTLE_AIR_BURSTING_TEXTURE = EquipMisc.id("hud/turtle_air_bursting");

    public static final Map<Identifier, AdditionalTrimModel> BRONZE_TRIM_MODELS = Map.ofEntries(
            createBronzeTrimModel(ItemIds.LEATHER_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET, true),
            createBronzeTrimModel(ItemIds.CHAINMAIL_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createBronzeTrimModel(ItemIds.COPPER_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createBronzeTrimModel(ItemIds.IRON_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createBronzeTrimModel(ItemIds.GOLDEN_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createBronzeTrimModel(ItemIds.DIAMOND_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createBronzeTrimModel(ItemIds.NETHERITE_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createBronzeTrimModel(ItemIds.TURTLE_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createBronzeTrimModel(ItemIds.LEATHER_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, true),
            createBronzeTrimModel(ItemIds.CHAINMAIL_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createBronzeTrimModel(ItemIds.COPPER_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createBronzeTrimModel(ItemIds.IRON_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createBronzeTrimModel(ItemIds.GOLDEN_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createBronzeTrimModel(ItemIds.DIAMOND_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createBronzeTrimModel(ItemIds.NETHERITE_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createBronzeTrimModel(ItemIds.LEATHER_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, true),
            createBronzeTrimModel(ItemIds.CHAINMAIL_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createBronzeTrimModel(ItemIds.COPPER_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createBronzeTrimModel(ItemIds.IRON_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createBronzeTrimModel(ItemIds.GOLDEN_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createBronzeTrimModel(ItemIds.DIAMOND_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createBronzeTrimModel(ItemIds.NETHERITE_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createBronzeTrimModel(ItemIds.LEATHER_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS, true),
            createBronzeTrimModel(ItemIds.CHAINMAIL_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS),
            createBronzeTrimModel(ItemIds.COPPER_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS),
            createBronzeTrimModel(ItemIds.IRON_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS),
            createBronzeTrimModel(ItemIds.GOLDEN_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS),
            createBronzeTrimModel(ItemIds.DIAMOND_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS),
            createBronzeTrimModel(ItemIds.NETHERITE_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS)
    );

    private static Map.Entry<Identifier, AdditionalTrimModel> createBronzeTrimModel(ResourceKey<Item> item, Identifier prefix) {
        return createBronzeTrimModel(item, prefix, false);
    }

    private static Map.Entry<Identifier, AdditionalTrimModel> createBronzeTrimModel(ResourceKey<Item> item, Identifier prefix, boolean dyed) {
        return Map.entry(item.identifier(), new AdditionalTrimModel(
                EquipMisc.id(item.identifier().getPath() + "_bronze_trim"),
                BuiltInRegistries.ITEM.getValueOrThrow(item),
                prefix,
                dyed
        ));
    }

    public record AdditionalTrimModel(Identifier modelId, Item item, Identifier prefix, boolean dyed) {}

    @Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
        SpecialModelRenderers.ID_MAPPER.put(EquipMisc.id("shield"), TexturedShieldModelRenderer.Unbaked.CODEC);
        ItemComponentTooltipProviderRegistry.addBefore(DataComponents.TRIM, EquipMiscComponents.CHAINMAIL_UPGRADE);

        ModelLoadingPlugin.register(pluginContext -> {
            pluginContext.modifyItemModelBeforeBake().register((model, context) -> {
                var trimModel = BRONZE_TRIM_MODELS.get(context.itemId());
                if (trimModel == null) return model;
                return select(
                        new TrimMaterialProperty(),
                        model,
                        when(EquipMiscTrimMaterials.BRONZE, plainModel(trimModel.modelId))
                );
            });
        });
	}
}