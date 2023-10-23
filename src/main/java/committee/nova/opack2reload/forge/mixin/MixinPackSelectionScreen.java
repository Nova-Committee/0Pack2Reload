package committee.nova.opack2reload.forge.mixin;

import committee.nova.opack2reload.forge.api.IPackSelectionModel;
import committee.nova.opack2reload.forge.api.IPackSelectionScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.packs.PackSelectionModel;
import net.minecraft.client.gui.screens.packs.PackSelectionScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PackSelectionScreen.class)
public abstract class MixinPackSelectionScreen extends Screen implements IPackSelectionScreen {
    @Shadow
    protected abstract void closeWatcher();

    @Shadow
    @Final
    private PackSelectionModel model;
    @Unique
    private Button cancelButton;

    protected MixinPackSelectionScreen(Component text) {
        super(text);
    }

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/packs/PackSelectionScreen;reload()V"))
    private void inject$init(CallbackInfo ci) {

        cancelButton = this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, button -> {
            ((IPackSelectionModel) this.model).cancel();
            closeWatcher();
        }).bounds(this.width / 2 - 75, this.height - 24, 150, 20).build());
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }
}
