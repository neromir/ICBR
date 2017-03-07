package com.runicsystems.icbr.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Author: Daen
 * Created 2/28/2017 9:35 PM
 */
public class ModItems
{
//	public static Item bar = new Item().setUnlocalizedName("bar").setCreativeTab(CreativeTabs.MISC);
	public static ItemBase ingotCopper;
	public static ItemBase copperCoil;

	public static void init()
	{
		ingotCopper = register(new ItemBase("ingotCopper").setCreativeTab(CreativeTabs.MATERIALS));
		copperCoil = register(new ItemBase("copperCoil").setCreativeTab(CreativeTabs.MISC));
	}

	private static <T extends Item> T register(T item)
	{
		GameRegistry.register(item);
		if(item instanceof ItemBase)
		{
			((ItemBase)item).registerItemModel();
		}

		return item;
	}
}
