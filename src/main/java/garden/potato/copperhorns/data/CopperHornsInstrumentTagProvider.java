package garden.potato.copperhorns.data;

import garden.potato.copperhorns.CopperHornInstrument;
import garden.potato.copperhorns.CopperHornInstrumentTags;
import garden.potato.copperhorns.CopperHornInstruments;
import garden.potato.copperhorns.registry.CopperHornRegistries;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class CopperHornsInstrumentTagProvider extends FabricTagProvider<CopperHornInstrument> {
    public CopperHornsInstrumentTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator, CopperHornRegistries.INSTRUMENT);
    }

    @Override
    protected void generateTags() {
        this.getOrCreateTagBuilder(CopperHornInstrumentTags.REGULAR_COPPER_HORNS)
                .add(CopperHornInstruments.GREAT_COPPER_HORN)
                .add(CopperHornInstruments.OLD_COPPER_HORN)
                .add(CopperHornInstruments.PURE_COPPER_HORN)
                .add(CopperHornInstruments.DRY_COPPER_HORN)
                .add(CopperHornInstruments.MUMBLE_COPPER_HORN)
                .add(CopperHornInstruments.CLEAR_COPPER_HORN);

        this.getOrCreateTagBuilder(CopperHornInstrumentTags.SCREAMING_COPPER_HORNS)
                .add(CopperHornInstruments.SWEET_COPPER_HORN)
                .add(CopperHornInstruments.FEARLESS_COPPER_HORN)
                .add(CopperHornInstruments.SECRET_COPPER_HORN)
                .add(CopperHornInstruments.FRESH_COPPER_HORN);

        this.getOrCreateTagBuilder(CopperHornInstrumentTags.COPPER_HORNS)
                .addTag(CopperHornInstrumentTags.REGULAR_COPPER_HORNS)
                .addTag(CopperHornInstrumentTags.SCREAMING_COPPER_HORNS);
    }
}
