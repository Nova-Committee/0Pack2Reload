package committee.nova.opack2reload.forge.mixin;

import net.minecraft.client.Options;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Options.class)
public abstract class MixinOptions {
    @Shadow
    public List<String> resourcePacks;

    @Shadow
    public List<String> incompatibleResourcePacks;

    @Inject(method = "updateResourcePacks", at = @At("HEAD"))
    private void inject$updateResourcePacks(PackRepository repo, CallbackInfo ci) {
        if (!resourcePacks.isEmpty()) return;
        final List<Pack> packs = ((InvokerPackRepository) repo).callRebuildSelected(List.of());
        for (Pack pack : packs) {
            if (!pack.isFixedPosition()) {
                resourcePacks.add(pack.getId());
                if (!pack.getCompatibility().isCompatible()) {
                    incompatibleResourcePacks.add(pack.getId());
                }
            }
        }
    }
}
