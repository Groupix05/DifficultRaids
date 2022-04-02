package com.calculusmaster.difficultraids.entity;

import com.calculusmaster.difficultraids.DifficultRaids;
import com.calculusmaster.difficultraids.entity.entities.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DifficultRaidsEntityTypes
{
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, DifficultRaids.MODID);

    public static final RegistryObject<EntityType<WarriorIllagerEntity>> WARRIOR_ILLAGER =
            ENTITY_TYPES.register("warrior_illager",
                    () -> EntityType.Builder.of(WarriorIllagerEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F).clientTrackingRange(8).fireImmune()
                            .build(new ResourceLocation(DifficultRaids.MODID, "warrior_illager").toString()));

    public static final RegistryObject<EntityType<DartIllagerEntity>> DART_ILLAGER =
            ENTITY_TYPES.register("dart_illager",
                    () -> EntityType.Builder.of(DartIllagerEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F).clientTrackingRange(8).fireImmune()
                            .build(new ResourceLocation(DifficultRaids.MODID, "dart_illager").toString()));

    public static final RegistryObject<EntityType<ElectroIllagerEntity>> ELECTRO_ILLAGER =
            ENTITY_TYPES.register("electro_illager",
                    () -> EntityType.Builder.of(ElectroIllagerEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F).clientTrackingRange(8).fireImmune()
                            .build(new ResourceLocation(DifficultRaids.MODID, "electro_illager").toString()));

    public static final RegistryObject<EntityType<NecromancerIllagerEntity>> NECROMANCER_ILLAGER =
            ENTITY_TYPES.register("necromancer_illager",
                    () -> EntityType.Builder.of(NecromancerIllagerEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F).clientTrackingRange(8).fireImmune()
                            .build(new ResourceLocation(DifficultRaids.MODID, "necromancer_illager").toString()));

    public static final RegistryObject<EntityType<ShamanIllagerEntity>> SHAMAN_ILLAGER =
            ENTITY_TYPES.register("shaman_illager",
                    () -> EntityType.Builder.of(ShamanIllagerEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F).clientTrackingRange(8).fireImmune()
                            .build(new ResourceLocation(DifficultRaids.MODID, "shaman_illager").toString()));

    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}
