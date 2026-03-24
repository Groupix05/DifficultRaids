package com.calculusmaster.difficultraids.events.compat;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class IllageArtSpawnBlocker {

    private static final Set<String> BLOCKED_TYPES = Set.of(
            "illageart:church_wizard",
            "illageart:grand_archmage",
            "illageart:calamity_nurse",
            "illageart:spider_summoner",
            "illageart:thefireking",
            "illageart:floating_elder",
            "illageart:key_holder"
    );

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void blockIllageArtRaidSpawns(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        var key = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        if (key == null) return;

        String typeId = key.toString();

        if (BLOCKED_TYPES.contains(typeId) && event.getLevel() instanceof ServerLevel serverLevel) {
            if (serverLevel.isRaided(entity.blockPosition())) {
                event.setCanceled(true);
            }
        }
    }
}