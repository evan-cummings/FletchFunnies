package net.fletch.fletchfunnies.datagen;

import net.fletch.fletchfunnies.FletchFunnies;
import net.fletch.fletchfunnies.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FletchFunnies.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.FUNNY_ORB);
        simpleItem(ModItems.DFUNNY);
        handheldItem(ModItems.FUNNY_SWORD);
        differentPath(ModItems.APPLE_PIE, "pie");
        differentPath(ModItems.BOMB_PIE, "pie");
        simpleItem(ModItems.PIE_TIN);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FletchFunnies.MOD_ID,"item/"+item.getId().getPath()));
    }
    //weird stuff like swords and wu wa we
    private ItemModelBuilder handheldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(FletchFunnies.MOD_ID,"item/"+item.getId().getPath()));
    }
    //if the texture file doesnt match the ID name
    private ItemModelBuilder differentPath(RegistryObject<Item> item, String fileName){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(FletchFunnies.MOD_ID,"item/"+fileName));
    }
}
