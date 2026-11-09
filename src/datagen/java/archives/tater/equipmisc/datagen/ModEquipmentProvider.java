package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMisc;
import archives.tater.equipmisc.registry.EquipMiscMaterials;
import archives.tater.equipmisc.registry.EquipMiscTrimMaterials;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.equipment.trim.TrimMaterials;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentProvider extends FabricCodecDataProvider<EquipmentClientInfo> {
    protected ModEquipmentProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture, PackOutput.Target.RESOURCE_PACK, "equipment", EquipmentClientInfo.CODEC);
    }

    @Override
    protected void configure(BiConsumer<Identifier, EquipmentClientInfo> provider, HolderLookup.Provider registryLookup) {
        provider.accept(
                EquipMiscMaterials.BRONZE_ARMOR.assetId().identifier(), EquipmentClientInfo.builder()
                .addHumanoidLayers(EquipMisc.id("bronze"))
                .replaceTrimPalette(EquipMiscTrimMaterials.BRONZE, TrimMaterials.Palette.EQUIPMISC_BRONZE_DARKER.id())
                .build());
    }

    @Override
    public String getName() {
        return "Equipment Assets";
    }
}
