package com.runicsystems.icbr.common.block;

import com.runicsystems.icbr.common.item.ItemOreDict;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Author: neromir
 * Created 3/3/2017 8:50 PM
 */
public class BlockOre extends BlockBase implements ItemOreDict
{
	private String oreName;

	public BlockOre(String name, String oreName)
	{
		super(Material.ROCK, name);
		setHardness(3f);
		setResistance(5f);

		this.oreName = oreName;
	}

	@Override
	public void initOreDict()
	{
		OreDictionary.registerOre(oreName, this);
	}

	@Override
	public BlockOre setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
