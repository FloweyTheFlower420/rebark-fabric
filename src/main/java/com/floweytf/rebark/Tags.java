package com.floweytf.rebark;

import com.floweytf.rebark.mixin.BlockTagsAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;

public class Tags {
    private static final Tag.Named<Block> REBARK_BLACKLIST_TAG = BlockTagsAccessor.getHelper().bind(RebarkMain.MODID + ":rebark_blacklist");
    private static final Tag.Named<Block> STRIP_BLACKLIST_TAG = BlockTagsAccessor.getHelper().bind(RebarkMain.MODID + ":strip_blacklist");
    private static final Tag.Named<Block> REBARK_WHITELIST_TAG = BlockTagsAccessor.getHelper().bind(RebarkMain.MODID + ":rebark_blacklist");
    private static final Tag.Named<Block> STRIP_WHITELIST_TAG = BlockTagsAccessor.getHelper().bind(RebarkMain.MODID + ":strip_blacklist");

    public static boolean validateRebark(Block block) {
        boolean passWhitelist;
        if(REBARK_WHITELIST_TAG.getValues().size() == 0) // whitelist isn't used
            passWhitelist = true;
        else
            passWhitelist = REBARK_WHITELIST_TAG.contains(block);

        return passWhitelist && !REBARK_BLACKLIST_TAG.contains(block);
    }

    public static boolean validateStrip(Block block) {
        boolean passWhitelist;
        if(STRIP_WHITELIST_TAG.getValues().size() == 0) // whitelist isn't used
            passWhitelist = true;
        else
            passWhitelist = STRIP_WHITELIST_TAG.contains(block);

        return passWhitelist && !STRIP_BLACKLIST_TAG.contains(block);
    }
}
