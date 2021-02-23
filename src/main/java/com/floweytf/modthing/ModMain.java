package com.floweytf.modthing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.nio.file.Path;

public class ModMain implements ModInitializer {
    public static final Block ATHANITE_BLOCK_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final Block ATHANITE_ORE_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0f));
    public static final Item ATHANITE_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item ATHANITE_NUGGET = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final BlockItem ATHANITE_BLOCK_ITEM = new BlockItem(ATHANITE_BLOCK_BLOCK, new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final BlockItem ATHANITE_ORE_ITEM = new BlockItem(ATHANITE_ORE_BLOCK,  new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static double decayRate = .5;
    public static int radius = 16;

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("athanite", "athanite_block"), ATHANITE_BLOCK_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("athanite", "athanite_ore"), ATHANITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("athanite", "athanite_ingot"), ATHANITE_INGOT);
        Registry.register(Registry.ITEM, new Identifier("athanite", "athanite_nugget"), ATHANITE_NUGGET);
        Registry.register(Registry.ITEM, new Identifier("athanite", "athanite_block"), ATHANITE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("athanite", "athanite_ore"), ATHANITE_ORE_ITEM);
    }
}
