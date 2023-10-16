package committee.nova.opack2reload;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod("opack2reload")
public class OPack2Reload {
    public OPack2Reload() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> "", (a, b) -> true));
    }
}
