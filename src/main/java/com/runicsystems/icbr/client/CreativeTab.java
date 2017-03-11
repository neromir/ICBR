package com.runicsystems.icbr.client;

import com.runicsystems.icbr.ICBR;
import com.runicsystems.icbr.common.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Author: neromir
 * Created 3/10/2017 6:32 PM
 */
public class CreativeTab extends CreativeTabs
{
	public CreativeTab()
	{
		super(ICBR.MODID);
		setBackgroundImageName("item_search.png");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.copperCoil;
	}

	@Override
	public boolean hasSearchBar()
	{
		return true;
	}
}
