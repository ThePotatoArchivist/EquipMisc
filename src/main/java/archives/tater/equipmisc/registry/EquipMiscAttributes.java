package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class EquipMiscAttributes {

    private static RegistryEntry<EntityAttribute> register(Identifier id, double fallback, double min, double max, boolean tracked) {
        return Registry.registerReference(Registries.ATTRIBUTE, id, new ClampedEntityAttribute(id.toTranslationKey("attribute.name"), fallback, min, max).setTracked(tracked));
    }

    public static final int TICKS_PER_AIR = 30;

    public static final RegistryEntry<EntityAttribute> MAX_AIR = register(EquipMisc.id("max_air"), 10.0, 0.0, 1024.0, true);

    public static void init() {
        ServerEntityEvents.EQUIPMENT_CHANGE.register((livingEntity, equipmentSlot, itemStack, itemStack1) -> {
            var attributes = itemStack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
            if (attributes == null) return;
            if (livingEntity.getAir() > livingEntity.getMaxAir())
                livingEntity.setAir(livingEntity.getMaxAir());
        });
    }
}
