package com.runicsystems.icbr.common.block.launcher;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

/**
 * Author: neromir
 * Created 12/8/2017 2:55 PM
 */
public class TESRRingLauncher extends TileEntitySpecialRenderer<TileEntityRingLauncher>
{
	@Override
	public void renderTileEntityAt(TileEntityRingLauncher te, double x, double y, double z, float partialTicks, int destroyStage)
	{
		ItemStack stack = te.inventory.getStackInSlot(0);
		if(stack != null)
		{
			GlStateManager.enableRescaleNormal();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
			GlStateManager.enableBlend();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.pushMatrix();

			// Make the item bounce in the y direction
			//double offset = Math.sin((te.getWorld().getTotalWorldTime() + partialTicks) / 8) / 4.0;
			// Move the entity to the center of the block and above
			GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
			//GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks) * 4, 0, 1, 0);

			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, te.getWorld(), null);
			model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);

			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
	}
}
