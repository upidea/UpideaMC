package com.upidea.upideamc;

import java.io.File;
import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

import com.upidea.upideamc.ASM.Transform.*;

@TransformerExclusions({ "com.upidea.upideamc.ASM" })
public class UpideaMcASMLoadingPlugin implements IFMLLoadingPlugin
{
	public static boolean runtimeDeobf;
	public static File location;

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{
				TF_EntityRenderer.class.getName(),
				TF_RenderGlobal.class.getName(),
				TF_EntityLeashKnot.class.getName(),
				TF_EntityPlayerMP.class.getName(),
				TF_EntityPlayer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return UpideaMineCraftCore.class.getName();
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		runtimeDeobf = (Boolean) data.get("runtimeDeobfuscationEnabled");
		location = (File) data.get("coremodLocation");
	}

}
