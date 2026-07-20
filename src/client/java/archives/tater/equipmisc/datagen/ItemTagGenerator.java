package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.registry.EquipMiscItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

import static archives.tater.equipmisc.registry.EquipMiscItemIds.*;
import static net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags.*;
import static net.minecraft.tags.ItemTags.*;

public class ItemTagGenerator extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagGenerator(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(Provider wrapperLookup) {
        builder(EquipMiscItemTags.REPAIRS_BRONZE_ARMOR).add(BRONZE_INGOT);
        builder(EquipMiscItemTags.BRONZE_TOOL_MATERIALS).add(BRONZE_INGOT);
        builder(EquipMiscItemTags.BRONZE_EQUIPMENT).add(
                BRONZE_HELMET,
                BRONZE_CHESTPLATE,
                BRONZE_LEGGINGS,
                BRONZE_BOOTS,
                BRONZE_SWORD,
                BRONZE_SHOVEL,
                BRONZE_PICKAXE,
                BRONZE_AXE,
                BRONZE_HOE,
                BRONZE_SPEAR,
                BRONZE_SHIELD,
                BRONZE_SHEARS,
                FLINT_AND_BRONZE,
                BRONZE_KNIFE
        );
        builder(EquipMiscItemTags.ENCHANTED_INVISIBLE_EQUIPMENT).add(
                ItemIds.CHAINMAIL_HELMET,
                ItemIds.CHAINMAIL_CHESTPLATE,
                ItemIds.CHAINMAIL_LEGGINGS,
                ItemIds.CHAINMAIL_BOOTS
        );

        builder(SWORDS).add(BRONZE_SWORD);
        builder(SHOVELS).add(BRONZE_SHOVEL);
        builder(PICKAXES).add(BRONZE_PICKAXE);
        builder(AXES).add(BRONZE_AXE);
        builder(HOES).add(BRONZE_HOE);
        builder(SPEARS).add(BRONZE_SPEAR);
        builder(HEAD_ARMOR).add(BRONZE_HELMET);
        builder(CHEST_ARMOR).add(BRONZE_CHESTPLATE);
        builder(LEG_ARMOR).add(BRONZE_LEGGINGS);
        builder(FOOT_ARMOR).add(BRONZE_BOOTS);
        builder(BEACON_PAYMENT_ITEMS).add(BRONZE_INGOT);

        builder(CREEPER_IGNITERS).add(FLINT_AND_BRONZE);
        builder(MINING_ENCHANTABLE).add(BRONZE_SHEARS);
        builder(DURABILITY_ENCHANTABLE).add(FLINT_AND_BRONZE, BRONZE_SHEARS, BRONZE_SHIELD);
        builder(CLUSTER_MAX_HARVESTABLES).add(BRONZE_PICKAXE);

        builder(MELEE_WEAPON_TOOLS).add(BRONZE_SWORD, BRONZE_AXE);
        builder(MINING_TOOL_TOOLS).add(BRONZE_PICKAXE);
        builder(SHIELD_TOOLS).add(BRONZE_SHIELD);
        builder(IGNITER_TOOLS).add(FLINT_AND_BRONZE);
        builder(SHEAR_TOOLS).add(BRONZE_SHEARS);
        builder(commonTag("tools/knife")).add(BRONZE_KNIFE);
        builder(ARMORS).add(BRONZE_HELMET, BRONZE_CHESTPLATE, BRONZE_LEGGINGS, BRONZE_BOOTS);
        builder(INGOTS).add(BRONZE_INGOT);
        builder(RAW_MATERIALS).add(RAW_BRONZE);
        builder(commonTag("ingots/bronze")).add(BRONZE_INGOT);
        builder(commonTag("raw_materials/bronze")).add(RAW_BRONZE);

        builder(ModTags.Items.KNIVES).add(BRONZE_KNIFE);
    }

    private static TagKey<Item> commonTag(String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TagUtil.C_TAG_NAMESPACE, path));
    }
}
