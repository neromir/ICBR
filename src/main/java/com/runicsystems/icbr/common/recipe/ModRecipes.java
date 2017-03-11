package com.runicsystems.icbr.common.recipe;

import com.runicsystems.icbr.common.block.ModBlocks;
import com.runicsystems.icbr.common.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Author: neromir
 * Created 3/10/2017 6:44 PM
 */
public class ModRecipes
{
	public static void init()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.wireCopper, 3), " C ", " C ", " C ", 'C', ModItems.ingotCopper);
		GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 0.7f);
	}
}
