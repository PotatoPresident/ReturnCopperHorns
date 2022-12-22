package garden.potato.copperhorns;

import garden.potato.copperhorns.registry.CopperHornRegistries;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class CopperHornItem extends Item {
    private static final String INSTRUMENT_KEY = "instrument";
    private final TagKey<CopperHornInstrument> instrumentTag;

    public CopperHornItem(Settings settings, TagKey<CopperHornInstrument> instrumentTag) {
        super(settings);
        this.instrumentTag = instrumentTag;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        Optional<RegistryKey<CopperHornInstrument>> optional = this.getInstrument(stack).flatMap(RegistryEntry::getKey);
        if (optional.isPresent()) {
            MutableText mutableText = Text.translatable(Util.createTranslationKey(INSTRUMENT_KEY, optional.get().getValue()));
            tooltip.add(mutableText.formatted(Formatting.GRAY));
        }
    }

    public static ItemStack getStackForInstrument(Item item, RegistryEntry<CopperHornInstrument> instrument) {
        ItemStack itemStack = new ItemStack(item);
        CopperHornItem.setInstrument(itemStack, instrument);
        return itemStack;
    }

    public static void setRandomInstrumentFromTag(ItemStack stack, TagKey<CopperHornInstrument> instrumentTag, Random random) {
        Optional<RegistryEntry<CopperHornInstrument>> optional = CopperHornRegistries.INSTRUMENT.getEntryList(instrumentTag).flatMap(entryList -> entryList.getRandom(random));
        optional.ifPresent(copperHornInstrumentRegistryEntry -> CopperHornItem.setInstrument(stack, copperHornInstrumentRegistryEntry));
    }

    private static void setInstrument(ItemStack stack, RegistryEntry<CopperHornInstrument> instrument) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putString(INSTRUMENT_KEY, instrument.getKey().orElseThrow(() -> new IllegalStateException("Invalid instrument")).getValue().toString());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        Optional<? extends RegistryEntry<CopperHornInstrument>> optional = this.getInstrument(itemStack);
        if (optional.isPresent()) {
            CopperHornInstrument instrument = optional.get().value();
            user.setCurrentHand(hand);
            CopperHornItem.playSound(world, user, instrument);
            user.getItemCooldownManager().set(this, instrument.useDuration());
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        Optional<? extends RegistryEntry<CopperHornInstrument>> optional = this.getInstrument(stack);
        return optional.map(copperHornInstrumentRegistryEntry -> copperHornInstrumentRegistryEntry.value().useDuration()).orElse(0);
    }

    private Optional<? extends RegistryEntry<CopperHornInstrument>> getInstrument(ItemStack stack) {
        Identifier identifier;
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound != null && (identifier = Identifier.tryParse(nbtCompound.getString(INSTRUMENT_KEY))) != null) {
            return CopperHornRegistries.INSTRUMENT.getEntry(RegistryKey.of(CopperHornRegistries.INSTRUMENT_KEY, identifier));
        }
        Iterator<RegistryEntry<CopperHornInstrument>> iterator = CopperHornRegistries.INSTRUMENT.iterateEntries(this.instrumentTag).iterator();
        if (iterator.hasNext()) {
            return Optional.of(iterator.next());
        }
        return Optional.empty();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }

    private static void playSound(World world, PlayerEntity player, CopperHornInstrument instrument) {
        SoundEvent soundEvent = instrument.melodySoundEvent();
        if (player.getPitch() <= -60.0) soundEvent = instrument.harmonySoundEvent();
        if (player.isSneaking()) soundEvent = instrument.bassSoundEvent();

        float f = instrument.range() / 16.0f;
        world.playSoundFromEntity(player, player, soundEvent, SoundCategory.RECORDS, f, 1.0f);
        world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, player.getPos(), GameEvent.Emitter.of(player));
    }
}
