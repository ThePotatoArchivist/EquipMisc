package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public interface EquipMiscSounds {
    private static SoundEvent register(String path) {
        return register(EquipMisc.id(path));
    }

    private static SoundEvent register(Identifier id) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    SoundEvent TURTLE_BRUSH = register("entity.turtle.brush");

    static void init() {

    }
}
