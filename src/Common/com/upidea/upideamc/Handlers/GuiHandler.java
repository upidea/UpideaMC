package com.upidea.upideamc.Handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

import com.upidea.upideamc.Containers.*;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity te = world.getTileEntity(x, y, z);

		switch(id)
		{
            case 31:
            {
                return new UpideaMcContainerPlayer(player.inventory, false, player);
            }
            default:
            {
                return null;
            }
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
}
