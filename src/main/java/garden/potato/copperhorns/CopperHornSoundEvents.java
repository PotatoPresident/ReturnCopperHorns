package garden.potato.copperhorns;

import com.google.common.collect.ImmutableList;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

import java.util.stream.IntStream;

public class CopperHornSoundEvents {
    public static final ImmutableList<SoundEvent> COPPER_HORN_BASS_SOUNDS = CopperHornSoundEvents.registerCopperHornSounds(CopperHornSoundType.BASS);
    public static final ImmutableList<SoundEvent> COPPER_HORN_HARMONY_SOUNDS = CopperHornSoundEvents.registerCopperHornSounds(CopperHornSoundType.HARMONY);
    public static final ImmutableList<SoundEvent> COPPER_HORN_MELODY_SOUNDS = CopperHornSoundEvents.registerCopperHornSounds(CopperHornSoundType.MELODY);

    private static ImmutableList<SoundEvent> registerCopperHornSounds(CopperHornSoundType type) {
        return IntStream.range(0, 10).mapToObj(variant ->
                CopperHornSoundEvents.register("item.copper_horn.sound." + type.value + "." + variant)
        ).collect(ImmutableList.toImmutableList());
    }

    private static SoundEvent register(String id) {
        return Registry.register(Registry.SOUND_EVENT, CopperHorns.id(id), new SoundEvent(CopperHorns.id(id)));
    }

    private enum CopperHornSoundType {
        BASS("bass"),
        HARMONY("harmony"),
        MELODY("melody");

        public final String value;

        CopperHornSoundType(String value) {
            this.value = value;
        }
    }
}
