package archives.tater.equipmisc.registry;

import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;

public interface EquipMiscBlockItemTags {
    private static BlockItemTagId createCommon(String path) {
        var id = Identifier.fromNamespaceAndPath(TagUtil.C_TAG_NAMESPACE, path);
        return BlockItemTagId.create(id, id);
    }

    BlockItemTagId STORAGE_BLOCKS_BRONZE = createCommon("storage_blocks/bronze");
}
