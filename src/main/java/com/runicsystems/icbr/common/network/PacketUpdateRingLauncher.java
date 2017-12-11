package com.runicsystems.icbr.common.network;

import com.runicsystems.icbr.common.block.launcher.TileEntityRingLauncher;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Author: neromir
 * Created 12/8/2017 2:31 PM
 */
public class PacketUpdateRingLauncher implements IMessage
{
	private BlockPos pos;
	private ItemStack stack;

	public PacketUpdateRingLauncher(BlockPos pos, ItemStack stack)
	{
		this.pos = pos;
		this.stack = stack;
	}

	public PacketUpdateRingLauncher(TileEntityRingLauncher tileEntity)
	{
		this(tileEntity.getPos(), tileEntity.inventory.getStackInSlot(0));
	}

	// Needed by Forge to instantiate an empty object for population with fromBytes().
	public PacketUpdateRingLauncher()
	{
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeLong(pos.toLong());
		ByteBufUtils.writeItemStack(buf, stack);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		pos = BlockPos.fromLong(buf.readLong());
		stack = ByteBufUtils.readItemStack(buf);
	}

	public static class Handler implements IMessageHandler<PacketUpdateRingLauncher, IMessage>
	{
		@Override
		public IMessage onMessage(PacketUpdateRingLauncher message, MessageContext ctx)
		{
			Minecraft.getMinecraft().addScheduledTask(() ->
			{
				TileEntityRingLauncher entity = (TileEntityRingLauncher)Minecraft.getMinecraft().theWorld.getTileEntity(message.pos);
				entity.inventory.setStackInSlot(0, message.stack);
			});

			// Returning null because we don't want a response. This makes sense in this case because this message
			// is effectively a response to a previous request (or is an unsolicited broadcast sometimes?)
			return null;
		}
	}
}
