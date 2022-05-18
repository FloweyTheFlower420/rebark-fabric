package com.floweytf.rebark;

import com.floweytf.rebark.mixin.AxeItemAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

import java.util.Map;
import java.util.stream.Collectors;

public class RebarkClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((packetListener, packetSender, mc) -> {
            if (BarkItem.UNSTRIP == null) {
                BarkItem.UNSTRIP = AxeItemAccessor.getStrip().entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
            }
        });
    }
}
