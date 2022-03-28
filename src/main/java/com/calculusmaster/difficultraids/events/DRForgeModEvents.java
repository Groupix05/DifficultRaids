package com.calculusmaster.difficultraids.events;

import com.calculusmaster.difficultraids.DifficultRaids;
import com.calculusmaster.difficultraids.entity.entities.WarriorIllagerEntity;
import com.calculusmaster.difficultraids.util.SetRaidDifficultyCommand;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DifficultRaids.MODID)
public class DRForgeModEvents
{
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event)
    {
        SetRaidDifficultyCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void addSpawn(EntityJoinWorldEvent event)
    {
        final float defaultMaxDistance = 16.0F;
        final float defaultWalkSpeedModifier = 0.8F;
        final float defaultSprintSpeedModifier = 0.85F;

        //Both Villager and WanderingTrader
        if(event.getEntity() instanceof AbstractVillager villager)
        {
            villager.goalSelector.addGoal(1,
                    new AvoidEntityGoal<>(villager, WarriorIllagerEntity.class, defaultMaxDistance, defaultWalkSpeedModifier, defaultSprintSpeedModifier));
        }
    }
}
