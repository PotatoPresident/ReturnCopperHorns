package garden.potato.copperhorns.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import garden.potato.copperhorns.CopperHornInstrument;
import garden.potato.copperhorns.CopperHornItem;
import garden.potato.copperhorns.CopperHorns;
import garden.potato.copperhorns.registry.CopperHornRegistries;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.tag.TagKey;

import java.util.List;

public class SetCopperHornSoundLootFunction extends ConditionalLootFunction {
    public static final MapCodec<SetCopperHornSoundLootFunction> CODEC = RecordCodecBuilder.mapCodec(
            instance -> addConditionsField(instance)
                    .and(TagKey.codec(CopperHornRegistries.INSTRUMENT_KEY).fieldOf("options").forGetter(function -> function.instrumentTagKey))
                    .apply(instance, SetCopperHornSoundLootFunction::new)
    );
    final TagKey<CopperHornInstrument> instrumentTagKey;

    SetCopperHornSoundLootFunction(List<LootCondition> lootConditions, TagKey<CopperHornInstrument> tagKey) {
        super(lootConditions);
        this.instrumentTagKey = tagKey;
    }

    @Override
    public LootFunctionType<SetCopperHornSoundLootFunction> getType() {
        return CopperHorns.SET_COPPER_HORN_INSTRUMENT;
    }

    @Override
    public ItemStack process(ItemStack stack, LootContext context) {
        CopperHornItem.setRandomInstrumentFromTag(stack, this.instrumentTagKey, context.getRandom());
        return stack;
    }

    public static ConditionalLootFunction.Builder<?> builder(TagKey<CopperHornInstrument> tagKey) {
        return SetCopperHornSoundLootFunction.builder((List<LootCondition> lootConditions) -> new SetCopperHornSoundLootFunction(lootConditions, tagKey));
    }
}
