package net.fletch.fletchfunnies.item;

import net.fletch.fletchfunnies.FletchFunnies;
import net.fletch.fletchfunnies.item.custom.BombPieItem;
import net.fletch.fletchfunnies.item.custom.DiceItem;
import net.fletch.fletchfunnies.item.custom.ApplePieItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FletchFunnies.MOD_ID);

    public static final RegistryObject<Item> FUNNY_ORB=ITEMS.register("funny_orb",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FUNNY_SWORD=ITEMS.register("funny_sword",
            ()-> new SwordItem(Tiers.IRON, 1005, 1, new Item.Properties()));
    public static final RegistryObject<Item> DFUNNY =ITEMS.register("dfunny",
            ()-> new DiceItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> PIE_TIN=ITEMS.register("pie_tin",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> APPLE_PIE=ITEMS.register("apple_pie",
            ()->new ApplePieItem(new Item.Properties().food((new FoodProperties.Builder()).nutrition(8).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> BOMB_PIE=ITEMS.register("bomb_pie",
            ()->new BombPieItem(new Item.Properties().food((new FoodProperties.Builder()).nutrition(8).saturationMod(0.3F).build())));








    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
