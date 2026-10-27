package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.minecraft.references.BlockItemId;

public interface EquipMiscBlockItemIds {
    private static BlockItemId create(String path) {
        var id = EquipMisc.id(path);
        return BlockItemId.create(id, id);
    }

    BlockItemId BRONZE_BLOCK = create("bronze_block");
}
