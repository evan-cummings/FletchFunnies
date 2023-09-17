package net.fletch.fletchfunnies;

import com.mojang.logging.LogUtils;
import net.fletch.fletchfunnies.block.ModBlocks;
import net.fletch.fletchfunnies.item.ModCreativeModeTab;
import net.fletch.fletchfunnies.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FletchFunnies.MOD_ID)
public class FletchFunnies {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "fletchfunnies";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public FletchFunnies() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab()== CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ModItems.FUNNY_ORB);
        }
        if(event.getTab()==CreativeModeTabs.COMBAT){
            event.accept(ModItems.FUNNY_SWORD);
        }
        if(event.getTab()== ModCreativeModeTab.FUNNIES_TAB){
            event.accept(ModItems.PIE_TIN);
            event.accept(ModItems.FUNNY_ORB);
            event.accept(ModItems.FUNNY_SWORD);
            event.accept(ModItems.DFUNNY);
            event.accept(ModBlocks.FUNNY_CUBE);
            event.accept(ModItems.APPLE_PIE);
            event.accept(ModItems.BOMB_PIE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
