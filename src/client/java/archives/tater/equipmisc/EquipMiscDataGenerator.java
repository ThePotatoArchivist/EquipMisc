package archives.tater.equipmisc;

import archives.tater.equipmisc.datagen.EquipMiscLangGenerator;
import archives.tater.equipmisc.datagen.EquipMiscModelGenerator;
import archives.tater.equipmisc.datagen.EquipMiscRecipeGenerator;
import archives.tater.equipmisc.datagen.EquipMiscTagGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EquipMiscDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(EquipMiscLangGenerator::new);
		pack.addProvider(EquipMiscModelGenerator::new);
		pack.addProvider(EquipMiscRecipeGenerator::new);
		pack.addProvider(EquipMiscTagGenerator::new);
	}
}
