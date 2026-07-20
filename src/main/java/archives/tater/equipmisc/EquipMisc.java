package archives.tater.equipmisc;

import archives.tater.equipmisc.registry.EquipMiscAttributes;
import archives.tater.equipmisc.registry.EquipMiscComponents;
import archives.tater.equipmisc.registry.EquipMiscItems;
import archives.tater.equipmisc.registry.EquipMiscRecipes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquipMisc implements ModInitializer {
	public static final String MOD_ID = "equipmisc";

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final String FARMERS_DELIGHT = "farmersdelight";
    public static final boolean FARMERS_DELIGHT_INSTALLED = FabricLoader.getInstance().isModLoaded(FARMERS_DELIGHT);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        EquipMiscAttributes.init();
        EquipMiscComponents.init();
        EquipMiscItems.init();
        EquipMiscRecipes.init();
        EquipMiscLoot.init();

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!(entity instanceof Turtle turtleEntity) || turtleEntity.isBaby()) return InteractionResult.PASS;
            var stack = player.getItemInHand(hand);
            if (!stack.is(ConventionalItemTags.BRUSH_TOOLS)) return InteractionResult.PASS;
            if (world instanceof ServerLevel serverWorld) {
                turtleEntity.dropFromEntityInteractLootTable(serverWorld, BuiltInLootTables.TURTLE_GROW, player, stack, entity::spawnAtLocation);
                entity.playSound(SoundEvents.ARMADILLO_BRUSH); // TODO custom sound event
                entity.gameEvent(GameEvent.ENTITY_INTERACT);
            }
            stack.hurtAndBreak(24, player, hand);
            return InteractionResult.SUCCESS;
        });
	}
}