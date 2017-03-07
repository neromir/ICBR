package com.runicsystems.icbr.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Author: neromir
 * Created 3/6/2017 7:07 PM
 */
public class RingLauncher extends BlockBase
{
	public RingLauncher()
	{
		super(Material.ROCK, "ringLauncher");
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public RingLauncher setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
