package net.ttzplayz.create_wizardry.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import org.jetbrains.annotations.NotNull;

public class DualTextureTintedClientFluidType implements IClientFluidTypeExtensions {
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;
    private final int tintColor;

    public DualTextureTintedClientFluidType(ResourceLocation stillTexture, ResourceLocation flowingTexture, int tintColor) {
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
        this.tintColor = tintColor | 0xFF000000;
    }

    @Override
    public @NotNull ResourceLocation getStillTexture() {
        return stillTexture;
    }

    @Override
    public @NotNull ResourceLocation getFlowingTexture() {
        return flowingTexture;
    }

    @Override
    public int getTintColor() {
        return tintColor;
    }
}
