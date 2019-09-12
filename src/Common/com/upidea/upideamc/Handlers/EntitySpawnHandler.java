package com.upidea.upideamc.Handlers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import com.upidea.upideamc.Core.UpideaMc_Core;
import com.upidea.upideamc.Core.Player.InventoryPlayerUpideaMc;
import com.upidea.upideamc.Containers.UpideaMcContainerPlayer;

public class EntitySpawnHandler
{
	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event)
	{
		// 出生的时候，　注册一些出生事件
		if (event.entity instanceof EntityPlayer && !event.entity.getEntityData().hasKey("hasSpawned"))
		{
			if(!(((EntityPlayer)event.entity).inventory instanceof InventoryPlayerUpideaMc))
				((EntityPlayer)event.entity).inventory = UpideaMc_Core.getNewInventory((EntityPlayer)event.entity);

            // 直接给开了９格合成台
            ((EntityPlayer)event.entity).getEntityData().setBoolean("craftingTable", true);

//			((EntityPlayer)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000);
//			((EntityPlayer)event.entity).setHealth(1000);
			event.entity.getEntityData().setBoolean("hasSpawned", true);
		}

		// 玩家进入游戏的时候，注册一些登录事件
		if (event.entity instanceof EntityPlayer)
		{

            testchat((EntityPlayer)event.entity);

		    if(!(((EntityPlayer)event.entity).inventory instanceof InventoryPlayerUpideaMc))
				((EntityPlayer)event.entity).inventory = UpideaMc_Core.getNewInventory((EntityPlayer)event.entity);

			((EntityPlayer)event.entity).inventoryContainer = new UpideaMcContainerPlayer(((EntityPlayer)event.entity).inventory, !event.world.isRemote, (EntityPlayer)event.entity);
			((EntityPlayer)event.entity).openContainer = ((EntityPlayer)event.entity).inventoryContainer;
		}
	}

	public void testchat(EntityPlayer playerIn)
	{
        String msg = String.format("Up: %s, Down: %s", "爱咋地咋地", "不好玩的东西");
        ChatComponentText cctTotal = new ChatComponentText(msg);
        cctTotal.getChatStyle().setColor(EnumChatFormatting.GRAY).setItalic(true);
        playerIn.addChatComponentMessage(cctTotal);
	}
}
