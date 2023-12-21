package garden.potato.copperhorns.recipe;

import garden.potato.copperhorns.CopperHornInstrumentTags;
import garden.potato.copperhorns.CopperHornItem;
import garden.potato.copperhorns.CopperHorns;
import garden.potato.copperhorns.mixin.GoatHornItemInvoker;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Instrument;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.InstrumentTags;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Optional;

public class CopperHornRecipe extends ShapedRecipe {
    public CopperHornRecipe(CraftingRecipeCategory category) {
        super("", category, RawShapedRecipe.create(
                Map.of(
                        'G', Ingredient.ofItems(Items.GOAT_HORN),
                        'C', Ingredient.ofItems(Items.COPPER_INGOT)
                ), " C ", "CGC", " C "
        ), new ItemStack(CopperHorns.COPPER_HORN));
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack goatHorn = ItemStack.EMPTY;
        for (int i = 0; i < inventory.size() && goatHorn.isEmpty(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isOf(Items.GOAT_HORN)) continue;
            goatHorn = itemStack;
        }
        var tag = CopperHornInstrumentTags.REGULAR_COPPER_HORNS;

        Optional<? extends RegistryEntry<Instrument>> instrument = ((GoatHornItemInvoker) goatHorn.getItem()).invokeGetInstrument(goatHorn);
        if (instrument.isPresent()) {
            if (instrument.get().isIn(InstrumentTags.SCREAMING_GOAT_HORNS)) tag = CopperHornInstrumentTags.SCREAMING_COPPER_HORNS;
        }

        ItemStack copperHorn = new ItemStack(CopperHorns.COPPER_HORN);
        CopperHornItem.setRandomInstrumentFromTag(copperHorn, tag, Random.create());

        return copperHorn;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CopperHorns.COPPER_HORN_RECIPE;
    }

    @Override
    public boolean matches(RecipeInputInventory craftingInventory, World world) {
        return super.matches(craftingInventory, world);
    }
}
