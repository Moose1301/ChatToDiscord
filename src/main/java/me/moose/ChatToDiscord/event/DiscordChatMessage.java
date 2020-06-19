package me.moose.ChatToDiscord.event;

import me.moose.ChatToDiscord.bot.BotControl;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordChatMessage extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot() || e.getAuthor().isFake() || e.isWebhookMessage()) return;
        String[] args = e.getMessage().getContentRaw().split(" ");
        if(args[0].equals("$")) return;
        if(!e.getChannel().equals(BotControl.getInstance().channel)) return;
        if(e.getChannel().equals(BotControl.getInstance().channel)) {
            BotControl.getInstance().sendMessageToGame(e.getAuthor(), args);
        }

    }




}
