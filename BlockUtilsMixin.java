package com.example.scopenbreak.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * TARGET IS UNVERIFIED. Run javap on the SecurityCraft Fabric 26.3 jar and make the
 * class name, method name and parameter list below match exactly.
 */
@Pseudo
@Mixin(targets = "net.geforcemods.securitycraft.util.BlockUtils", remap = false)
public abstract class BlockUtilsMixin {

    // Obsidian is 50. Tweak freely.
    private static final float[] LEVEL_HARDNESS = { 50f, 100f, 150f, 250f };

    @Inject(method = "getDestroyProgress", at = @At("RETURN"), cancellable = true, remap = false)
    private static void scopenbreak$allowSlowBreak(
            @Coerce Object originalFunction,
            BlockState state,
            Player player,
            BlockGetter level,
            BlockPos pos,
            CallbackInfoReturnable<Float> cir) {

        // SecurityCraft allowed it (owner / allowed player): leave untouched.
        if (cir.getReturnValueF() > 0f) return;

        float base = state.getDestroySpeed(level, pos);
        if (base < 0f) return; // genuinely unbreakable

        float hardness = LEVEL_HARDNESS[reinforcementLevel(base)];
        float speed = player.getDestroySpeed(state);
        int divisor = player.hasCorrectToolForDrops(state) ? 30 : 100;

        cir.setReturnValue(speed / hardness / divisor);
    }

    private static int reinforcementLevel(float base) {
        if (base < 2f)  return 0;
        if (base < 6f)  return 1;
        if (base < 25f) return 2;
        return 3;
    }
}
