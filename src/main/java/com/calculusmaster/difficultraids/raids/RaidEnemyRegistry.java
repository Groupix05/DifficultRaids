package com.calculusmaster.difficultraids.raids;

import com.calculusmaster.difficultraids.entity.DifficultRaidsEntityTypes;
import com.calculusmaster.difficultraids.setup.DifficultRaidsConfig;
import com.calculusmaster.difficultraids.util.Compat;
import com.infamous.dungeons_mobs.mod.ModEntityTypes;
import com.teamabnormals.savage_and_ravage.core.registry.SREntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;

import java.util.*;

import static com.calculusmaster.difficultraids.entity.DifficultRaidsEntityTypes.*;

public class RaidEnemyRegistry
{
    public static final Map<RaidDifficulty, RaidEnemies> WAVES = new HashMap<>();
    private static final Set<String> REGISTERED_RAIDER_TYPES = new HashSet<>();

    //Minecraft
    public static final String VINDICATOR = "VINDICATOR";
    public static final String EVOKER = "EVOKER";
    public static final String PILLAGER = "PILLAGER";
    public static final String WITCH = "WITCH";
    public static final String RAVAGER = "RAVAGER";

    //Difficult Raids
    public static final String ILLUSIONER = "ILLUSIONER";
    public static final String WARRIOR = "WARRIOR_ILLAGER";
    public static final String DART = "DART_ILLAGER";
    public static final String CONDUCTOR = "ELECTRO_ILLAGER";
    public static final String NECROMANCER = "NECROMANCER_ILLAGER";
    public static final String SHAMAN = "SHAMAN_ILLAGER";
    public static final String TANK = "TANK_ILLAGER";
    public static final String ASSASSIN = "ASSASSIN_ILLAGER";
    public static final String FROSTMAGE = "FROST_ILLAGER";

    public static final String NUAOS = "NUAOS_ELITE";
    public static final String XYDRAX = "XYDRAX_ELITE";
    public static final String MODUR = "MODUR_ELITE";
    public static final String VOLDON = "VOLDON_ELITE";

    //Hunter Illager
    public static final String HUNTER = "HUNTERILLAGER";

    //Enchant With Mob
    public static final String ENCHANTER = "ENCHANTER";

    //It Takes a Pillage
    public static final String ARCHER = "ARCHER";
    public static final String SKIRMISHER = "SKIRMISHER";
    public static final String LEGIONER = "LEGIONER";

    //Illage & Spillage
    public static final String IGNITER = "entity.illageandspillage.igniter".toUpperCase();
    public static final String TWITTOLLAGER = "entity.illageandspillage.twittollager".toUpperCase();
    public static final String PRESERVER = "entity.illageandspillage.preserver".toUpperCase();
    public static final String ABSORBER = "entity.illageandspillage.absorber".toUpperCase();
    public static final String CROCOFANG = "entity.illageandspillage.crocofang".toUpperCase();
    public static final String MAGISPELLER = "entity.illageandspillage.magispeller".toUpperCase();
    public static final String SPIRITCALLER = "entity.illageandspillage.spiritcaller".toUpperCase();
    public static final String FREAKAGER = "entity.illageandspillage.freakager".toUpperCase();
    public static final String BOSS_RANDOMIZER = "entity.illageandspillage.boss_randomizer".toUpperCase();

    //Savage and Ravage
    public static final String GRIEFER = "GRIEFER";
    public static final String EXECUTIONER = "EXECUTIONER";
    public static final String TRICKSTER = "TRICKSTER";
    public static final String ICEOLOGER_SR = "SR_ICEOLOGER";

