package nl.devpieter.donttakedamage.mixin;

import java.io.File;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

	MinecraftClient client = MinecraftClient.getInstance();

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(at = @At("TAIL"), method = "applyDamage")
	protected void applyDamage(DamageSource source, float amount, CallbackInfo info) {
		client.player.sendMessage(new LiteralText("\u00A7cYOU LOST! HAHAHA!"), false);
		try {

			File triggerFile = new File(System.getProperty("java.io.tmpdir") + "trigger_bsod.txt");
			triggerFile.createNewFile();

		} catch (Exception e) {
			client.player.sendMessage(new LiteralText("An error occurred while creating the trigger file! \u00A7cBut you still lost xD"), false);
			e.printStackTrace();
		}
	}
}
