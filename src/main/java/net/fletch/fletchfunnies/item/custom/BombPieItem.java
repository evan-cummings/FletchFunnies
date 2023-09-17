package net.fletch.fletchfunnies.item.custom;

import net.fletch.fletchfunnies.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BombPieItem extends BowlFoodItem {
    public BombPieItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        ItemStack itemstack = super.finishUsingItem(pStack, pLevel, pEntityLiving);
        //return pie tin
        //instead of Items.Bowl return ModItems.Pie
        pLevel.explode((Entity)null, pEntityLiving.getX(), pEntityLiving.getY()+1D, pEntityLiving.getZ(), 5.0F, Level.ExplosionInteraction.TNT);
        return pEntityLiving instanceof Player && ((Player)pEntityLiving).getAbilities().instabuild ? itemstack : new ItemStack(ModItems.PIE_TIN.get());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        //Screen.hasShiftDown() woah
        pTooltipComponents.add(Component.literal("Made in a factory.").withStyle(ChatFormatting.YELLOW));
    }
}
