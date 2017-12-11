package com.runicsystems.icbr.common.block.launcher;

import com.runicsystems.icbr.ICBR;
import com.runicsystems.icbr.common.item.ItemBase;
import com.runicsystems.icbr.common.network.PacketRequestUpdateRingLauncher;
import com.runicsystems.icbr.common.network.PacketUpdateRingLauncher;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * Author: neromir
 * Created 12/7/2017 6:49 PM
 */
public class TileEntityRingLauncher extends TileEntity
{
	private static final String INVENTORY_TAG = "inventory";

	public static final Integer RING_LIMIT = 1;

	public ItemStackHandler inventory = new ItemStackHandler(1)
	{
		@Override
		protected int getStackLimit(int slot, ItemStack stack)
		{
			// Only allow placing of the item "ring" into the ring launcher. We do this with a fairly crude
			// check on the name attribute for the magic string, since the ring is only an ItemBase with a
			// name at the moment.
			if(slot == 0 && stack.getItem() instanceof ItemBase)
			{
				ItemBase itemBase = (ItemBase) stack.getItem();
				if(itemBase.getName().equals("ring"))
				{
					return RING_LIMIT;
				}
			}
			return 0;
		}

		@Override
		protected void onContentsChanged(int slot)
		{
			if(!worldObj.isRemote)
			{
				ICBR.network.sendToAllAround(new PacketUpdateRingLauncher(TileEntityRingLauncher.this), new NetworkRegistry.TargetPoint(worldObj.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
			}
		}
	};

	@Override
	public void onLoad()
	{
		if(worldObj.isRemote)
		{
			ICBR.network.sendToServer(new PacketRequestUpdateRingLauncher(this));
		}
	}

	// TODO: Review if this needs to be modified or removed. Added it for tutorial and for adding basic functionality,
	// but this may not be necessary once I figure out how to render the ring.
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return new AxisAlignedBB(getPos(), getPos().add(1, 2, 1));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setTag(INVENTORY_TAG, inventory.serializeNBT());
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		inventory.deserializeNBT(compound.getCompoundTag(INVENTORY_TAG));
		super.readFromNBT(compound);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
	}
}
