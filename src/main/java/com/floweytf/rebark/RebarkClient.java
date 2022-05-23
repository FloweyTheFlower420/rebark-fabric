package com.floweytf.rebark;

import com.floweytf.rebark.mixin.AxeItemAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RebarkClient implements ClientModInitializer {
    public static ResourceLocation blockToId(Block b) {
        return Registry.BLOCK.getKey(b);
    }

    public static Block map(Block b) {
        return Registry.BLOCK.get(BarkItem.UNSTRIP.get(blockToId(b)));
    }
    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((packetListener, packetSender, mc) -> {
            if (BarkItem.UNSTRIP == null) {
                BarkItem.UNSTRIP = AxeItemAccessor.getStrip().entrySet()
                    .stream()
                    .map((e) -> new AbstractMap.SimpleImmutableEntry<>(blockToId(e.getKey()), blockToId(e.getValue())))
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
            }
        });
    }
}
