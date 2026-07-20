package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class EquipMiscBlockTags {

    private static TagKey<Block> create(String path) {
        return TagKey.create(Registries.BLOCK, EquipMisc.id(path));
    }

    public static final TagKey<Block> RAVAGER_HELMET_CAN_DESTROY = create("ravager_helmet_can_destroy");
}
