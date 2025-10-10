package archives.tater.equipmisc;

import archives.tater.equipmisc.client.render.item.model.TexturedShieldModelRenderer;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import net.minecraft.util.Identifier;

public class EquipMiscClient implements ClientModInitializer {

    public static final Identifier TURTLE_AIR_TEXTURE = EquipMisc.id("hud/turtle_air");
    public static final Identifier TURTLE_AIR_BURSTING_TEXTURE = EquipMisc.id("hud/turtle_air_bursting");

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
        SpecialModelTypes.ID_MAPPER.put(EquipMisc.id("shield"), TexturedShieldModelRenderer.Unbaked.CODEC);
	}
}