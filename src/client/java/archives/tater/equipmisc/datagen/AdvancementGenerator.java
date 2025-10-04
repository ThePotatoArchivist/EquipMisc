package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMisc;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static archives.tater.equipmisc.registry.EquipMiscItems.BRONZE_EQUIPMENT;
import static archives.tater.equipmisc.registry.EquipMiscItems.BRONZE_PICKAXE;
import static net.minecraft.util.Util.createTranslationKey;

public class AdvancementGenerator extends FabricAdvancementProvider {
    public AdvancementGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        var items = wrapperLookup.getOrThrow(RegistryKeys.ITEM);

        consumer.accept(Advancement.Builder.createUntelemetered()
                .parent(new AdvancementEntry(Identifier.ofVanilla("story/iron_tools"), null))
                .display(new AdvancementDisplay(
                        BRONZE_PICKAXE.getDefaultStack(),
                        Text.translatable(BRONZE_TOOLS_TITLE),
                        Text.translatable(BRONZE_TOOLS_DESCRIPTION),
                        Optional.empty(),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                ))
                .criterion("has_bronze_equipment", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().tag(items, BRONZE_EQUIPMENT)))
                .build(BRONZE_TOOLS)
        );
    }

    public static final Identifier BRONZE_TOOLS = EquipMisc.id("bronze_tools");
    public static final String BRONZE_TOOLS_TITLE = createTranslationKey("advancements.story", BRONZE_TOOLS) + ".title";
    public static final String BRONZE_TOOLS_DESCRIPTION = createTranslationKey("advancements.story", BRONZE_TOOLS) + ".description";
}
