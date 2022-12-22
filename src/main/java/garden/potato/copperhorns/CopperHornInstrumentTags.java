package garden.potato.copperhorns;

import garden.potato.copperhorns.registry.CopperHornRegistries;
import net.minecraft.registry.tag.TagKey;

public interface CopperHornInstrumentTags {
    TagKey<CopperHornInstrument> REGULAR_COPPER_HORNS = CopperHornInstrumentTags.of("regular_copper_horns");
    TagKey<CopperHornInstrument> SCREAMING_COPPER_HORNS = CopperHornInstrumentTags.of("screaming_copper_horns");
    TagKey<CopperHornInstrument> COPPER_HORNS = CopperHornInstrumentTags.of("copper_horns");

    private static TagKey<CopperHornInstrument> of(String id) {
        return TagKey.of(CopperHornRegistries.INSTRUMENT_KEY, CopperHorns.id(id));
    }
}
