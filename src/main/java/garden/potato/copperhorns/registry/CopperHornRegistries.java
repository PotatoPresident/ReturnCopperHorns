package garden.potato.copperhorns.registry;

import garden.potato.copperhorns.CopperHornInstrument;
import garden.potato.copperhorns.CopperHorns;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class CopperHornRegistries {
    public static final RegistryKey<Registry<CopperHornInstrument>> INSTRUMENT_KEY = RegistryKey.ofRegistry(CopperHorns.id("instrument"));
    public static final DefaultedRegistry<CopperHornInstrument> INSTRUMENT = FabricRegistryBuilder.createDefaulted(CopperHornInstrument.class, INSTRUMENT_KEY.getValue(), CopperHorns.id("great_copper_horn")).buildAndRegister();
}
