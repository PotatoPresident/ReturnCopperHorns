package garden.potato.copperhorns;

import garden.potato.copperhorns.loot.SetCopperHornSoundLootFunction;
import garden.potato.copperhorns.recipe.CopperHornRecipe;
import garden.potato.copperhorns.registry.CopperHornRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class CopperHorns implements ModInitializer {
	public static final String MOD_ID = "copper-horns";
	public static final Item COPPER_HORN = new CopperHornItem(new Item.Settings().maxCount(1), CopperHornInstrumentTags.COPPER_HORNS);
	public static final SpecialRecipeSerializer<CopperHornRecipe> COPPER_HORN_RECIPE = new SpecialRecipeSerializer<>(CopperHornRecipe::new);
	public static final LootFunctionType<SetCopperHornSoundLootFunction> SET_COPPER_HORN_INSTRUMENT = Registry.register(
			Registries.LOOT_FUNCTION_TYPE, id("set_instrument"),
			new LootFunctionType<>(SetCopperHornSoundLootFunction.CODEC)
	);
	public static final ComponentType<RegistryEntry<CopperHornInstrument>> INSTRUMENT_COMPONENT =
			ComponentType.<RegistryEntry<CopperHornInstrument>>builder()
					.codec(CopperHornInstrument.ENTRY_CODEC)
					.packetCodec(CopperHornInstrument.ENTRY_PACKET_CODEC)
					.cache()
					.build();

	@Override
	public void onInitialize() {
		CopperHornInstruments.registerAndGetDefault(CopperHornRegistries.INSTRUMENT);
		Registry.register(Registries.DATA_COMPONENT_TYPE, id("copper_horn_instrument"), INSTRUMENT_COMPONENT);
		Registry.register(Registries.ITEM, id("copper_horn"), COPPER_HORN);
		Registry.register(Registries.RECIPE_SERIALIZER, id("copper_horn_recipe"), COPPER_HORN_RECIPE);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			for (RegistryEntry<CopperHornInstrument> registryEntry : CopperHornRegistries.INSTRUMENT.iterateEntries(CopperHornInstrumentTags.COPPER_HORNS)) {
				entries.addAfter(Items.GOAT_HORN, CopperHornItem.getStackForInstrument(CopperHorns.COPPER_HORN, registryEntry));
			}
		});

		LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
			if (key.equals(LootTables.PILLAGER_OUTPOST_CHEST)) {
				tableBuilder.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.conditionally(RandomChanceLootCondition.builder(0.3f).build())
						.with(ItemEntry.builder(COPPER_HORN))
						.apply(SetCopperHornSoundLootFunction.builder(CopperHornInstrumentTags.REGULAR_COPPER_HORNS))
				);
			}
		});
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
