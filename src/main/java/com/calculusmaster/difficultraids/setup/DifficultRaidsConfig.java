package com.calculusmaster.difficultraids.setup;

import com.calculusmaster.difficultraids.DifficultRaids;
import com.calculusmaster.difficultraids.config.RaidDifficultyConfig;
import com.calculusmaster.difficultraids.config.RaiderConfigs;
import com.calculusmaster.difficultraids.raids.RaidDifficulty;
import com.calculusmaster.difficultraids.raids.RaidEnemyRegistry;
import com.calculusmaster.difficultraids.util.DifficultRaidsUtil;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.calculusmaster.difficultraids.util.DifficultRaidsUtil.OverflowHandlingMode.REPEAT;
import static com.calculusmaster.difficultraids.util.DifficultRaidsUtil.OverflowHandlingMode.ZERO;

public class DifficultRaidsConfig
{
    private static final Logger LOGGER = LogUtils.getLogger();

    //Common Config Values
    public static ForgeConfigSpec.IntValue WAVE_COUNT_EASY;
    public static ForgeConfigSpec.IntValue WAVE_COUNT_NORMAL;
    public static ForgeConfigSpec.IntValue WAVE_COUNT_HARD;
    public static ForgeConfigSpec.EnumValue<DifficultRaidsUtil.OverflowHandlingMode> OVERFLOW_MODE;
    public static ForgeConfigSpec.BooleanValue RESTRICTIVE_MODE;

    public static ForgeConfigSpec.DoubleValue BELL_SEARCH_RADIUS;
    public static ForgeConfigSpec.BooleanValue FRIENDLY_FIRE_ARROWS;

    public static ForgeConfigSpec.IntValue HIGHLIGHT_THRESHOLD;
    public static ForgeConfigSpec.BooleanValue BOSS_BARS;
    public static ForgeConfigSpec.BooleanValue SHOW_WAVE_INFORMATION;

    public static ForgeConfigSpec.BooleanValue INSANITY_MODE;
    public static ForgeConfigSpec.DoubleValue INSANITY_COUNT_MULTIPLIER;
    public static ForgeConfigSpec.BooleanValue DISPLAY_INSANITY_MODE;

    public static Map<String, ForgeConfigSpec.BooleanValue> ENABLED_RAIDERS = new HashMap<>();

    public static RaidDifficultyConfig DEFAULT, HERO, LEGEND, MASTER, GRANDMASTER;

