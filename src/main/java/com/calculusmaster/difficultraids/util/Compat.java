package com.calculusmaster.difficultraids.util;

import net.minecraftforge.fml.ModList;

public enum Compat
{
    GUARD_VILLAGERS("guardvillagers"),
    HUNTERS_RETURN("hunters_return"),
    ENCHANT_WITH_MOB("enchantwithmob"),
    IT_TAKES_A_PILLAGE("takesapillage"),
    ILLAGE_AND_SPILLAGE("illageandspillage"),
    SAVAGE_AND_RAVAGE("savage_and_ravage"),
    DUNGEONS_MOBS("dungeonsmobs"),
    ILLAGER_REVOLUTION("illagerrevolutionmod"),
    LEOS_ILLAGERS("leosillagers"),
    RECRUITS("recruits"),
    WORKERS("workers"),
    CONJURER("conjurer_illager"),
    NECROMANCER("necromancer"),
    WERDENS_ILLAGERS("wip"),
    IRONS_SPELLBOOKS("irons_spellbooks"),
    GAMBLER("gambler"),
    GUARD_ILLAGERS("guardillagers"),
    ILLAGER_ADDITIONS("illager_additions"),
    MOBZ("mobz"),
    ILLAGER_INVASION("illagerinvasion"),
    GOETY("goety"),
    SUMMONER("thesummoner"),
    BONES_UPDATE("bonesupdate"),
    MODIFIGER("the_modifiger"),
    MASQUERADER("masquerader_mod"),
    MUSKETEER("musketeer_illager"),
    BAGUS_MOB("bagusmob"),
    RAIDED("raided"),
    MORE_ILLAGERS("more_illagers"),
    SLASH_ILLAGER("slash_illager"),
    BRAZIER("brazier"),
    FRIENDS_AND_FOES("friendsandfoes"),
    ALL_BARK_ALL_BITE("all_bark_all_bite"),
    RAVAGE_AND_CABBAGE("ravageandcabbage"),
    MO_FEATURES("morefeatures"),
    CRIMSON_STEVES_MOBS("crimson_steves_mobs"),
    WANDERING_ILLAGER("wandering_illager"),
    FRANCIS_ILLAGERS("expansion"),
    EXPADOS_ILLAGERS("expados_illagerss"),
    KARATE_ILLAGER("karateillagerfinallyported"),
    ILLAGER_BRUTE("illager_brute"),
    SORCERER_ILLAGER("sorcerer_illager"),
    EARTH_MOBS("earthmobsmod"),
    ILLAGER_WORLD_WAR("illager_world_war"),
    JUST_ILLAGERS("justillagers"),
    COLDS_WANDERING_TRADER("coldstrader"),
    ILLAGER_UNIVERSE("illager_universe"),
    PILLAGER_BOSS("pillager_boss"),
    MISCHIEF_ILLAGERS("mischief_illagers"),
    VIRTUSOS_GRAND_BATTLE("virtusosgrandbattle"),
    PILLAGERS_PLUS("pillagersplus"),
    IMMERSIVE_ENGINEERING("immersiveengineering"),
    KAMILSKIS_ADDITIONS("rpg"),
    EIDOLON_REPRAISED("eidolon"),
    INVADE("invade"),
    WILD_DELIGHT("wild_delight"),
    RATS("rats"),
    JEROTES_VILLAGES("jerotesvillage"),
    FROM_THE_SHADOWS("fromtheshadows"),
    BORN_IN_CHAOS("born_in_chaos_v1"),
    COMPANIONS("companions"),
    NOXIAPI("noixmodapi"),
    ROOST2("roost2"),
    BENS_SHARKS("benssharks"),
    ILLAGER_EXANSIONS("illager_expansions")
    ;

    private final String modid;

    Compat(String modid)
    {
        this.modid = modid;
    }

    public boolean isLoaded()
    {
        return ModList.get().isLoaded(this.modid);
    }
}
