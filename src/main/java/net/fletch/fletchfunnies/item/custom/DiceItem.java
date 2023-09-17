package net.fletch.fletchfunnies.item.custom;

import net.fletch.fletchfunnies.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class DiceItem extends Item {
    public DiceItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide() && pUsedHand == InteractionHand.MAIN_HAND){
            //get a random number
            //set a cooldown
            int number=getRandomNumber(20)+1;
            tellPlayers(pPlayer, number);
            diceResult(pLevel, pPlayer, number);
            pPlayer.getInventory().removeItem(pPlayer.getItemInHand(pUsedHand));
            pPlayer.getCooldowns().addCooldown(this, 20);
        }


        return super.use(pLevel, pPlayer, pUsedHand);
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        //Screen.hasShiftDown() woah
        pTooltipComponents.add(Component.literal("Fancy a game of chance?").withStyle(ChatFormatting.YELLOW));
    }

    private void tellPlayers(Player player, int random){
        String chatMessage="§l§b"+player.getDisplayName().getString() + " rolled a "+random+"!";
        player.getServer().getPlayerList().broadcastSystemMessage(Component.literal(chatMessage), true);

        //player.sendSystemMessage(Component.literal("Your Number is " + random));
        //player.getServer().getCommands().performPrefixedCommand(player.getServer().createCommandSourceStack(), "tellraw @a {\"text\":\""+chatMessage);
    }
    private void diceResult(Level level, Player player, int number){
        //1-10 "bad"
        //11-20 "good"
        //or 1-5 bad, 6-15 mediocre, 16-20 good
        if(number==1){
            level.explode((Entity)null, player.getX(), player.getY()+1D, player.getZ(), 5.0F, Level.ExplosionInteraction.TNT);
        }
        if(number==2){
            Entity creep = new Creeper(EntityType.CREEPER, level);
            creep.moveTo(player.getX(), player.getY(), player.getZ(), 2f, 2f);
            level.addFreshEntity(creep);
        }
        if(number==3){
            Entity bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
            bolt.moveTo(player.getX(), player.getY()+1, player.getZ(), 2f, 2f);
            level.addFreshEntity(bolt);
        }
        if(number==4){
            for(int i=0; i<4; i++) {
                Entity zombie = new Zombie(EntityType.ZOMBIE, level);
                zombie.moveTo(player.getX(), player.getY(), player.getZ(), 2f, 2f);
                level.addFreshEntity(zombie);
            }
        }
        if(number==5){

            player.addEffect(new MobEffectInstance(MobEffects.POISON, 15*20, 2));
        }
        if(number==6){

            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30*20, 2));
        }
        if(number==7){
            for(ItemStack items:player.getInventory().items){
                player.drop(items, true, true);
                player.getInventory().removeItem(items);
            }
            for(ItemStack armor: player.getInventory().armor){
                player.drop(armor, true, true);
                player.getInventory().removeItem(armor);
            }
        }
        if(number==11){
            Entity pig=new Pig(EntityType.PIG, level);
            pig.moveTo(player.getX(), player.getY(), player.getZ(), 2f, 2f);
            pig.setCustomName(Component.literal("Crinkle"));
            level.addFreshEntity(pig);
        }

        if(number==13){
            int random=getRandomNumber(7);
            for(int i=0; i<=random; i++) {
                Entity sheep = new Sheep(EntityType.SHEEP, level);
                sheep.moveTo(player.getX(), player.getY(), player.getZ(), 2f, 2f);
                sheep.setCustomName(Component.literal("jeb_"));
                level.addFreshEntity(sheep);
            }
        }
        if(number==20){
            ItemStack gifts=new ItemStack(Items.DIAMOND.asItem(), 10, null);
            player.drop(gifts, false, true);
        }
    }

    private int getRandomNumber(int range){
        return RandomSource.createNewThreadLocalInstance().nextInt(range);
    }
}
