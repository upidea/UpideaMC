package com.upidea.upideamc;

import com.bioxx.tfc.Commands.MyTestCommand;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.upidea.upideamc.Handlers.EntitySpawnHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPENDENCIES, guiFactory = Reference.GUI_FACTORY)
public class UpideaMineCraft
{
    public static final Logger LOG = LogManager.getLogger(Reference.MOD_NAME);

    @Mod.Instance(Reference.MOD_ID)
    public static UpideaMineCraft instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    // The packet pipeline
    // public static final PacketPipeline PACKET_PIPELINE = new PacketPipeline();

    public UpideaMineCraft()
    {
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Register Gui Handler
        proxy.registerGuiHandler();
    }

    @EventHandler
    public void initialize(FMLInitializationEvent event)
    {
        // Register the Entity Spawn Handler
        MinecraftForge.EVENT_BUS.register(new EntitySpawnHandler());

		// some example code
        // System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent evt)
    {
        evt.registerServerCommand(new MyTestCommand());
    }
}
