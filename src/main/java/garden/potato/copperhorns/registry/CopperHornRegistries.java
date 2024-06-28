package garden.potato.copperhorns.registry;

import garden.potato.copperhorns.CopperHornInstrument;
import garden.potato.copperhorns.CopperHorns;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class CopperHornRegistries {
    public static final RegistryKey<Registry<CopperHornInstrument>> INSTRUMENT_KEY = RegistryKey.ofRegistry(CopperHorns.id("copper_horn_instrument"));
    public static final DefaultedRegistry<CopperHornInstrument> INSTRUMENT = FabricRegistryBuilder.createDefaulted(INSTRUMENT_KEY, CopperHorns.id("great_copper_horn")).buildAndRegister();
}
