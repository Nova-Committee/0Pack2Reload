package committee.nova.opack2reload.forge.mixin;

import committee.nova.opack2reload.forge.api.IPackSelectionScreen;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.PackScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PackScreen.class)
public abstract class MixinPackScreen extends Screen implements IPackSelectionScreen {
    protected MixinPackScreen(ITextComponent p_i51108_1_) {
        super(p_i51108_1_);
    }

    @Shadow
    protected abstract void closeWatcher();

    @Shadow
    @Final
    private Screen lastScreen;
    @Unique
    private Button cancelButton;


    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/PackScreen;reload()V"))
    private void inject$init(CallbackInfo ci) {
        cancelButton = this.addButton(new Button(this.width / 2 - 75, this.height - 24, 150, 20, DialogTexts.GUI_CANCEL, button -> {
            this.getMinecraft().setScreen(this.lastScreen);
            this.closeWatcher();
        }));
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }
}
