package garden.potato.copperhorns;

import garden.potato.copperhorns.loot.SetCopperHornSoundLootFunction;
import garden.potato.copperhorns.recipe.CopperHornRecipe;
import garden.potato.copperhorns.registry.CopperHornRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CopperHorns implements ModInitializer {
    public static final String MOD_ID = "copper-horns";
    public static final Item COPPER_HORN = new CopperHornItem(new Item.Settings().group(ItemGroup.MISC).maxCount(1), CopperHornInstrumentTags.COPPER_HORNS);
    public static final SpecialRecipeSerializer<CopperHornRecipe> COPPER_HORN_RECIPE = new SpecialRecipeSerializer<>(CopperHornRecipe::new);
    public static final LootFunctionType SET_COPPER_HORN_INSTRUMENT = Registry.register(Registry.LOOT_FUNCTION_TYPE, id("set_instrument"), new LootFunctionType(new SetCopperHornSoundLootFunction.Serializer()));

    @Override
    public void onInitialize() {
        CopperHornInstruments.registerAndGetDefault(CopperHornRegistries.INSTRUMENT);
        Registry.register(Registry.ITEM, id("copper_horn"), COPPER_HORN);
        Registry.register(Registry.RECIPE_SERIALIZER, id("copper_horn_recipe"), COPPER_HORN_RECIPE);

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(LootTables.PILLAGER_OUTPOST_CHEST)) {
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
        return new Identifier(MOD_ID, path);
    }
}
