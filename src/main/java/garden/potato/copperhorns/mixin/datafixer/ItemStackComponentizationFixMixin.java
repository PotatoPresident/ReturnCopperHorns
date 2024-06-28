package garden.potato.copperhorns.mixin.datafixer;

import com.mojang.serialization.Dynamic;
import net.minecraft.datafixer.fix.ItemStackComponentizationFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStackComponentizationFix.class)
public class ItemStackComponentizationFixMixin {
    @Inject(method = "fixStack", at = @At("TAIL"))
    private static void fixStackCopperHorn(ItemStackComponentizationFix.StackData data, Dynamic<?> dynamic, CallbackInfo ci) {
        if (data.itemEquals("copper-horns:copper_horn")) {
            data.moveToComponent("instrument", "copper-horns:copper_horn_instrument");
        }
    }
}
