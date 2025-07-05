package net.mcreator.bettercreeper;

import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.resources.ResourceLocation;

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

    public MyCreeperRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(CreeperRenderState renderState) {
        // The swelling field should be available in CreeperRenderState
        float fuseProgress = renderState.swelling;

        if (fuseProgress > 0.0F) {
            final float GREEN_END_THRESHOLD = 10.0F / 30.0F;
            final float ORANGE_END_THRESHOLD = 20.0F / 30.0F;

            if (fuseProgress < GREEN_END_THRESHOLD) {
                float subProgress = fuseProgress / GREEN_END_THRESHOLD;
                if (subProgress < (3.0F / 10.0F)) {
                    return GREEN_1_TEXTURE;
                } else if (subProgress < (6.0F / 10.0F)) {
                    return GREEN_2_TEXTURE;
                } else {
                    return GREEN_3_TEXTURE;
                }
            } else if (fuseProgress < ORANGE_END_THRESHOLD) {
                float subProgress = (fuseProgress - GREEN_END_THRESHOLD) / (ORANGE_END_THRESHOLD - GREEN_END_THRESHOLD);
                if (subProgress < (3.0F / 10.0F)) {
                    return ORANGE_1_TEXTURE;
                } else if (subProgress < (6.0F / 10.0F)) {
                    return ORANGE_2_TEXTURE;
                } else {
                    return ORANGE_3_TEXTURE;
                }
            } else {
                float subProgress = (fuseProgress - ORANGE_END_THRESHOLD) / (1.0F - ORANGE_END_THRESHOLD);
                if (subProgress < (3.0F / 10.0F)) {
                    return RED_1_TEXTURE;
                } else if (subProgress < (6.0F / 10.0F)) {
                    return RED_2_TEXTURE;
                } else {
                    return RED_3_TEXTURE;
                }
            }
        }

        return DEFAULT_CREEPER_TEXTURE;
    }
}