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
    GUARD_ILLAGERS("guardillagers")
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
