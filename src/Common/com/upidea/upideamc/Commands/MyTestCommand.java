package com.bioxx.tfc.Commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;

import com.upidea.upideamc.Core.UpideaMc_Core;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

public class MyTestCommand extends CommandBase{

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }
    @Override
	public String getCommandName() {
		return "mytest";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] params) 
	{
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		UpideaMc_Core.sendInfoMessage(player, new ChatComponentText("你输入了命令：　mytest，　再来！"));

        // throw new PlayerNotFoundException("我了个去！");

		int type = 0;
        try {
			type = Integer.valueOf(params[0]).intValue();
		} catch (NumberFormatException e) {
			type = 0;
		}

		player.getServerForPlayer().getEntityTracker().func_151248_b(player, new S0BPacketAnimation(player, type));
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) 
	{
        return StatCollector.translateToLocal("cmdhelp.mytest");
	}

}
