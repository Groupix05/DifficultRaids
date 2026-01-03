package com.calculusmaster.difficultraids.util;

import baguchan.enchantwithmob.registry.ModEntities;
import baguchan.hunters_return.init.HunterEntityRegistry;
import baguchan.the_modifiger.registry.ModEntityRegistry;
import blusunrize.immersiveengineering.common.register.IEEntityTypes;
import cn.leolezury.leosillagers.init.EntityInit;
import codyhuh.ravagecabbage.registry.RCEntities;
import com.Polarice3.Goety.common.entities.ModEntityType;
import com.belgieyt.morefeatures.core.registry.MFEntity;
import com.beta.theresilianceofraiders.init.TheresilianceofraidersModEntities;
import com.bilibili.player_ix.noixmod_api.register.NoixmodAPIEntities;
import com.coldspell.coldstrader.init.ColdstraderModEntities;
import com.faboslav.friendsandfoes.common.init.FriendsAndFoesEntityTypes;
import com.francisplay446.raiderv.init.RaidervModEntities;
import com.francisplayz446.expansion.init.ExpansionModEntities;
import com.github.alexthe666.rats.registry.RatsEntityRegistry;
import com.hexagram2021.oceanworld.common.register.OWEntities;
import com.infamous.all_bark_all_bite.common.registry.ABABEntityTypes;
import com.jerotes.jerotesvillage.init.JerotesVillageEntityType;
import com.legacy.conjurer_illager.registry.IllagerEntityTypes;
import com.calculusmaster.difficultraids.entity.DifficultRaidsEntityTypes;
import com.izofar.takesapillage.init.ModEntityTypes;
import com.mrbysco.raided.registry.RaidedRegistry;
import com.mysticmage.musketeer_illager.init.MusketeerIllagerModEntities;
import com.possible_triangle.brazier.Content;
import com.sh1nylabs.bonesupdate.init.BonesEntities;
import com.teamabnormals.savage_and_ravage.core.registry.SREntityTypes;
import comfrancisplayz446.necromancer.init.NecromancerModEntities;
import elucent.eidolon.registries.EidolonEntities;
import francisplayz446.karateillagerfinallyported.init.KarateillagerfinallyportedModEntities;
import fuzs.illagerinvasion.init.ModRegistry;
import net.francisplayz446.summoner.init.ThesummonerModEntities;
import net.mcreator.crimson_steves_mobs.init.CrimsonStevesMobsModEntities;
import net.mcreator.expadosillagerss.init.ExpadosIllagerssModEntities;
import net.mcreator.gloom_raiders.init.GloomRaidersModEntities;
import net.mcreator.illagerbrute.init.IllagerBruteModEntities;
import net.mcreator.illagerexpansions.init.IllagerExpansionsModEntities;
import net.mcreator.illageruniverse.init.IllagerUniverseModEntities;
import net.mcreator.illagerworldwar.init.IllagerWorldWarModEntities;
import net.mcreator.justillagers.init.JustillagersModEntities;
import net.mcreator.mechapillagerboss.init.MechapillagerbossModEntities;
import net.mcreator.moreillagers.init.MoreIllagersModEntities;
import net.mcreator.pillagerboss.init.PillagerBossModEntities;
import net.mcreator.pillagersplus.init.PillagersplusModEntities;
import net.mcreator.poachers.init.PoachersModEntities;
import net.mcreator.roost.init.Roost2ModEntities;
import net.mcreator.rpg.init.RpgModEntities;
import net.mcreator.sharks.init.BenssharksModEntities;
import net.mcreator.sorcererillager.init.SorcererIllagerModEntities;
import net.mcreator.theresistanceoftheraiders.init.TheResistanceOfTheRaidersModEntities;
import net.mcreator.virtusosgrandbattle.init.VirtusosgrandbattleModEntities;
import net.mobz.init.MobZEntities;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import net.minecraft.world.entity.EntityType;
import net.tamirsvn.mischiefillagers.init.MischiefIllagersModEntities;

