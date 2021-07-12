package com.floweytf.rebark.mixin;

import com.floweytf.rebark.RebarkMain;
import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Inject(at = @At(value = "FIELD", target="Lnet/minecraft/sounds/SoundEvents;AXE_STRIP:Lnet/minecraft/sounds/SoundEvent;", opcode = Opcodes.GETSTATIC),
        method = "useOn(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;")
    private void onUseMixin(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack heldItem = useOnContext.getItemInHand();
        Player player = useOnContext.getPlayer();
        // no fucking clue
        Level world = useOnContext.getPlayer().level;
        ItemStack itemStack = new ItemStack(RebarkMain.BARK);
        BlockPos pos = useOnContext.getClickedPos().relative(useOnContext.getClickedFace());

        world.addFreshEntity(new ItemEntity(
            world,
            pos.getX() + .5f,
            pos.getY() + .3f,
            pos.getZ() + .5f,
            itemStack
        ));
    }
}
