package com.calculusmaster.difficultraids.raids;

import com.Polarice3.Goety.common.entities.ModEntityType;
import com.belgieyt.morefeatures.core.registry.MFEntity;
import com.calculusmaster.difficultraids.DifficultRaids;
import com.calculusmaster.difficultraids.data.raiderentries.RaiderEntriesHolder;
import com.calculusmaster.difficultraids.setup.DifficultRaidsConfig;
import com.calculusmaster.difficultraids.util.Compat;
//import io.redspace.ironsspellbooks.registries.EntityRegistry;
import com.faboslav.friendsandfoes.common.init.FriendsAndFoesEntityTypes;
import com.legacy.conjurer_illager.registry.IllagerEntityTypes;
import com.mojang.logging.LogUtils;
import com.pikachu.mod.illager_more.init.ModEntityTypes;
import com.possible_triangle.brazier.Content;
import com.sh1nylabs.bonesupdate.init.BonesEntities;
import com.teamabnormals.savage_and_ravage.core.registry.SREntityTypes;
import comfrancisplayz446.necromancer.init.NecromancerModEntities;
import net.firefoxsalesman.dungeonsmobs.entity.ModEntities;
import net.mcreator.francisillagers.init.FrancisillagersModEntities;
import net.mcreator.justillagers.init.JustillagersModEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.mobz.init.MobZEntities;
import org.slf4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static com.calculusmaster.difficultraids.entity.DifficultRaidsEntityTypes.*;

public class RaidEnemyRegistry
{
    public static final Map<RaidDifficulty, RaidDifficultyEnemyManager> DEFAULT_WAVES = new HashMap<>();
    public static final Map<RaidDifficulty, RaidDifficultyEnemyManager> CURRENT_WAVES = new HashMap<>();
    public static final Set<String> REGISTERED_RAIDER_TYPES = new HashSet<>();

    //Minecraft
    public static final String VINDICATOR = "VINDICATOR";
    public static final String EVOKER = "EVOKER";
    public static final String PILLAGER = "PILLAGER";
    public static final String WITCH = "WITCH";
    public static final String RAVAGER = "RAVAGER";

    //Difficult Raids
    public static final String ILLUSIONER = "ILLUSIONER_MC";
    public static final String WARRIOR = "WARRIOR_ILLAGER";
    public static final String DART = "DART_ILLAGER";
    public static final String CONDUCTOR = "ELECTRO_ILLAGER";
    public static final String NECROMANCER = "NECROMANCER_ILLAGER";
    public static final String SHAMAN = "SHAMAN_ILLAGER";
    public static final String TANK = "TANK_ILLAGER";
    public static final String ASSASSIN = "ASSASSIN_ILLAGER";
    public static final String FROSTMAGE = "FROST_ILLAGER";
    public static final String ASHENMANCER = "ASHENMANCER_ILLAGER";

    public static final String NUAOS = "NUAOS_ELITE";
    public static final String XYDRAX = "XYDRAX_ELITE";
    public static final String MODUR = "MODUR_ELITE";
    public static final String VOLDON = "VOLDON_ELITE";

    //HuntersReturn
    public static final String HUNTER = "hunters_return";

    //Enchant With Mob
    public static final String ENCHANTER = "enchanter";

    //It Takes a Pillage
    public static final String ARCHER = "ARCHER";
    public static final String SKIRMISHER = "SKIRMISHER";
    public static final String LEGIONER = "LEGIONER";

    //Illage & Spillage
    public static final String IGNITER = "entity.illageandspillage.igniter";
    public static final String TWITTOLLAGER = "entity.illageandspillage.twittollager";
    public static final String PRESERVER = "entity.illageandspillage.preserver";
    public static final String ABSORBER = "entity.illageandspillage.absorber";
    public static final String CROCOFANG = "entity.illageandspillage.crocofang";
    public static final String ENGINEER = "entity.illageandspillage.engineer";
    public static final String MAGISPELLER = "entity.illageandspillage.magispeller";
    public static final String SPIRITCALLER = "entity.illageandspillage.spiritcaller";
    public static final String FREAKAGER = "entity.illageandspillage.freakager";
    public static final String BOSS_RANDOMIZER = "entity.illageandspillage.boss_randomizer";

    //Savage and Ravage
    public static final String GRIEFER = "GRIEFER";
    public static final String EXECUTIONER = "EXECUTIONER";
    public static final String TRICKSTER = "TRICKSTER";
    public static final String ICEOLOGER_SR = "SR_ICEOLOGER";

    //Dungeons Mobs
    public static final String MOUNTAINEER = "mountaineer";
    public static final String ROYAL_GUARD = "royal_guard";
    public static final String GEOMANCER = "geomancer";
    public static final String ILLUSIONER_DM = "DM_ILLUSIONER";
    public static final String MAGE = "mage";
    public static final String ICEOLOGER_DM = "DM_ICEOLOGER";
    public static final String WINDCALLER = "windcaller";
    public static final String SQUALL_GOLEM = "squall_golem";
    public static final String REDSTONE_GOLEM = "redstone_golem";

    //Illager Revolution
    public static final String BLADE_KNIGHT = "blade_knight";
    public static final String BEAST_TAMER = "illager_beast_tamer";
    public static final String SCAVENGER = "illager_scavenger";
    public static final String SOUL_SAGE = "soul_sage";

    //Leo's Illagers
    public static final String LIGHTNINGCALLER = "entity.leosillagers.lightning_caller";
    public static final String CLOWNAGER = "entity.leosillagers.clownager";
    public static final String CONFUSER = "entity.leosillagers.confuser";
    public static final String SHIELD_VINDICATOR = "entity.leosillagers.vindicator_with_shield";
    public static final String METEORITE_CALLER = "entity.leosillagers.meteorite_caller";
    public static final String SNOWOLAGER = "entity.leosillagers.snowolager";
    public static final String NECROMANCER_LEO = "entity.leosillagers.necromancer";
    public static final String SUMMONER = "entity.leosillagers.summoner";
    public static final String TROUBLEMAKER = "entity.leosillagers.troublemaker";

    //The Conjurer
    public static final String CONJURER = "conjurer";

    //Necromancer Mod Port
    public static final String NECROMANCER_MOD = "necromancer_mod";

    //Werden's Illagers +
    public static final String SINISTER = "sinister";
    public static final String SHADOMANCER = "shadomancer";

    //Iron's Spells and Spellbooks
    public static final String ARCHEVOKER = "archevoker";

    //Gambler Illager
    public static final String GAMBLER = "gambler";

    //Guard Illagers
    public static final String GUARD_ILLAGER = "guard_illager";

    //Illager Additions
    public static final String ROYAL_GUARD_SPEAR = "royal_longaxe_guard";
    public static final String SPEARMAN = "zaaaaguard";
    public static final String SAMURAI = "samurai";
    public static final String BEAMLOGER = "blastioner";
    public static final String COWBOY = "cowboy_IA";
    public static final String SHOGUN = "shogun";

    //MobZ
    public static final String SPIDER_MAGE = "spider_mage";
    public static final String ZOMBIE_MAGE = "zombie_mage";
    public static final String PILLAGER_BOSS = "pillager_boss";
    public static final String ILLUSIONER_MOBZ = "illusioner_mobz";
    public static final String BABY_RAVAGER = "baby_ravager";

    //Illager Invasion
    public static final String PROVOKER = "ILLAGERINVASION_PROVOKER";
    public static final String INQUISITOR = "ILLAGERINVASION_INQUISITOR";
    public static final String MARAUDER = "ILLAGERINVASION_MARAUDER";
    public static final String BASHER = "ILLAGERINVASION_BASHER";
    public static final String FIRECALLER = "ILLAGERINVASION_FIRECALLER";
    public static final String NECROMANCER_INV = "ILLAGERINVASION_NECROMANCER";
    public static final String ALCHEMIST = "ILLAGERINVASION_ALCHEMIST";
    public static final String SORCERER = "ILLAGERINVASION_SORCERER";
    public static final String ARCHIVIST = "ILLAGERINVASION_ARCHIVIST";
    public static final String INVOKER = "ILLAGERINVASION_INVOKER";

    //Goety
    public static final String WARLOCK = "GOETY_WARLOCK";
    public static final String MAVERICK = "GOETY_MAVERICK";
    public static final String HERETIC = "GOETY_HERETIC";
    public static final String PIKER = "GOETY_PIKER";
    public static final String RIPPER = "GOETY_RIPPER";
    public static final String CRUSHER = "GOETY_CRUSHER";
    public static final String STORM_CASTER = "GOETY_STORM_CASTER";
    public static final String CRYOLOGER = "GOETY_CRYOLOGER";
    public static final String PREACHER = "GOETY_PREACHER";
    public static final String CONQUILLAGER = "GOETY_CONQUILLAGER";
    public static final String INQUILLAGER = "GOETY_INQUILLAGER";
    public static final String ENVIOKER = "GOETY_ENVIOKER";
    public static final String SORCERER_GOETY = "GOETY_SORCERER";
    public static final String HOSTILE_RED_GOLEM = "GOETY_HOSTILE_RED_GOLEM";
    public static final String HOSTILE_RED_MONSTER = "GOETY_HOSTILE_RED_MONSTER";
    public static final String MINISTER = "GOETY_MINISTER";
    public static final String TRAMPLER = "GOETY_TRAMPLER";
    public static final String VIZIER = "GOETY_VIZIER";
    public static final String APOSTLE = "GOETY_APOSTLE";

    //The Summoner Illager
    public static final String THE_SUMMONER = "summoner";
    public static final String THE_SUMMONER_BOSS = "summoner_boss";

    //Bones Update
    public static final String NECROMANCER_BONES = "NECROMANCER_BONES";

    //Musketeer Illager
    public static final String MARKSMAN = "marksman";

    //The Modifiger
    public static final String MODIFIGER = "modifiger";

    //Masquerader
    public static final String MASQUERADER = "masquerader";

    //Bagus Mob
    public static final String TENGU = "tengu";
    public static final String NINJAR = "ninjar";

    //Raided
    public static final String NECROMANCER_RAIDED = "raided:necromancer";
    public static final String ELECTROMANCER_RAIDED = "raided:electromancer";
    public static final String SAVAGER = "raided:savager";
    public static final String INCINERATOR = "raided:incinerator";
    public static final String INQUISITOR_RAIDED = "raided:inquisitor";

    //More Illagers
    public static final String GUNILLAGER = "gunillager";
    public static final String ILLIGEVE = "illgeve";
    public static final String SURPRISER = "surpriser";
    public static final String ROCKETILLAGER = "rocketillager";
    public static final String CREEPILLAGER = "creepillager";
    public static final String MONSTERILLAGER = "monsterillager";

    //Slash Illager
    public static final String BLADE_MASTER = "blade_master";

    //Friends & Foes
    public static final String ICEOLOGER_FF = "ICEOLOGER_FF";

    //Mo' Features
    public static final String PILLAGER_BRUTE = "pillager_brute";

    //Brazier
    public static final String CRAZED = "crazed";

    //Crimson Steves Mobs
    public static final String T_RABUS = "t_rabus_boss";
    public static final String CYBORG_VINDICATOR = "modid:cyborg_vindicator";
    public static final String CRUDE_RED_GOLEM = "crude_redstone_golem";
    public static final String CRUDE_RED_MONSTROSITY = "crude_redstone_monstrosity";
    public static final String RED_MONSTROSITY_CSM = "original_redstone_monstrosity";
    public static final String MINI_CRUDE_RED_GOLEM = "mini_crude_redstone_golem";
    public static final String PHANTOM_TAMER = "phantom_tamer";

