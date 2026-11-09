package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.registry.EquipMiscSounds;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;

import java.util.concurrent.CompletableFuture;

import static net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder.RegistrationBuilder.ofEvent;

public class SoundsGenerator extends FabricSoundsProvider {
    public SoundsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registryLookup, SoundExporter exporter) {
        exporter.add(EquipMiscSounds.TURTLE_BRUSH, SoundTypeBuilder.of(EquipMiscSounds.TURTLE_BRUSH)
                .sound(ofEvent(SoundEvents.ARMADILLO_BRUSH)));
    }

    @Override
    public String getName() {
        return "Sounds";
    }
}
