package me.moose.ChatToDiscord.event;

import me.moose.ChatToDiscord.bot.BotControl;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ServerChatEvent;

import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class IGChatEvent {
    @SubscribeEvent
    public void serverchat(ServerChatEvent event) {
        String message = event.getMessage();
        String usernameplayer =  event.getUsername();

        BotControl.getInstance().sendMessageToDiscord(usernameplayer, message, "MESSAGE");
    }
    @SubscribeEvent
    public void playerjoin(PlayerEvent.PlayerLoggedInEvent event) {
        String usernameplayer =  event.player.getDisplayNameString();

        BotControl.getInstance().sendMessageToDiscord(usernameplayer, "message", "JOIN");
    }
    @SubscribeEvent
    public void playerleave(PlayerEvent.PlayerLoggedOutEvent event) {
        String usernameplayer =  event.player.getDisplayNameString();

        BotControl.getInstance().sendMessageToDiscord(usernameplayer, "message", "LEAVE");
    }
}