    //Misc Tags
    public static TagKey<EntityType<?>> WINDS_CURSE_IMMUNE = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("difficultraids:winds_curse_immune"));

    public static void initializeConfigs()
    {
        DEFAULT.init();
        HERO.init();
        LEGEND.init();
        MASTER.init();
        GRANDMASTER.init();
    }

    public static void register()
    {
        //General Config
        ForgeConfigSpec.Builder GENERAL = new ForgeConfigSpec.Builder();

        GENERAL.push("Wave Amounts");

        WAVE_COUNT_EASY = GENERAL
                .comment("Number of waves in a Raid on Easy difficulty.")
                .defineInRange("waveCountEasy", 3, 1, Integer.MAX_VALUE);

        WAVE_COUNT_NORMAL = GENERAL
                .comment("Number of waves in a Raid on Normal difficulty.")
                .defineInRange("waveCountNormal", 5, 1, Integer.MAX_VALUE);

        WAVE_COUNT_HARD = GENERAL
                .comment("Number of waves in a Raid on Hard difficulty.")
                .defineInRange("waveCountHard", 7, 1, Integer.MAX_VALUE);

        OVERFLOW_MODE = GENERAL
                .comment("Advanced config. Only edit if you're working with the datapack configuration!")
                .comment("Determines how the mod will handle the case where a raider does not have a spawn count defined for a wave.")
                .comment("This can happen if the number of waves is changed, but the spawn counts list for a raider is not updated.")
                .comment("ZERO: The raider will not spawn if it does not have a spawn count defined for the current wave.")
                .comment("REPEAT: The raider will spawn with the last wave's (whatever the last registered wave is for it) spawn count for the current wave.")
                .defineEnum("arrayOverflowMode", ZERO, ZERO, REPEAT);

        RESTRICTIVE_MODE = GENERAL
                .comment("If enabled, this will make it so that if a raider is not registered implicitly by DifficultRaids, or a datapack, it will not spawn in higher difficulty raids.")
                .comment("This can be used if you want full control over raid spawns, and do not want other mods to inject their raiders if not registered by DifficultRaids or a datapack.")
                .define("restrictiveMode", false);

        GENERAL.pop();

        GENERAL.push("Raid Mechanics");

        BELL_SEARCH_RADIUS = GENERAL
                .comment("Search radius of the Bell when hit.")
                .comment("WARNING: This can cause a decent bit of lag at high radius values because it will search more and more blocks around the village center.")
                .comment("Vanilla Minecraft uses a radius of 48 blocks.")
                .defineInRange("bellSearchRadius", 256.0, 0., Integer.MAX_VALUE);

        FRIENDLY_FIRE_ARROWS = GENERAL
                .comment("Toggles whether arrows fired by Raiders (such as Pillagers) can deal damage to other Raiders.")
                .define("friendlyFireArrowsEnabled", false);

        GENERAL.pop();

        GENERAL.push("UI");

        HIGHLIGHT_THRESHOLD = GENERAL
                .comment("If there are fewer raiders alive than this threshold, they will be highlighted permanently. Set to 0 to disable highlighting.")
                .defineInRange("highlightThreshold", 3, 0, Integer.MAX_VALUE);

        BOSS_BARS = GENERAL
                .comment("Toggles whether Boss Bars for Elite Raiders/Bosses will show up during Raids.")
                .comment("Enabling this will create Boss Event Bars for:", "DifficultRaids: Nuaos, Xydrax, Voldon, Modur", "Illage & Spillage: Freakager, Magispeller, Spiritcaller", "Dungeons Mobs: Redstone Golem", "Leo's Illagers: Lightningcaller, Clownager")
                .define("bossBarsEnabled", true);

        SHOW_WAVE_INFORMATION = GENERAL
                .comment("Determines if wave information will show up in the Raid Event title.")
                .define("showWaveInformation", true);

        GENERAL.pop();

        GENERAL.push("Insanity Mode");

        INSANITY_MODE = GENERAL
                .comment("Activate Insanity mode.")
                .comment("'Detecting hundreds of raiders in the region. Are you certain whatever you're doing is worth it?'")
                .define("insanityMode", false);

        INSANITY_COUNT_MULTIPLIER = GENERAL
                .comment("The multiplier for the number of raiders spawned in Insanity mode.")
                .comment("This gets applied on top of whatever difficulty a Raid is at. This will not apply to Default Raids.")
                .defineInRange("insanityCountMultiplier", 3.0, 1.0, Double.MAX_VALUE);
        DISPLAY_INSANITY_MODE = GENERAL
                .comment("Toggles whether or not to display 'Insane' on the raid")
                .define("displayInsanityMode", true);

        GENERAL.pop();


        GENERAL.comment("Customize which Raiders will show up in Raids. A game restart is required.")
                .push("Enabled Raiders");

        GENERAL.push("Vanilla Raiders");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.VINDICATOR, GENERAL.define("enableVindicators", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.EVOKER, GENERAL.define("enableEvokers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.PILLAGER, GENERAL.define("enablePillagers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.WITCH, GENERAL.define("enableWitches", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.RAVAGER, GENERAL.define("enableRavagers", true));
        GENERAL.pop().push("DifficultRaids");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ILLUSIONER, GENERAL.define("enableIllusioners", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.WARRIOR, GENERAL.define("enableWarriors", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.DART, GENERAL.define("enableDarts", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CONDUCTOR, GENERAL.define("enableConductors", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.NECROMANCER, GENERAL.define("enableNecromancers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SHAMAN, GENERAL.define("enableShamans", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.TANK, GENERAL.define("enableTanks", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ASSASSIN, GENERAL.define("enableAssassins", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.FROSTMAGE, GENERAL.define("enableFrostmages", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ASHENMANCER, GENERAL.define("enableAshenmancers", true));


        GENERAL.pop().push("Requires HuntersReturn");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.HUNTER, GENERAL.define("enableHunters", true));
        GENERAL.pop().push("Requires EnchantWithMob");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ENCHANTER, GENERAL.define("enableEnchanters", true));
        GENERAL.pop().push("Requires It Takes a Pillage");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ARCHER, GENERAL.define("enableArchers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SKIRMISHER, GENERAL.define("enableSkirmishers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.LEGIONER, GENERAL.define("enableLegioners", true));
        GENERAL.pop().push("Requires Illage & Spillage");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.IGNITER, GENERAL.define("enableIgniters", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.TWITTOLLAGER, GENERAL.define("enableTwittollagers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.PRESERVER, GENERAL.define("enablePreservers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ABSORBER, GENERAL.define("enableAbsorbers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CROCOFANG, GENERAL.define("enableCrocofangs", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ENGINEER, GENERAL.define("enableEngineers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MAGISPELLER, GENERAL.define("enableMagispellers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SPIRITCALLER, GENERAL.define("enableSpiritcallers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.FREAKAGER, GENERAL.define("enableFreakagers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.BOSS_RANDOMIZER, GENERAL.define("enableBossRandomizers", true));
        GENERAL.pop().push("Requires Savage and Ravage");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.GRIEFER, GENERAL.define("enableGriefers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.EXECUTIONER, GENERAL.define("enableExecutioners", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.TRICKSTER, GENERAL.define("enableTricksters", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ICEOLOGER_SR, GENERAL.define("enableSavageRavageIceologers", true));
        GENERAL.pop().push("Requires Leo's Illagers");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SHIELD_VINDICATOR, GENERAL.define("enableShieldVindicator", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.LIGHTNINGCALLER, GENERAL.define("enableLightningCaller", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CLOWNAGER, GENERAL.define("enableClownager", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CONFUSER, GENERAL.define("enableConfuser", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.METEORITE_CALLER, GENERAL.define("enableMeteoriteCaller", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SNOWOLAGER, GENERAL.define("enableSnowolager", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.NECROMANCER_LEO, GENERAL.define("enableNecromancerLeo", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SUMMONER, GENERAL.define("enableSummoner", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.TROUBLEMAKER, GENERAL.define("enableTroublemaker", true));
        GENERAL.pop().push("Requires Dungeon Mobs");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MOUNTAINEER, GENERAL.define("enableMountaineers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ROYAL_GUARD, GENERAL.define("enableRoyalGuards", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.GEOMANCER, GENERAL.define("enableGeomancers", true));
//        ENABLED_RAIDERS.put(RaidEnemyRegistry.ILLUSIONER_DM, GENERAL.define("enableDungeonMobsIllusioners", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MAGE, GENERAL.define("enableMages", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ICEOLOGER_DM, GENERAL.define("enableDungeonMobsIceologers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.WINDCALLER, GENERAL.define("enableWindcallers", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SQUALL_GOLEM, GENERAL.define("enableSquallGolems", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.REDSTONE_GOLEM, GENERAL.define("enableRedstoneGolems", true));
        GENERAL.pop().push("Requires The Conjurer");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CONJURER, GENERAL.define("enableConjurer", false));
        GENERAL.pop().push("Requires Necromancer Mod Port");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.NECROMANCER_MOD, GENERAL.define("enableModNecromancer", true));
        GENERAL.pop().push("Requires Werden's Illagers +");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SINISTER, GENERAL.define("enableSinister", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SHADOMANCER, GENERAL.define("enableShadomancer", true));
        //GENERAL.pop().push("Requires Iron's Spells and Spellbooks");
        //ENABLED_RAIDERS.put(RaidEnemyRegistry.ARCHEVOKER, GENERAL.define("enableArchevoker", true));
        //GENERAL.pop().push("Requires Gambler Illager");
        //ENABLED_RAIDERS.put(RaidEnemyRegistry.GAMBLER, GENERAL.define("enableGambler", true));
        GENERAL.pop().push("Requires Guard Illagers");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.GUARD_ILLAGER, GENERAL.define("enableGuardillager", true));
        GENERAL.pop().push("Requires MobZ");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SPIDER_MAGE, GENERAL.define("enableSpiderMage", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ZOMBIE_MAGE, GENERAL.define("enableZombieMage", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.PILLAGER_BOSS, GENERAL.define("enablePillagerBoss", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ILLUSIONER_MOBZ, GENERAL.define("enableMobZIllusioner", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.BABY_RAVAGER, GENERAL.define("enableBabyRavager", true));
        GENERAL.pop().push("Requires Illager Invasion");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.PROVOKER, GENERAL.define("enablePovoker", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.INQUISITOR, GENERAL.define("enableInquisitor", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MARAUDER, GENERAL.define("enableMarauder", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.BASHER, GENERAL.define("enableBasher", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.FIRECALLER, GENERAL.define("enableFirecaller", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.NECROMANCER_INV, GENERAL.define("enableInvasionNecromancer", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ALCHEMIST, GENERAL.define("enableAlchemist", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SORCERER, GENERAL.define("enableSorcerer", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ARCHIVIST, GENERAL.define("enableArchivist", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.INVOKER, GENERAL.define("enableInvoker", true));
        GENERAL.pop().push("Requires Goety");
        GENERAL.comment("Those settings can be overwritten by Goety");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.WARLOCK, GENERAL.define("enableWarlock", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MAVERICK, GENERAL.define("enableMaverick", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.HERETIC, GENERAL.define("enableHeretic", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.PIKER, GENERAL.define("enablePiker", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.RIPPER, GENERAL.define("enableRipper", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CRUSHER, GENERAL.define("enableCrusher", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.STORM_CASTER, GENERAL.define("enableStorm_caster", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CRYOLOGER, GENERAL.define("enableCryologer", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.PREACHER, GENERAL.define("enablePreacher", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CONQUILLAGER, GENERAL.define("enableConquillager", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.INQUILLAGER, GENERAL.define("enableInquillager", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ENVIOKER, GENERAL.define("enableEnvioker", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SORCERER_GOETY, GENERAL.define("enableGoetySorcerer", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MINISTER, GENERAL.define("enableMinister", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.TRAMPLER, GENERAL.define("enableTrampler", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.VIZIER, GENERAL.define("enableVizier", false));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.APOSTLE, GENERAL.define("enableApostle", false));
        GENERAL.comment("If you enable Redstone Golem and Redstone Monstrosity, make sure to enable them in Goety config");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.HOSTILE_RED_GOLEM, GENERAL.define("enableRedstoneGolemGoety", false));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.HOSTILE_RED_MONSTER, GENERAL.define("enableRedstoneMonstrosity", false));
        GENERAL.pop().push("Requires The Summoner Illager Port");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.THE_SUMMONER, GENERAL.define("enableSummoner", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.THE_SUMMONER_BOSS, GENERAL.define("enableSummonerBoss", true));
        GENERAL.pop().push("Requires Bones Update");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.NECROMANCER_BONES, GENERAL.define("enableBonesNecromancer", true));
        GENERAL.pop().push("Requires Illager Additions");
        GENERAL.comment("Those settings can be overwritten by Illager Additions");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ROYAL_GUARD_SPEAR, GENERAL.define("enableRoyalGuardSpear", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SPEARMAN, GENERAL.define("enableSpearman", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SAMURAI, GENERAL.define("enableSamurai", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.BEAMLOGER, GENERAL.define("enableBeamloger", true));
        GENERAL.comment("If you enable Cowboy or Shogun, make sure to enable them in Illager Additions config");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.COWBOY, GENERAL.define("enableCowboy", false));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SHOGUN, GENERAL.define("enableShogun", false));
        GENERAL.pop().push("Requires The Modifiger");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MODIFIGER, GENERAL.define("enableModifiger", true));
        GENERAL.pop().push("Requires Musketeer Illager");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MARKSMAN, GENERAL.define("enableMarksman", true));
        GENERAL.pop().push("Requires The Masquerader");
        GENERAL.comment("If you change the spawn rate of this mob, make sure no other bosses from Illage & Spillage spawn in the same wave, or they will have infinite force field.");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MASQUERADER, GENERAL.define("enableMasquerader", true));
        GENERAL.pop().push("Requires Bagus Mob");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.TENGU, GENERAL.define("enableTengu", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.NINJAR, GENERAL.define("enableNinjar", true));
        GENERAL.pop().push("Requires Illager Revolution");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.BEAST_TAMER, GENERAL.define("enableBeastTamer", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.BLADE_KNIGHT, GENERAL.define("enableBladeKnight", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SCAVENGER, GENERAL.define("enableScavenger", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SOUL_SAGE, GENERAL.define("enableSoulSage", true));
        GENERAL.pop().push("Requires Raided");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.NECROMANCER_RAIDED, GENERAL.define("enableRaidedNecromancer", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ELECTROMANCER_RAIDED, GENERAL.define("enableRaidedElectromancer", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SAVAGER, GENERAL.define("enableSavager", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.INCINERATOR, GENERAL.define("enableIncinerator", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.INQUISITOR_RAIDED, GENERAL.define("enableRaidedInquisitor", true));
        GENERAL.pop().push("Requires More Illagers");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.GUNILLAGER, GENERAL.define("enableGunillager", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ILLIGEVE, GENERAL.define("enableIlligeve", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.SURPRISER, GENERAL.define("enableSurpriser", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ROCKETILLAGER, GENERAL.define("enableRocketillager", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CREEPILLAGER, GENERAL.define("enableCreepillager", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MONSTERILLAGER, GENERAL.define("enableMonsterillager", true));
        GENERAL.pop().push("Requires Slash Illager");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.BLADE_MASTER, GENERAL.define("enableBladeMaster", true));
        GENERAL.pop().push("Requires Friends & Foes");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.ICEOLOGER_FF, GENERAL.define("enableFriendsAndFoesIceologer", true));
        GENERAL.pop().push("Requires Mo' Features");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.PILLAGER_BRUTE, GENERAL.define("enablePillagerBrute", true));
        GENERAL.pop().push("Requires Brazier");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CRAZED, GENERAL.define("enableCrazed", false));
        GENERAL.pop().push("Requires Ravage & Cabbage");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CABBAGER, GENERAL.define("enableCabbager", true));
        GENERAL.pop().push("Requires CrimsonSteve's more mobs");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.PHANTOM_TAMER, GENERAL.define("enablePhamtomTamer", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.T_RABUS, GENERAL.define("enableTRabus", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CYBORG_VINDICATOR, GENERAL.define("enableCyborgVindicator", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CRUDE_RED_GOLEM, GENERAL.define("enableCrudeRedstoneGolem", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.CRUDE_RED_MONSTROSITY, GENERAL.define("enableCrudeRedstoneMonstrosity", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.RED_MONSTROSITY_CSM, GENERAL.define("enableCrimsonStevesRedstoneMonstrosity", true));
        ENABLED_RAIDERS.put(RaidEnemyRegistry.MINI_CRUDE_RED_GOLEM, GENERAL.define("enableMiniCrudeRedstoneGolem", true));
        GENERAL.pop().push("Requires All Bark, All Bite");
        ENABLED_RAIDERS.put(RaidEnemyRegistry.HOUNDMASTER, GENERAL.define("enableHoundmaster", true));

        GENERAL.pop();

        GENERAL.pop();


        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GENERAL.build(), DifficultRaids.MODID + "/general.toml");

        //Raid Difficulty Configs
        for(RaidDifficulty rd : RaidDifficulty.values())
        {
            RaidDifficultyConfig config = new RaidDifficultyConfig();
            ForgeConfigSpec.Builder spec = new ForgeConfigSpec.Builder();

            if(rd == RaidDifficulty.DEFAULT) spec.comment("Note: Changing values in this config will have no impact on the game. Default Raids are Vanilla and do not feature anything from this mod. The config file exists for consistency within the code.\n");
            else spec.comment("Edit config values for " + rd.getFormattedName() + " Raids.\n");

            //General
            spec.push("General Settings");

            config.elitesEnabled = spec
                    .comment("Determines if Elite Raiders will show up in Raids.")
                    .define("elitesEnabled", true);

            config.playerHealthBoostAmount = spec
                    .comment("Extra health that Raiders will receive per additional player in the Raid. 0 to disable Raiders receiving extra health.")
                    .defineInRange("playerHealthBoostAmount", 2.0F, 0., Double.MAX_VALUE);

            spec.pop();

            //Vindicator
            spec.push("Vindicator Settings");
            config.vindicator = new RaiderConfigs.Vindicator(rd, spec);
            spec.pop();

            //Evoker
            spec.push("Evoker Settings");
            config.evoker = new RaiderConfigs.Evoker(rd, spec);
            spec.pop();

            //Pillager
            spec.push("Pillager Settings");
            config.pillager = new RaiderConfigs.Pillager(rd, spec);
            spec.pop();

            //Ravager
            spec.push("Ravager Settings");
            config.ravager = new RaiderConfigs.Ravager(rd, spec);
            spec.pop();

            //Warrior
            spec.push("Warrior Settings");
            config.warrior = new RaiderConfigs.Warrior(rd, spec);
            spec.pop();

            //Dart
            spec.push("Dart Settings");
            config.dart = new RaiderConfigs.Dart(rd, spec);
            spec.pop();

            //Conductor
            spec.push("Conductor Settings");
            config.conductor = new RaiderConfigs.Conductor(rd, spec);
            spec.pop();

            //Necromancer
            spec.push("Necromancer Settings");
            config.necromancer = new RaiderConfigs.Necromancer(rd, spec);
            spec.pop();

            //Shaman
            spec.push("Shaman Settings");
            config.shaman = new RaiderConfigs.Shaman(rd, spec);
            spec.pop();

            //Tank
            spec.push("Tank Settings");
            config.tank = new RaiderConfigs.Tank(rd, spec);
            spec.pop();

            //Assassin
            spec.push("Assassin Settings");
            config.assassin = new RaiderConfigs.Assassin(rd, spec);
            spec.pop();

            //Frostmage
            spec.push("Frostmage Settings");
            config.frostmage = new RaiderConfigs.Frostmage(rd, spec);
            spec.pop();

            //Ashenmancer
            spec.push("Ashenmancer Settings");
            config.ashenmancer = new RaiderConfigs.Ashenmancer(rd, spec);
            spec.pop();

            spec.push("Elite Raiders");

            //Nuaos
            spec.push("Nuaos Settings");
            config.nuaos = new RaiderConfigs.Nuaos(rd, spec);
            spec.pop();

            //Xydrax
            spec.push("Xydrax Settings");
            config.xydrax = new RaiderConfigs.Xydrax(rd, spec);
            spec.pop();

            //Modur
            spec.push("Modur Settings");
            config.modur = new RaiderConfigs.Modur(rd, spec);
            spec.pop();

            //Voldon
            spec.push("Voldon Settings");
            config.voldon = new RaiderConfigs.Voldon(rd, spec);
            spec.pop();

            spec.pop();

            spec.push("Compatibility");

            //Hunters Return
            spec.comment("REQUIRES 'Hunters Return'").push("Hunter Illager Settings");
            config.hunter = new RaiderConfigs.Hunter(rd, spec);
            spec.pop();

            //Archer
            spec.comment("REQUIRES 'It Takes a Pillage'").push("Archer Settings");
            config.archer = new RaiderConfigs.Archer(rd, spec);
            spec.pop();

            //Skirmisher
            spec.comment("REQUIRES 'It Takes a Pillage'").push("Skirmisher Settings");
            config.skirmisher = new RaiderConfigs.Skirmisher(rd, spec);
            spec.pop();

            //Legioner
            spec.comment("REQUIRES 'It Takes a Pillage'").push("Legioner Settings");
            config.legioner = new RaiderConfigs.Legioner(rd, spec);
            spec.pop();

            //Executioner
            spec.comment("REQUIRES 'Savage and Ravage'").push("Executioner Settings");
            config.executioner = new RaiderConfigs.Executioner(rd, spec);
            spec.pop();

            //Mountaineer
            spec.comment("REQUIRES 'Dungeon Mobs'").push("Mountaineer Settings");
            config.mountaineer = new RaiderConfigs.Mountaineer(rd, spec);
            spec.pop();

            //Royal Guard
            spec.comment("REQUIRES 'Dungeon Mobs'").push("Royal Guard Settings");
            config.royalguard = new RaiderConfigs.RoyalGuard(rd, spec);
            spec.pop();

            //Vindicator With Shield
            spec.comment("REQUIRES 'Leo's Illagers'").push("Vindicator With Shield Settings");
            config.vindicatorWithShield = new RaiderConfigs.VindicatorWithShield(rd, spec);
            spec.pop();

            spec.pop();

            ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, spec.build(), DifficultRaids.MODID + "/raid-" + rd.toString().toLowerCase() + ".toml");

            switch(rd)
            {
                case DEFAULT -> DEFAULT = config;
                case HERO -> HERO = config;
                case LEGEND -> LEGEND = config;
                case MASTER -> MASTER = config;
                case GRANDMASTER -> GRANDMASTER = config;
            }


        }
    }
}