    //Ravage & Cabbage
    public static final String CABBAGER = "cabbager";

    //All Bark All Bite
    public static final String HOUNDMASTER = "houndmaster";

    //Karate Illager
    public static final String KARATE = "karate_illager";

    //Illager Brute
    public static final String ILLAGER_BRUTE = "illagerbrute";

    //Sorcerer Illager
    public static final String SORCERER_ILLAGER = "sorcererillager";

    //Wandering Illager
    public static final String WANDERING_ILLAGER = "wanderingillager";

    //Expandos Illagers
    public static final String BRASHER = "brasher";
    public static final String KNOCKER = "knocker";
    public static final String WRECKER = "wrecker";
    public static final String HACKLE = "hackle";
    public static final String JUGGERNAUT = "juggernaut";
    public static final String BLUDGEONER = "bludgeoner";
    public static final String PIKELOGER = "pikeloger";
    public static final String GRINDICATOR = "grindicator";

    //Francis Illagers
    public static final String OMNILLAGER = "omnillager";
    public static final String COWBOY_FRANCIS = "cowboy_francis";
    public static final String THROWER = "thrower";
    public static final String BLADE_GUARD = "blade_guard";
    public static final String RUNNER = "runner";
    public static final String DESERT_ILLAGER = "lightning_caller";
    public static final String CRINDICATOR = "crindicator";
    public static final String CREAKOLOGER = "creakologer";
    public static final String JURGOLOGER = "jurgologer";
    public static final String BLACK_MASTER = "blackmaster";

    //Earth Mobs
    public static final String VILER_WITCH = "viler_witch";

    //Illager World War
    public static final String PILLAGER_CAR = "pillager_car";
    public static final String PILLAGER_SOLDIER = "pillager_soldier_armed";
    public static final String VINDICATOR_FLAMETHROWER = "pillager_soldier_with_flamethrower";
    public static final String ASSAULT_PILLAGER = "assault_pillager_soldier";
    public static final String PILLAGER_PLANE = "plane_avec_pillager_soldier";
    public static final String PILLAGER_CANNON = "cannon_with_soldier_pillager";

    //Colds: Wandering Traidor
    public static final String WANDERING_TRAITOR = "wandering_traitor";

    //Just Illagers
    public static final String DRUSKI = "druski";
    public static final String LACHER = "guard";
    public static final String CRISKO = "crisko";
    public static final String BLAKER = "blaker";
    public static final String TOXICIST = "toxicist";
    public static final String REVENANT = "reverant";
    public static final String AMPI = "ampi";

    private static final int[] BLANK = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

    public static boolean isRaiderTypeEnabled(String raiderType)
    {
        return DifficultRaidsConfig.ENABLED_RAIDERS.containsKey(raiderType) && DifficultRaidsConfig.ENABLED_RAIDERS.get(raiderType).get();
    }

    public static boolean isRaiderTypeRegistered(String raiderType)
    {
        return REGISTERED_RAIDER_TYPES.contains(raiderType);
    }

