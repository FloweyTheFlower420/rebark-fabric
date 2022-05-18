package com.floweytf.rebark;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class BarkItem extends Item {
    public static Map<Block, Block> UNSTRIP = null;
    public BarkItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = world.getBlockState(blockpos);

        Block block = blockstate.getBlock();
        if(!UNSTRIP.containsKey(block))
            return InteractionResult.PASS;
        if(!Tags.validateRebark(UNSTRIP.get(block)))
            return InteractionResult.FAIL;
        Block output = UNSTRIP.get(block);
        BlockState outputState = output.defaultBlockState().setValue(
            RotatedPillarBlock.AXIS,
            blockstate.getValue(RotatedPillarBlock.AXIS)
        );

        Player playerEntity = context.getPlayer();

        world.playSound(playerEntity, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
        if (!world.isClientSide) {
            world.setBlock(blockpos, outputState, 11);
        }

        int count = playerEntity.getItemInHand(context.getHand()).getCount();
        playerEntity.getItemInHand(context.getHand()).setCount(count - 1);

        return InteractionResult.sidedSuccess(world.isClientSide);
    }
}
