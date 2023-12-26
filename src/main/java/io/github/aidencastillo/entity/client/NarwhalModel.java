package io.github.aidencastillo.entity.client;// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class NarwhalModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "narwhalmodel"), "main");
	private final ModelPart narwhal;
	private final ModelPart head;

	public NarwhalModel(ModelPart root) {
		this.narwhal = root.getChild("narwhal");
		this.head = narwhal.getChild("body").getChild("torso").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition narwhal = partdefinition.addOrReplaceChild("narwhal", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = narwhal.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -13.0F, -8.0F, 16.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 29).addBox(-6.0F, -11.0F, -20.0F, 12.0F, 11.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition horn = head.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(48, 39).addBox(-1.0F, -10.0F, -22.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(-1.0F, -12.0F, -24.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-1.0F, -14.0F, -26.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fins = torso.addOrReplaceChild("fins", CubeListBuilder.create().texOffs(24, 62).addBox(-16.0F, -5.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 52).addBox(8.0F, -5.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(70, 71).addBox(-18.0F, -4.0F, 4.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 71).addBox(13.0F, -4.0F, 4.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -3.0F, 35.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(11.0F, -3.0F, 35.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(56, 62).addBox(-14.0F, -5.0F, 31.0F, 11.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 14).addBox(3.0F, -5.0F, 31.0F, 11.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(36, 29).addBox(-10.0F, -5.0F, 26.0F, 21.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 70).addBox(-4.0F, -6.0F, 21.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-5.0F, -8.0F, 15.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(41, 45).addBox(-6.0F, -10.0F, 8.0F, 12.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		narwhal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

//	@Override
//	public ModelPart root() {
//		return narwhal;
//	}
}