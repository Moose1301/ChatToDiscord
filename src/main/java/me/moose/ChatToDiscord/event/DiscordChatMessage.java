package me.moose.ChatToDiscord.event;

import me.moose.ChatToDiscord.Config.ConfigHandler;
import me.moose.ChatToDiscord.bot.BotControl;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class DiscordChatMessage extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot() || e.getAuthor().isFake() || e.isWebhookMessage()) return;
        String[] args = e.getMessage().getContentRaw().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(s);
            sb.append(" ");
        }
        if(args[0].equals("$")) return;
        if(!e.getChannel().getId().equals(ConfigHandler.getInstance().getChannelid())) return;
        if(e.getChannel().getId().equals(ConfigHandler.getInstance().getChannelid())) {
                BotControl.getInstance().sendMessageToGame(e.getAuthor(), args);
        }

    }




}
