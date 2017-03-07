package com.runicsystems.icbr.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Author: neromir
 * Created 3/3/2017 8:50 PM
 */
public class BlockOre extends BlockBase
{
	public BlockOre(String name)
	{
		super(Material.ROCK, name);
		setHardness(3f);
		setResistance(5f);
	}

	@Override
	public BlockOre setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
