package committee.nova.opack2reload.fabric.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(OptionsScreen.class)
public abstract class MixinOptionsScreen {
    @Shadow
    @Final
    private Options options;

    @Inject(method = "updatePackList", at = @At("HEAD"))
    private void inject$updatePackList(PackRepository list, CallbackInfo ci) {
        if (!options.resourcePacks.isEmpty()) return;
        final List<Pack> packs = ((InvokerPackRepository) list).callRebuildSelected(ImmutableList.of());
        for (Pack pack : packs) {
            if (!pack.isFixedPosition()) {
                this.options.resourcePacks.add(pack.getId());
                if (!pack.getCompatibility().isCompatible()) {
                    this.options.incompatibleResourcePacks.add(pack.getId());
                }
            }
        }
    }
}
