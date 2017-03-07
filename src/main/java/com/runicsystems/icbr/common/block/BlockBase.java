package com.runicsystems.icbr.common.block;

import com.runicsystems.icbr.ICBR;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

/**
 * Author: neromir
 * Created 3/3/2017 8:46 PM
 */
public class BlockBase extends Block
{
	protected String name;

	public BlockBase(Material materialIn, String name)
	{
		super(materialIn);
		this.name = name;

		setUnlocalizedName(name);
		setRegistryName(name);
	}

	public void registerItemModel(ItemBlock itemBlock)
	{
		ICBR.proxy.registerItemRenderer(itemBlock, 0, name);
	}

	@Override
	public BlockBase setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
