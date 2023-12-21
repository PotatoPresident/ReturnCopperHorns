package garden.potato.copperhorns.data;

import garden.potato.copperhorns.CopperHorns;
import garden.potato.copperhorns.recipe.CopperHornRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;

public class CopperHornRecipeProvider extends FabricRecipeProvider {

    public CopperHornRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ComplexRecipeJsonBuilder.create(CopperHornRecipe::new).offerTo(exporter, CopperHorns.id("copper_horn").toString());
    }
}
