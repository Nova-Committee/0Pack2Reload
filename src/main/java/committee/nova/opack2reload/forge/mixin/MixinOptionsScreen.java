package committee.nova.opack2reload.forge.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
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
    private GameSettings options;

    @Inject(method = "updatePackList", at = @At("HEAD"))
    private void inject$updatePackList(ResourcePackList list, CallbackInfo ci) {
        if (!options.resourcePacks.isEmpty()) return;
        final List<ResourcePackInfo> packs = ((InvokerResourcePackList) list).callRebuildSelected(ImmutableList.of());
        for (ResourcePackInfo pack : packs) {
            if (!pack.isFixedPosition()) {
                this.options.resourcePacks.add(pack.getId());
                if (!pack.getCompatibility().isCompatible()) {
                    this.options.incompatibleResourcePacks.add(pack.getId());
                }
            }
        }
    }
}