import java.util.ArrayList;
import java.util.List;

public class DifficultRaidsUtil
{
    public static final String ELECTRO_ILLAGER_CUSTOM_BOLT_TAG = "DifficultRaids_Electro_Bolt";

    public enum OverflowHandlingMode { ZERO, REPEAT }

    //For Armor Modifiers
    public static final List<EntityType<?>> STANDARD_RAIDERS = new ArrayList<>();
    public static final List<EntityType<?>> ADVANCED_RAIDERS = new ArrayList<>();
    public static final List<EntityType<?>> BASIC_MAGIC_RAIDERS = new ArrayList<>();
    public static final List<EntityType<?>> ADVANCED_MAGIC_RAIDERS = new ArrayList<>();

    public static void registerArmorModifierRaiderLists()
    {
        //Default – Skipping: Assassin, Elites, Tank
        STANDARD_RAIDERS.addAll(List.of(EntityType.PILLAGER, EntityType.VINDICATOR, EntityType.WITCH, DifficultRaidsEntityTypes.WARRIOR_ILLAGER.get(), DifficultRaidsEntityTypes.DART_ILLAGER.get()));
        ADVANCED_RAIDERS.add(EntityType.ILLUSIONER);
        BASIC_MAGIC_RAIDERS.addAll(List.of(EntityType.WITCH, EntityType.EVOKER, DifficultRaidsEntityTypes.SHAMAN_ILLAGER.get()));
        ADVANCED_MAGIC_RAIDERS.addAll(List.of(DifficultRaidsEntityTypes.ELECTRO_ILLAGER.get(), DifficultRaidsEntityTypes.NECROMANCER_ILLAGER.get(), DifficultRaidsEntityTypes.FROST_ILLAGER.get(), DifficultRaidsEntityTypes.ASHENMANCER_ILLAGER.get()));

        //Mod Compat
        if(Compat.HUNTERS_RETURN.isLoaded()) STANDARD_RAIDERS.add(HunterEntityRegistry.HUNTERILLAGER.get());

        if(Compat.ENCHANT_WITH_MOB.isLoaded()) ADVANCED_MAGIC_RAIDERS.add(ModEntities.ENCHANTER.get());

        if(Compat.IT_TAKES_A_PILLAGE.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(ModEntityTypes.ARCHER.get(), ModEntityTypes.SKIRMISHER.get()));
            ADVANCED_RAIDERS.add(ModEntityTypes.LEGIONER.get());
        }

        if(Compat.ILLAGE_AND_SPILLAGE.isLoaded()) //Skipping: Absorber, Magispeller/Freakager/Spiritcaller/Boss Randomizer
        {
            STANDARD_RAIDERS.addAll(List.of(
                    com.yellowbrossproductions.illageandspillage.init.ModEntityTypes.Preserver.get(),
                    com.yellowbrossproductions.illageandspillage.init.ModEntityTypes.Igniter.get()
            ));
            ADVANCED_RAIDERS.addAll(List.of(
                    com.yellowbrossproductions.illageandspillage.init.ModEntityTypes.Twittollager.get(),
                    com.yellowbrossproductions.illageandspillage.init.ModEntityTypes.Crocofang.get(),
                    com.yellowbrossproductions.illageandspillage.init.ModEntityTypes.Engineer.get()
            ));
        }

        if(Compat.SAVAGE_AND_RAVAGE.isLoaded())
        {
            ADVANCED_RAIDERS.add(SREntityTypes.EXECUTIONER.get());
            BASIC_MAGIC_RAIDERS.addAll(List.of(SREntityTypes.GRIEFER.get(), SREntityTypes.TRICKSTER.get()));
            ADVANCED_MAGIC_RAIDERS.add(SREntityTypes.ICEOLOGER.get());
        }

