package com.runicsystems.icbr.common.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Author: Daen
 * Created 2/28/2017 9:35 PM
 */
public class ModItems
{
	public static ItemBase ingotCopper;
	public static ItemBase copperCoil;
	public static ItemBase wireCopper;

	public static void init()
	{
		ingotCopper = register(new ItemBase("ingotCopper"));
		copperCoil = register(new ItemBase("copperCoil"));
		wireCopper = register(new ItemBase("wireCopper"));
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
