package garden.potato.copperhorns;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import garden.potato.copperhorns.registry.CopperHornRegistries;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.dynamic.Codecs;

public record CopperHornInstrument(RegistryEntry<SoundEvent> bassSoundEvent, RegistryEntry<SoundEvent> harmonySoundEvent, RegistryEntry<SoundEvent> melodySoundEvent, int useDuration, float range) {
    public static final Codec<CopperHornInstrument> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            SoundEvent.ENTRY_CODEC.fieldOf("bass_sound_event").forGetter(CopperHornInstrument::bassSoundEvent),
                            SoundEvent.ENTRY_CODEC.fieldOf("harmony_sound_event").forGetter(CopperHornInstrument::harmonySoundEvent),
                            SoundEvent.ENTRY_CODEC.fieldOf("melody_sound_event").forGetter(CopperHornInstrument::melodySoundEvent),
                            Codecs.POSITIVE_INT.fieldOf("use_duration").forGetter(CopperHornInstrument::useDuration),
                            Codecs.POSITIVE_FLOAT.fieldOf("range").forGetter(CopperHornInstrument::range)
                    )
                    .apply(instance, CopperHornInstrument::new)
    );
    public static final PacketCodec<RegistryByteBuf, CopperHornInstrument> PACKET_CODEC = PacketCodec.tuple(
            SoundEvent.ENTRY_PACKET_CODEC, CopperHornInstrument::bassSoundEvent,
            SoundEvent.ENTRY_PACKET_CODEC, CopperHornInstrument::harmonySoundEvent,
            SoundEvent.ENTRY_PACKET_CODEC, CopperHornInstrument::melodySoundEvent,
            PacketCodecs.VAR_INT, CopperHornInstrument::useDuration,
            PacketCodecs.FLOAT, CopperHornInstrument::range,
            CopperHornInstrument::new
    );
    public static final Codec<RegistryEntry<CopperHornInstrument>> ENTRY_CODEC = RegistryElementCodec.of(CopperHornRegistries.INSTRUMENT_KEY, CODEC);
    public static final PacketCodec<RegistryByteBuf, RegistryEntry<CopperHornInstrument>> ENTRY_PACKET_CODEC = PacketCodecs.registryEntry(
            CopperHornRegistries.INSTRUMENT_KEY, PACKET_CODEC
    );
}
