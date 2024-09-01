package com.calculusmaster.difficultraids.setup;

import com.calculusmaster.difficultraids.DifficultRaids;
import com.calculusmaster.difficultraids.setup.DifficultRaidsConfig;
import java.util.HashMap;
import java.util.*;
import com.calculusmaster.difficultraids.enchantments.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DifficultRaidsEnchantments
{
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, DifficultRaids.MODID);

    public static RegistryObject<Enchantment> RAIDERS_BANE;
    public static RegistryObject<Enchantment> INVISIBILITY;
    public static RegistryObject<Enchantment> CRITICAL_STRIKE;
    public static RegistryObject<Enchantment> CRITICAL_BURST;
    public static RegistryObject<Enchantment> CRITICAL_RESISTANCE;
    public static RegistryObject<Enchantment> LIGHTNING_RESISTANCE;
    public static RegistryObject<Enchantment> PROJECTILE_EVASION;

    public static synchronized void register(IEventBus eventBus)
    {
        RAIDERS_BANE = ENCHANTMENTS.register("raiders_bane", () -> new RaidersBaneEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

        INVISIBILITY = ENCHANTMENTS.register("invisibility", () -> new InvisibilityEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_CHEST, EquipmentSlot.CHEST));

        CRITICAL_STRIKE = ENCHANTMENTS.register("critical_strike", () -> new CriticalStrikeEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

        CRITICAL_BURST = ENCHANTMENTS.register("critical_burst", () -> new CriticalBurstEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

        CRITICAL_RESISTANCE = ENCHANTMENTS.register("critical_resistance", () -> new CriticalResistanceEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET));

        LIGHTNING_RESISTANCE = ENCHANTMENTS.register("lightning_resistance", () -> new LightningResistanceEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_HEAD, EquipmentSlot.HEAD));

        PROJECTILE_EVASION = ENCHANTMENTS.register("projectile_evasion", () -> new ProjectileEvasionEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_FEET, EquipmentSlot.FEET));

        ENCHANTMENTS.register(eventBus);
    }

}
