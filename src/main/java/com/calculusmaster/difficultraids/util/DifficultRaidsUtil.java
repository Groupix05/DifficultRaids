package com.calculusmaster.difficultraids.util;

import baguchan.enchantwithmob.registry.ModEntities;
import baguchan.hunters_return.init.HunterEntityRegistry;
import baguchan.the_modifiger.registry.ModEntityRegistry;
import cn.leolezury.leosillagers.init.EntityInit;
import codyhuh.ravagecabbage.registry.RCEntities;
import com.Polarice3.Goety.common.entities.ModEntityType;
import com.belgieyt.morefeatures.core.registry.MFEntity;
import com.calculusmaster.difficultraids.DifficultRaids;
import com.faboslav.friendsandfoes.common.init.FriendsAndFoesEntityTypes;
import com.infamous.all_bark_all_bite.common.registry.ABABEntityTypes;
import com.legacy.conjurer_illager.registry.IllagerEntityTypes;
import com.calculusmaster.difficultraids.entity.DifficultRaidsEntityTypes;
import com.izofar.takesapillage.init.ModEntityTypes;
import com.mrbysco.raided.registry.RaidedRegistry;
import com.mysticmage.musketeer_illager.init.MusketeerIllagerModEntities;
import com.possible_triangle.brazier.Content;
import com.sh1nylabs.bonesupdate.init.BonesEntities;
import com.teamabnormals.savage_and_ravage.core.registry.SREntityTypes;
import comfrancisplayz446.necromancer.init.NecromancerModEntities;
import francisplayz446.karateillagerfinallyported.init.KarateillagerfinallyportedModEntities;
import fuzs.illagerinvasion.init.ModRegistry;
import net.francisplayz446.summoner.init.ThesummonerModEntities;
import net.mcreator.crimson_steves_mobs.init.CrimsonStevesMobsModEntities;
import net.mcreator.expadosillagers.init.ExpadosIllagersModEntities;
import net.mcreator.francisillagers.init.FrancisillagersModEntities;
import net.mcreator.illagerbrute.init.IllagerBruteModEntities;
import net.mcreator.moreillagers.init.MoreIllagersModEntities;
import net.mcreator.sorcererillager.init.SorcererIllagerModEntities;
import net.mobz.init.MobZEntities;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import net.minecraft.world.entity.EntityType;

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
            STANDARD_RAIDERS.add(net.BKTeam.illagerrevolutionmod.entity.ModEntityTypes.ILLAGER_SCAVENGER.get());
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
            ADVANCED_MAGIC_RAIDERS.add(ExpadosIllagersModEntities.GRINDICATOR.get());
            STANDARD_RAIDERS.addAll(List.of(
                    ExpadosIllagersModEntities.PIKELOGER.get(),
                    ExpadosIllagersModEntities.KNOCKER.get(),
                    ExpadosIllagersModEntities.HACKLE.get(),
                    ExpadosIllagersModEntities.BLUDGEONER.get(),
                    ExpadosIllagersModEntities.BRASHER.get(),
                    ExpadosIllagersModEntities.WRECKER.get()
            ));
        }

        if(Compat.FRANCIS_ILLAGERS.isLoaded())
        {
            STANDARD_RAIDERS.addAll(List.of(
                    FrancisillagersModEntities.COWBOY.get(),
                    FrancisillagersModEntities.THROWER.get(),
                    FrancisillagersModEntities.BLADE_GUARD.get(),
                    FrancisillagersModEntities.RUNNER.get(),
                    FrancisillagersModEntities.CRINDICATOR.get(),
                    FrancisillagersModEntities.LIGHTNING_CALLER.get(),
                    FrancisillagersModEntities.BLACKMASTER.get(),
                    FrancisillagersModEntities.CREAKOLOGER.get()
            ));
            BASIC_MAGIC_RAIDERS.add(FrancisillagersModEntities.JURGOLOGER.get());
            ADVANCED_RAIDERS.add(FrancisillagersModEntities.OMNILLAGER.get());
        }
    }
}
