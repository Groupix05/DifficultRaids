package com.calculusmaster.difficultraids.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class GetRaiderTypeCommand
{
    @SuppressWarnings("unchecked")
    private static EntityType<? extends Raider> getEntityType(Raid.RaiderType rt)
    {
        try {
            Field field = Raid.RaiderType.class.getDeclaredField("f_37833_");
            field.setAccessible(true);
            return (EntityType<? extends Raider>) field.get(rt);
        } catch (NoSuchFieldException e) {
            try {
                Field field = Raid.RaiderType.class.getDeclaredField("entityType");
                field.setAccessible(true);
                return (EntityType<? extends Raider>) field.get(rt);
            } catch (Exception ex) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder = Commands
                .literal("difficultraids")
                .then(
                        Commands.literal("getraidertype")
                                .requires(css -> {
                                    try { return css.getPlayerOrException().hasPermissions(2); }
                                    catch (CommandSyntaxException e) {
                                        e.printStackTrace();
                                        return false;
                                    }
                                })
                                .then(
                                        Commands.argument("target", EntityArgument.entity())
                                                .executes(css -> {
                                                    Entity target = EntityArgument.getEntity(css, "target");
                                                    ResourceLocation registryName = ForgeRegistries.ENTITY_TYPES.getKey(target.getType());

                                                    String targetName = registryName != null ? registryName.toString() : "unknown";

                                                    if (!(target instanceof Raider)) {
                                                        css.getSource().sendFailure(Component.literal(
                                                                "'" + targetName + "' is not a Raider."
                                                        ));
                                                        return 0;
                                                    }

                                                    Optional<Raid.RaiderType> found = Arrays.stream(Raid.RaiderType.values())
                                                            .filter(rt -> getEntityType(rt) == target.getType())
                                                            .findFirst();

                                                    if (found.isEmpty()) {
                                                        css.getSource().sendFailure(Component.literal(
                                                                "'" + targetName + "' is a Raider but has no registered RaiderType."
                                                        ));
                                                        return 0;
                                                    }

                                                    String raiderTypeName = found.get().name();
                                                    css.getSource().sendSuccess(() -> Component.literal(
                                                            "RaiderType of '" + targetName + "' is " + raiderTypeName
                                                    ), true);

                                                    return 1;
                                                })
                                )
                );

        dispatcher.register(literalArgumentBuilder);
    }
}