        if(Compat.DUNGEONS_MOBS.isLoaded()) //Skipping: Squall Golem, Redstone Golem
        {
            STANDARD_RAIDERS.add(net.firefoxsalesman.dungeonsmobs.entity.ModEntities.MOUNTAINEER.get());
            ADVANCED_RAIDERS.add(net.firefoxsalesman.dungeonsmobs.entity.ModEntities.ROYAL_GUARD.get());
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    net.firefoxsalesman.dungeonsmobs.entity.ModEntities.MAGE.get(),
                    net.firefoxsalesman.dungeonsmobs.entity.ModEntities.ICEOLOGER.get()
            ));
            ADVANCED_MAGIC_RAIDERS.addAll(List.of(
                    net.firefoxsalesman.dungeonsmobs.entity.ModEntities.GEOMANCER.get(),
                    net.firefoxsalesman.dungeonsmobs.entity.ModEntities.WINDCALLER.get()
            ));
        }

        if(Compat.ILLAGER_REVOLUTION.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    net.BKTeam.illagerrevolutionmod.entity.ModEntityTypes.ILLAGER_SCAVENGER.get(),
                    net.BKTeam.illagerrevolutionmod.entity.ModEntityTypes.ACOLYTE.get()
            ));
            BASIC_MAGIC_RAIDERS.add(net.BKTeam.illagerrevolutionmod.entity.ModEntityTypes.ILLAGER_BEAST_TAMER.get());
            ADVANCED_RAIDERS.add(net.BKTeam.illagerrevolutionmod.entity.ModEntityTypes.BLADE_KNIGHT.get());
            ADVANCED_MAGIC_RAIDERS.add(net.BKTeam.illagerrevolutionmod.entity.ModEntityTypes.SOUL_SAGE.get());
        }

        if(Compat.LEOS_ILLAGERS.isLoaded()) //Skipped: Lightningcaller, Clownager
        {
            STANDARD_RAIDERS.addAll(List.of(EntityInit.VINDICATOR_WITH_SHIELD.get(), EntityInit.TROUBLEMAKER.get()));
            ADVANCED_RAIDERS.add(EntityInit.CONFUSER.get());
            BASIC_MAGIC_RAIDERS.addAll(List.of(EntityInit.SNOWOLAGER.get(), EntityInit.NECROMANCER.get()));
            ADVANCED_MAGIC_RAIDERS.addAll(List.of(EntityInit.METEORITE_CALLER.get(), EntityInit.SUMMONER.get()));
        }

        if(Compat.CONJURER.isLoaded()) ADVANCED_MAGIC_RAIDERS.add(IllagerEntityTypes.CONJURER);

        if(Compat.NECROMANCER.isLoaded()) BASIC_MAGIC_RAIDERS.add(NecromancerModEntities.NECROMANCER.get());

        if(Compat.WERDENS_ILLAGERS.isLoaded())
        {
            BASIC_MAGIC_RAIDERS.add(net.werdenrc5.wip.entity.ModEntities.SINISTER.get());
            ADVANCED_MAGIC_RAIDERS.add(net.werdenrc5.wip.entity.ModEntities.SHADOMANCER.get());
        }

        if(Compat.IRONS_SPELLBOOKS.isLoaded()) BASIC_MAGIC_RAIDERS.add(EntityRegistry.ARCHEVOKER.get());

        if(Compat.GAMBLER.isLoaded()) ADVANCED_MAGIC_RAIDERS.add(com.min01.gambler.entity.ModEntities.GAMBLER.get());

        if(Compat.GUARD_ILLAGERS.isLoaded()) STANDARD_RAIDERS.add(com.min01.guardillagers.init.ModEntityTypes.GUARD_ILLAGER.get());

        if(Compat.ILLAGER_ADDITIONS.isLoaded())
        {
            ADVANCED_RAIDERS.addAll(List.of(
                    com.pikachu.mod.illager_more.init.ModEntityTypes.ROYAL_GUARD_SPEAR.get(),
                    com.pikachu.mod.illager_more.init.ModEntityTypes.SPEARMAN.get(),
                    com.pikachu.mod.illager_more.init.ModEntityTypes.HARD_SAMURAI.get(),
                    com.pikachu.mod.illager_more.init.ModEntityTypes.COWBOY.get(),
                    com.pikachu.mod.illager_more.init.ModEntityTypes.SHOGUN.get()
            ));
            BASIC_MAGIC_RAIDERS.add(com.pikachu.mod.illager_more.init.ModEntityTypes.BLASTIONER.get());
        }

        if(Compat.MOBZ.isLoaded()) // Skipping Baby ravager
        {
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    MobZEntities.SPIDER_MAGE.get(),
                    MobZEntities.ZOMBIE_MAGE.get()
            ));
            ADVANCED_RAIDERS.addAll(List.of(
                    MobZEntities.PILLAGER_BOSS.get(),
                    MobZEntities.ILLUSIONER.get()
            ));
        }

        if(Compat.ILLAGER_INVASION.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    ModRegistry.PROVOKER_ENTITY_TYPE.get(),
                    ModRegistry.INQUISITOR_ENTITY_TYPE.get(),
                    ModRegistry.MARAUDER_ENTITY_TYPE.get(),
                    ModRegistry.BASHER_ENTITY_TYPE.get()
            ));
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    ModRegistry.FIRECALLER_ENTITY_TYPE.get(),
                    ModRegistry.NECROMANCER_ENTITY_TYPE.get(),
                    ModRegistry.ALCHEMIST_ENTITY_TYPE.get(),
                    ModRegistry.SORCERER_ENTITY_TYPE.get(),
                    ModRegistry.ARCHIVIST_ENTITY_TYPE.get()
            ));
            ADVANCED_MAGIC_RAIDERS.add(ModRegistry.INVOKER_ENTITY_TYPE.get());
        }

        if(Compat.GOETY.isLoaded()) // Skipping trampler, Redstone Monstrosity, Redstone Golem and Ripper
        {
            ADVANCED_MAGIC_RAIDERS.addAll(List.of(
                    ModEntityType.VIZIER.get(),
                    ModEntityType.APOSTLE.get()
            ));
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    ModEntityType.WARLOCK.get(),
                    ModEntityType.HERETIC.get(),
                    ModEntityType.MINISTER.get(),
                    ModEntityType.ENVIOKER.get(),
                    ModEntityType.PREACHER.get(),
                    ModEntityType.CRYOLOGER.get(),
                    ModEntityType.STORM_CASTER.get(),
                    ModEntityType.SORCERER.get()
            ));
            STANDARD_RAIDERS.addAll(List.of(
                    ModEntityType.MAVERICK.get(),
                    ModEntityType.INQUILLAGER.get(),
                    ModEntityType.CONQUILLAGER.get(),
                    ModEntityType.CRUSHER.get(),
                    ModEntityType.PIKER.get()
            ));
        }

        if(Compat.SUMMONER.isLoaded()) BASIC_MAGIC_RAIDERS.addAll(List.of(ThesummonerModEntities.SUMMONER.get(),ThesummonerModEntities.SUMMONER_BOSS.get()));

        if(Compat.BONES_UPDATE.isLoaded()) BASIC_MAGIC_RAIDERS.add(BonesEntities.NECROMANCER.get());

        if(Compat.MUSKETEER.isLoaded()) STANDARD_RAIDERS.add(MusketeerIllagerModEntities.MARKSMAN.get());

        if(Compat.MODIFIGER.isLoaded()) BASIC_MAGIC_RAIDERS.add(ModEntityRegistry.MODIFIGER.get());

        if(Compat.BAGUS_MOB.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    baguchan.bagusmob.registry.ModEntityRegistry.NINJAR.get(),
                    baguchan.bagusmob.registry.ModEntityRegistry.TENGU.get()
            ));
        }

        if(Compat.RAIDED.isLoaded()) //Skipping Savager
        {
            STANDARD_RAIDERS.addAll(List.of(
                    RaidedRegistry.INQUISITOR.getEntityType(),
                    RaidedRegistry.INCINERATOR.getEntityType()
            ));
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    RaidedRegistry.NECROMANCER.getEntityType(),
                    RaidedRegistry.ELECTROMANCER.getEntityType()
            ));
        }

        if(Compat.MORE_ILLAGERS.isLoaded()) //Skipping Monsterillager and Creepillager
        {
            STANDARD_RAIDERS.addAll(List.of(
                    MoreIllagersModEntities.GUNILLAGER.get(),
                    MoreIllagersModEntities.ROCKETILLAGER.get(),
                    MoreIllagersModEntities.ILLGEVE.get(),
                    MoreIllagersModEntities.SURPRISER.get()
            ));
        }

        if(Compat.SLASH_ILLAGER.isLoaded()) ADVANCED_RAIDERS.add(baguchan.slash_illager.registry.ModEntityRegistry.BLADE_MASTER.get());

        if(Compat.BRAZIER.isLoaded()) BASIC_MAGIC_RAIDERS.add(Content.CRAZED.get());

        if(Compat.MO_FEATURES.isLoaded()) STANDARD_RAIDERS.add(MFEntity.PILLAGER_BRUTE.get());

        if(Compat.FRIENDS_AND_FOES.isLoaded()) BASIC_MAGIC_RAIDERS.add(FriendsAndFoesEntityTypes.ICEOLOGER.get());

        if(Compat.ALL_BARK_ALL_BITE.isLoaded()) STANDARD_RAIDERS.add(ABABEntityTypes.HOUNDMASTER.get());

        if(Compat.RAVAGE_AND_CABBAGE.isLoaded()) STANDARD_RAIDERS.add(RCEntities.CABBAGER.get());

        //Skipping T-Rabus, Cyborg Vindicator, Crude Redstone Golem, Curde Redstone Monstosity, Crude Minion Redstone Golem and Redstone Monstrosity
        if(Compat.CRIMSON_STEVES_MOBS.isLoaded()) BASIC_MAGIC_RAIDERS.add(CrimsonStevesMobsModEntities.PHANTOM_TAMER.get());

        if(Compat.SORCERER_ILLAGER.isLoaded()) BASIC_MAGIC_RAIDERS.add(SorcererIllagerModEntities.SORCERERILLAGER.get());

        if(Compat.ILLAGER_BRUTE.isLoaded()) STANDARD_RAIDERS.add(IllagerBruteModEntities.ILLAGERBRUTE.get());

        if(Compat.WANDERING_ILLAGER.isLoaded()) STANDARD_RAIDERS.add(de.achtii.wandering_illager.entity.ModEntities.WANDERINGILLAGER.get());

        if(Compat.KARATE_ILLAGER.isLoaded()) STANDARD_RAIDERS.add(KarateillagerfinallyportedModEntities.KARATE_ILLAGER.get());

        if(Compat.EXPADOS_ILLAGERS.isLoaded()) //Skipping Juggernaut
        {
            ADVANCED_MAGIC_RAIDERS.add(ExpadosIllagerssModEntities.GRINDICATOR.get());
            STANDARD_RAIDERS.addAll(List.of(
                    ExpadosIllagerssModEntities.BLUNTOLOGER.get(),
                    ExpadosIllagerssModEntities.CLEAVAGER.get(),
                    ExpadosIllagerssModEntities.SPEAROLOGER.get(),
                    ExpadosIllagerssModEntities.BLUDGEONER.get(),
                    ExpadosIllagerssModEntities.BRASHER.get()
            ));
        }

        if(Compat.FRANCIS_ILLAGERS.isLoaded()) //skipping Grummicator
        {
            STANDARD_RAIDERS.addAll(List.of(
                    ExpansionModEntities.PATROLOGER.get(),
                    ExpansionModEntities.CHEFAGER.get(),
                    ExpansionModEntities.GRINDICATOR.get(),
                    ExpansionModEntities.MAGE.get(),
                    ExpansionModEntities.CHEFOLOGER.get(),
                    ExpansionModEntities.PYROBANDIT.get()
            ));
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    ExpansionModEntities.SANDOLOGER.get(),
                    ExpansionModEntities.VRINCITADOR.get(),
                    ExpansionModEntities.FIREOLOGER.get()

            ));
        }

        if(Compat.EARTH_MOBS.isLoaded()) BASIC_MAGIC_RAIDERS.add(baguchan.earthmobsmod.registry.ModEntities.VILER_WITCH.get());

        if(Compat.ILLAGER_WORLD_WAR.isLoaded()) //Skipping Illager Car, Plane & Cannon
        {
            STANDARD_RAIDERS.addAll(List.of(
                    IllagerWorldWarModEntities.ASSAULT_PILLAGER_SOLDIER.get(),
                    IllagerWorldWarModEntities.PILLAGER_SOLDIER_WITH_FLAMETHROWER.get(),
                    IllagerWorldWarModEntities.PILLAGER_SOLDIER.get(),
                    IllagerWorldWarModEntities.PILLAGER_SOLDIER_ARMED.get()
            ));
        }

        if(Compat.JUST_ILLAGERS.isLoaded()) //Skipping Enderfant because it doesnt extend Raider, Skipping Ampi
        {
            STANDARD_RAIDERS.addAll(List.of(
                    JustillagersModEntities.REVERANT.get(),
                    JustillagersModEntities.TOXICIST.get(),
                    JustillagersModEntities.BLAKER.get(),
                    JustillagersModEntities.CRISKO.get(),
                    JustillagersModEntities.GUARD.get(),
                    JustillagersModEntities.DRUSKI.get()
            ));
        }

        if(Compat.COLDS_WANDERING_TRADER.isLoaded()) BASIC_MAGIC_RAIDERS.add(ColdstraderModEntities.WANDERING_TRAITOR.get());

        if(Compat.ILLAGER_UNIVERSE.isLoaded()) //Skipping Shadow Goat
        {
            STANDARD_RAIDERS.addAll(List.of(
                    IllagerUniverseModEntities.CALLOLOGER.get(),
                    IllagerUniverseModEntities.PRINZOLOGER.get()
            ));
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    IllagerUniverseModEntities.UPGRADER.get(),
                    IllagerUniverseModEntities.UPGRADER_GOLD.get(),
                    IllagerUniverseModEntities.UPGRADER_DIAMOND.get()
            ));
        }

        if(Compat.PILLAGER_BOSS.isLoaded())
        {
            ADVANCED_RAIDERS.addAll(List.of(
                    PillagerBossModEntities.PILLAGERBOSS.get(),
                    PillagerBossModEntities.AFTERLIFEPILLAGERBOSS.get()
            ));
        }

        if(Compat.VIRTUSOS_GRAND_BATTLE.isLoaded()) ADVANCED_RAIDERS.add(VirtusosgrandbattleModEntities.VIRTUOSO_OF_VARIETY.get());

        if(Compat.MISCHIEF_ILLAGERS.isLoaded())
        {
            ADVANCED_RAIDERS.addAll(List.of(
                    MischiefIllagersModEntities.SPARKOLOGER.get(),
                    MischiefIllagersModEntities.FANGCLAW.get(),
                    MischiefIllagersModEntities.PHOTOGRAPHER.get(),
                    MischiefIllagersModEntities.MISCHIEVER.get()
            ));
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    MischiefIllagersModEntities.LIBRAVOKER.get(),
                    MischiefIllagersModEntities.BUBBLEOLOGER.get(),
                    MischiefIllagersModEntities.PARTYGER.get(),
                    MischiefIllagersModEntities.DOODLER.get(),
                    MischiefIllagersModEntities.MAGNETIZER.get(),
                    MischiefIllagersModEntities.IMPRECATOR.get(),
                    MischiefIllagersModEntities.BASS_BLASTER.get(),
                    MischiefIllagersModEntities.WITHERMANCER.get()
            ));
        }

        if(Compat.PILLAGERS_PLUS.isLoaded()) //Other Illagers cannot be added because they dont extend Raider
        {
            STANDARD_RAIDERS.addAll(List.of(
                    PillagersplusModEntities.SWORD_PILLAGER.get(),
                    PillagersplusModEntities.SPEAR_PILLAGER.get(),
                    PillagersplusModEntities.HOG_RIDER.get()
            ));
        }

        if(Compat.IMMERSIVE_ENGINEERING.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    IEEntityTypes.COMMANDO.get(),
                    IEEntityTypes.FUSILIER.get(),
                    IEEntityTypes.BULWARK.get()
            ));
        }

        if(Compat.KAMILSKIS_ADDITIONS.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    RpgModEntities.BALLOON_PILLAGER.get(),
                    RpgModEntities.SILVERFISH_WRANGLER.get()
            ));
        }

        if(Compat.EIDOLON_REPRAISED.isLoaded()) BASIC_MAGIC_RAIDERS.add(EidolonEntities.NECROMANCER.get());

        if(Compat.FROM_THE_SHADOWS.isLoaded()) ADVANCED_RAIDERS.add(net.sonmok14.fromtheshadows.server.utils.registry.EntityRegistry.CLERIC.get());

        if(Compat.RATS.isLoaded()) BASIC_MAGIC_RAIDERS.add(RatsEntityRegistry.PIED_PIPER.get());

        if(Compat.WILD_DELIGHT.isLoaded()) STANDARD_RAIDERS.add(bagu_chan.wild_delight.registry.ModEntityTypes.WILD_CHEF.get());

        if(Compat.JEROTES_VILLAGES.isLoaded()) //skipping firepower pourer, Ominous Banner Projection
        {
            STANDARD_RAIDERS.addAll(List.of(
                    JerotesVillageEntityType.TRUMPETER.get(),
                    JerotesVillageEntityType.BLASTER.get(),
                    JerotesVillageEntityType.DEFECTOR.get(),
                    JerotesVillageEntityType.MAPMAKER.get(),
                    JerotesVillageEntityType.BANNER_BEARER.get(),
                    JerotesVillageEntityType.EXECUTIONER.get(),
                    JerotesVillageEntityType.EXPLORER.get(),
                    JerotesVillageEntityType.SLAVERY_SUPERVISOR.get(),
                    JerotesVillageEntityType.CYCLONER.get(),
                    JerotesVillageEntityType.JAVELIN_THROWER.get(),
                    JerotesVillageEntityType.ZOMBIE_KEEPER.get(),
                    JerotesVillageEntityType.WILD_FINDER.get(),
                    JerotesVillageEntityType.SUBMARINER.get(),
                    JerotesVillageEntityType.SPIRVE.get()
            ));
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    JerotesVillageEntityType.TELEPORTER.get(),
                    JerotesVillageEntityType.LAMP_WIZARD.get(),
                    JerotesVillageEntityType.BITTER_COLD_SORCERER.get(),
                    JerotesVillageEntityType.FIRE_SPITTER.get(),
                    JerotesVillageEntityType.PURPLE_SAND_WITCH.get(),
                    JerotesVillageEntityType.WITCH_SCHOLAR.get()
            ));
            ADVANCED_RAIDERS.addAll(List.of(
                    JerotesVillageEntityType.AX_CRAZY.get(),
                    JerotesVillageEntityType.PURPLE_SAND_HAG.get()
            ));
            ADVANCED_MAGIC_RAIDERS.addAll(List.of(
                    JerotesVillageEntityType.NECROMANCY_WARLOCK.get(),
                    JerotesVillageEntityType.GAVILER.get(),
                    JerotesVillageEntityType.BIG_WITCH.get()
            ));
        }

        if(Compat.NOXIAPI.isLoaded()) //Other Illagers cannot be added because they dont extend Raider
        {
            STANDARD_RAIDERS.addAll(List.of(
                    NoixmodAPIEntities.BUGLER.get(),
                    NoixmodAPIEntities.ARMORER.get(),
                    NoixmodAPIEntities.HUNTER.get(),
                    NoixmodAPIEntities.DRUNKENNESS.get(),
                    NoixmodAPIEntities.FLAGMAN.get()
            ));
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    NoixmodAPIEntities.MOURNER.get(),
                    NoixmodAPIEntities.ABOMINATION.get()
            ));
            ADVANCED_MAGIC_RAIDERS.add(NoixmodAPIEntities.BIOLOGIST.get());
        }

        if(Compat.ROOST2.isLoaded())
        {
            STANDARD_RAIDERS.add(Roost2ModEntities.ROOST_RIDER.get());
        }

        if(Compat.BENS_SHARKS.isLoaded()) BASIC_MAGIC_RAIDERS.add(BenssharksModEntities.THALASSOGER.get());

        if(Compat.ILLAGER_EXANSIONS.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    IllagerExpansionsModEntities.ELITE_VINDICATOR.get(),
                    IllagerExpansionsModEntities.RETALIATOR.get(),
                    IllagerExpansionsModEntities.CRINDICATOR.get(),
                    IllagerExpansionsModEntities.ELITE_PILLAGER.get()
            ));
        }

        if(Compat.GLOOM_RAIDERS.isLoaded()) //Skipping Spell Sword, Psychc_w, Psychic_m, Headsman, New Big Nun, earl, fly earl, awaken earl
        {
            STANDARD_RAIDERS.add(GloomRaidersModEntities.SHYLOCK.get());
            ADVANCED_RAIDERS.add(GloomRaidersModEntities.NUN.get());
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    GloomRaidersModEntities.NEW_DRAMATIST.get(),
                    GloomRaidersModEntities.DRAMATIST.get()
            ));
        }

        if(Compat.OCEANWORLD.isLoaded()) BASIC_MAGIC_RAIDERS.add(OWEntities.OCEANOLOGER);

        if(Compat.POACHERS.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    PoachersModEntities.BLOWER.get(),
                    PoachersModEntities.STABBER.get(),
                    PoachersModEntities.HARVESTER.get()
            ));
        }

        if(Compat.MECHA_PILLAGER.isLoaded())
        {
            ADVANCED_RAIDERS.addAll(List.of(
                    MechapillagerbossModEntities.MECHA_PILLAGER.get(),
                    MechapillagerbossModEntities.MECHA_VINDICATOR.get()
            ));
        }

        if(Compat.RESILIANCE_OF_RAIDERS.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    TheresilianceofraidersModEntities.CRUSHER.get(),
                    TheresilianceofraidersModEntities.CLASHAGER.get(),
                    TheresilianceofraidersModEntities.SHIVERGER.get()
            ));
        }

        if(Compat.RESISTANCE_OF_ILLAGERS.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    TheResistanceOfTheRaidersModEntities.GUARDIAN.get(),
                    TheResistanceOfTheRaidersModEntities.ASSAILANT.get(),
                    TheResistanceOfTheRaidersModEntities.HARRIER.get()
            ));
        }

        if(Compat.VINDICATION_OF_ILLAGERS.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    RaidervModEntities.BLACKMASTER.get(),
                    RaidervModEntities.PLANNER.get(),
                    RaidervModEntities.VISOLOGER.get(),
                    RaidervModEntities.INTRUDER.get(),
                    RaidervModEntities.VOLTOLOGER.get(),
                    RaidervModEntities.SMITHER.get(),
                    RaidervModEntities.RAGER.get()
            ));
            BASIC_MAGIC_RAIDERS.addAll(List.of(
                    RaidervModEntities.PREACHER.get(),
                    RaidervModEntities.PATROL_CAPTAIN.get()
            ));
        }

        if(Compat.GOETY_AWAKEN.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    com.k1sak1.goetyawaken.common.entities.ModEntityType.VINDICATOR_CHEF.get(),
                    com.k1sak1.goetyawaken.common.entities.ModEntityType.HOSTILE_ROYALGUARD.get()
            ));
        }
    }
}
