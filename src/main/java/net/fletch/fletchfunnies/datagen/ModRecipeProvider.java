package net.fletch.fletchfunnies.datagen;

import net.fletch.fletchfunnies.block.ModBlocks;
import net.fletch.fletchfunnies.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        nineBlockStorageRecipes(pWriter, RecipeCategory.BUILDING_BLOCKS, ModItems.FUNNY_ORB.get(), RecipeCategory.MISC, ModBlocks.FUNNY_CUBE.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.APPLE_PIE.get())
                .requires(Items.WHEAT)
                .requires(Items.SUGAR)
                .requires(Items.APPLE)
                .requires(ModItems.PIE_TIN.get())
                .unlockedBy("has_pie_tin", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.PIE_TIN.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BOMB_PIE.get())
                .requires(Items.WHEAT)
                .requires(Items.SUGAR)
                .requires(Items.TNT)
                .requires(ModItems.PIE_TIN.get())
                .unlockedBy("has_pie_tin", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.PIE_TIN.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PIE_TIN.get(), 2)
                .define('#', Items.IRON_NUGGET)
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_iron_nugget", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_NUGGET).build()))
                .save(pWriter);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FUNNY_SWORD.get())
//                .define('#', ModItems.FUNNY_ORB.get())
//                .pattern("# #")
//                .pattern("# #")
//                .pattern("###")
//                .unlockedBy("has_funny_orb", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(ModItems.FUNNY_ORB.get()).build()))
//                .save(pWriter);


    }
}
