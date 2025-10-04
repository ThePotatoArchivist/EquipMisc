package archives.tater.equipmisc;

import archives.tater.equipmisc.client.render.item.model.TexturedShieldModelRenderer;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.render.item.model.special.SpecialModelTypes;

public class EquipMiscClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
        SpecialModelTypes.ID_MAPPER.put(EquipMisc.id("shield"), TexturedShieldModelRenderer.Unbaked.CODEC);
	}
}