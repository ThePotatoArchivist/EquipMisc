package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMiscItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class EquipMiscTagGenerator extends FabricTagProvider.ItemTagProvider {
    public EquipMiscTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(
                EquipMiscItems.REINFORCED_LEATHER_HELMET,
                EquipMiscItems.REINFORCED_LEATHER_CHESTPLATE,
                EquipMiscItems.REINFORCED_LEATHER_LEGGINGS,
                EquipMiscItems.REINFORCED_LEATHER_BOOTS,
                EquipMiscItems.BRONZE_HELMET,
                EquipMiscItems.BRONZE_CHESTPLATE,
                EquipMiscItems.BRONZE_LEGGINGS,
                EquipMiscItems.BRONZE_BOOTS
        );
        getOrCreateTagBuilder(ItemTags.CLUSTER_MAX_HARVESTABLES).add(EquipMiscItems.BRONZE_PICKAXE);
        getOrCreateTagBuilder(ItemTags.PICKAXES).add(EquipMiscItems.BRONZE_PICKAXE);
        getOrCreateTagBuilder(EquipMiscItems.INVISIBLE_ARMOR).add(
                Items.CHAINMAIL_HELMET,
                Items.CHAINMAIL_CHESTPLATE,
                Items.CHAINMAIL_LEGGINGS,
                Items.CHAINMAIL_BOOTS
        );
    }
}
