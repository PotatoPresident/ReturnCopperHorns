package garden.potato.copperhorns.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CopperHornsDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(CopperHornsInstrumentTagProvider::new);
        fabricDataGenerator.addProvider(CopperHornRecipeProvider::new);
    }
}
