package com.upidea.upideamc;

public class Reference 
{
	public static final String MOD_ID = "upideaminecraft";
	public static final String MOD_NAME = "UpideaMineCraft";

	public static final int VERSION_MAJOR = 0;
	public static final int VERSION_MINOR = 0;
	public static final int VERSION_REVISION = 1;

	public static final String MOD_VERSION = VERSION_MAJOR+"."+VERSION_MINOR+"."+VERSION_REVISION;

	public static final String MOD_DEPENDENCIES = "required-after:upideamc_coremod";
	public static final String MOD_CHANNEL = "UpideaMineCraft";
	public static final String SERVER_PROXY_CLASS = "com.upidea.upideamc.CommonProxy";
	public static final String CLIENT_PROXY_CLASS = "com.upidea.upideamc.ClientProxy";

	public static final String ASSET_PATH = "/assets/" + MOD_ID + "/";
	public static final String ASSET_PATH_GUI = "textures/gui/";

	public static final String GUI_FACTORY = "com.upidea.upideamc.Core.Config.GuiFactory";
}
