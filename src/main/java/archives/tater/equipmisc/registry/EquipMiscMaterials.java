package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAssets;

public interface EquipMiscMaterials {

    ArmorMaterial BRONZE_ARMOR = new ArmorMaterial(
            25,
            ArmorMaterials.IRON.defense(),
            15,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            EquipMiscItemTags.REPAIRS_BRONZE_ARMOR,
            ResourceKey.create(EquipmentAssets.ROOT_ID, EquipMisc.id("bronze"))
    );

    ToolMaterial BRONZE_TOOL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            831,
            6.0F,
            2.0F,
            15,
            EquipMiscItemTags.BRONZE_TOOL_MATERIALS
    );
}
