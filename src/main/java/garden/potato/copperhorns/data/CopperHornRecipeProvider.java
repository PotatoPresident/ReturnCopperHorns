package garden.potato.copperhorns.data;

import garden.potato.copperhorns.CopperHorns;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public class CopperHornRecipeProvider extends FabricRecipeProvider {

    public CopperHornRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ComplexRecipeJsonBuilder.create(CopperHorns.COPPER_HORN_RECIPE).offerTo(exporter, CopperHorns.id("copper_horn").toString());
    }
}
