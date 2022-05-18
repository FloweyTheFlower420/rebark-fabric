package com.floweytf.rebark;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;

public class Tags {
    private static final Tag<Block> REBARK_BLACKLIST_TAG = TagFactory.BLOCK.create(new ResourceLocation(RebarkMain.MODID, "rebark_blacklist"));
    private static final Tag<Block> STRIP_BLACKLIST_TAG = TagFactory.BLOCK.create(new ResourceLocation(RebarkMain.MODID, "strip_blacklist"));
    private static final Tag<Block> REBARK_WHITELIST_TAG = TagFactory.BLOCK.create(new ResourceLocation(RebarkMain.MODID, "rebark_blacklist"));
    private static final Tag<Block> STRIP_WHITELIST_TAG = TagFactory.BLOCK.create(new ResourceLocation(RebarkMain.MODID, "strip_blacklist"));

    public static boolean validateRebark(Block block) {
        boolean passWhitelist;

        if (REBARK_WHITELIST_TAG.getValues().size() == 0) // whitelist isn't used
            passWhitelist = true;
        else
            passWhitelist = REBARK_WHITELIST_TAG.contains(block);

        return passWhitelist && !REBARK_BLACKLIST_TAG.contains(block);
    }

    public static boolean validateStrip(Block block) {
        boolean passWhitelist;

        if (STRIP_WHITELIST_TAG.getValues().size() == 0) // whitelist isn't used
            passWhitelist = true;
        else
            passWhitelist = STRIP_WHITELIST_TAG.contains(block);

        return passWhitelist && !STRIP_BLACKLIST_TAG.contains(block);
    }
}
