package archives.tater.equipmisc;

import archives.tater.equipmisc.registry.EquipMiscAttributes;
import archives.tater.equipmisc.registry.EquipMiscItems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;

import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.world.event.GameEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquipMisc implements ModInitializer {
	public static final String MOD_ID = "equipmisc";

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        EquipMiscAttributes.init();
        EquipMiscItems.init();

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!(entity instanceof TurtleEntity turtleEntity) || turtleEntity.isBaby()) return ActionResult.PASS;
            var stack = player.getStackInHand(hand);
            if (!stack.isIn(ConventionalItemTags.BRUSH_TOOLS)) return ActionResult.PASS;
            if (!(world instanceof ServerWorld serverWorld)) {
                stack.damage(16, player, hand);
                return ActionResult.SUCCESS;
            }
            turtleEntity.forEachBrushedItem(serverWorld, LootTables.TURTLE_GROW_GAMEPLAY, player, stack, entity::dropStack);
            entity.playSoundIfNotSilent(SoundEvents.ENTITY_ARMADILLO_BRUSH); // TODO custom sound event
            entity.emitGameEvent(GameEvent.ENTITY_INTERACT);

            stack.damage(16, player, hand);
            return ActionResult.SUCCESS;
        });
	}
}