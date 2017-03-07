package com.runicsystems.icbr.common.item;

import com.runicsystems.icbr.ICBR;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Author: Daen
 * Created 2/28/2017 9:40 PM
 */
public class ItemBase extends Item
{
	protected String name;

	public ItemBase(String name)
	{
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	public void registerItemModel()
	{
		ICBR.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public ItemBase setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
