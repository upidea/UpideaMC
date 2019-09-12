package com.upidea.upideamc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public void registerGuiHandler()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(UpideaMineCraft.instance, new com.upidea.upideamc.Handlers.GuiHandler());
	}

	public String getCurrentLanguage()
	{
		return null;
	}

	public boolean getGraphicsLevel()
	{
		return false;
	}
}
