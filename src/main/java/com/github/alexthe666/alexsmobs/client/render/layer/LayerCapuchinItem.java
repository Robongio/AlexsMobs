package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelCapuchinMonkey;
import com.github.alexthe666.alexsmobs.client.model.ModelGorilla;
import com.github.alexthe666.alexsmobs.client.render.RenderCapuchinMonkey;
import com.github.alexthe666.alexsmobs.client.render.RenderGorilla;
import com.github.alexthe666.alexsmobs.entity.EntityCapuchinMonkey;
import com.github.alexthe666.alexsmobs.entity.EntityGorilla;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.vector.Vector3f;

public class LayerCapuchinItem extends LayerRenderer<EntityCapuchinMonkey, ModelCapuchinMonkey> {

    public LayerCapuchinItem(RenderCapuchinMonkey render) {
        super(render);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityCapuchinMonkey entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if(entitylivingbaseIn.getAnimation() == EntityCapuchinMonkey.ANIMATION_THROW && entitylivingbaseIn.getAnimationTick() <= 5) {
            ItemStack itemstack = new ItemStack(Items.COBBLESTONE);
            matrixStackIn.push();
            if (entitylivingbaseIn.isChild()) {
                matrixStackIn.scale(0.35F, 0.35F, 0.35F);
                matrixStackIn.translate(0.5D, 2.6D, 0.15D);
                translateToHand(false, matrixStackIn);
                matrixStackIn.translate(-0.4F, 0.75F, -0.0F);
                matrixStackIn.scale(2.8F, 2.8F, 2.8F);
            } else {
                translateToHand(false, matrixStackIn);
                matrixStackIn.translate(0.125F, 0.5F, 0.1F);
            }
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-2.5F));
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-90F));
            Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.pop();
        }
    }

    protected void translateToHand(boolean left, MatrixStack matrixStack) {
        this.getEntityModel().root.translateRotate(matrixStack);
        this.getEntityModel().body.translateRotate(matrixStack);
        this.getEntityModel().arm_right.translateRotate(matrixStack);
    }
}
