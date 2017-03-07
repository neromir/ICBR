package com.runicsystems.icbr.common.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Author: neromir
 * Created 3/3/2017 8:55 PM
 */
public class ModBlocks
{
	public static BlockOre oreCopper;
	public static RingLauncher ringLauncher;

	public static void init()
	{
		oreCopper = register(new BlockOre("oreCopper").setCreativeTab(CreativeTabs.MATERIALS));
		ringLauncher = register(new RingLauncher().setCreativeTab(CreativeTabs.MISC));
	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock)
	{
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);

		if(block instanceof BlockBase)
		{
			((BlockBase)block).registerItemModel(itemBlock);
		}

		return block;
	}

	private static <T extends Block> T register(T block)
	{
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}
}
