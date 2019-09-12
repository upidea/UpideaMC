package com.upidea.upideamc.Handlers.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.minecraftforge.client.event.GuiOpenEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GuiHandler extends com.upidea.upideamc.Handlers.GuiHandler
{
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity te;
		try
		{
			te= world.getTileEntity(x, y, z);
		}
		catch(Exception e)
		{
			te = null;
		}

		switch(id)
		{
		case 31:
			return new com.upidea.upideamc.GUI.UpideaMcGuiInventory(player);
		default:
			return null;
		}
	}

	@SubscribeEvent
	public void openGuiHandler(GuiOpenEvent event)
	{
		if(event.gui instanceof net.minecraft.client.gui.inventory.GuiInventory && !(event.gui instanceof com.upidea.upideamc.GUI.UpideaMcGuiInventory))
			event.gui = new com.upidea.upideamc.GUI.UpideaMcGuiInventory(Minecraft.getMinecraft().thePlayer);
	}
}
