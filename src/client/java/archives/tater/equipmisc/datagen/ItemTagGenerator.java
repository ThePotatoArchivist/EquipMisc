package archives.tater.equipmisc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static archives.tater.equipmisc.registry.EquipMiscItems.*;
import static net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags.*;
import static net.minecraft.registry.tag.ItemTags.*;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {

    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup wrapperLookup) {
        valueLookupBuilder(REPAIRS_BRONZE_ARMOR).add(BRONZE_INGOT);
        valueLookupBuilder(BRONZE_TOOL_MATERIALS).add(BRONZE_INGOT);

        valueLookupBuilder(SWORDS).add(BRONZE_SWORD);
        valueLookupBuilder(SHOVELS).add(BRONZE_SHOVEL);
        valueLookupBuilder(PICKAXES).add(BRONZE_PICKAXE);
        valueLookupBuilder(AXES).add(BRONZE_AXE);
        valueLookupBuilder(HOES).add(BRONZE_HOE);
        valueLookupBuilder(HEAD_ARMOR).add(BRONZE_HELMET);
        valueLookupBuilder(CHEST_ARMOR).add(BRONZE_CHESTPLATE);
        valueLookupBuilder(LEG_ARMOR).add(BRONZE_LEGGINGS);
        valueLookupBuilder(FOOT_ARMOR).add(BRONZE_BOOTS);
        valueLookupBuilder(BEACON_PAYMENT_ITEMS).add(BRONZE_INGOT);

        valueLookupBuilder(CREEPER_IGNITERS).add(FLINT_AND_BRONZE);
        valueLookupBuilder(MINING_ENCHANTABLE).add(BRONZE_SHEARS);
        valueLookupBuilder(DURABILITY_ENCHANTABLE).add(FLINT_AND_BRONZE, BRONZE_SHEARS, BRONZE_SHIELD);
        valueLookupBuilder(CLUSTER_MAX_HARVESTABLES).add(BRONZE_PICKAXE);

        valueLookupBuilder(MELEE_WEAPON_TOOLS).add(BRONZE_SWORD, BRONZE_AXE);
        valueLookupBuilder(MINING_TOOL_TOOLS).add(BRONZE_PICKAXE);
        valueLookupBuilder(SHIELD_TOOLS).add(BRONZE_SHIELD);
        valueLookupBuilder(IGNITER_TOOLS).add(FLINT_AND_BRONZE);
        valueLookupBuilder(SHEAR_TOOLS).add(BRONZE_SHEARS);
        valueLookupBuilder(ARMORS).add(BRONZE_HELMET, BRONZE_CHESTPLATE, BRONZE_LEGGINGS, BRONZE_BOOTS);
        valueLookupBuilder(INGOTS).add(BRONZE_INGOT);
        valueLookupBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of(TagUtil.C_TAG_NAMESPACE, "ingots/bronze"))).add(BRONZE_INGOT);
    }
}
