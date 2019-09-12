package com.upidea.upideamc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerGuiHandler()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(UpideaMineCraft.instance, new com.upidea.upideamc.Handlers.Client.GuiHandler());
        // Register Gui Event Handler 这里有意思了，　给　GuiHandler　订阅了MFL事件 GuiOpenEvent
        // 一旦遇到 GuiInventory 打开的时候，　就用自定义的界面替换旧界面，将简易合成栏扩展为９格的合成栏
        MinecraftForge.EVENT_BUS.register(new com.upidea.upideamc.Handlers.Client.GuiHandler());
	}

	@Override
	public String getCurrentLanguage()
	{
		return Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode();
	}

	@Override
	public boolean getGraphicsLevel()
	{
		Minecraft.getMinecraft();
		return Minecraft.isFancyGraphicsEnabled();
	}
}