    public static void registerRaiders()
    {
        RaidEnemyRegistry.createRaiderType(ILLUSIONER, EntityType.ILLUSIONER);
        RaidEnemyRegistry.createRaiderType(WARRIOR, WARRIOR_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(DART, DART_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(CONDUCTOR, ELECTRO_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(NECROMANCER, NECROMANCER_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(SHAMAN, SHAMAN_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(TANK, TANK_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(ASSASSIN, ASSASSIN_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(FROSTMAGE, FROST_ILLAGER.get());
        RaidEnemyRegistry.createRaiderType(ASHENMANCER, ASHENMANCER_ILLAGER.get());

        RaidEnemyRegistry.createRaiderType(NUAOS, NUAOS_ELITE.get());
        RaidEnemyRegistry.createRaiderType(XYDRAX, XYDRAX_ELITE.get());
        RaidEnemyRegistry.createRaiderType(MODUR, MODUR_ELITE.get());
        RaidEnemyRegistry.createRaiderType(VOLDON, VOLDON_ELITE.get());

        //Compatibility
        if(Compat.SAVAGE_AND_RAVAGE.isLoaded()) RaidEnemyRegistry.createRaiderType(ICEOLOGER_SR, SREntityTypes.ICEOLOGER.get());
        if(Compat.DUNGEONS_MOBS.isLoaded())
        {
            //RaidEnemyRegistry.createRaiderType(ILLUSIONER_DM, ModEntities.ILLUSIONER.get());
            RaidEnemyRegistry.createRaiderType(ICEOLOGER_DM, ModEntities.ICEOLOGER.get());
        }
        if(Compat.CONJURER.isLoaded()) RaidEnemyRegistry.createRaiderType(CONJURER, IllagerEntityTypes.CONJURER);
        //Archevoker and Gambler doesn't extend EntityType Raider, so we cant add them in raids
        //if(Compat.IRONS_SPELLBOOKS.isLoaded()) RaidEnemyRegistry.createRaiderType(ARCHEVOKER, EntityRegistry.ARCHEVOKER.get());
        //if(Compat.GAMBLER.isLoaded()) RaidEnemyRegistry.createRaiderType(GAMBLER, castToRaiderType(com.min01.gambler.entity.ModEntities.GAMBLER.get()));
        if(Compat.MOBZ.isLoaded())
        {
            RaidEnemyRegistry.createRaiderType(SPIDER_MAGE, MobZEntities.SPIDER_MAGE.get());
            RaidEnemyRegistry.createRaiderType(ZOMBIE_MAGE, MobZEntities.ZOMBIE_MAGE.get());
            RaidEnemyRegistry.createRaiderType(PILLAGER_BOSS, MobZEntities.PILLAGER_BOSS.get());
            RaidEnemyRegistry.createRaiderType(ILLUSIONER_MOBZ, MobZEntities.ILLUSIONER.get());
            RaidEnemyRegistry.createRaiderType(BABY_RAVAGER, MobZEntities.BABY_RAVAGER.get());
        }
        if(Compat.NECROMANCER.isLoaded()) RaidEnemyRegistry.createRaiderType(NECROMANCER_MOD, NecromancerModEntities.NECROMANCER.get());
        if(Compat.BONES_UPDATE.isLoaded()) RaidEnemyRegistry.createRaiderType(NECROMANCER_BONES, BonesEntities.NECROMANCER.get());
        if(Compat.GOETY.isLoaded())
        {
            RaidEnemyRegistry.createRaiderType(TRAMPLER, ModEntityType.TRAMPLER.get());
            RaidEnemyRegistry.createRaiderType(VIZIER, ModEntityType.VIZIER.get());
            RaidEnemyRegistry.createRaiderType(APOSTLE, ModEntityType.APOSTLE.get());
        }
        if(Compat.FRIENDS_AND_FOES.isLoaded()) RaidEnemyRegistry.createRaiderType(ICEOLOGER_FF, FriendsAndFoesEntityTypes.ICEOLOGER.get());
        if(Compat.BRAZIER.isLoaded()) RaidEnemyRegistry.createRaiderType(CRAZED, Content.CRAZED.get());
        //I dont know why but Mo Features mobs are not registered in raids even if they do it, so we register it here
        if(Compat.MO_FEATURES.isLoaded()) RaidEnemyRegistry.createRaiderType(PILLAGER_BRUTE, MFEntity.PILLAGER_BRUTE.get());
        if(Compat.WANDERING_ILLAGER.isLoaded()) RaidEnemyRegistry.createRaiderType(WANDERING_ILLAGER, de.achtii.wandering_illager.entity.ModEntities.WANDERINGILLAGER.get());
        if(Compat.ILLAGER_ADDITIONS.isLoaded()) RaidEnemyRegistry.createRaiderType(COWBOY, ModEntityTypes.COWBOY.get());
        if(Compat.FRANCIS_ILLAGERS.isLoaded()) RaidEnemyRegistry.createRaiderType(COWBOY_FRANCIS, FrancisillagersModEntities.COWBOY.get());
    }

    public static void compileWaveData(final Map<ResourceLocation, RaiderEntriesHolder> data)
    {
        CURRENT_WAVES.clear();
        for(RaidDifficulty d : RaidDifficulty.values()) if(d != RaidDifficulty.DEFAULT)
            CURRENT_WAVES.put(d, new RaidDifficultyEnemyManager(DEFAULT_WAVES.get(d)));

        List<RaiderEntriesHolder> replaceEntries = new ArrayList<>();
        List<RaiderEntriesHolder> modifyEntries = new ArrayList<>();

        for(Map.Entry<ResourceLocation, RaiderEntriesHolder> entry : data.entrySet())
        {
            boolean replace = entry.getKey().getNamespace().equals(DifficultRaids.MODID);
            LogUtils.getLogger().info("DifficultRaids: Organizing Raid Data for ResourceLocation{%s}, replace=%s.".formatted(entry.getKey().toString(), replace));

            RaiderEntriesHolder holder = entry.getValue();
            holder.setReplace(replace);
            (replace ? replaceEntries : modifyEntries).add(holder);
        }

        List<RaiderEntriesHolder> sequentialHolders = new ArrayList<>();
        sequentialHolders.addAll(replaceEntries);
        sequentialHolders.addAll(modifyEntries);

        for(RaiderEntriesHolder holder : sequentialHolders)
        {
            for(var holderEntry : holder.getWaves().entrySet())
            {
                RaidDifficultyEnemyManager manager = CURRENT_WAVES.get(holderEntry.getKey());
                for(String raiderType : holderEntry.getValue().keySet())
                    manager.add(raiderType, holderEntry.getValue().get(raiderType), holder.isReplace());
            }
        }
    }

    public static void printWaveData(Logger logger)
    {
        for(RaidDifficultyEnemyManager d : CURRENT_WAVES.values())
            logger.info("Raid Data: " + d.toString());
    }

    private static void createRaiderType(String typeName, EntityType<? extends Raider> type)
    {
        Raid.RaiderType.create(typeName, type, BLANK);
    }


    public static void registerWaves()
    {
        RaidEnemyRegistry.createDefaultWavesFor(RaidDifficulty.HERO)
                .withRaider(PILLAGER,               0, 4, 3, 3, 4, 5, 5, 3)
                .withRaider(VINDICATOR,             0, 2, 1, 1, 2, 3, 1, 2)
                .withRaider(WARRIOR,                0, 2, 1, 1, 2, 1, 3, 2)
                .withRaider(SKIRMISHER,             0, 1, 1, 2, 1, 1, 1, 3)
                .withRaider(TANK,                   0, 0, 2, 0, 2, 0, 2, 1)
                .withRaider(LEGIONER,               0, 0, 0, 1, 2, 1, 3, 3)
                .withRaider(DART,                   0, 0, 0, 1, 1, 1, 0, 0)
                .withRaider(HUNTER,                 0, 1, 2, 2, 2, 2, 2, 3)
                .withRaider(ARCHER,                 0, 2, 1, 2, 3, 3, 3, 5)
                .withRaider(WITCH,                  0, 0, 1, 0, 3, 1, 0, 2)
                .withRaider(RAVAGER,                0, 0, 0, 1, 0, 2, 1, 2)
                .withRaider(ILLUSIONER,             0, 0, 1, 0, 0, 0, 1, 0)
                .withRaider(ASSASSIN,               0, 0, 0, 0, 0, 1, 0, 0)
                .withRaider(EVOKER,                 0, 0, 0, 0, 1, 0, 2, 2)
                .withRaider(CONDUCTOR,              0, 0, 0, 0, 0, 0, 0, 1)
                .withRaider(NECROMANCER,            0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(FROSTMAGE,              0, 0, 0, 0, 0, 1, 0, 0)
                .withRaider(ASHENMANCER,            0, 0, 0, 0, 0, 0, 0, 1)
                .withRaider(SHAMAN,                 0, 0, 0, 1, 0, 0, 1, 1)
                .withRaider(ENCHANTER,              0, 0, 1, 1, 1, 1, 0, 1)
                .withRaider(IGNITER,                0, 0, 1, 2, 2, 2, 2, 4)
                .withRaider(TWITTOLLAGER,           0, 0, 1, 1, 2, 0, 2, 2)
                .withRaider(PRESERVER,              0, 1, 0, 1, 2, 1, 3, 4)
                .withRaider(ABSORBER,               0, 0, 1, 0, 1, 0, 0, 2)
                .withRaider(CROCOFANG,              0, 0, 1, 1, 2, 3, 1, 3)
                .withRaider(ENGINEER,               0, 0, 0, 1, 0, 1, 1, 1)
                .withRaider(MAGISPELLER,            0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(SPIRITCALLER,           0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(FREAKAGER,              0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(BOSS_RANDOMIZER,        0, 0, 0, 0, 0, 1, 0, 0)
                .withRaider(GRIEFER,                0, 1, 1, 2, 2, 2, 3, 3)
                .withRaider(EXECUTIONER,            0, 1, 1, 1, 0, 1, 2, 2)
                .withRaider(TRICKSTER,              0, 0, 1, 0, 1, 1, 1, 2)
                .withRaider(ICEOLOGER_SR,           0, 0, 0, 1, 0, 1, 0, 1)
                .withRaider(MOUNTAINEER,            0, 1, 3, 1, 1, 4, 2, 5)
                .withRaider(ROYAL_GUARD,            0, 1, 1, 1, 1, 2, 1, 2)
                .withRaider(GEOMANCER,              0, 0, 1, 1, 0, 2, 1, 2)
                .withRaider(ILLUSIONER_DM,          0, 0, 1, 0, 1, 1, 1, 2)
                .withRaider(MAGE,                   0, 0, 1, 1, 0, 2, 0, 2)
                .withRaider(ICEOLOGER_DM,           0, 0, 0, 1, 0, 1, 0, 1)
                .withRaider(WINDCALLER,             0, 0, 1, 1, 0, 2, 1, 2)
                .withRaider(SQUALL_GOLEM,           0, 0, 1, 1, 1, 1, 0, 2)
                .withRaider(REDSTONE_GOLEM,         0, 0, 0, 1, 0, 1, 0, 1)
                .withRaider(BLADE_KNIGHT,           0, 0, 1, 0, 1, 0, 0, 2)
                .withRaider(BEAST_TAMER,            0, 0, 1, 0, 1, 0, 0, 1)
                .withRaider(SCAVENGER,              0, 1, 1, 1, 0, 1, 2, 2)
                .withRaider(SOUL_SAGE,              0, 0, 0, 0, 0, 0, 1, 0)
                .withRaider(LIGHTNINGCALLER,        0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(CLOWNAGER,              0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(CONFUSER,               0, 0, 1, 0, 1, 1, 1, 2)
                .withRaider(SHIELD_VINDICATOR,      0, 1, 0, 1, 1, 1, 0, 1)
                .withRaider(METEORITE_CALLER,       0, 0, 0, 0, 0, 0, 0, 1)
                .withRaider(SNOWOLAGER,             0, 0, 0, 1, 0, 1, 0, 1)
                .withRaider(NECROMANCER_LEO,        0, 0, 0, 0, 0, 1, 0, 1)
                .withRaider(SUMMONER,               0, 0, 0, 0, 0, 1, 0, 1)
                .withRaider(TROUBLEMAKER,           0, 1, 0, 1, 1, 1, 0, 1)
                .withRaider(CONJURER,               0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(NECROMANCER_MOD,        0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(SINISTER,               0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(SHADOMANCER,            0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(ARCHEVOKER,             0, 0, 0, 1, 0, 0, 0, 0)
                .withRaider(GAMBLER,                0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(GUARD_ILLAGER,          0, 1, 1, 2, 1, 2, 1, 1)
                .withRaider(ROYAL_GUARD_SPEAR,      0, 0, 1, 0, 0, 0, 1, 1)
                .withRaider(SPEARMAN,               0, 0, 0, 1, 1, 0, 0, 1)
                .withRaider(SAMURAI,                0, 0, 0, 1, 0, 0, 0, 0)
                .withRaider(BEAMLOGER,              0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(COWBOY,                 0, 0, 0, 0, 0, 1, 0, 0)
                .withRaider(SHOGUN,                 0, 0, 0, 0, 0, 0, 1, 1)
                .withRaider(SPIDER_MAGE,            0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(ZOMBIE_MAGE,            0, 0, 0, 1, 0, 0, 0, 0)
                .withRaider(PILLAGER_BOSS,          0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(ILLUSIONER_MOBZ,        0, 0, 0, 1, 0, 0, 0, 1)
                .withRaider(BABY_RAVAGER,           0, 1, 2, 0, 2, 1, 1, 0)
                .withRaider(PROVOKER,               0, 1, 0, 0, 0, 0, 1, 0)
                .withRaider(INQUISITOR,             0, 0, 0, 0, 0, 1, 0, 0)
                .withRaider(MARAUDER,               0, 0, 1, 0, 0, 0, 0, 1)
                .withRaider(BASHER,                 0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(FIRECALLER,             0, 0, 0, 0, 0, 1, 0, 1)
                .withRaider(NECROMANCER_INV,        0, 0, 1, 0, 0, 0, 0, 0)
                .withRaider(ALCHEMIST,              0, 0, 0, 1, 0, 0, 1, 1)
                .withRaider(SORCERER,               0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(ARCHIVIST,              0, 0, 0, 1, 0, 0, 0, 0)
                .withRaider(INVOKER,                0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(WARLOCK,                0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(MAVERICK,               0, 0, 1, 0, 0, 0, 1, 0)
                .withRaider(HERETIC,                0, 0, 0, 0, 0, 1, 0, 0)
                .withRaider(PIKER,                  0, 2, 2, 1, 2, 2, 1, 1)
                .withRaider(RIPPER,                 0, 0, 0, 1, 0, 1, 0, 0)
                .withRaider(CRUSHER,                0, 0, 1, 0, 1, 0, 1, 1)
                .withRaider(STORM_CASTER,           0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(CRYOLOGER,              0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(PREACHER,               0, 0, 0, 0, 1, 1, 0, 0)
                .withRaider(CONQUILLAGER,           0, 1, 0, 1, 1, 0, 1, 0)
                .withRaider(INQUILLAGER,            0, 0, 1, 0, 0, 1, 0, 0)
                .withRaider(ENVIOKER,               0, 0, 0, 1, 0, 0, 1, 1)
                .withRaider(SORCERER_GOETY,         0, 0, 0, 0, 1, 0, 0, 1)
                .withRaider(HOSTILE_RED_GOLEM,      0, 0, 1, 0, 0, 1, 0, 0)
                .withRaider(HOSTILE_RED_MONSTER,    0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(MINISTER,               0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(TRAMPLER,               0, 0, 0, 1, 0, 1, 0, 1)
                .withRaider(VIZIER,                 0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(APOSTLE,                0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(THE_SUMMONER,           0, 0, 1, 0, 0, 1, 0, 0)
                .withRaider(THE_SUMMONER_BOSS,      0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(NECROMANCER_BONES,      0, 0, 0, 0, 1, 0, 0, 1)
                .withRaider(MODIFIGER,              0, 0, 0, 0, 1, 0, 1, 1)
                .withRaider(MARKSMAN,               0, 0, 1, 0, 1, 1, 0, 1)
                .withRaider(MASQUERADER,            0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(TENGU,                  0, 0, 0, 1, 0, 0, 1, 0)
                .withRaider(NINJAR,                 0, 1, 0, 0, 1, 2, 1, 1)
                .withRaider(NECROMANCER_RAIDED,     0, 0, 0, 1, 1, 0, 1, 1)
                .withRaider(ELECTROMANCER_RAIDED,   0, 1, 0, 0, 1, 1, 1, 1)
                .withRaider(SAVAGER,                0, 0, 1, 0, 1, 1, 1, 1)
                .withRaider(INCINERATOR,            0, 0, 0, 0, 1, 0, 0, 1)
                .withRaider(INQUISITOR_RAIDED,      0, 1, 1, 0, 1, 1, 0, 1)
                .withRaider(GUNILLAGER,             0, 0, 1, 0, 1, 1, 0, 1)
                .withRaider(ILLIGEVE,               0, 0, 0, 1, 0, 0, 0, 1)
                .withRaider(SURPRISER,              0, 0, 1, 0, 0, 1, 0, 1)
                .withRaider(ROCKETILLAGER,          0, 0, 0, 0, 1, 0, 0, 1)
                .withRaider(CREEPILLAGER,           0, 0, 0, 0, 0, 0, 0, 1)
                .withRaider(MONSTERILLAGER,         0, 0, 0, 0, 0, 0, 1, 0)
                .withRaider(BLADE_MASTER,           0, 0, 0, 0, 1, 0, 1, 0)
                .withRaider(ICEOLOGER_FF,           0, 0, 1, 0, 0, 1, 0, 1)
                .withRaider(PILLAGER_BRUTE,         0, 0, 0, 0, 1, 0, 0, 1)
                .withRaider(CRAZED,                 0, 0, 0, 1, 0, 1, 1, 0)
                .withRaider(CABBAGER,               0, 0, 1, 0, 1, 0, 1, 0)
                .withRaider(PHANTOM_TAMER,          0, 0, 0, 1, 0, 1, 0, 0)
                .withRaider(T_RABUS,                0, 0, 0, 0, 0, 0, 1, 0)
                .withRaider(CYBORG_VINDICATOR,      0, 0, 0, 0, 0, 0, 0, 1)
                .withRaider(CRUDE_RED_GOLEM,        0, 0, 0, 0, 1, 0, 0, 1)
                .withRaider(CRUDE_RED_MONSTROSITY,  0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(RED_MONSTROSITY_CSM,    0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(MINI_CRUDE_RED_GOLEM,   0, 1, 1, 0, 1, 0, 1, 1)
                .withRaider(HOUNDMASTER,            0, 0, 0, 1, 0, 1, 0, 0)
                .withRaider(KARATE,                 0, 1, 2, 1, 1, 1, 1, 1)
                .withRaider(ILLAGER_BRUTE,          0, 0, 0, 0, 1, 0, 0, 1)
                .withRaider(SORCERER_ILLAGER,       0, 0, 1, 1, 0, 1, 0, 0)
                .withRaider(WANDERING_ILLAGER,      0, 0, 0, 0, 0, 1, 1, 0)
                .withRaider(BRASHER,                0, 0, 1, 1, 0, 1, 0, 1)
                .withRaider(KNOCKER,                0, 1, 0, 0, 1, 1, 1, 0)
                .withRaider(WRECKER,                0, 1, 0, 1, 1, 0, 1, 0)
                .withRaider(HACKLE,                 0, 0, 1, 0, 1, 1, 0, 1)
                .withRaider(JUGGERNAUT,             0, 0, 0, 1, 0, 1, 0, 1)
                .withRaider(BLUDGEONER,             0, 1, 0, 1, 0, 1, 1, 1)
                .withRaider(PIKELOGER,              0, 0, 1, 1, 1, 1, 1, 1)
                .withRaider(GRINDICATOR,            0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(OMNILLAGER,             0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(COWBOY_FRANCIS,         0, 2, 2, 2, 1, 3, 2, 2)
                .withRaider(THROWER,                0, 0, 1, 1, 0, 1, 2, 0)
                .withRaider(BLADE_GUARD,            0, 0, 1, 1, 0, 1, 0, 0)
                .withRaider(RUNNER,                 0, 1, 1, 0, 1, 1, 1, 0)
                .withRaider(DESERT_ILLAGER,         0, 1, 1, 2, 1, 2, 2, 1)
                .withRaider(CRINDICATOR,            0, 0, 0, 1, 1, 0, 0, 0)
                .withRaider(CREAKOLOGER,            0, 0, 1, 0, 0, 1, 0, 1)
                .withRaider(JURGOLOGER,             0, 1, 1, 1, 0, 1, 1, 1)
                .withRaider(BLACK_MASTER,           0, 0, 1, 0, 1, 0, 0, 1)
                .withRaider(VILER_WITCH,            0, 0, 1, 1, 0, 1, 1, 1)
                .withRaider(PILLAGER_CAR,           0, 0, 0, 1, 0, 1, 0, 0)
                .withRaider(PILLAGER_SOLDIER,       0, 0, 1, 1, 1, 0, 1, 1)
                .withRaider(VINDICATOR_FLAMETHROWER,0, 0, 0, 0, 1, 1, 0, 1)
                .withRaider(ASSAULT_PILLAGER,       0, 0, 1, 0, 1, 1, 1, 0)
                .withRaider(PILLAGER_PLANE,         0, 0, 0, 0, 1, 1, 1, 1)
                .withRaider(PILLAGER_CANNON,        0, 0, 0, 1, 0, 1, 0, 1)
                .withRaider(WANDERING_TRAITOR,      0, 0, 1, 0, 1, 1, 0, 1)
                .withRaider(DRUSKI,                 0, 1, 2, 2, 1, 1, 2, 2)
                .withRaider(LACHER,                 0, 0, 1, 1, 0, 1, 0, 2)
                .withRaider(CRISKO,                 0, 2, 2, 2, 1, 2, 2, 2)
                .withRaider(BLAKER,                 0, 2, 2, 3, 2, 2, 2, 2)
                .withRaider(TOXICIST,               0, 2, 2, 2, 2, 1, 2, 2)
                .withRaider(REVENANT,               0, 0, 0, 1, 1, 0, 1, 0)
                .withRaider(AMPI,                   0, 0, 0, 0, 0, 0, 0, 0)
                .withEliteWave(5, NUAOS_ELITE.get())
                .withEliteWave(7, NUAOS_ELITE.get(), VOLDON_ELITE.get())
                .registerDefault();

        RaidEnemyRegistry.createDefaultWavesFor(RaidDifficulty.LEGEND)
                .withRaider(PILLAGER,               0, 4, 3, 3, 4, 5, 5, 3)
                .withRaider(VINDICATOR,             0, 2, 3, 1, 3, 4, 2, 3)
                .withRaider(WARRIOR,                0, 2, 2, 3, 3, 1, 4, 4)
                .withRaider(SKIRMISHER,             0, 2, 2, 2, 3, 2, 2, 3)
                .withRaider(TANK,                   0, 0, 2, 1, 2, 1, 2, 1)
                .withRaider(LEGIONER,               0, 1, 1, 2, 2, 3, 3, 4)
                .withRaider(DART,                   0, 0, 2, 1, 2, 1, 3, 0)
                .withRaider(HUNTER,                 0, 1, 3, 2, 3, 2, 3, 4)
                .withRaider(ARCHER,                 0, 2, 2, 4, 3, 4, 3, 6)
                .withRaider(WITCH,                  0, 1, 1, 2, 3, 1, 2, 2)
                .withRaider(RAVAGER,                0, 0, 1, 1, 0, 2, 1, 2)
                .withRaider(ILLUSIONER,             0, 0, 1, 1, 1, 0, 1, 0)
                .withRaider(ASSASSIN,               0, 1, 1, 1, 1, 1, 1, 1)
                .withRaider(EVOKER,                 0, 0, 2, 2, 1, 2, 2, 2)
                .withRaider(CONDUCTOR,              0, 0, 1, 0, 0, 0, 1, 1)
                .withRaider(NECROMANCER,            0, 0, 0, 2, 0, 1, 2, 1)
                .withRaider(FROSTMAGE,              0, 0, 0, 0, 2, 2, 0, 1)
                .withRaider(ASHENMANCER,            0, 0, 0, 1, 0, 1, 0, 1)
                .withRaider(SHAMAN,                 0, 0, 1, 1, 1, 2, 2, 3)
                .withRaider(ENCHANTER,              0, 1, 2, 0, 0, 2, 0, 2)
                .withRaider(IGNITER,                0, 2, 2, 3, 2, 3, 2, 5)
                .withRaider(TWITTOLLAGER,           0, 1, 2, 1, 2, 1, 2, 3)
                .withRaider(PRESERVER,              0, 1, 1, 2, 2, 2, 4, 4)
                .withRaider(ABSORBER,               0, 0, 1, 1, 2, 0, 1, 2)
                .withRaider(CROCOFANG,              0, 1, 2, 1, 2, 3, 2, 4)
                .withRaider(ENGINEER,               0, 0, 1, 1, 0, 1, 1, 2)
                .withRaider(MAGISPELLER,            0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(SPIRITCALLER,           0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(FREAKAGER,              0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(BOSS_RANDOMIZER,        0, 0, 1, 0, 0, 1, 0, 1)
                .withRaider(GRIEFER,                0, 1, 2, 2, 3, 2, 3, 3)
                .withRaider(EXECUTIONER,            0, 1, 2, 1, 1, 2, 2, 3)
                .withRaider(TRICKSTER,              0, 0, 1, 0, 1, 2, 2, 3)
                .withRaider(ICEOLOGER_SR,           0, 0, 1, 2, 0, 2, 0, 2)
                .withRaider(MOUNTAINEER,            0, 2, 3, 2, 2, 5, 2, 6)
                .withRaider(ROYAL_GUARD,            0, 1, 2, 2, 1, 4, 1, 4)
                .withRaider(GEOMANCER,              0, 1, 1, 2, 1, 2, 1, 3)
                .withRaider(ILLUSIONER_DM,          0, 0, 1, 2, 1, 3, 1, 3)
                .withRaider(MAGE,                   0, 1, 1, 1, 0, 2, 1, 3)
                .withRaider(ICEOLOGER_DM,           0, 0, 1, 2, 0, 2, 0, 2)
                .withRaider(WINDCALLER,             0, 1, 1, 2, 1, 2, 1, 3)
                .withRaider(SQUALL_GOLEM,           0, 1, 1, 2, 1, 2, 1, 2)
                .withRaider(REDSTONE_GOLEM,         0, 1, 1, 1, 1, 1, 1, 1)
                .withRaider(BLADE_KNIGHT,           0, 0, 1, 1, 1, 0, 1, 2)
                .withRaider(BEAST_TAMER,            0, 0, 1, 0, 1, 1, 1, 1)
                .withRaider(SCAVENGER,              0, 1, 2, 1, 1, 2, 2, 3)
                .withRaider(SOUL_SAGE,              0, 0, 0, 1, 0, 0, 1, 1)
                .withRaider(LIGHTNINGCALLER,        0, 0, 0, 0, 0, 0, 0, 1)
                .withRaider(CLOWNAGER,              0, 0, 0, 0, 0, 1, 0, 0)
                .withRaider(CONFUSER,               0, 0, 1, 2, 1, 3, 1, 3)
                .withRaider(SHIELD_VINDICATOR,      0, 1, 1, 2, 2, 2, 2, 3)
                .withRaider(METEORITE_CALLER,       0, 0, 1, 0, 0, 1, 0, 1)
                .withRaider(SNOWOLAGER,             0, 0, 1, 2, 0, 2, 0, 2)
                .withRaider(NECROMANCER_LEO,        0, 0, 1, 1, 0, 1, 0, 2)
                .withRaider(SUMMONER,               0, 0, 0, 1, 1, 1, 2, 1)
                .withRaider(TROUBLEMAKER,           0, 1, 1, 2, 2, 1, 1, 1)
                .withRaider(CONJURER,               0, 0, 0, 0, 1, 0, 0, 1)
                .withRaider(NECROMANCER_MOD,        0, 0, 1, 0, 0, 0, 1, 1)
                .withRaider(SINISTER,               0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(SHADOMANCER,            0, 0, 0, 1, 0, 0, 0, 0)
                .withRaider(ARCHEVOKER,             0, 0, 0, 1, 1, 0, 0, 0)
                .withRaider(GAMBLER,                0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(GUARD_ILLAGER,          0, 2, 2, 2, 2, 3, 2, 2)
                .withRaider(ROYAL_GUARD_SPEAR,      0, 0, 1, 0, 0, 0, 1, 1)
                .withRaider(SPEARMAN,               0, 0, 1, 1, 1, 0, 0, 1)
                .withRaider(SAMURAI,                0, 1, 0, 1, 0, 0, 1, 0)
                .withRaider(BEAMLOGER,              0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(COWBOY,                 0, 0, 1, 0, 0, 1, 1, 0)
                .withRaider(SHOGUN,                 0, 0, 0, 0, 0, 0, 1, 1)
                .withRaider(SPIDER_MAGE,            0, 0, 0, 1, 1, 0, 1, 0)
                .withRaider(ZOMBIE_MAGE,            0, 0, 1, 1, 0, 0, 1, 1)
                .withRaider(PILLAGER_BOSS,          0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(ILLUSIONER_MOBZ,        0, 0, 1, 1, 0, 1, 0, 1)
                .withRaider(BABY_RAVAGER,           0, 2, 3, 1, 3, 2, 1, 3)
                .withRaider(PROVOKER,               0, 1, 0, 1, 1, 0, 1, 0)
                .withRaider(INQUISITOR,             0, 0, 1, 0, 1, 1, 0, 0)
                .withRaider(MARAUDER,               0, 0, 1, 1, 0, 0, 0, 1)
                .withRaider(BASHER,                 0, 0, 1, 0, 1, 1, 0, 0)
                .withRaider(FIRECALLER,             0, 0, 1, 0, 0, 1, 0, 1)
                .withRaider(NECROMANCER_INV,        0, 0, 1, 1, 0, 0, 0, 0)
                .withRaider(ALCHEMIST,              0, 1, 0, 1, 1, 0, 1, 1)
                .withRaider(SORCERER,               0, 0, 0, 0, 1, 1, 0, 1)
                .withRaider(ARCHIVIST,              0, 1, 0, 1, 0, 0, 0, 1)
                .withRaider(INVOKER,                0, 0, 0, 0, 0, 1, 0, 0)
                .withRaider(WARLOCK,                0, 0, 1, 0, 1, 1, 0, 1)
                .withRaider(MAVERICK,               0, 0, 1, 1, 0, 1, 1, 0)
                .withRaider(HERETIC,                0, 1, 0, 0, 1, 1, 0, 1)
                .withRaider(PIKER,                  0, 3, 3, 2, 3, 3, 2, 2)
                .withRaider(RIPPER,                 0, 0, 1, 2, 1, 1, 1, 0)
                .withRaider(CRUSHER,                0, 1, 2, 1, 2, 1, 2, 1)
                .withRaider(STORM_CASTER,           0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(CRYOLOGER,              0, 0, 1, 0, 1, 0, 1, 1)
                .withRaider(PREACHER,               0, 1, 0, 1, 2, 1, 0, 1)
                .withRaider(CONQUILLAGER,           0, 2, 1, 2, 1, 0, 1, 1)
                .withRaider(INQUILLAGER,            0, 1, 2, 0, 1, 1, 0, 0)
                .withRaider(ENVIOKER,               0, 0, 1, 1, 1, 0, 1, 1)
                .withRaider(SORCERER_GOETY,         0, 1, 1, 0, 1, 0, 0, 1)
                .withRaider(HOSTILE_RED_GOLEM,      0, 0, 1, 1, 1, 1, 0, 1)
                .withRaider(HOSTILE_RED_MONSTER,    0, 0, 0, 0, 0, 1, 0, 0)
                .withRaider(MINISTER,               0, 1, 1, 0, 1, 0, 1, 1)
                .withRaider(TRAMPLER,               0, 1, 0, 2, 1, 1, 0, 2)
                .withRaider(VIZIER,                 0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(APOSTLE,                0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(THE_SUMMONER,           0, 1, 1, 0, 1, 1, 0, 1)
                .withRaider(THE_SUMMONER_BOSS,      0, 0, 0, 0, 0, 0, 0, 1)
                .withRaider(NECROMANCER_BONES,      0, 0, 1, 1, 1, 0, 0, 1)
                .withRaider(MODIFIGER,              0, 0, 0, 2, 1, 1, 2, 1)
                .withRaider(MARKSMAN,               0, 1, 1, 1, 2, 1, 0, 1)
                .withRaider(MASQUERADER,            0, 0, 0, 1, 1, 0, 1, 0)
                .withRaider(TENGU,                  0, 1, 1, 1, 0, 1, 2, 1)
                .withRaider(NINJAR,                 0, 1, 1, 1, 2, 2, 2, 1)
                .withRaider(NECROMANCER_RAIDED,     0, 1, 1, 2, 1, 0, 1, 2)
                .withRaider(ELECTROMANCER_RAIDED,   0, 1, 2, 1, 1, 1, 2, 1)
                .withRaider(SAVAGER,                0, 1, 2, 1, 1, 2, 2, 1)
                .withRaider(INCINERATOR,            0, 0, 1, 0, 1, 0, 0, 1)
                .withRaider(INQUISITOR_RAIDED,      0, 1, 1, 1, 1, 2, 1, 1)
                .withRaider(GUNILLAGER,             0, 0, 1, 1, 2, 1, 0, 1)
                .withRaider(ILLIGEVE,               0, 0, 1, 1, 0, 0, 1, 1)
                .withRaider(SURPRISER,              0, 0, 1, 0, 1, 1, 1, 1)
                .withRaider(ROCKETILLAGER,          0, 0, 0, 1, 1, 0, 0, 1)
                .withRaider(CREEPILLAGER,           0, 0, 0, 0, 0, 1, 0, 1)
                .withRaider(MONSTERILLAGER,         0, 0, 0, 0, 1, 0, 1, 0)
                .withRaider(BLADE_MASTER,           0, 0, 1, 1, 1, 0, 1, 1)
                .withRaider(ICEOLOGER_FF,           0, 0, 1, 1, 1, 1, 1, 1)
                .withRaider(PILLAGER_BRUTE,         0, 0, 1, 0, 1, 0, 0, 1)
                .withRaider(CRAZED,                 0, 1, 1, 2, 1, 1, 1, 1)
                .withRaider(CABBAGER,               0, 1, 2, 1, 1, 0, 1, 0)
                .withRaider(PHANTOM_TAMER,          0, 0, 1, 1, 0, 1, 1, 1)
                .withRaider(T_RABUS,                0, 0, 0, 0, 0, 1, 1, 0)
                .withRaider(CYBORG_VINDICATOR,      0, 0, 0, 0, 0, 1, 0, 1)
                .withRaider(CRUDE_RED_GOLEM,        0, 0, 1, 0, 1, 0, 1, 1)
                .withRaider(CRUDE_RED_MONSTROSITY,  0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(RED_MONSTROSITY_CSM,    0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(MINI_CRUDE_RED_GOLEM,   0, 1, 1, 1, 2, 1, 1, 1)
                .withRaider(HOUNDMASTER,            0, 0, 1, 1, 0, 1, 0, 1)
                .withRaider(KARATE,                 0, 1, 3, 2, 1, 2, 2, 2)
                .withRaider(ILLAGER_BRUTE,          0, 0, 0, 1, 1, 1, 0, 1)
                .withRaider(SORCERER_ILLAGER,       0, 1, 1, 2, 1, 2, 1, 1)
                .withRaider(WANDERING_ILLAGER,      0, 0, 1, 1, 0, 1, 1, 0)
                .withRaider(BRASHER,                0, 1, 1, 1, 1, 2, 1, 1)
                .withRaider(KNOCKER,                0, 2, 1, 1, 2, 1, 1, 1)
                .withRaider(WRECKER,                0, 1, 1, 2, 1, 1, 2, 1)
                .withRaider(HACKLE,                 0, 1, 1, 0, 1, 1, 1, 1)
                .withRaider(JUGGERNAUT,             0, 0, 1, 1, 0, 1, 0, 1)
                .withRaider(BLUDGEONER,             0, 1, 0, 1, 0, 1, 1, 1)
                .withRaider(PIKELOGER,              0, 0, 1, 1, 1, 1, 1, 1)
                .withRaider(GRINDICATOR,            0, 0, 0, 0, 0, 0, 0, 1)
                .withRaider(OMNILLAGER,             0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(COWBOY_FRANCIS,         0, 2, 3, 3, 2, 4, 3, 3)
                .withRaider(THROWER,                0, 1, 2, 2, 1, 1, 2, 2)
                .withRaider(BLADE_GUARD,            0, 0, 1, 2, 1, 1, 0, 1)
                .withRaider(RUNNER,                 0, 2, 2, 1, 2, 2, 1, 0)
                .withRaider(DESERT_ILLAGER,         0, 1, 2, 3, 2, 3, 3, 2)
                .withRaider(CRINDICATOR,            0, 0, 0, 1, 2, 0, 1, 1)
                .withRaider(CREAKOLOGER,            0, 0, 1, 1, 0, 1, 1, 1)
                .withRaider(JURGOLOGER,             0, 1, 2, 2, 1, 1, 2, 1)
                .withRaider(BLACK_MASTER,           0, 0, 1, 0, 1, 1, 1, 1)
                .withRaider(VILER_WITCH,            0, 1, 1, 2, 1, 2, 2, 1)
                .withRaider(PILLAGER_CAR,           0, 0, 1, 1, 0, 1, 1, 0)
                .withRaider(PILLAGER_SOLDIER,       0, 1, 1, 2, 1, 2, 2, 1)
                .withRaider(VINDICATOR_FLAMETHROWER,0, 0, 1, 1, 1, 2, 0, 1)
                .withRaider(ASSAULT_PILLAGER,       0, 1, 2, 1, 2, 1, 1, 0)
                .withRaider(PILLAGER_PLANE,         0, 0, 1, 0, 1, 2, 2, 1)
                .withRaider(PILLAGER_CANNON,        0, 0, 0, 1, 2, 1, 1, 1)
                .withRaider(WANDERING_TRAITOR,      0, 1, 1, 2, 2, 1, 1, 1)
                .withRaider(DRUSKI,                 0, 2, 2, 3, 2, 1, 3, 2)
                .withRaider(LACHER,                 0, 1, 1, 2, 1, 1, 1, 2)
                .withRaider(CRISKO,                 0, 3, 3, 2, 2, 2, 3, 2)
                .withRaider(BLAKER,                 0, 3, 3, 3, 2, 3, 2, 3)
                .withRaider(TOXICIST,               0, 2, 3, 3, 2, 2, 2, 3)
                .withRaider(REVENANT,               0, 0, 1, 1, 1, 0, 1, 1)
                .withRaider(AMPI,                   0, 0, 0, 0, 0, 0, 1, 0)
                .withEliteWave(3, NUAOS_ELITE.get(), VOLDON_ELITE.get())
                .withEliteWave(5, VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(7, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .registerDefault();

        RaidEnemyRegistry.createDefaultWavesFor(RaidDifficulty.MASTER)
                .withRaider(PILLAGER,               0, 5, 6, 5, 6, 6, 5, 5)
                .withRaider(VINDICATOR,             0, 3, 2, 3, 3, 2, 3, 4)
                .withRaider(WARRIOR,                0, 3, 1, 3, 3, 2, 4, 4)
                .withRaider(SKIRMISHER,             0, 2, 2, 4, 3, 5, 2, 5)
                .withRaider(TANK,                   0, 2, 2, 2, 3, 3, 3, 3)
                .withRaider(LEGIONER,               0, 1, 2, 3, 2, 4, 3, 5)
                .withRaider(DART,                   0, 0, 2, 2, 2, 2, 3, 4)
                .withRaider(HUNTER,                 0, 3, 4, 4, 4, 5, 3, 4)
                .withRaider(ARCHER,                 0, 3, 3, 5, 4, 5, 5, 7)
                .withRaider(WITCH,                  0, 1, 3, 5, 4, 5, 3, 3)
                .withRaider(RAVAGER,                0, 1, 1, 1, 0, 3, 1, 3)
                .withRaider(ILLUSIONER,             0, 1, 1, 2, 1, 1, 2, 3)
                .withRaider(ASSASSIN,               0, 2, 2, 2, 2, 2, 2, 2)
                .withRaider(EVOKER,                 0, 1, 2, 3, 4, 1, 1, 3)
                .withRaider(CONDUCTOR,              0, 1, 2, 0, 1, 2, 2, 3)
                .withRaider(NECROMANCER,            0, 1, 0, 3, 1, 2, 0, 3)
                .withRaider(ASHENMANCER,            0, 1, 0, 2, 1, 2, 0, 2)
                .withRaider(FROSTMAGE,              0, 1, 0, 0, 1, 2, 4, 3)
                .withRaider(SHAMAN,                 0, 2, 2, 2, 2, 3, 3, 3)
                .withRaider(ENCHANTER,              0, 1, 1, 1, 1, 1, 1, 3)
                .withRaider(IGNITER,                0, 3, 2, 4, 3, 4, 3, 6)
                .withRaider(TWITTOLLAGER,           0, 1, 3, 1, 3, 2, 2, 3)
                .withRaider(PRESERVER,              0, 2, 2, 2, 2, 2, 4, 4)
                .withRaider(ABSORBER,               0, 1, 1, 1, 2, 1, 1, 3)
                .withRaider(CROCOFANG,              0, 2, 2, 3, 2, 4, 2, 5)
                .withRaider(ENGINEER,               0, 1, 1, 0, 1, 1, 1, 2)
                .withRaider(MAGISPELLER,            0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(SPIRITCALLER,           0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(FREAKAGER,              0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(BOSS_RANDOMIZER,        0, 0, 1, 1, 0, 1, 1, 1)
                .withRaider(GRIEFER,                0, 2, 3, 2, 3, 3, 3, 4)
                .withRaider(EXECUTIONER,            0, 2, 2, 2, 3, 2, 3, 4)
                .withRaider(TRICKSTER,              0, 1, 2, 1, 2, 2, 2, 3)
                .withRaider(ICEOLOGER_SR,           0, 1, 2, 3, 1, 3, 1, 3)
                .withRaider(MOUNTAINEER,            0, 3, 4, 5, 2, 7, 4, 7)
                .withRaider(ROYAL_GUARD,            0, 2, 2, 3, 2, 4, 3, 5)
                .withRaider(GEOMANCER,              0, 2, 1, 3, 2, 3, 1, 3)
                .withRaider(ILLUSIONER_DM,          0, 1, 2, 2, 2, 3, 2, 3)
                .withRaider(MAGE,                   0, 1, 2, 2, 1, 3, 1, 3)
                .withRaider(ICEOLOGER_DM,           0, 1, 2, 3, 1, 3, 1, 3)
                .withRaider(WINDCALLER,             0, 1, 2, 3, 2, 3, 2, 3)
                .withRaider(SQUALL_GOLEM,           0, 1, 2, 2, 2, 3, 2, 3)
                .withRaider(REDSTONE_GOLEM,         0, 1, 1, 2, 1, 2, 1, 2)
                .withRaider(BLADE_KNIGHT,           0, 1, 1, 1, 1, 1, 1, 2)
                .withRaider(BEAST_TAMER,            0, 1, 2, 0, 2, 1, 2, 1)
                .withRaider(SCAVENGER,              0, 2, 2, 2, 3, 2, 3, 4)
                .withRaider(SOUL_SAGE,              0, 0, 1, 1, 1, 0, 1, 1)
                .withRaider(LIGHTNINGCALLER,        0, 0, 0, 1, 0, 0, 0, 1)
                .withRaider(CLOWNAGER,              0, 0, 1, 0, 0, 1, 0, 0)
                .withRaider(CONFUSER,               0, 1, 1, 2, 1, 3, 1, 3)
                .withRaider(SHIELD_VINDICATOR,      0, 2, 2, 2, 2, 3, 2, 3)
                .withRaider(METEORITE_CALLER,       0, 0, 1, 1, 1, 2, 0, 2)
                .withRaider(SNOWOLAGER,             0, 1, 2, 3, 1, 3, 1, 3)
                .withRaider(NECROMANCER_LEO,        0, 1, 1, 1, 1, 1, 1, 2)
                .withRaider(SUMMONER,               0, 1, 1, 1, 2, 2, 2, 2)
                .withRaider(TROUBLEMAKER,           0, 3, 2, 3, 2, 3, 2, 3)
                .withRaider(CONJURER,               0, 0, 1, 0, 1, 0, 1, 1)
                .withRaider(NECROMANCER_MOD,        0, 1, 1, 0, 0, 1, 1, 2)
                .withRaider(SINISTER,               0, 0, 0, 1, 1, 0, 0, 0)
                .withRaider(SHADOMANCER,            0, 0, 1, 1, 0, 0, 0, 1)
                .withRaider(ARCHEVOKER,             0, 0, 1, 1, 1, 1, 0, 0)
                .withRaider(GAMBLER,                0, 0, 0, 0, 1, 0, 0, 0)
                .withRaider(GUARD_ILLAGER,          0, 2, 5, 4, 2, 3, 3, 4)
                .withRaider(ROYAL_GUARD_SPEAR,      0, 0, 1, 1, 0, 1, 1, 1)
                .withRaider(SPEARMAN,               0, 0, 1, 2, 1, 0, 0, 1)
                .withRaider(SAMURAI,                0, 1, 0, 1, 0, 1, 1, 0)
                .withRaider(BEAMLOGER,              0, 0, 1, 0, 1, 1, 0, 0)
                .withRaider(COWBOY,                 0, 1, 2, 0, 1, 1, 1, 0)
                .withRaider(SHOGUN,                 0, 0, 0, 1, 1, 0, 1, 1)
                .withRaider(SPIDER_MAGE,            0, 0, 1, 1, 2, 0, 1, 2)
                .withRaider(ZOMBIE_MAGE,            0, 1, 1, 2, 0, 1, 2, 1)
                .withRaider(PILLAGER_BOSS,          0, 0, 1, 0, 1, 0, 0, 0)
                .withRaider(ILLUSIONER_MOBZ,        0, 0, 1, 2, 1, 2, 0, 1)
                .withRaider(BABY_RAVAGER,           0, 3, 4, 2, 4, 3, 2, 4)
                .withRaider(PROVOKER,               0, 1, 1, 2, 1, 0, 1, 0)
                .withRaider(INQUISITOR,             0, 0, 1, 1, 2, 1, 0, 0)
                .withRaider(MARAUDER,               0, 0, 1, 1, 0, 0, 0, 1)
                .withRaider(BASHER,                 0, 1, 1, 0, 2, 1, 0, 0)
                .withRaider(FIRECALLER,             0, 1, 1, 0, 0, 2, 0, 2)
                .withRaider(NECROMANCER_INV,        0, 0, 1, 2, 1, 0, 0, 0)
                .withRaider(ALCHEMIST,              0, 1, 2, 2, 1, 0, 1, 1)
                .withRaider(SORCERER,               0, 0, 1, 1, 2, 1, 0, 2)
                .withRaider(ARCHIVIST,              0, 1, 0, 1, 2, 1, 0, 1)
                .withRaider(INVOKER,                0, 0, 0, 1, 0, 1, 0, 0)
                .withRaider(WARLOCK,                0, 1, 1, 1, 2, 1, 1, 1)
                .withRaider(MAVERICK,               0, 1, 1, 2, 1, 2, 1, 0)
                .withRaider(HERETIC,                0, 2, 1, 0, 2, 1, 0, 2)
                .withRaider(PIKER,                  0, 4, 4, 3, 4, 4, 3, 3)
                .withRaider(RIPPER,                 0, 1, 1, 3, 2, 2, 1, 1)
                .withRaider(CRUSHER,                0, 2, 3, 2, 3, 2, 3, 2)
                .withRaider(STORM_CASTER,           0, 0, 1, 0, 1, 1, 0, 1)
                .withRaider(CRYOLOGER,              0, 1, 1, 1, 2, 1, 1, 2)
                .withRaider(PREACHER,               0, 1, 1, 2, 2, 1, 1, 1)
                .withRaider(CONQUILLAGER,           0, 3, 2, 3, 1, 1, 2, 2)
                .withRaider(INQUILLAGER,            0, 2, 2, 1, 2, 1, 0, 1)
                .withRaider(ENVIOKER,               0, 1, 1, 2, 1, 1, 2, 1)
                .withRaider(SORCERER_GOETY,         0, 1, 2, 1, 1, 0, 1, 1)
                .withRaider(HOSTILE_RED_GOLEM,      0, 1, 2, 1, 1, 2, 1, 1)
                .withRaider(HOSTILE_RED_MONSTER,    0, 0, 0, 1, 0, 1, 0, 0)
                .withRaider(MINISTER,               0, 1, 2, 1, 1, 1, 1, 1)
                .withRaider(TRAMPLER,               0, 1, 1, 2, 3, 1, 1, 2)
                .withRaider(VIZIER,                 0, 0, 0, 0, 0, 0, 1, 0)
                .withRaider(APOSTLE,                0, 0, 0, 0, 0, 0, 0, 1)
                .withRaider(THE_SUMMONER,           0, 2, 1, 0, 1, 2, 0, 1)
                .withRaider(THE_SUMMONER_BOSS,      0, 0, 0, 0, 0, 1, 0, 1)
                .withRaider(NECROMANCER_BONES,      0, 1, 1, 1, 1, 1, 1, 1)
                .withRaider(MODIFIGER,              0, 1, 1, 3, 2, 2, 3, 3)
                .withRaider(MARKSMAN,               0, 1, 2, 2, 3, 2, 1, 2)
                .withRaider(MASQUERADER,            0, 0, 0, 0, 2, 0, 0, 0)
                .withRaider(TENGU,                  0, 1, 2, 3, 2, 1, 2, 1)
                .withRaider(NINJAR,                 0, 2, 2, 2, 3, 2, 2, 2)
                .withRaider(NECROMANCER_RAIDED,     0, 1, 2, 3, 1, 0, 1, 2)
                .withRaider(ELECTROMANCER_RAIDED,   0, 1, 2, 1, 2, 1, 2, 1)
                .withRaider(SAVAGER,                0, 2, 2, 3, 2, 3, 2, 1)
                .withRaider(INCINERATOR,            0, 1, 1, 1, 1, 0, 1, 1)
                .withRaider(INQUISITOR_RAIDED,      0, 1, 2, 1, 2, 3, 2, 1)
                .withRaider(GUNILLAGER,             0, 1, 2, 1, 2, 2, 1, 1)
                .withRaider(ILLIGEVE,               0, 0, 1, 1, 1, 0, 2, 1)
                .withRaider(SURPRISER,              0, 0, 1, 1, 2, 1, 1, 2)
                .withRaider(ROCKETILLAGER,          0, 0, 1, 1, 1, 0, 2, 1)
                .withRaider(CREEPILLAGER,           0, 0, 0, 0, 0, 1, 1, 1)
                .withRaider(MONSTERILLAGER,         0, 0, 0, 0, 1, 0, 1, 1)
                .withRaider(BLADE_MASTER,           0, 1, 1, 2, 1, 1, 1, 1)
                .withRaider(ICEOLOGER_FF,           0, 1, 2, 1, 1, 1, 1, 1)
                .withRaider(PILLAGER_BRUTE,         0, 0, 1, 0, 1, 1, 1, 1)
                .withRaider(CRAZED,                 0, 1, 1, 2, 1, 1, 1, 1)
                .withRaider(CABBAGER,               0, 1, 2, 2, 2, 1, 1, 1)
                .withRaider(PHANTOM_TAMER,          0, 1, 1, 2, 1, 1, 2, 1)
                .withRaider(T_RABUS,                0, 0, 0, 0, 1, 1, 1, 0)
                .withRaider(CYBORG_VINDICATOR,      0, 0, 0, 1, 0, 1, 0, 1)
                .withRaider(CRUDE_RED_GOLEM,        0, 0, 1, 1, 1, 0, 2, 1)
                .withRaider(CRUDE_RED_MONSTROSITY,  0, 0, 0, 0, 0, 0, 1, 0)
                .withRaider(RED_MONSTROSITY_CSM,    0, 0, 0, 0, 0, 0, 0, 1)
                .withRaider(MINI_CRUDE_RED_GOLEM,   0, 1, 2, 2, 3, 2, 2, 2)
                .withRaider(HOUNDMASTER,            0, 1, 2, 1, 1, 1, 1, 1)
                .withRaider(KARATE,                 0, 2, 3, 3, 2, 3, 3, 3)
                .withRaider(ILLAGER_BRUTE,          0, 0, 1, 1, 2, 1, 1, 1)
                .withRaider(SORCERER_ILLAGER,       0, 1, 2, 3, 2, 3, 2, 1)
                .withRaider(WANDERING_ILLAGER,      0, 1, 1, 1, 1, 2, 1, 1)
                .withRaider(BRASHER,                0, 1, 2, 2, 1, 2, 2, 1)
                .withRaider(KNOCKER,                0, 3, 2, 2, 3, 2, 2, 1)
                .withRaider(WRECKER,                0, 1, 2, 3, 2, 1, 2, 2)
                .withRaider(HACKLE,                 0, 1, 2, 1, 2, 1, 2, 1)
                .withRaider(JUGGERNAUT,             0, 0, 2, 1, 1, 2, 1, 1)
                .withRaider(BLUDGEONER,             0, 1, 0, 2, 1, 1, 2, 1)
                .withRaider(PIKELOGER,              0, 1, 2, 2, 2, 2, 2, 2)
                .withRaider(GRINDICATOR,            0, 0, 0, 0, 0, 1, 0, 1)
                .withRaider(OMNILLAGER,             0, 0, 0, 0, 1, 0, 0, 1)
                .withRaider(COWBOY_FRANCIS,         0, 3, 4, 4, 3, 5, 4, 4)
                .withRaider(THROWER,                0, 2, 3, 3, 3, 2, 3, 2)
                .withRaider(BLADE_GUARD,            0, 1, 2, 2, 2, 1, 1, 1)
                .withRaider(RUNNER,                 0, 3, 3, 2, 2, 2, 2, 1)
                .withRaider(DESERT_ILLAGER,         0, 2, 3, 4, 2, 4, 4, 3)
                .withRaider(CRINDICATOR,            0, 1, 2, 1, 2, 0, 1, 1)
                .withRaider(CREAKOLOGER,            0, 1, 1, 2, 1, 1, 1, 2)
                .withRaider(JURGOLOGER,             0, 1, 2, 2, 1, 1, 2, 1)
                .withRaider(BLACK_MASTER,           0, 0, 1, 1, 2, 1, 2, 2)
                .withRaider(VILER_WITCH,            0, 2, 2, 2, 2, 3, 2, 1)
                .withRaider(PILLAGER_CAR,           0, 1, 1, 1, 1, 2, 2, 1)
                .withRaider(PILLAGER_SOLDIER,       0, 2, 2, 3, 2, 3, 2, 2)
                .withRaider(VINDICATOR_FLAMETHROWER,0, 1, 1, 2, 1, 2, 1, 1)
                .withRaider(ASSAULT_PILLAGER,       0, 2, 3, 2, 2, 2, 2, 2)
                .withRaider(PILLAGER_PLANE,         0, 1, 1, 1, 2, 2, 2, 1)
                .withRaider(PILLAGER_CANNON,        0, 1, 1, 1, 2, 1, 2, 2)
                .withRaider(WANDERING_TRAITOR,      0, 1, 2, 2, 3, 2, 2, 1)
                .withRaider(DRUSKI,                 0, 2, 3, 4, 3, 2, 3, 3)
                .withRaider(LACHER,                 0, 1, 2, 2, 2, 1, 2, 3)
                .withRaider(CRISKO,                 0, 3, 4, 3, 3, 3, 4, 3)
                .withRaider(BLAKER,                 0, 4, 4, 4, 3, 3, 3, 3)
                .withRaider(TOXICIST,               0, 3, 3, 4, 3, 3, 3, 3)
                .withRaider(REVENANT,               0, 0, 2, 1, 2, 1, 1, 1)
                .withRaider(AMPI,                   0, 0, 0, 1, 0, 0, 1, 0)
                .withEliteWave(1, NUAOS_ELITE.get(), VOLDON_ELITE.get())
                .withEliteWave(3, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(5, XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(6, XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(7, XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .registerDefault();

        RaidEnemyRegistry.createDefaultWavesFor(RaidDifficulty.GRANDMASTER)
                .withRaider(PILLAGER,               0, 6, 7, 6, 7, 7, 6, 6)
                .withRaider(VINDICATOR,             0, 4, 3, 4, 4, 3, 4, 5)
                .withRaider(WARRIOR,                0, 4, 2, 4, 4, 3, 5, 5)
                .withRaider(SKIRMISHER,             0, 2, 2, 4, 3, 5, 2, 5)
                .withRaider(TANK,                   0, 2, 2, 3, 4, 4, 4, 4)
                .withRaider(LEGIONER,               0, 1, 2, 3, 2, 4, 3, 5)
                .withRaider(DART,                   0, 0, 2, 2, 2, 2, 3, 4)
                .withRaider(HUNTER,                 0, 3, 4, 4, 4, 5, 3, 4)
                .withRaider(ARCHER,                 0, 3, 3, 5, 4, 5, 5, 7)
                .withRaider(WITCH,                  0, 1, 3, 5, 4, 5, 3, 3)
                .withRaider(RAVAGER,                0, 1, 1, 1, 0, 3, 1, 3)
                .withRaider(ILLUSIONER,             0, 0, 1, 2, 1, 1, 2, 3)
                .withRaider(ASSASSIN,               0, 2, 2, 2, 2, 2, 2, 2)
                .withRaider(EVOKER,                 0, 1, 2, 3, 4, 1, 1, 3)
                .withRaider(CONDUCTOR,              0, 1, 2, 0, 1, 2, 2, 3)
                .withRaider(NECROMANCER,            0, 1, 0, 3, 1, 2, 0, 3)
                .withRaider(ASHENMANCER,            0, 1, 1, 3, 1, 3, 1, 3)
                .withRaider(FROSTMAGE,              0, 1, 0, 0, 1, 2, 4, 3)
                .withRaider(SHAMAN,                 0, 2, 2, 2, 2, 3, 3, 3)
                .withRaider(ENCHANTER,              0, 1, 1, 1, 1, 1, 1, 3)
                .withRaider(IGNITER,                0, 3, 2, 4, 3, 4, 3, 6)
                .withRaider(TWITTOLLAGER,           0, 1, 3, 1, 3, 2, 2, 3)
                .withRaider(PRESERVER,              0, 2, 2, 2, 2, 2, 4, 4)
                .withRaider(ABSORBER,               0, 1, 1, 1, 2, 1, 1, 3)
                .withRaider(CROCOFANG,              0, 2, 2, 3, 2, 4, 2, 5)
                .withRaider(ENGINEER,               0, 1, 1, 2, 0, 1, 2, 2)
                .withRaider(MAGISPELLER,            0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(SPIRITCALLER,           0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(FREAKAGER,              0, 0, 0, 0, 0, 0, 0, 0)
                .withRaider(BOSS_RANDOMIZER,        0, 0, 1, 1, 0, 2, 2, 2)
                .withRaider(GRIEFER,                0, 2, 3, 2, 3, 3, 3, 4)
                .withRaider(EXECUTIONER,            0, 2, 2, 2, 3, 2, 3, 4)
                .withRaider(TRICKSTER,              0, 1, 2, 1, 2, 2, 2, 3)
                .withRaider(ICEOLOGER_SR,           0, 1, 2, 3, 1, 3, 1, 3)
                .withRaider(MOUNTAINEER,            0, 3, 4, 5, 2, 7, 4, 7)
                .withRaider(ROYAL_GUARD,            0, 2, 2, 3, 2, 4, 3, 5)
                .withRaider(GEOMANCER,              0, 2, 1, 3, 2, 3, 1, 3)
                .withRaider(ILLUSIONER_DM,          0, 1, 2, 2, 2, 3, 2, 3)
                .withRaider(MAGE,                   0, 1, 2, 2, 1, 3, 1, 3)
                .withRaider(ICEOLOGER_DM,           0, 1, 2, 3, 1, 3, 1, 3)
                .withRaider(WINDCALLER,             0, 1, 2, 3, 2, 3, 2, 3)
                .withRaider(SQUALL_GOLEM,           0, 1, 2, 2, 2, 3, 2, 3)
                .withRaider(REDSTONE_GOLEM,         0, 1, 1, 3, 1, 3, 1, 3)
                .withRaider(BLADE_KNIGHT,           0, 1, 1, 1, 1, 1, 1, 2)
                .withRaider(BEAST_TAMER,            0, 1, 2, 0, 2, 1, 2, 1)
                .withRaider(SCAVENGER,              0, 2, 2, 2, 3, 2, 3, 4)
                .withRaider(SOUL_SAGE,              0, 0, 1, 2, 1, 2, 1, 2)
                .withRaider(LIGHTNINGCALLER,        0, 0, 0, 1, 1, 0, 0, 1)
                .withRaider(CLOWNAGER,              0, 0, 1, 0, 0, 1, 1, 0)
                .withRaider(CONFUSER,               0, 1, 1, 2, 1, 3, 1, 3)
                .withRaider(SHIELD_VINDICATOR,      0, 2, 2, 2, 2, 3, 2, 3)
                .withRaider(METEORITE_CALLER,       0, 0, 1, 1, 1, 2, 0, 2)
                .withRaider(SNOWOLAGER,             0, 1, 2, 3, 1, 3, 1, 3)
                .withRaider(NECROMANCER_LEO,        0, 1, 1, 1, 1, 1, 1, 2)
                .withRaider(SUMMONER,               0, 1, 1, 1, 2, 3, 2, 2)
                .withRaider(TROUBLEMAKER,           0, 3, 2, 3, 2, 3, 2, 3)
                .withRaider(CONJURER,               0, 1, 1, 0, 1, 2, 1, 2)
                .withRaider(NECROMANCER_MOD,        0, 1, 2, 1, 0, 1, 2, 2)
                .withRaider(SINISTER,               0, 0, 0, 1, 1, 0, 1, 2)
                .withRaider(SHADOMANCER,            0, 0, 1, 1, 0, 0, 1, 0)
                .withRaider(ARCHEVOKER,             0, 0, 1, 2, 1, 1, 0, 0)
                .withRaider(GAMBLER,                0, 0, 1, 0, 1, 2, 0, 0)
                .withRaider(GUARD_ILLAGER,          0, 3, 6, 4, 3, 5, 4, 4)
                .withRaider(ROYAL_GUARD_SPEAR,      0, 1, 1, 2, 1, 1, 2, 1)
                .withRaider(SPEARMAN,               0, 1, 2, 2, 1, 0, 1, 1)
                .withRaider(SAMURAI,                0, 0, 1, 2, 1, 1, 2, 1)
                .withRaider(BEAMLOGER,              0, 1, 1, 1, 1, 2, 1, 0)
                .withRaider(COWBOY,                 0, 0, 2, 1, 2, 1, 1, 0)
                .withRaider(SHOGUN,                 0, 0, 1, 1, 2, 0, 1, 1)
                .withRaider(SPIDER_MAGE,            0, 2, 3, 2, 2, 0, 1, 2)
                .withRaider(ZOMBIE_MAGE,            0, 1, 2, 3, 2, 1, 2, 1)
                .withRaider(PILLAGER_BOSS,          0, 0, 1, 1, 1, 0, 0, 1)
                .withRaider(ILLUSIONER_MOBZ,        0, 1, 1, 2, 2, 3, 0, 2)
                .withRaider(BABY_RAVAGER,           0, 4, 5, 3, 5, 4, 3, 5)
                .withRaider(PROVOKER,               0, 1, 1, 3, 2, 1, 1, 0)
                .withRaider(INQUISITOR,             0, 0, 1, 1, 2, 1, 0, 0)
                .withRaider(MARAUDER,               0, 1, 1, 2, 1, 1, 0, 3)
                .withRaider(BASHER,                 0, 0, 1, 2, 3, 1, 2, 1)
                .withRaider(FIRECALLER,             0, 1, 2, 1, 0, 2, 0, 2)
                .withRaider(NECROMANCER_INV,        0, 1, 1, 2, 2, 0, 1, 0)
                .withRaider(ALCHEMIST,              0, 1, 2, 3, 1, 1, 1, 1)
                .withRaider(SORCERER,               0, 2, 1, 1, 2, 1, 0, 2)
                .withRaider(ARCHIVIST,              0, 1, 1, 3, 2, 1, 0, 1)
                .withRaider(INVOKER,                0, 0, 0, 1, 1, 1, 0, 1)
                .withRaider(WARLOCK,                0, 2, 1, 2, 3, 1, 2, 1)
                .withRaider(MAVERICK,               0, 1, 2, 2, 1, 3, 1, 0)
                .withRaider(HERETIC,                0, 2, 3, 1, 3, 1, 1, 2)
                .withRaider(PIKER,                  0, 5, 5, 4, 5, 5, 4, 4)
                .withRaider(RIPPER,                 0, 1, 2, 4, 3, 3, 2, 1)
                .withRaider(CRUSHER,                0, 3, 4, 3, 4, 3, 4, 3)
                .withRaider(STORM_CASTER,           0, 1, 2, 1, 1, 2, 2, 1)
                .withRaider(CRYOLOGER,              0, 1, 1, 1, 3, 1, 2, 2)
                .withRaider(PREACHER,               0, 1, 2, 3, 3, 2, 2, 2)
                .withRaider(CONQUILLAGER,           0, 3, 3, 4, 2, 2, 2, 2)
                .withRaider(INQUILLAGER,            0, 2, 3, 1, 2, 1, 0, 1)
                .withRaider(ENVIOKER,               0, 1, 2, 3, 2, 2, 2, 1)
                .withRaider(SORCERER_GOETY,         0, 1, 2, 1, 2, 2, 2, 1)
                .withRaider(HOSTILE_RED_GOLEM,      0, 1, 2, 2, 1, 3, 2, 1)
                .withRaider(HOSTILE_RED_MONSTER,    0, 0, 0, 1, 2, 1, 2, 1)
                .withRaider(MINISTER,               0, 1, 3, 2, 1, 2, 2, 1)
                .withRaider(TRAMPLER,               0, 1, 2, 3, 4, 2, 2, 3)
                .withRaider(VIZIER,                 0, 0, 0, 1, 0, 1, 1, 2)
                .withRaider(APOSTLE,                0, 0, 0, 0, 1, 1, 0, 2)
                .withRaider(THE_SUMMONER,           0, 2, 2, 1, 2, 3, 1, 2)
                .withRaider(THE_SUMMONER_BOSS,      0, 0, 0, 1, 1, 1, 0, 2)
                .withRaider(NECROMANCER_BONES,      0, 1, 2, 2, 1, 2, 1, 2)
                .withRaider(MODIFIGER,              0, 2, 2, 3, 2, 3, 3, 3)
                .withRaider(MARKSMAN,               0, 2, 2, 3, 4, 3, 2, 2)
                .withRaider(MASQUERADER,            0, 1, 0, 0, 3, 0, 0, 0)
                .withRaider(TENGU,                  0, 2, 3, 4, 2, 2, 3, 2)
                .withRaider(NINJAR,                 0, 2, 3, 2, 3, 2, 3, 2)
                .withRaider(NECROMANCER_RAIDED,     0, 2, 2, 3, 1, 0, 1, 2)
                .withRaider(ELECTROMANCER_RAIDED,   0, 2, 3, 2, 2, 2, 2, 2)
                .withRaider(SAVAGER,                0, 2, 3, 4, 3, 4, 3, 2)
                .withRaider(INCINERATOR,            0, 1, 2, 1, 2, 2, 2, 2)
                .withRaider(INQUISITOR_RAIDED,      0, 2, 2, 3, 3, 3, 2, 4)
                .withRaider(GUNILLAGER,             0, 1, 3, 2, 2, 3, 1, 1)
                .withRaider(ILLIGEVE,               0, 1, 1, 2, 1, 0, 2, 1)
                .withRaider(SURPRISER,              0, 1, 1, 2, 2, 1, 1, 2)
                .withRaider(ROCKETILLAGER,          0, 0, 1, 2, 1, 1, 2, 1)
                .withRaider(CREEPILLAGER,           0, 0, 0, 0, 1, 1, 1, 1)
                .withRaider(MONSTERILLAGER,         0, 0, 0, 1, 1, 0, 1, 1)
                .withRaider(BLADE_MASTER,           0, 1, 2, 3, 2, 1, 2, 2)
                .withRaider(ICEOLOGER_FF,           0, 2, 2, 3, 2, 1, 2, 1)
                .withRaider(PILLAGER_BRUTE,         0, 1, 1, 2, 2, 1, 2, 3)
                .withRaider(CRAZED,                 0, 1, 2, 3, 2, 1, 2, 1)
                .withRaider(CABBAGER,               0, 2, 3, 2, 2, 2, 3, 2)
                .withRaider(PHANTOM_TAMER,          0, 1, 2, 2, 2, 2, 2, 3)
                .withRaider(T_RABUS,                0, 0, 0, 1, 1, 1, 1, 1)
                .withRaider(CYBORG_VINDICATOR,      0, 0, 1, 1, 0, 1, 1, 2)
                .withRaider(CRUDE_RED_GOLEM,        0, 0, 1, 1, 1, 0, 2, 1)
                .withRaider(CRUDE_RED_MONSTROSITY,  0, 0, 0, 0, 1, 0, 1, 0)
                .withRaider(RED_MONSTROSITY_CSM,    0, 0, 0, 0, 0, 1, 0, 1)
                .withRaider(MINI_CRUDE_RED_GOLEM,   0, 2, 3, 3, 4, 3, 2, 3)
                .withRaider(HOUNDMASTER,            0, 2, 3, 2, 2, 1, 2, 2)
                .withRaider(KARATE,                 0, 3, 4, 4, 3, 4, 4, 4)
                .withRaider(ILLAGER_BRUTE,          0, 0, 2, 2, 3, 1, 2, 1)
                .withRaider(SORCERER_ILLAGER,       0, 2, 3, 4, 2, 3, 3, 2)
                .withRaider(WANDERING_ILLAGER,      0, 1, 2, 1, 2, 3, 1, 4)
                .withRaider(BRASHER,                0, 1, 3, 3, 3, 2, 3, 2)
                .withRaider(KNOCKER,                0, 3, 4, 3, 4, 3, 3, 5)
                .withRaider(WRECKER,                0, 2, 3, 4, 3, 2, 2, 3)
                .withRaider(HACKLE,                 0, 1, 3, 2, 2, 3, 2, 1)
                .withRaider(JUGGERNAUT,             0, 1, 2, 2, 3, 2, 1, 1)
                .withRaider(BLUDGEONER,             0, 1, 1, 2, 2, 2, 2, 1)
                .withRaider(PIKELOGER,              0, 2, 2, 3, 3, 3, 2, 2)
                .withRaider(GRINDICATOR,            0, 0, 0, 1, 0, 1, 0, 1)
                .withRaider(OMNILLAGER,             0, 0, 0, 1, 1, 0, 0, 1)
                .withRaider(COWBOY_FRANCIS,         0, 4, 5, 5, 4, 6, 5, 5)
                .withRaider(THROWER,                0, 3, 3, 4, 4, 3, 3, 2)
                .withRaider(BLADE_GUARD,            0, 2, 2, 3, 3, 2, 3, 3)
                .withRaider(RUNNER,                 0, 3, 4, 3, 3, 3, 2, 3)
                .withRaider(DESERT_ILLAGER,         0, 3, 4, 5, 3, 5, 4, 3)
                .withRaider(CRINDICATOR,            0, 1, 2, 2, 2, 1, 1, 1)
                .withRaider(CREAKOLOGER,            0, 1, 2, 2, 2, 1, 2, 3)
                .withRaider(JURGOLOGER,             0, 1, 2, 3, 1, 2, 2, 2)
                .withRaider(BLACK_MASTER,           0, 1, 1, 2, 3, 2, 2, 3)
                .withRaider(VILER_WITCH,            0, 2, 3, 2, 3, 4, 2, 2)
                .withRaider(PILLAGER_CAR,           0, 1, 2, 2, 1, 2, 2, 1)
                .withRaider(PILLAGER_SOLDIER,       0, 2, 3, 3, 3, 4, 3, 3)
                .withRaider(VINDICATOR_FLAMETHROWER,0, 1, 2, 3, 2, 2, 2, 1)
                .withRaider(ASSAULT_PILLAGER,       0, 3, 4, 3, 3, 3, 4, 4)
                .withRaider(PILLAGER_PLANE,         0, 1, 2, 3, 3, 4, 2, 2)
                .withRaider(PILLAGER_CANNON,        0, 1, 2, 2, 2, 3, 2, 2)
                .withRaider(WANDERING_TRAITOR,      0, 1, 2, 3, 4, 3, 2, 2)
                .withRaider(DRUSKI,                 0, 2, 4, 5, 3, 3, 3, 3)
                .withRaider(LACHER,                 0, 1, 3, 3, 2, 2, 3, 3)
                .withRaider(CRISKO,                 0, 4, 4, 3, 4, 4, 4, 3)
                .withRaider(BLAKER,                 0, 4, 5, 4, 4, 4, 3, 5)
                .withRaider(TOXICIST,               0, 3, 4, 5, 3, 4, 3, 4)
                .withRaider(REVENANT,               0, 1, 2, 2, 3, 1, 2, 3)
                .withRaider(AMPI,                   0, 0, 1, 1, 0, 1, 1, 1)
                .withEliteWave(1, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(2, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(3, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(4, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(5, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(6, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .withEliteWave(7, NUAOS_ELITE.get(), VOLDON_ELITE.get(), XYDRAX_ELITE.get(), MODUR_ELITE.get())
                .registerDefault();

        //Reflection Option:
        //ObfuscationReflectionHelper.findField(Raid.RaiderType.class, "f_37815_").set(Raid.RaiderType.VINDICATOR, new int[]{0, 0, 2, 0, 1, 4, 2, 5});
    }

    public static boolean isEliteWave(RaidDifficulty raidDifficulty, int wave)
    {
        return CURRENT_WAVES.get(raidDifficulty).isEliteWave(wave);
    }

    public static EntityType<?> getRandomElite(RaidDifficulty raidDifficulty, int wave)
    {
        List<EntityType<?>> pool = CURRENT_WAVES.get(raidDifficulty).getElites(wave);

        if(pool.isEmpty()) return null;
        else if(pool.size() == 1) return pool.get(0);
        else return pool.get(new Random().nextInt(pool.size()));
    }

    public static List<Integer> getWaves(RaidDifficulty raidDifficulty, String raiderType)
    {
        return CURRENT_WAVES.get(raidDifficulty).getWaves().getOrDefault(raiderType, Arrays.stream(BLANK).boxed().collect(Collectors.toList()));
    }

    //Waves

    public static RaidDifficultyEnemyManager createDefaultWavesFor(RaidDifficulty raidDifficulty)
    {
        return new RaidDifficultyEnemyManager(raidDifficulty);
    }
}
