package committee.nova.opack2reload.fabric.mixin;

import committee.nova.opack2reload.fabric.api.IPackSelectionModel;
import net.minecraft.client.gui.screens.packs.PackSelectionModel;
import net.minecraft.server.packs.repository.PackRepository;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Consumer;

@Mixin(PackSelectionModel.class)
public abstract class MixinPackSelectionModel implements IPackSelectionModel {
    @Shadow
    @Final
    private Consumer<PackRepository> output;

    @Shadow
    @Final
    private PackRepository repository;

    @Override
    public void cancel() {
        this.output.accept(this.repository);
    }
}
