package committee.nova.opack2reload.forge.mixin;

import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Collection;
import java.util.List;

@Mixin(ResourcePackList.class)
public interface InvokerResourcePackList {
    @Invoker
    List<ResourcePackInfo> callRebuildSelected(Collection<String> list0);
}
