package com.calculusmaster.difficultraids.commands;

import com.calculusmaster.difficultraids.util.DifficultRaidsUtil;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;

import java.util.List;

public class DumpRaidersListCommand
{
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder = Commands
                .literal("difficultraids")
                .requires(css -> {
                    try { return css.getPlayerOrException().hasPermissions(2); }
                    catch (CommandSyntaxException e) { return false; }
                })
                .then(Commands.literal("dumpraiders")
                        .then(Commands.argument("raiderType", StringArgumentType.word())
                                .suggests((ctx, builder) -> {
                                    builder.suggest("STANDARD_RAIDERS");
                                    builder.suggest("ADVANCED_RAIDERS");
                                    builder.suggest("BASIC_MAGIC_RAIDERS");
                                    builder.suggest("ADVANCED_MAGIC_RAIDERS");
                                    return builder.buildFuture();
                                })
                                .executes(ctx -> dumpRaiders(ctx, StringArgumentType.getString(ctx, "raiderType")))
                        )
                );

        dispatcher.register(literalArgumentBuilder);
    }

    private static int dumpRaiders(CommandContext<CommandSourceStack> ctx, String type)
    {
        CommandSourceStack source = ctx.getSource();
        List<EntityType<?>> list;

        switch(type.toUpperCase())
        {
            case "STANDARD_RAIDERS" -> list = DifficultRaidsUtil.STANDARD_RAIDERS;
            case "ADVANCED_RAIDERS" -> list = DifficultRaidsUtil.ADVANCED_RAIDERS;
            case "BASIC_MAGIC_RAIDERS" -> list = DifficultRaidsUtil.BASIC_MAGIC_RAIDERS;
            case "ADVANCED_MAGIC_RAIDERS" -> list = DifficultRaidsUtil.ADVANCED_MAGIC_RAIDERS;
            default -> {
                source.sendFailure(Component.literal("Unknown raider type: " + type));
                return 0;
            }
        }

        if(list.isEmpty())
        {
            source.sendSuccess(() -> Component.literal(type + " list is empty!"), false);
        }
        else
        {
            source.sendSuccess(() -> Component.literal("Dumping " + type + ":"), false);
            for(EntityType<?> entity : list)
            {
                source.sendSuccess(() -> Component.literal(" - " + EntityType.getKey(entity)), false);
            }
        }

        return 1;
    }
}
