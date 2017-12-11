package com.runicsystems.icbr.common.network;

import com.runicsystems.icbr.common.block.launcher.TileEntityRingLauncher;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Author: neromir
 * Created 12/8/2017 2:46 PM
 */
public class PacketRequestUpdateRingLauncher implements IMessage
{
	private BlockPos pos;
	private int dimension;

	public PacketRequestUpdateRingLauncher(BlockPos pos, int dimension)
	{
		this.pos = pos;
		this.dimension = dimension;
	}

	public PacketRequestUpdateRingLauncher(TileEntityRingLauncher entity)
	{
		this(entity.getPos(), entity.getWorld().provider.getDimension());
	}

	// Needed by Forge to instantiate an empty object for population with fromBytes().
	public PacketRequestUpdateRingLauncher()
	{
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeLong(pos.toLong());
		buf.writeInt(dimension);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		pos = BlockPos.fromLong(buf.readLong());
		dimension = buf.readInt();
	}

	public static class Handler implements IMessageHandler<PacketRequestUpdateRingLauncher, PacketUpdateRingLauncher>
	{
		@Override
		public PacketUpdateRingLauncher onMessage(PacketRequestUpdateRingLauncher message, MessageContext ctx)
		{
			// Since this will be executed on the server, which manages multiple worlds (dimensions), we need to get the world
			// for the dimension the client is in, then retrieve the RingLauncher from it for sending the update to the client.
			World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(message.dimension);
			TileEntityRingLauncher entity = (TileEntityRingLauncher)world.getTileEntity(message.pos);
			if(entity != null)
				return new PacketUpdateRingLauncher(entity);
			else
				return null;
		}
	}
}
