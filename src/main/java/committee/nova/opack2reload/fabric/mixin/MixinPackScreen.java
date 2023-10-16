package committee.nova.opack2reload.fabric.mixin;

import committee.nova.opack2reload.fabric.api.IPackSelectionScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
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
public abstract class MixinPackScreen extends Screen implements IPackSelectionScreen {
    protected MixinPackScreen(Component component) {
        super(component);
    }

    @Shadow
    protected abstract void closeWatcher();

    @Shadow
    @Final
    private Screen lastScreen;
    @Unique
    private Button cancelButton;


    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/packs/PackSelectionScreen;reload()V"))
    private void inject$init(CallbackInfo ci) {
        cancelButton = this.addButton(new Button(this.width / 2 - 75, this.height - 24, 150, 20, CommonComponents.GUI_CANCEL, button -> {
            if (minecraft == null) return;
            minecraft.setScreen(this.lastScreen);
            this.closeWatcher();
        }));
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }
}
