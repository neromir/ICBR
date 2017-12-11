package com.runicsystems.icbr.common.block.launcher;

import com.runicsystems.icbr.ICBR;
import com.runicsystems.icbr.common.block.BlockTileEntity;
import com.runicsystems.icbr.common.item.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

/**
 * Author: neromir
 * Created 3/6/2017 7:07 PM
 */
public class RingLauncher extends BlockTileEntity<TileEntityRingLauncher>
{
	public RingLauncher()
	{
		super(Material.ROCK, "ringLauncher");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		// isRemote denotes if the world is in the server or client; false indicates server, true a client
		if(!world.isRemote)
		{
			TileEntityRingLauncher tile = getTileEntity(world, pos);
			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);

			// Don't even bother if we get back a null pointer
			// TODO: This should probably be logged
			if(itemHandler == null)
				return true;

			if(!player.isSneaking())
			{
				// If their hand is empty, try to pull a ring off of the launcher
				if(heldItem == null)
					player.setHeldItem(hand, itemHandler.extractItem(0, 1, false));
				else
				{
					// If it is not empty, attempt to insert the item. If the returned value is the same as what we passed in and we tried to pass
					// in a ring stack, then the launcher is full. This means that it should be retrieved and added to the
					// player's held stack instead, provided that the held stack is not full.
					ItemStack resultingStack = itemHandler.insertItem(0, heldItem, false);
					if(resultingStack == heldItem && heldItem.getItem() == ModItems.ring && heldItem.stackSize < 64)
					{
						resultingStack = itemHandler.extractItem(0, 1, false);
						resultingStack.stackSize += heldItem.stackSize;
					}
					player.setHeldItem(hand, resultingStack);
				}

				tile.markDirty();
			}
			else
			{
				// If the player is sneaking, send them a message about what the ring launcher has in it
				ItemStack stack = itemHandler.getStackInSlot(0);
				if(stack != null)
				{
					String localized = ICBR.proxy.localize(stack.getUnlocalizedName() + ".name");
					player.addChatMessage(new TextComponentString(stack.stackSize + "x " + localized));
				}
				else
					player.addChatMessage(new TextComponentString("Empty"));
			}
		}

		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		// Drop the item that's in the launcher's inventory when you break it
		TileEntityRingLauncher tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if(stack != null)
		{
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntityInWorld(item);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public Class<TileEntityRingLauncher> getTileEntityClass()
	{
		return TileEntityRingLauncher.class;
	}

	@Nullable
	@Override
	public TileEntityRingLauncher createTileEntity(World world, IBlockState state)
	{
		return new TileEntityRingLauncher();
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
