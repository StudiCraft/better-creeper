package net.mcreator.bettercreeper;

import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;

public class MyCreeperRenderer extends CreeperRenderer {

    private static final ResourceLocation GREEN_1_TEXTURE = ResourceLocation.fromNamespaceAndPath("bettercreeper", "textures/entity/creeper/green_1.png");
    private static final ResourceLocation GREEN_2_TEXTURE = ResourceLocation.fromNamespaceAndPath("bettercreeper", "textures/entity/creeper/green_2.png");
    private static final ResourceLocation GREEN_3_TEXTURE = ResourceLocation.fromNamespaceAndPath("bettercreeper", "textures/entity/creeper/green_3.png");
    
    private static final ResourceLocation ORANGE_1_TEXTURE = ResourceLocation.fromNamespaceAndPath("bettercreeper", "textures/entity/creeper/orange_1.png");
    private static final ResourceLocation ORANGE_2_TEXTURE = ResourceLocation.fromNamespaceAndPath("bettercreeper", "textures/entity/creeper/orange_2.png");
    private static final ResourceLocation ORANGE_3_TEXTURE = ResourceLocation.fromNamespaceAndPath("bettercreeper", "textures/entity/creeper/orange_3.png");
    
    private static final ResourceLocation RED_1_TEXTURE = ResourceLocation.fromNamespaceAndPath("bettercreeper", "textures/entity/creeper/red_1.png");
    private static final ResourceLocation RED_2_TEXTURE = ResourceLocation.fromNamespaceAndPath("bettercreeper", "textures/entity/creeper/red_2.png");
    private static final ResourceLocation RED_3_TEXTURE = ResourceLocation.fromNamespaceAndPath("bettercreeper", "textures/entity/creeper/red_3.png");
    
    private static final ResourceLocation DEFAULT_CREEPER_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/creeper/creeper.png");

    // Store the original swelling value for texture selection
    private float storedSwelling = 0.0F;

    public MyCreeperRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(CreeperRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        // Store the original swelling value before modifying it
        storedSwelling = renderState.swelling;
        
        // Temporarily disable the white overlay effect by setting swelling to 0
        renderState.swelling = 0.0F;
        
        // Call the parent render method without the white flash effect
        super.render(renderState, poseStack, bufferSource, packedLight);
        
        // Restore the original swelling value
        renderState.swelling = storedSwelling;
    }

    @Override
    public ResourceLocation getTextureLocation(CreeperRenderState renderState) {
        // Use the stored swelling value instead of the potentially modified one
        float fuseProgress = storedSwelling;

        if (fuseProgress > 0.0F) {
            // Adjust thresholds for 0.0 to 1.0 range
            final float GREEN_END_THRESHOLD = 0.33F;   // First third
            final float ORANGE_END_THRESHOLD = 0.66F;  // Second third
            // Red phase is the final third (0.66F to 1.0F)

            if (fuseProgress <= GREEN_END_THRESHOLD) {
                // Green phase (0.0 to 0.33)
                float subProgress = fuseProgress / GREEN_END_THRESHOLD;
                if (subProgress < 0.33F) {
                    return GREEN_1_TEXTURE;
                } else if (subProgress < 0.66F) {
                    return GREEN_2_TEXTURE;
                } else {
                    return GREEN_3_TEXTURE;
                }
            } else if (fuseProgress <= ORANGE_END_THRESHOLD) {
                // Orange phase (0.33 to 0.66)
                float subProgress = (fuseProgress - GREEN_END_THRESHOLD) / (ORANGE_END_THRESHOLD - GREEN_END_THRESHOLD);
                if (subProgress < 0.33F) {
                    return ORANGE_1_TEXTURE;
                } else if (subProgress < 0.66F) {
                    return ORANGE_2_TEXTURE;
                } else {
                    return ORANGE_3_TEXTURE;
                }
            } else {
                // Red phase (0.66 to 1.0)
                float subProgress = (fuseProgress - ORANGE_END_THRESHOLD) / (1.0F - ORANGE_END_THRESHOLD);
                if (subProgress < 0.33F) {
                    return RED_1_TEXTURE;
                } else if (subProgress < 0.66F) {
                    return RED_2_TEXTURE;
                } else {
                    return RED_3_TEXTURE;
                }
            }
        }

        return DEFAULT_CREEPER_TEXTURE;
    }
}