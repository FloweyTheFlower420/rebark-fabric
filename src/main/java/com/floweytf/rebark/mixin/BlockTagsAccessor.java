package com.floweytf.rebark.mixin;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.StaticTagHelper;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(BlockTags.class)
public interface BlockTagsAccessor {
    @Accessor("HELPER")
    static StaticTagHelper<Block> getHelper() {
        throw new AssertionError();
    }
}