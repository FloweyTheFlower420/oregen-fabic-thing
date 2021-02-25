package com.floweytf.modthing.mixins;

import com.floweytf.modthing.ModMain;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Mixin(Biome.class)
public class BiomeMixin {
    @Inject(method = "generateFeatureStep(Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/world/ChunkRegion;JLnet/minecraft/world/gen/ChunkRandom;Lnet/minecraft/util/math/BlockPos;)V", at = @At("HEAD"))
    private void generateFeatureStep(StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, ChunkRegion region, long populationSeed, ChunkRandom random, BlockPos pos, CallbackInfo ci) {
        if(!ModMain.checkIfOreWillGenerate(pos, random))
            return;

        List<BlockPos> posHashSet = new ArrayList<>();
        for(int x = 0; x < 16; x++) {
            for(int y = 0; y < 11; y++) {
                for(int z = 0; z < 16; z++) {
                    BlockPos other = new BlockPos(x, y, z);
                    if(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD.test(region.getBlockState(other), random))
                        posHashSet.add(other);
                }
            }
        }

        region.setBlockState(posHashSet.get(random.nextInt(posHashSet.size())), ModMain.ATHANITE_ORE_BLOCK.getDefaultState(), 0b11);
    }
}
