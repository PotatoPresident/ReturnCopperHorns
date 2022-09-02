package garden.potato.copperhorns.data;

import garden.potato.copperhorns.CopperHorns;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public class CopperHornRecipeProvider extends FabricRecipeProvider {
    public CopperHornRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        ComplexRecipeJsonBuilder.create(CopperHorns.COPPER_HORN_RECIPE).offerTo(exporter, CopperHorns.id("copper_horn_recipe").toString());
    }
}
