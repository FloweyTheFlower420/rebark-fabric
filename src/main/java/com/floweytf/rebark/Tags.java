package com.floweytf.rebark;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class Tags {
    private static final TagKey<Block> REBARK_BLACKLIST_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(RebarkMain.MODID, "rebark_blacklist"));
    private static final TagKey<Block> STRIP_BLACKLIST_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(RebarkMain.MODID,  "strip_blacklist"));
    private static final TagKey<Block> REBARK_WHITELIST_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(RebarkMain.MODID,  "rebark_blacklist"));
    private static final TagKey<Block> STRIP_WHITELIST_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(RebarkMain.MODID,  "strip_blacklist"));

    public static boolean validateRebark(Block block) {
        boolean passWhitelist;

        if(Registry.BLOCK.getTag(REBARK_WHITELIST_TAG).get().size() == 0) // whitelist isn't used
            passWhitelist = true;
        else
            passWhitelist = block.builtInRegistryHolder().is(REBARK_WHITELIST_TAG);

        return passWhitelist && !block.builtInRegistryHolder().is(REBARK_BLACKLIST_TAG);
    }

    public static boolean validateStrip(Block block) {
        boolean passWhitelist;

        if(Registry.BLOCK.getTag(STRIP_WHITELIST_TAG).get().size() == 0) // whitelist isn't used
            passWhitelist = true;
        else
            passWhitelist = block.builtInRegistryHolder().is(STRIP_WHITELIST_TAG);

        return passWhitelist && !block.builtInRegistryHolder().is(STRIP_BLACKLIST_TAG);
    }
}
