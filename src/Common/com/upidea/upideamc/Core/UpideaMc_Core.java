package com.upidea.upideamc.Core;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.upidea.upideamc.Core.Player.InventoryPlayerUpideaMc;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.WorldInfo;

import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.upidea.upideamc.UpideaMineCraft;
//import com.upidea.upideamc.Chunkdata.ChunkData;
//import com.upidea.upideamc.Chunkdata.ChunkDataManager;
//import com.upidea.upideamc.Core.Player.BodyTempStats;
//import com.upidea.upideamc.Core.Player.FoodStatsTFC;
//import com.upidea.upideamc.Core.Player.InventoryPlayerTFC;
//import com.upidea.upideamc.Core.Player.SkillStats;
//import com.upidea.upideamc.Food.ItemFoodTFC;
//import com.upidea.upideamc.Items.ItemOre;
//import com.upidea.upideamc.Items.ItemTerra;
//import com.upidea.upideamc.Items.ItemBlocks.ItemTerraBlock;
//import com.upidea.upideamc.TileEntities.TEMetalSheet;
//import com.upidea.upideamc.WorldGen.TFCBiome;
//import com.upidea.upideamc.api.*;
//import com.upidea.upideamc.api.Constant.Global;
//import com.upidea.upideamc.api.Entities.IAnimal;
//import com.upidea.upideamc.api.Enums.EnumFuelMaterial;
//import com.upidea.upideamc.api.Interfaces.IFood;

public class UpideaMc_Core
{
	@SideOnly(Side.CLIENT)
	public static int getMouseX()
	{
		ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);
		int i = scaledresolution.getScaledWidth();
		int k = Mouse.getX() * i / Minecraft.getMinecraft().displayWidth;

