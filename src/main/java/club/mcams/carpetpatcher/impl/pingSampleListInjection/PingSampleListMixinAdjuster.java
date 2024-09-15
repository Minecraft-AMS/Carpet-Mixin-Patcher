package club.mcams.carpetpatcher.impl.pingSampleListInjection;

import com.bawnorton.mixinsquared.adjuster.tools.AdjustableAnnotationNode;
import com.bawnorton.mixinsquared.adjuster.tools.AdjustableModifyConstantNode;
import com.bawnorton.mixinsquared.api.MixinAnnotationAdjuster;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.List;

public class PingSampleListMixinAdjuster implements MixinAnnotationAdjuster {
    @Override
    public AdjustableAnnotationNode adjust(List<String> targetClassNames, String mixinClassName, MethodNode handlerNode, AdjustableAnnotationNode annotation) {
        if ("carpet.mixins.MinecraftServer_pingPlayerSampleLimit".equals(mixinClassName) && annotation.is(ModifyConstant.class)) {
            AdjustableModifyConstantNode wrapOpNode = annotation.as(AdjustableModifyConstantNode.class);
            List<String> methods = wrapOpNode.getMethod();
            if ("tickServer".equals(methods.get(0))) {
                methods.set(0, "createMetadataPlayers");
                wrapOpNode.setMethod(methods);
                return wrapOpNode;
            }
        }
        return annotation;
    }
}
