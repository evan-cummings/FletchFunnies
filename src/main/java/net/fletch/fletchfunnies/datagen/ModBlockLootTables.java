package net.fletch.fletchfunnies.datagen;

import net.fletch.fletchfunnies.block.ModBlocks;
import net.fletch.fletchfunnies.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.FUNNY_CUBE.get());
        //ores
//        add(ModBlocks.FUNNY_CUBE.get(),
//                (block)-> createOreDrop(ModBlocks.FUNNY_CUBE.get(), ModItems.FUNNY_ORB.get()));
    }

    @Override
    protected  Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