		return k;
	}

	@SideOnly(Side.CLIENT)
	public static int getMouseY()
	{
		ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);
		int j = scaledresolution.getScaledHeight();
		int l = j - Mouse.getY() * j / Minecraft.getMinecraft().displayHeight - 1;

		return l;
	}

	public static int getExtraEquipInventorySize(){
		//Just the back
		return 1;
	}

	public static InventoryPlayer getNewInventory(EntityPlayer player)
	{
		InventoryPlayer ip = player.inventory;
		NBTTagList nbt = new NBTTagList();
		nbt = player.inventory.writeToNBT(nbt);
		ip = new InventoryPlayerUpideaMc(player);
		ip.readFromNBT(nbt);
		return ip;
	}

	public static void setupWorld(World world)
	{
		long seed = world.getSeed();
		Random r = new Random(seed);
		world.provider.registerWorld(world);
//		Recipes.registerAnvilRecipes(r, world);
//		TFC_Time.updateTime(world);
		// TerraFirmaCraft.proxy.registerSkyProvider(world.provider);
	}

	public static void setupWorld(World w, long seed)
	{
		try
		{
			// ReflectionHelper.setPrivateValue(WorldInfo.class,
			// w.getWorldInfo(), "randomSeed", seed);
			ReflectionHelper.setPrivateValue(WorldInfo.class, w.getWorldInfo(), seed, 0);
			setupWorld(w);
		}
		catch (Exception ex)
		{
		}
	}

	public static boolean showShiftInformation()
	{
		return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
	}

	public static boolean showCtrlInformation()
	{
		return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
	}

	public static void bindTexture(ResourceLocation texture)
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
	}

	public static float getEnvironmentalDecay(float temp)
	{
		if (temp > 0)
		{
			float tempFactor = 1f - (15f / (15f + temp));
			return tempFactor * 2;
		}
		else
			return 0;
	}

	//Takes a small float in the range of 0.5 to 1.5. The resulting float would be of the form [0 0111111 [the byte] 0..0], such that the byte returned
	//is the only unknown value
	public static byte getByteFromSmallFloat(float f){
		MathHelper.clamp_float(f, 0.5f, 1.5f);
		return (byte)((Float.floatToIntBits(f) >> 16) & 0xff);
	}

	public static float getSmallFloatFromByte(byte b)
	{
		return ByteBuffer.wrap(new byte[]{(byte)63, b,(byte)(0),(byte)0}).getFloat();
	}

	public static void giveItemToPlayer(ItemStack is, EntityPlayer player)
	{
		if(player.worldObj.isRemote)
			return;
		EntityItem ei = player.entityDropItem(is, 1);
		ei.delayBeforeCanPickup = 0;
	}

	public static void writeInventoryToNBT(NBTTagCompound nbt, ItemStack[] storage)
	{
		writeInventoryToNBT(nbt, storage, "Items");
	}

	public static void writeInventoryToNBT(NBTTagCompound nbt, ItemStack[] storage, String name)
	{
		NBTTagList nbttaglist = new NBTTagList();
		for(int i = 0; i < storage.length; i++)
		{
			if(storage[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				storage[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbt.setTag(name, nbttaglist);
	}

	public static void readInventoryFromNBT(NBTTagCompound nbt, ItemStack[] storage)
	{
		readInventoryFromNBT(nbt, storage, "Items");
	}

	public static void readInventoryFromNBT(NBTTagCompound nbt, ItemStack[] storage, String name)
	{
		NBTTagList nbttaglist = nbt.getTagList(name, 10);
		for(int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte byte0 = nbttagcompound1.getByte("Slot");
			if(byte0 >= 0 && byte0 < storage.length)
				storage[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
	}

	public static ItemStack getItemInInventory(Item item, IInventory iinv)
	{
		for(int i = 0; i < iinv.getSizeInventory(); i++)
		{
			iinv.getStackInSlot(i);
			if(iinv.getStackInSlot(i) != null && iinv.getStackInSlot(i).getItem() == item)
			{
				return iinv.getStackInSlot(i);
			}
		}
		return null;
	}

	public static void destroyBlock(World world, int x, int y, int z)
	{
		if(world.getBlock(x, y, z) != Blocks.air)
		{
			world.getBlock(x, y, z).dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	public static boolean areItemsEqual(ItemStack is1, ItemStack is2)
	{
		Item i1 = null; int d1 = 0;
		Item i2 = null; int d2 = 0;
		if(is1 != null)
		{
			i1 = is1.getItem(); d1 = is1.getItemDamage();
		}
		if(is2 != null)
		{
			i2 = is2.getItem(); d2 = is2.getItemDamage();
		}
		return i1 == i2 && d1 == d2;
	}

	public static boolean setBlockWithDrops(World world, int x, int y, int z, Block b, int meta)
	{
		Block block = world.getBlock(x, y, z);

		if (block.getMaterial() != Material.air)
		{
			int l = world.getBlockMetadata(x, y, z);
			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (l << 12));
			block.dropBlockAsItem(world, x, y, z, l, 0);
		}
		return world.setBlock(x, y, z, b, meta, 3);
	}

	/**
	 * This is a wrapper method for the vanilla world method with no MCP mapping
	 */
	public static boolean setBlockToAirWithDrops(World world, int x, int y, int z)
	{
		return world.func_147480_a(x, y, z, true);
	}

	public static String translate(String s)
	{
		return StatCollector.translateToLocal(s);
	}

	public static void sendInfoMessage(EntityPlayer player, IChatComponent text)
	{
		text.getChatStyle().setColor(EnumChatFormatting.GRAY).setItalic(true);
		player.addChatComponentMessage(text);
	}

	public static long getSuperSeed(World w)
	{
		return w.getSeed()+w.getWorldInfo().getNBTTagCompound().getLong("superseed");
	}
	
	public static boolean isExposedToRain(World world, int x, int y, int z)
	{
		int highestY = world.getPrecipitationHeight(x, z) - 1;
		boolean isExposed = true;
		if (world.canBlockSeeTheSky(x, y + 1, z)) // Either no blocks, or transparent blocks above.
		{
			// Glass blocks, or blocks with a solid top or bottom block the rain.
			if (world.getBlock(x, highestY, z) instanceof BlockGlass
					|| world.getBlock(x, highestY, z) instanceof BlockStainedGlass
					|| world.isSideSolid(x, highestY, z, ForgeDirection.UP) 
					|| world.isSideSolid(x, highestY, z, ForgeDirection.DOWN))
				isExposed = false;
		}
		else // Can't see the sky
			isExposed = false;

		return world.isRaining() && isExposed;
	}
}
