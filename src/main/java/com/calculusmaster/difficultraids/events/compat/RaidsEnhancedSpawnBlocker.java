package com.calculusmaster.difficultraids.events.compat;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class RaidsEnhancedSpawnBlocker {

    private static final Set<String> BLOCKED_TYPES = Set.of(
            "raidsenhanced:zapper",
            "raidsenhanced:golem_of_last_resort",
            "raidsenhanced:raid_blimp",
            "raidsenhanced:raid_drill"
    );

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void blockIllageArtRaidSpawns(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        var key = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        if (key == null) return;

        String typeId = key.toString();

        if (BLOCKED_TYPES.contains(typeId) && event.getLevel() instanceof ServerLevel serverLevel) {
            var raid = serverLevel.getRaidAt(entity.blockPosition());
            if (raid != null && raid.getBadOmenLevel() > 1) {
                event.setCanceled(true);
            }
        }
    }
}