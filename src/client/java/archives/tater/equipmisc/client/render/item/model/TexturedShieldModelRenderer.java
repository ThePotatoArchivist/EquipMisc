package archives.tater.equipmisc.client.render.item.model;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.model.special.ShieldModelRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.texture.SpriteHolder;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

/**
 * @see archives.tater.equipmisc.mixin.client.ShieldModelRendererMixin
 */
public class TexturedShieldModelRenderer extends ShieldModelRenderer {
    public final SpriteIdentifier baseTexture;
    public final SpriteIdentifier noPatternBaseTexture;

    public TexturedShieldModelRenderer(SpriteHolder spriteHolder, ShieldEntityModel model, Identifier baseTexture, Identifier noPatternBaseTexture) {
        super(spriteHolder, model);
        this.baseTexture = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, baseTexture);
        this.noPatternBaseTexture = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, noPatternBaseTexture);
    }

    public record Unbaked(Identifier baseTexture, Identifier noPatternBaseTexture) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Identifier.CODEC.fieldOf("base_texture").forGetter(Unbaked::baseTexture),
                Identifier.CODEC.fieldOf("no_pattern_base_texture").forGetter(Unbaked::noPatternBaseTexture)
        ).apply(instance, Unbaked::new));

        @Override
        public SpecialModelRenderer<?> bake(BakeContext context) {
            return new TexturedShieldModelRenderer(context.spriteHolder(), new ShieldEntityModel(context.entityModelSet().getModelPart(EntityModelLayers.SHIELD)), baseTexture, noPatternBaseTexture);
        }

        @Override
        public MapCodec<? extends SpecialModelRenderer.Unbaked> getCodec() {
            return CODEC;
        }
    }
}
