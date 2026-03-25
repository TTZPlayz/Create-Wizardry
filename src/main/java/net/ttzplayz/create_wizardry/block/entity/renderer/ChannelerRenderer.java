package net.ttzplayz.create_wizardry.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.fluid.SmartFluidTank;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import net.createmod.catnip.platform.ForgeCatnipServices;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.fluids.FluidStack;
import net.ttzplayz.create_wizardry.block.entity.ChannelerBlockEntity;

public class ChannelerRenderer extends SmartBlockEntityRenderer<ChannelerBlockEntity> {

    public ChannelerRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(ChannelerBlockEntity channeler, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        super.renderSafe(channeler, partialTicks, poseStack, buffer, light, overlay);
        renderFluid(channeler, partialTicks, poseStack, buffer, light);
    }

    protected void renderFluid(ChannelerBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light) {
        SmartFluidTankBehaviour tank = be.internalTank;
        if (tank != null) {
            SmartFluidTankBehaviour.TankSegment primaryTank = tank.getPrimaryTank();
            SmartFluidTank primaryHandler = tank.getPrimaryHandler();
            FluidStack fluidStack = primaryTank.getRenderedFluid();
            if (fluidStack.isEmpty() && primaryHandler != null) {
                fluidStack = primaryHandler.getFluid();
            }

            float level = primaryTank.getFluidLevel().getValue(partialTicks);
            if (level <= 0.0F && primaryHandler != null && primaryHandler.getCapacity() > 0) {
                level = primaryHandler.getFluidAmount() / (float) primaryHandler.getCapacity();
            }

            if (!fluidStack.isEmpty() && level != 0.0F) {
                float inset = 1.0F / 16.0F;
                float min = inset;
                float max = 1.0F - inset;
                float yMin = inset + 0.001F;
                float yMax = (4.0F / 16.0F) - 0.001F;
                float fluidTop = yMin + (yMax - yMin) * level;

                ms.pushPose();
                ForgeCatnipServices.FLUID_RENDERER.renderFluidBox(fluidStack, min, yMin, min, max, fluidTop, max, buffer, ms, light, false, false);
                ms.popPose();
            }
        }
    }
}
