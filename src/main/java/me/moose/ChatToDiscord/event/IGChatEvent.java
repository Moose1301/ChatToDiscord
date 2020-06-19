package me.moose.ChatToDiscord.event;

import me.moose.ChatToDiscord.bot.BotControl;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class IGChatEvent {
    @SubscribeEvent
    public void serverchat(ServerChatEvent event) {
        String message = event.getMessage();
        String usernameplayer =  event.getUsername();
        BotControl.getInstance().sendMessageToDiscord(usernameplayer, message, "MESSAGE");
    }
}
