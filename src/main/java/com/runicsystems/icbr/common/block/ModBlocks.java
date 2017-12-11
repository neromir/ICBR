package com.runicsystems.icbr.common.block;

import com.runicsystems.icbr.common.block.launcher.RingLauncher;
import com.runicsystems.icbr.common.item.ItemOreDict;
import net.minecraft.block.Block;
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
		oreCopper = register(new BlockOre("oreCopper", "oreCopper"));
		ringLauncher = register(new RingLauncher());
	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock)
	{
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);

		if(block instanceof BlockBase)
		{
			((BlockBase)block).registerItemModel(itemBlock);
		}

		if(block instanceof ItemOreDict)
		{
			((ItemOreDict)block).initOreDict();
		}

		if(itemBlock instanceof ItemOreDict)
		{
			((ItemOreDict)itemBlock).initOreDict();
		}

		if(block instanceof BlockTileEntity)
		{
			GameRegistry.registerTileEntity(((BlockTileEntity<?>)block).getTileEntityClass(), block.getRegistryName().toString());
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
