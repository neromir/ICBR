package com.runicsystems.icbr.common.item;

import net.minecraftforge.oredict.OreDictionary;

/**
 * Author: neromir
 * Created 12/7/2017 3:43 PM
 */
public class ItemOre extends ItemBase implements ItemOreDict
{
	private String oreName;

	public ItemOre(String name, String oreName)
	{
		super(name);
		this.oreName = oreName;
	}

	@Override
	public void initOreDict()
	{
		OreDictionary.registerOre(oreName, this);
	}
}
