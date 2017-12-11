package com.runicsystems.icbr.client;

import com.runicsystems.icbr.ICBR;
import com.runicsystems.icbr.common.CommonProxy;
import com.runicsystems.icbr.common.block.launcher.TESRRingLauncher;
import com.runicsystems.icbr.common.block.launcher.TileEntityRingLauncher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Author: Daen
 * Created 2/28/2017 9:31 PM
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ICBR.MODID + ":" + id, "inventory"));
	}

	@Override
	public String localize(String unlocalized, Object... args)
	{
		return I18n.format(unlocalized, args);
	}

	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRingLauncher.class, new TESRRingLauncher());
	}
}
