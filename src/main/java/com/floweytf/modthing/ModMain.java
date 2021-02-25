package com.floweytf.modthing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.nio.file.Path;
import java.util.Random;

public class ModMain implements ModInitializer {
    public static final Block ATHANITE_BLOCK_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final Block ATHANITE_ORE_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0f));
    public static final Item ATHANITE_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item ATHANITE_NUGGET = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final BlockItem ATHANITE_BLOCK_ITEM = new BlockItem(ATHANITE_BLOCK_BLOCK, new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final BlockItem ATHANITE_ORE_ITEM = new BlockItem(ATHANITE_ORE_BLOCK,  new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static double decayRate = .5;
    public static int radius = 16;

    private static final ConfiguredFeature<?, ?> ORE_ATHANITE_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ATHANITE_ORE_BLOCK.getDefaultState(),
                    24)) // vein size
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                    0,
                    0,
                    11)))
            .spreadHorizontally()
            .repeat(1);

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("athanite", "athanite_block"), ATHANITE_BLOCK_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("athanite", "athanite_ore"), ATHANITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("athanite", "athanite_ingot"), ATHANITE_INGOT);
        Registry.register(Registry.ITEM, new Identifier("athanite", "athanite_nugget"), ATHANITE_NUGGET);
        Registry.register(Registry.ITEM, new Identifier("athanite", "athanite_block"), ATHANITE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("athanite", "athanite_ore"), ATHANITE_ORE_ITEM);
    }

    public static boolean checkIfOreWillGenerate(BlockPos p, Random random) {
        // distance
        double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());
        double chance = Math.pow(decayRate, distance / radius);
        return random.nextDouble() < chance;
    }
}