    //Dungeons Mobs
    public static final String MOUNTAINEER = "mountaineer".toUpperCase();
    public static final String ROYAL_GUARD = "royal_guard".toUpperCase();
    public static final String GEOMANCER = "geomancer".toUpperCase();
    public static final String ILLUSIONER_DM = "DM_ILLUSIONER";
    public static final String MAGE = "mage".toUpperCase();
    public static final String ICEOLOGER_DM = "iceologer".toUpperCase();
    public static final String WINDCALLER = "windcaller".toUpperCase();
    public static final String SQUALL_GOLEM = "squall_golem".toUpperCase();
    public static final String REDSTONE_GOLEM = "redstone_golem".toUpperCase();

    private static final int[] BLANK = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

    public static boolean isRaiderTypeEnabled(String raiderType)
    {
        return DifficultRaidsConfig.ENABLED_RAIDERS.containsKey(raiderType.toUpperCase()) && DifficultRaidsConfig.ENABLED_RAIDERS.get(raiderType.toUpperCase()).get();
    }

    public static boolean isRaiderTypeRegistered(String raiderType)
    {
        return REGISTERED_RAIDER_TYPES.contains(raiderType.toUpperCase());
    }

    public static void registerRaiders()
    {
        RaidEnemyRegistry.createRaiderType(ILLUSIONER, EntityType.ILLUSIONER);
        RaidEnemyRegistry.createRaiderType(WARRIOR, DifficultRaidsEntityTypes.WARRIOR_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(DART, DifficultRaidsEntityTypes.DART_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(CONDUCTOR, DifficultRaidsEntityTypes.ELECTRO_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(NECROMANCER, DifficultRaidsEntityTypes.NECROMANCER_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(SHAMAN, DifficultRaidsEntityTypes.SHAMAN_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(TANK, DifficultRaidsEntityTypes.TANK_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(ASSASSIN, DifficultRaidsEntityTypes.ASSASSIN_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(FROSTMAGE, DifficultRaidsEntityTypes.FROST_ILLAGER.get());

        RaidEnemyRegistry.createRaiderType(NUAOS, NUAOS_ELITE.get());
        RaidEnemyRegistry.createRaiderType(XYDRAX, XYDRAX_ELITE.get());
        RaidEnemyRegistry.createRaiderType(MODUR, MODUR_ELITE.get());
        RaidEnemyRegistry.createRaiderType(VOLDON, VOLDON_ELITE.get());

        //Compatibility
        if(Compat.SAVAGE_AND_RAVAGE.isLoaded()) RaidEnemyRegistry.createRaiderType(ICEOLOGER_SR, SREntityTypes.ICEOLOGER.get());
        if(Compat.DUNGEONS_MOBS.isLoaded()) RaidEnemyRegistry.createRaiderType(ILLUSIONER_DM, ModEntityTypes.ILLUSIONER.get());
    }

    private static void createRaiderType(String typeName, EntityType<? extends Raider> type)
    {
        Raid.RaiderType.create(typeName, type, BLANK);
    }

    public static void registerWaves()
    {
        RaidEnemyRegistry.createWavesFor(RaidDifficulty.DEFAULT)
                .withRaider(PILLAGER,           new int[]{0, 4, 3, 3, 4, 4, 4, 2})
                .withRaider(VINDICATOR,         new int[]{0, 0, 2, 0, 1, 4, 2, 5})
                .withRaider(WARRIOR,            new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(SKIRMISHER,         new int[]{0, 0, 0, 2, 0, 1, 1, 2})
                .withRaider(TANK,               new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(LEGIONER,           new int[]{0, 0, 0, 0, 2, 0, 2, 3})
                .withRaider(DART,               new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(HUNTER,             new int[]{0, 0, 1, 2, 2, 1, 2, 3})
                .withRaider(ARCHER,             new int[]{0, 2, 0, 0, 2, 3, 2, 4})
                .withRaider(WITCH,              new int[]{0, 0, 0, 0, 3, 0, 0, 1})
                .withRaider(RAVAGER,            new int[]{0, 0, 0, 1, 0, 1, 0, 2})
                .withRaider(ILLUSIONER,         new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(ASSASSIN,           new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(EVOKER,             new int[]{0, 0, 0, 0, 0, 1, 1, 2})
                .withRaider(CONDUCTOR,          new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(NECROMANCER,        new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(FROSTMAGE,          new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(SHAMAN,             new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(ENCHANTER,          new int[]{0, 0, 1, 0, 1, 1, 2, 1})
                .withRaider(IGNITER,            new int[]{0, 0, 1, 2, 0, 2, 1, 4})
                .withRaider(TWITTOLLAGER,       new int[]{0, 0, 0, 0, 1, 0, 2, 1})
                .withRaider(PRESERVER,          new int[]{0, 0, 0, 1, 2, 0, 3, 4})
                .withRaider(ABSORBER,           new int[]{0, 0, 0, 0, 1, 0, 0, 2})
                .withRaider(CROCOFANG,          new int[]{0, 0, 1, 0, 1, 2, 1, 3})
                .withRaider(MAGISPELLER,        new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(SPIRITCALLER,       new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(FREAKAGER,          new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(BOSS_RANDOMIZER,    new int[]{0, 0, 0, 0, 1, 0, 0, 1})
                .withRaider(GRIEFER,            new int[]{0, 1, 0, 1, 2, 2, 3, 2})
                .withRaider(EXECUTIONER,        new int[]{0, 0, 1, 0, 0, 1, 2, 2})
                .withRaider(TRICKSTER,          new int[]{0, 0, 0, 0, 0, 1, 1, 2})
                .withRaider(ICEOLOGER_SR,       new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(MOUNTAINEER,        new int[]{0, 0, 2, 0, 1, 4, 2, 5})
                .withRaider(ROYAL_GUARD,        new int[]{0, 0, 1, 0, 0, 2, 1, 2})
                .withRaider(GEOMANCER,          new int[]{0, 0, 0, 0, 0, 1, 1, 2})
                .withRaider(ILLUSIONER_DM,      new int[]{0, 0, 0, 0, 0, 1, 1, 2})
                .withRaider(MAGE,               new int[]{0, 0, 1, 0, 0, 1, 0, 2})
                .withRaider(ICEOLOGER_DM,       new int[]{0, 0, 0, 0, 0, 1, 1, 2})
                .withRaider(WINDCALLER,         new int[]{0, 0, 0, 0, 0, 1, 1, 2})
                .withRaider(SQUALL_GOLEM,       new int[]{0, 0, 0, 1, 0, 1, 0, 2})
                .withRaider(REDSTONE_GOLEM,     new int[]{0, 0, 0, 0, 0, 0, 0, 1})
                .register();

        RaidEnemyRegistry.createWavesFor(RaidDifficulty.HERO)
                .withRaider(PILLAGER,           new int[]{0, 4, 3, 3, 4, 5, 5, 3})
                .withRaider(VINDICATOR,         new int[]{0, 2, 1, 1, 2, 3, 1, 2})
                .withRaider(WARRIOR,            new int[]{0, 2, 1, 1, 2, 1, 3, 2})
                .withRaider(SKIRMISHER,         new int[]{0, 1, 1, 2, 1, 1, 1, 3})
                .withRaider(TANK,               new int[]{0, 0, 2, 0, 2, 0, 2, 1})
                .withRaider(LEGIONER,           new int[]{0, 0, 0, 1, 2, 1, 3, 3})
                .withRaider(DART,               new int[]{0, 0, 0, 1, 1, 1, 0, 0})
                .withRaider(HUNTER,             new int[]{0, 1, 2, 2, 2, 2, 2, 3})
                .withRaider(ARCHER,             new int[]{0, 2, 1, 2, 3, 3, 3, 5})
                .withRaider(WITCH,              new int[]{0, 0, 1, 0, 3, 1, 0, 2})
                .withRaider(RAVAGER,            new int[]{0, 0, 0, 1, 0, 2, 1, 2})
                .withRaider(ILLUSIONER,         new int[]{0, 0, 1, 0, 0, 0, 1, 0})
                .withRaider(ASSASSIN,           new int[]{0, 0, 0, 0, 0, 1, 0, 0})
                .withRaider(EVOKER,             new int[]{0, 0, 0, 0, 1, 0, 2, 2})
                .withRaider(CONDUCTOR,          new int[]{0, 0, 0, 0, 0, 0, 0, 1})
                .withRaider(NECROMANCER,        new int[]{0, 0, 0, 0, 1, 0, 0, 0})
                .withRaider(FROSTMAGE,          new int[]{0, 0, 0, 0, 0, 1, 0, 0})
                .withRaider(SHAMAN,             new int[]{0, 0, 0, 1, 0, 0, 1, 1})
                .withRaider(ENCHANTER,          new int[]{0, 0, 1, 1, 1, 1, 0, 1})
                .withRaider(IGNITER,            new int[]{0, 0, 1, 2, 2, 2, 2, 4})
                .withRaider(TWITTOLLAGER,       new int[]{0, 0, 1, 1, 2, 0, 2, 2})
                .withRaider(PRESERVER,          new int[]{0, 1, 0, 1, 2, 1, 3, 4})
                .withRaider(ABSORBER,           new int[]{0, 0, 1, 0, 1, 0, 0, 2})
                .withRaider(CROCOFANG,          new int[]{0, 0, 1, 1, 2, 3, 1, 3})
                .withRaider(MAGISPELLER,        new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(SPIRITCALLER,       new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(FREAKAGER,          new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(BOSS_RANDOMIZER,    new int[]{0, 0, 0, 1, 0, 1, 0, 1})
                .withRaider(GRIEFER,            new int[]{0, 1, 1, 2, 2, 2, 3, 3})
                .withRaider(EXECUTIONER,        new int[]{0, 1, 1, 1, 0, 1, 2, 2})
                .withRaider(TRICKSTER,          new int[]{0, 0, 1, 0, 1, 1, 1, 2})
                .withRaider(ICEOLOGER_SR,       new int[]{0, 0, 0, 1, 0, 1, 0, 1})
                .withRaider(MOUNTAINEER,        new int[]{0, 1, 3, 1, 1, 4, 2, 5})
                .withRaider(ROYAL_GUARD,        new int[]{0, 1, 1, 1, 1, 2, 1, 2})
                .withRaider(GEOMANCER,          new int[]{0, 0, 1, 1, 0, 2, 1, 2})
                .withRaider(ILLUSIONER_DM,      new int[]{0, 0, 1, 0, 1, 1, 1, 2})
                .withRaider(MAGE,               new int[]{0, 0, 1, 1, 0, 2, 0, 2})
                .withRaider(ICEOLOGER_DM,       new int[]{0, 0, 0, 1, 0, 1, 0, 1})
                .withRaider(WINDCALLER,         new int[]{0, 0, 1, 1, 0, 2, 1, 2})
                .withRaider(SQUALL_GOLEM,       new int[]{0, 0, 1, 1, 1, 1, 0, 2})
                .withRaider(REDSTONE_GOLEM,     new int[]{0, 0, 0, 1, 0, 1, 0, 1})
                .withEliteWave(5, NUAOS_ELITE.get())
                .withEliteWave(7, NUAOS_ELITE.get(), VOLDON_ELITE.get())
                .register();

        RaidEnemyRegistry.createWavesFor(RaidDifficulty.LEGEND)
                .withRaider(PILLAGER,           new int[]{0, 4, 3, 3, 4, 5, 5, 3})
                .withRaider(VINDICATOR,         new int[]{0, 2, 3, 1, 3, 4, 2, 3})
                .withRaider(WARRIOR,            new int[]{0, 2, 2, 3, 3, 1, 4, 4})
                .withRaider(SKIRMISHER,         new int[]{0, 2, 2, 2, 3, 2, 2, 3})
                .withRaider(TANK,               new int[]{0, 0, 2, 1, 2, 1, 2, 1})
                .withRaider(LEGIONER,           new int[]{0, 1, 1, 2, 2, 3, 3, 4})
                .withRaider(DART,               new int[]{0, 0, 2, 1, 2, 1, 3, 0})
                .withRaider(HUNTER,             new int[]{0, 1, 3, 2, 3, 2, 3, 4})
                .withRaider(ARCHER,             new int[]{0, 2, 2, 4, 3, 4, 3, 6})
                .withRaider(WITCH,              new int[]{0, 1, 1, 2, 3, 1, 2, 2})
                .withRaider(RAVAGER,            new int[]{0, 0, 1, 1, 0, 2, 1, 2})
                .withRaider(ILLUSIONER,         new int[]{0, 0, 1, 1, 1, 0, 1, 0})
                .withRaider(ASSASSIN,           new int[]{0, 1, 1, 1, 1, 1, 1, 1})
                .withRaider(EVOKER,             new int[]{0, 0, 2, 2, 1, 2, 2, 2})
                .withRaider(CONDUCTOR,          new int[]{0, 0, 1, 0, 0, 0, 1, 1})
                .withRaider(NECROMANCER,        new int[]{0, 0, 0, 2, 0, 1, 2, 1})
                .withRaider(FROSTMAGE,          new int[]{0, 0, 0, 0, 2, 2, 0, 1})
                .withRaider(SHAMAN,             new int[]{0, 0, 1, 1, 1, 2, 2, 3})
                .withRaider(ENCHANTER,          new int[]{0, 1, 2, 0, 0, 2, 0, 2})
                .withRaider(IGNITER,            new int[]{0, 2, 2, 3, 2, 3, 2, 5})
                .withRaider(TWITTOLLAGER,       new int[]{0, 1, 2, 1, 2, 1, 2, 3})
                .withRaider(PRESERVER,          new int[]{0, 1, 1, 2, 2, 2, 4, 4})
                .withRaider(ABSORBER,           new int[]{0, 0, 1, 1, 2, 0, 1, 2})
                .withRaider(CROCOFANG,          new int[]{0, 1, 2, 1, 2, 3, 2, 4})
                .withRaider(MAGISPELLER,        new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(SPIRITCALLER,       new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(FREAKAGER,          new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(BOSS_RANDOMIZER,    new int[]{0, 0, 1, 0, 0, 1, 1, 1})
                .withRaider(GRIEFER,            new int[]{0, 1, 2, 2, 3, 2, 3, 3})
                .withRaider(EXECUTIONER,        new int[]{0, 1, 2, 1, 1, 2, 2, 3})
                .withRaider(TRICKSTER,          new int[]{0, 0, 1, 0, 1, 2, 2, 3})
                .withRaider(ICEOLOGER_SR,       new int[]{0, 0, 1, 2, 0, 2, 0, 2})
                .withRaider(MOUNTAINEER,        new int[]{0, 2, 3, 2, 2, 5, 2, 6})
                .withRaider(ROYAL_GUARD,        new int[]{0, 1, 2, 2, 1, 4, 1, 4})
                .withRaider(GEOMANCER,          new int[]{0, 1, 1, 2, 1, 2, 1, 3})
                .withRaider(ILLUSIONER_DM,      new int[]{0, 0, 1, 2, 1, 3, 1, 3})
                .withRaider(MAGE,               new int[]{0, 1, 1, 1, 0, 2, 1, 3})
                .withRaider(ICEOLOGER_DM,       new int[]{0, 0, 1, 2, 0, 2, 0, 2})
                .withRaider(WINDCALLER,         new int[]{0, 1, 1, 2, 1, 2, 1, 3})
                .withRaider(SQUALL_GOLEM,       new int[]{0, 1, 1, 2, 1, 2, 1, 2})
                .withRaider(REDSTONE_GOLEM,     new int[]{0, 1, 1, 1, 1, 1, 1, 1})
                .withEliteWave(3, NUAOS_ELITE.get(), VOLDON_ELITE.get())
                .withEliteWave(5, VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(7, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .register();

        RaidEnemyRegistry.createWavesFor(RaidDifficulty.MASTER)
                .withRaider(PILLAGER,           new int[]{0, 5, 6, 5, 6, 6, 5, 5})
                .withRaider(VINDICATOR,         new int[]{0, 3, 2, 3, 3, 2, 3, 4})
                .withRaider(WARRIOR,            new int[]{0, 3, 1, 3, 3, 2, 4, 4})
                .withRaider(SKIRMISHER,         new int[]{0, 2, 2, 4, 3, 5, 2, 5})
                .withRaider(TANK,               new int[]{0, 2, 2, 2, 3, 3, 3, 3})
                .withRaider(LEGIONER,           new int[]{0, 1, 2, 3, 2, 4, 3, 5})
                .withRaider(DART,               new int[]{0, 0, 2, 2, 2, 2, 3, 4})
                .withRaider(HUNTER,             new int[]{0, 3, 4, 4, 4, 5, 3, 4})
                .withRaider(ARCHER,             new int[]{0, 3, 3, 5, 4, 5, 5, 7})
                .withRaider(WITCH,              new int[]{0, 1, 3, 5, 4, 5, 3, 3})
                .withRaider(RAVAGER,            new int[]{0, 1, 1, 1, 0, 3, 1, 3})
                .withRaider(ILLUSIONER,         new int[]{0, 1, 1, 2, 1, 1, 2, 3})
                .withRaider(ASSASSIN,           new int[]{0, 2, 2, 2, 2, 2, 2, 2})
                .withRaider(EVOKER,             new int[]{0, 1, 2, 3, 4, 1, 1, 3})
                .withRaider(CONDUCTOR,          new int[]{0, 1, 2, 0, 1, 2, 2, 3})
                .withRaider(NECROMANCER,        new int[]{0, 1, 0, 3, 1, 2, 0, 3})
                .withRaider(FROSTMAGE,          new int[]{0, 1, 0, 0, 1, 2, 4, 3})
                .withRaider(SHAMAN,             new int[]{0, 2, 2, 2, 2, 3, 3, 3})
                .withRaider(ENCHANTER,          new int[]{0, 1, 1, 1, 1, 1, 1, 3})
                .withRaider(IGNITER,            new int[]{0, 3, 2, 4, 3, 4, 3, 6})
                .withRaider(TWITTOLLAGER,       new int[]{0, 1, 3, 1, 3, 2, 2, 3})
                .withRaider(PRESERVER,          new int[]{0, 2, 2, 2, 2, 2, 4, 4})
                .withRaider(ABSORBER,           new int[]{0, 1, 1, 1, 2, 1, 1, 3})
                .withRaider(CROCOFANG,          new int[]{0, 2, 2, 3, 2, 4, 2, 5})
                .withRaider(MAGISPELLER,        new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(SPIRITCALLER,       new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(FREAKAGER,          new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(BOSS_RANDOMIZER,    new int[]{0, 0, 1, 1, 0, 1, 1, 1})
                .withRaider(GRIEFER,            new int[]{0, 2, 3, 2, 3, 3, 3, 4})
                .withRaider(EXECUTIONER,        new int[]{0, 2, 2, 2, 3, 2, 3, 4})
                .withRaider(TRICKSTER,          new int[]{0, 1, 2, 1, 2, 2, 2, 3})
                .withRaider(ICEOLOGER_SR,       new int[]{0, 1, 2, 3, 1, 3, 1, 3})
                .withRaider(MOUNTAINEER,        new int[]{0, 3, 4, 5, 2, 7, 4, 7})
                .withRaider(ROYAL_GUARD,        new int[]{0, 2, 2, 3, 2, 4, 3, 5})
                .withRaider(GEOMANCER,          new int[]{0, 2, 1, 3, 2, 3, 1, 3})
                .withRaider(ILLUSIONER_DM,      new int[]{0, 1, 2, 2, 2, 3, 2, 3})
                .withRaider(MAGE,               new int[]{0, 1, 2, 2, 1, 3, 1, 3})
                .withRaider(ICEOLOGER_DM,       new int[]{0, 1, 2, 3, 1, 3, 1, 3})
                .withRaider(WINDCALLER,         new int[]{0, 1, 2, 3, 2, 3, 2, 3})
                .withRaider(SQUALL_GOLEM,       new int[]{0, 1, 2, 2, 2, 3, 2, 3})
                .withRaider(REDSTONE_GOLEM,     new int[]{0, 1, 1, 2, 1, 2, 1, 2})
                .withEliteWave(1, NUAOS_ELITE.get(), VOLDON_ELITE.get())
                .withEliteWave(3, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(5, XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(6, XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(7, XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .register();

        RaidEnemyRegistry.createWavesFor(RaidDifficulty.GRANDMASTER)
                .withRaider(PILLAGER,           new int[]{0, 6, 7, 6, 7, 7, 6, 6})
                .withRaider(VINDICATOR,         new int[]{0, 4, 3, 4, 4, 3, 4, 5})
                .withRaider(WARRIOR,            new int[]{0, 4, 2, 4, 4, 3, 5, 5})
                .withRaider(SKIRMISHER,         new int[]{0, 2, 2, 4, 3, 5, 2, 5})
                .withRaider(TANK,               new int[]{0, 2, 2, 3, 4, 4, 4, 4})
                .withRaider(LEGIONER,           new int[]{0, 1, 2, 3, 2, 4, 3, 5})
                .withRaider(DART,               new int[]{0, 0, 2, 2, 2, 2, 3, 4})
                .withRaider(HUNTER,             new int[]{0, 3, 4, 4, 4, 5, 3, 4})
                .withRaider(ARCHER,             new int[]{0, 3, 3, 5, 4, 5, 5, 7})
                .withRaider(WITCH,              new int[]{0, 1, 3, 5, 4, 5, 3, 3})
                .withRaider(RAVAGER,            new int[]{0, 1, 1, 1, 0, 3, 1, 3})
                .withRaider(ILLUSIONER,         new int[]{0, 0, 1, 2, 1, 1, 2, 3})
                .withRaider(ASSASSIN,           new int[]{0, 2, 2, 2, 2, 2, 2, 2})
                .withRaider(EVOKER,             new int[]{0, 1, 2, 3, 4, 1, 1, 3})
                .withRaider(CONDUCTOR,          new int[]{0, 1, 2, 0, 1, 2, 2, 3})
                .withRaider(NECROMANCER,        new int[]{0, 1, 0, 3, 1, 2, 0, 3})
                .withRaider(FROSTMAGE,          new int[]{0, 1, 0, 0, 1, 2, 4, 3})
                .withRaider(SHAMAN,             new int[]{0, 2, 2, 2, 2, 3, 3, 3})
                .withRaider(ENCHANTER,          new int[]{0, 1, 1, 1, 1, 1, 1, 3})
                .withRaider(IGNITER,            new int[]{0, 3, 2, 4, 3, 4, 3, 6})
                .withRaider(TWITTOLLAGER,       new int[]{0, 1, 3, 1, 3, 2, 2, 3})
                .withRaider(PRESERVER,          new int[]{0, 2, 2, 2, 2, 2, 4, 4})
                .withRaider(ABSORBER,           new int[]{0, 1, 1, 1, 2, 1, 1, 3})
                .withRaider(CROCOFANG,          new int[]{0, 2, 2, 3, 2, 4, 2, 5})
                .withRaider(MAGISPELLER,        new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(SPIRITCALLER,       new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(FREAKAGER,          new int[]{0, 0, 0, 0, 0, 0, 0, 0})
                .withRaider(BOSS_RANDOMIZER,    new int[]{0, 1, 1, 1, 1, 1, 1, 1})
                .withRaider(GRIEFER,            new int[]{0, 2, 3, 2, 3, 3, 3, 4})
                .withRaider(EXECUTIONER,        new int[]{0, 2, 2, 2, 3, 2, 3, 4})
                .withRaider(TRICKSTER,          new int[]{0, 1, 2, 1, 2, 2, 2, 3})
                .withRaider(ICEOLOGER_SR,       new int[]{0, 1, 2, 3, 1, 3, 1, 3})
                .withRaider(MOUNTAINEER,        new int[]{0, 3, 4, 5, 2, 7, 4, 7})
                .withRaider(ROYAL_GUARD,        new int[]{0, 2, 2, 3, 2, 4, 3, 5})
                .withRaider(GEOMANCER,          new int[]{0, 2, 1, 3, 2, 3, 1, 3})
                .withRaider(ILLUSIONER_DM,      new int[]{0, 1, 2, 2, 2, 3, 2, 3})
                .withRaider(MAGE,               new int[]{0, 1, 2, 2, 1, 3, 1, 3})
                .withRaider(ICEOLOGER_DM,       new int[]{0, 1, 2, 3, 1, 3, 1, 3})
                .withRaider(WINDCALLER,         new int[]{0, 1, 2, 3, 2, 3, 2, 3})
                .withRaider(SQUALL_GOLEM,       new int[]{0, 1, 2, 2, 2, 3, 2, 3})
                .withRaider(REDSTONE_GOLEM,     new int[]{0, 1, 1, 3, 1, 3, 1, 3})
                .withEliteWave(1, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(2, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(3, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(4, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(5, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(6, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(7, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .register();

        //Reflection Option:
        //ObfuscationReflectionHelper.findField(Raid.RaiderType.class, "f_37815_").set(Raid.RaiderType.VINDICATOR, new int[]{0, 0, 2, 0, 1, 4, 2, 5});
    }

    public static boolean isEliteWave(RaidDifficulty raidDifficulty, int wave)
    {
        return WAVES.get(raidDifficulty).eliteWaves.containsKey(wave);
    }

    public static EntityType<?> getRandomElite(RaidDifficulty raidDifficulty, int wave)
    {
        List<EntityType<?>> pool = WAVES.get(raidDifficulty).eliteWaves.get(wave);

        if(pool.size() == 1) return pool.get(0);
        else return pool.get(new Random().nextInt(pool.size()));
    }

    public static int[] getWaves(RaidDifficulty raidDifficulty, String raiderType)
    {
        return WAVES.get(raidDifficulty).waves.getOrDefault(raiderType.toUpperCase(), BLANK);
    }

    //Waves

    public static RaidEnemies createWavesFor(RaidDifficulty raidDifficulty)
    {
        return new RaidEnemies(raidDifficulty);
    }

    public static class RaidEnemies
    {
        private final RaidDifficulty raidDifficulty;
        private final Map<String, int[]> waves;
        private final Map<Integer, List<EntityType<?>>> eliteWaves;

        RaidEnemies(RaidDifficulty raidDifficulty)
        {
            this.raidDifficulty = raidDifficulty;
            this.waves = new HashMap<>();
            this.eliteWaves =new HashMap<>();
        }

        public RaidEnemies withRaider(String raider, int[] waves)
        {
            this.waves.put(raider, waves);
            return this;
        }

        public RaidEnemies withEliteWave(int wave, EntityType<?>... elites)
        {
            this.eliteWaves.put(wave, List.of(elites));
            return this;
        }

        public void register()
        {
            WAVES.put(this.raidDifficulty, this);
            REGISTERED_RAIDER_TYPES.addAll(this.waves.keySet());
        }
    }
}
