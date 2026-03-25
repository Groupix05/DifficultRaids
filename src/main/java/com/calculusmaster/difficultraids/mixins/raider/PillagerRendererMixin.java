package com.calculusmaster.difficultraids.mixins.raider;

import com.calculusmaster.difficultraids.DifficultRaids;
import net.minecraft.client.renderer.entity.PillagerRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PillagerRenderer.class)
public abstract class PillagerRendererMixin
{
    @Unique
    private static final ResourceLocation PILLAGER_DEFAULT = new ResourceLocation("minecraft:textures/entity/illager/pillager.png");
    @Unique
    private static final ResourceLocation PILLAGER_HERO = DifficultRaids.location("textures/entity/pillager/pillager_hero.png");
    @Unique
    private static final ResourceLocation PILLAGER_LEGEND = DifficultRaids.location("textures/entity/pillager/pillager_legend.png");
    @Unique
    private static final ResourceLocation PILLAGER_MASTER = DifficultRaids.location("textures/entity/pillager/pillager_master.png");
    @Unique
    private static final ResourceLocation PILLAGER_GRANDMASTER = DifficultRaids.location("textures/entity/pillager/pillager_grandmaster.png");

    //TODO: Finish varied Pillager textures
}
