package archives.tater.equipmisc.datagen;

import archives.tater.equipmisc.EquipMisc;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static archives.tater.equipmisc.registry.EquipMiscItems.BRONZE_EQUIPMENT;
import static archives.tater.equipmisc.registry.EquipMiscItems.BRONZE_PICKAXE;
import static net.minecraft.util.Util.makeDescriptionId;

public class AdvancementGenerator extends FabricAdvancementProvider {
    public AdvancementGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider wrapperLookup, Consumer<AdvancementHolder> consumer) {
        var items = wrapperLookup.lookupOrThrow(Registries.ITEM);

        consumer.accept(Advancement.Builder.recipeAdvancement()
                .parent(new AdvancementHolder(Identifier.withDefaultNamespace("story/iron_tools"), null))
                .display(new DisplayInfo(
                        BRONZE_PICKAXE.getDefaultInstance(),
                        Component.translatable(BRONZE_TOOLS_TITLE),
                        Component.translatable(BRONZE_TOOLS_DESCRIPTION),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                ))
                .addCriterion("has_bronze_equipment", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, BRONZE_EQUIPMENT)))
                .build(BRONZE_TOOLS)
        );
    }

    public static final Identifier BRONZE_TOOLS = EquipMisc.id("bronze_tools");
    public static final String BRONZE_TOOLS_TITLE = makeDescriptionId("advancements.story", BRONZE_TOOLS) + ".title";
    public static final String BRONZE_TOOLS_DESCRIPTION = makeDescriptionId("advancements.story", BRONZE_TOOLS) + ".description";
}
