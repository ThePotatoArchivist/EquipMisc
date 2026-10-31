package archives.tater.equipmisc.client;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.registry.EquipMiscTrimMaterials;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

import java.util.Map;

import static java.util.Map.entry;
import static net.minecraft.client.data.models.model.ItemModelUtils.*;
import static net.minecraft.util.Util.toMap;

public record AdditionalTrimModel(Map<ResourceKey<TrimMaterial>, Identifier> modelIds, Item item, Identifier prefix, boolean dyed) {

    private static final Map<ResourceKey<TrimMaterial>, String> MATERIALS = Map.of(
            EquipMiscTrimMaterials.BRONZE, "bronze"
    );

    public static final Map<Identifier, AdditionalTrimModel> MODELS = Map.ofEntries(
            createTrimModels(ItemIds.LEATHER_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET, true),
            createTrimModels(ItemIds.CHAINMAIL_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createTrimModels(ItemIds.COPPER_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createTrimModels(ItemIds.IRON_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createTrimModels(ItemIds.GOLDEN_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createTrimModels(ItemIds.DIAMOND_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createTrimModels(ItemIds.NETHERITE_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createTrimModels(ItemIds.TURTLE_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET),
            createTrimModels(ItemIds.LEATHER_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, true),
            createTrimModels(ItemIds.CHAINMAIL_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createTrimModels(ItemIds.COPPER_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createTrimModels(ItemIds.IRON_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createTrimModels(ItemIds.GOLDEN_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createTrimModels(ItemIds.DIAMOND_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createTrimModels(ItemIds.NETHERITE_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE),
            createTrimModels(ItemIds.LEATHER_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, true),
            createTrimModels(ItemIds.CHAINMAIL_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createTrimModels(ItemIds.COPPER_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createTrimModels(ItemIds.IRON_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createTrimModels(ItemIds.GOLDEN_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createTrimModels(ItemIds.DIAMOND_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createTrimModels(ItemIds.NETHERITE_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS),
            createTrimModels(ItemIds.LEATHER_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS, true),
            createTrimModels(ItemIds.CHAINMAIL_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS),
            createTrimModels(ItemIds.COPPER_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS),
            createTrimModels(ItemIds.IRON_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS),
            createTrimModels(ItemIds.GOLDEN_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS),
            createTrimModels(ItemIds.DIAMOND_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS),
            createTrimModels(ItemIds.NETHERITE_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS)
    );

    private static Map.Entry<Identifier, AdditionalTrimModel> createTrimModels(ResourceKey<Item> item, Identifier prefix) {
        return createTrimModels(item, prefix, false);
    }

    private static Map.Entry<Identifier, AdditionalTrimModel> createTrimModels(ResourceKey<Item> item, Identifier prefix, boolean dyed) {
        return entry(item.identifier(), new AdditionalTrimModel(
                MATERIALS.entrySet().stream()
                        .map(entry -> entry(entry.getKey(), EquipMisc.id(item.identifier().getPath() + "_" + entry.getValue() + "_trim")))
                        .collect(toMap()),
                BuiltInRegistries.ITEM.getValueOrThrow(item),
                prefix,
                dyed
        ));
    }

    public static ClientItem modifyModel(Identifier itemId, ClientItem model) {
        var trimModel = AdditionalTrimModel.MODELS.get(itemId);
        if (trimModel == null) return model;
        return new ClientItem(
                select(
                        new TrimMaterialProperty(),
                        model.model(),
                        trimModel.modelIds.entrySet().stream()
                                .map(entry -> when(entry.getKey(), plainModel(entry.getValue())))
                                .toList()
                ),
                model.properties(),
                model.registrySwapper()
        );
    }
}
