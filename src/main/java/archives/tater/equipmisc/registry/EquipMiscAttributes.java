package archives.tater.equipmisc.registry;

import archives.tater.equipmisc.EquipMisc;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import org.jspecify.annotations.Nullable;

public interface EquipMiscAttributes {

    private static Holder<Attribute> register(Identifier id, double fallback, double min, double max, boolean tracked) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, id, new RangedAttribute(id.toLanguageKey("attribute.name"), fallback, min, max).setSyncable(tracked));
    }

    int DEFAULT_AIR_TICKS = 300;
    int TICKS_PER_AIR = 30;

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    Holder<Attribute> MAX_AIR = register(EquipMisc.id("max_air"), DEFAULT_AIR_TICKS / TICKS_PER_AIR, 0.0, 1024.0, true);

    private static boolean modifiesAir(@Nullable ItemAttributeModifiers attributes) {
        return attributes != null && attributes.modifiers().stream().anyMatch(entry -> entry.attribute() == MAX_AIR);
    }

    static void init() {
        ServerEntityEvents.EQUIPMENT_CHANGE.register((livingEntity, _, previousStack, currentStack) -> {
            if (!modifiesAir(previousStack.get(DataComponents.ATTRIBUTE_MODIFIERS)) && !modifiesAir(currentStack.get(DataComponents.ATTRIBUTE_MODIFIERS))) return;

            if (livingEntity.getAirSupply() > livingEntity.getMaxAirSupply())
                livingEntity.setAirSupply(livingEntity.getMaxAirSupply());
        });

        FabricDefaultAttributeRegistry.MODIFY.register(context -> {
            context.modify(_ -> true, (_, builder) ->
                    builder.add(MAX_AIR)
            );
        });
    }
}
