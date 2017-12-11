package com.runicsystems.icbr;

import com.runicsystems.icbr.client.CreativeTab;
import com.runicsystems.icbr.common.CommonProxy;
import com.runicsystems.icbr.common.block.ModBlocks;
import com.runicsystems.icbr.common.item.ModItems;
import com.runicsystems.icbr.common.network.PacketRequestUpdateRingLauncher;
import com.runicsystems.icbr.common.network.PacketUpdateRingLauncher;
import com.runicsystems.icbr.common.recipe.ModRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Author: Daen
 * Created 2/28/2017 9:27 PM
 */
@Mod(modid = ICBR.MODID, name = ICBR.MODNAME, version = ICBR.VERSION)
public class ICBR
{
	public static final String MODID = "icbr";
	public static final String MODNAME = "ICBR";
	public static final String VERSION = "${version}";

	@Mod.Instance(MODID)
	public static ICBR instance = new ICBR();
	@SidedProxy(clientSide = "com.runicsystems.icbr.client.ClientProxy", serverSide = "com.runicsystems.icbr.common.CommonProxy")
	public static CommonProxy proxy;

	public static final CreativeTab creativeTab = new CreativeTab();
	public static SimpleNetworkWrapper network;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		System.out.println("INITIALIZING ICBR");
		//proxy.preInit(event);
		ModItems.init();
		ModBlocks.init();

		network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		network.registerMessage(new PacketUpdateRingLauncher.Handler(), PacketUpdateRingLauncher.class, 0, Side.CLIENT);
		network.registerMessage(new PacketRequestUpdateRingLauncher.Handler(), PacketRequestUpdateRingLauncher.class, 1, Side.SERVER);

		proxy.registerRenderers();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
//		proxy.init(event);
		ModRecipes.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
//		proxy.postInit(event);
	}
}
