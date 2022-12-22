package garden.potato.copperhorns;

import garden.potato.copperhorns.registry.CopperHornRegistries;
import net.minecraft.item.Instruments;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public interface CopperHornInstruments {
    RegistryKey<CopperHornInstrument> GREAT_COPPER_HORN = CopperHornInstruments.of("great_copper_horn");
    RegistryKey<CopperHornInstrument> OLD_COPPER_HORN = CopperHornInstruments.of("old_copper_horn");
    RegistryKey<CopperHornInstrument> PURE_COPPER_HORN = CopperHornInstruments.of("pure_copper_horn");
    RegistryKey<CopperHornInstrument> MUMBLE_COPPER_HORN = CopperHornInstruments.of("mumble_copper_horn");
    RegistryKey<CopperHornInstrument> DRY_COPPER_HORN = CopperHornInstruments.of("dry_copper_horn");
    RegistryKey<CopperHornInstrument> CLEAR_COPPER_HORN = CopperHornInstruments.of("clear_copper_horn");
    RegistryKey<CopperHornInstrument> FRESH_COPPER_HORN = CopperHornInstruments.of("fresh_copper_horn");
    RegistryKey<CopperHornInstrument> SECRET_COPPER_HORN = CopperHornInstruments.of("secret_copper_horn");
    RegistryKey<CopperHornInstrument> FEARLESS_COPPER_HORN = CopperHornInstruments.of("fearless_copper_horn");
    RegistryKey<CopperHornInstrument> SWEET_COPPER_HORN = CopperHornInstruments.of("sweet_copper_horn");

    private static RegistryKey<CopperHornInstrument> of(String id) {
        return RegistryKey.of(CopperHornRegistries.INSTRUMENT_KEY, CopperHorns.id(id));
    }

    static void registerAndGetDefault(Registry<CopperHornInstrument> registry) {
        Registry.register(registry, GREAT_COPPER_HORN, new CopperHornInstrument(
                CopperHornSoundEvents.COPPER_HORN_BASS_SOUNDS.get(0),
                CopperHornSoundEvents.COPPER_HORN_HARMONY_SOUNDS.get(0),
                CopperHornSoundEvents.COPPER_HORN_MELODY_SOUNDS.get(0),
                Instruments.GOAT_HORN_USE_DURATION, Instruments.GOAT_HORN_RANGE
        ));
        Registry.register(registry, OLD_COPPER_HORN, new CopperHornInstrument(
                CopperHornSoundEvents.COPPER_HORN_BASS_SOUNDS.get(1),
                CopperHornSoundEvents.COPPER_HORN_HARMONY_SOUNDS.get(1),
                CopperHornSoundEvents.COPPER_HORN_MELODY_SOUNDS.get(1),
                Instruments.GOAT_HORN_USE_DURATION, Instruments.GOAT_HORN_RANGE
        ));
        Registry.register(registry, PURE_COPPER_HORN, new CopperHornInstrument(
                CopperHornSoundEvents.COPPER_HORN_BASS_SOUNDS.get(2),
                CopperHornSoundEvents.COPPER_HORN_HARMONY_SOUNDS.get(2),
                CopperHornSoundEvents.COPPER_HORN_MELODY_SOUNDS.get(2),
                Instruments.GOAT_HORN_USE_DURATION, Instruments.GOAT_HORN_RANGE
        ));
        Registry.register(registry, MUMBLE_COPPER_HORN, new CopperHornInstrument(
                CopperHornSoundEvents.COPPER_HORN_BASS_SOUNDS.get(3),
                CopperHornSoundEvents.COPPER_HORN_HARMONY_SOUNDS.get(3),
                CopperHornSoundEvents.COPPER_HORN_MELODY_SOUNDS.get(3),
                Instruments.GOAT_HORN_USE_DURATION, Instruments.GOAT_HORN_RANGE
        ));
        Registry.register(registry, DRY_COPPER_HORN, new CopperHornInstrument(
                CopperHornSoundEvents.COPPER_HORN_BASS_SOUNDS.get(4),
                CopperHornSoundEvents.COPPER_HORN_HARMONY_SOUNDS.get(4),
                CopperHornSoundEvents.COPPER_HORN_MELODY_SOUNDS.get(4),
                Instruments.GOAT_HORN_USE_DURATION, Instruments.GOAT_HORN_RANGE
        ));
        Registry.register(registry, CLEAR_COPPER_HORN, new CopperHornInstrument(
                CopperHornSoundEvents.COPPER_HORN_BASS_SOUNDS.get(5),
                CopperHornSoundEvents.COPPER_HORN_HARMONY_SOUNDS.get(5),
                CopperHornSoundEvents.COPPER_HORN_MELODY_SOUNDS.get(5),
                Instruments.GOAT_HORN_USE_DURATION, Instruments.GOAT_HORN_RANGE
        ));
        Registry.register(registry, FRESH_COPPER_HORN, new CopperHornInstrument(
                CopperHornSoundEvents.COPPER_HORN_BASS_SOUNDS.get(6),
                CopperHornSoundEvents.COPPER_HORN_HARMONY_SOUNDS.get(6),
                CopperHornSoundEvents.COPPER_HORN_MELODY_SOUNDS.get(6),
                Instruments.GOAT_HORN_USE_DURATION, Instruments.GOAT_HORN_RANGE
        ));
        Registry.register(registry, SECRET_COPPER_HORN, new CopperHornInstrument(
                CopperHornSoundEvents.COPPER_HORN_BASS_SOUNDS.get(7),
                CopperHornSoundEvents.COPPER_HORN_HARMONY_SOUNDS.get(7),
                CopperHornSoundEvents.COPPER_HORN_MELODY_SOUNDS.get(7),
                Instruments.GOAT_HORN_USE_DURATION, Instruments.GOAT_HORN_RANGE
        ));
        Registry.register(registry, FEARLESS_COPPER_HORN, new CopperHornInstrument(
                CopperHornSoundEvents.COPPER_HORN_BASS_SOUNDS.get(8),
                CopperHornSoundEvents.COPPER_HORN_HARMONY_SOUNDS.get(8),
                CopperHornSoundEvents.COPPER_HORN_MELODY_SOUNDS.get(8),
                Instruments.GOAT_HORN_USE_DURATION, Instruments.GOAT_HORN_RANGE
        ));
        Registry.register(registry, SWEET_COPPER_HORN, new CopperHornInstrument(
                CopperHornSoundEvents.COPPER_HORN_BASS_SOUNDS.get(9),
                CopperHornSoundEvents.COPPER_HORN_HARMONY_SOUNDS.get(9),
                CopperHornSoundEvents.COPPER_HORN_MELODY_SOUNDS.get(9),
                Instruments.GOAT_HORN_USE_DURATION, Instruments.GOAT_HORN_RANGE
        ));
    }
}
