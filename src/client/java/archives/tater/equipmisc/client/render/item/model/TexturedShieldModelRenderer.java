package archives.tater.equipmisc.client.render.item.model;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ShieldModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;

/**
 * @see archives.tater.equipmisc.mixin.client.ShieldModelRendererMixin
 */
public class TexturedShieldModelRenderer extends ShieldSpecialRenderer {
    public final Material baseTexture;
    public final Material noPatternBaseTexture;

    public TexturedShieldModelRenderer(MaterialSet spriteHolder, ShieldModel model, Identifier baseTexture, Identifier noPatternBaseTexture) {
        super(spriteHolder, model);
        this.baseTexture = new Material(Sheets.SHIELD_SHEET, baseTexture);
        this.noPatternBaseTexture = new Material(Sheets.SHIELD_SHEET, noPatternBaseTexture);
    }

    public record Unbaked(Identifier baseTexture, Identifier noPatternBaseTexture) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<TexturedShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Identifier.CODEC.fieldOf("base_texture").forGetter(TexturedShieldModelRenderer.Unbaked::baseTexture),
                Identifier.CODEC.fieldOf("no_pattern_base_texture").forGetter(TexturedShieldModelRenderer.Unbaked::noPatternBaseTexture)
        ).apply(instance, TexturedShieldModelRenderer.Unbaked::new));

        @Override
        public SpecialModelRenderer<?> bake(BakingContext context) {
            return new TexturedShieldModelRenderer(context.materials(), new ShieldModel(context.entityModelSet().bakeLayer(ModelLayers.SHIELD)), baseTexture, noPatternBaseTexture);
        }

        @Override
        public MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
            return CODEC;
        }
    }
}
