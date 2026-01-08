package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class EquipMiscAttributes {

    private static Holder<Attribute> register(Identifier id, double fallback, double min, double max, boolean tracked) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, id, new RangedAttribute(id.toLanguageKey("attribute.name"), fallback, min, max).setSyncable(tracked));
    }

    public static final int TICKS_PER_AIR = 30;

    public static final Holder<Attribute> MAX_AIR = register(EquipMisc.id("max_air"), 10.0, 0.0, 1024.0, true);

    public static void init() {
        ServerEntityEvents.EQUIPMENT_CHANGE.register((livingEntity, equipmentSlot, itemStack, itemStack1) -> {
            var attributes = itemStack.get(DataComponents.ATTRIBUTE_MODIFIERS);
            if (attributes == null) return;
            if (livingEntity.getAirSupply() > livingEntity.getMaxAirSupply())
                livingEntity.setAirSupply(livingEntity.getMaxAirSupply());
        });
    }
}
