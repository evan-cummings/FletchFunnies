package net.fletch.fletchfunnies.item;

import net.fletch.fletchfunnies.FletchFunnies;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FletchFunnies.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab FUNNIES_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event){
        FUNNIES_TAB = event.registerCreativeModeTab(new ResourceLocation(FletchFunnies.MOD_ID, "funnies_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.FUNNY_ORB.get()))
                        .title(Component.translatable("creativemodetab.funnies_tab")));
    }
}
