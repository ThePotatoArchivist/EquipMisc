package archives.tater.equipmisc;

import archives.tater.equipmisc.datagen.ItemTagGenerator;
import archives.tater.equipmisc.datagen.LangGenerator;
import archives.tater.equipmisc.datagen.ModelGenerator;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EquipMiscDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();
        pack.addProvider(ItemTagGenerator::new);
        pack.addProvider(ModelGenerator::new);
        pack.addProvider(LangGenerator::new);
	}
}
