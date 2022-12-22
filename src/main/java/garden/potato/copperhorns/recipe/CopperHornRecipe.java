package garden.potato.copperhorns.recipe;

import garden.potato.copperhorns.CopperHornInstrumentTags;
import garden.potato.copperhorns.CopperHornItem;
import garden.potato.copperhorns.CopperHorns;
import garden.potato.copperhorns.mixin.GoatHornItemInvoker;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Instrument;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.InstrumentTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Optional;

public class CopperHornRecipe extends ShapedRecipe {
    public CopperHornRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, "", category, 3, 2, DefaultedList.copyOf(Ingredient.EMPTY,
                Ingredient.ofItems(Items.COPPER_INGOT), Ingredient.ofItems(Items.GOAT_HORN), Ingredient.ofItems(Items.COPPER_INGOT),
                Ingredient.EMPTY, Ingredient.ofItems(Items.COPPER_INGOT), Ingredient.EMPTY
        ), new ItemStack(CopperHorns.COPPER_HORN));
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
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
    public boolean matches(CraftingInventory craftingInventory, World world) {
        return super.matches(craftingInventory, world);
    }
}
