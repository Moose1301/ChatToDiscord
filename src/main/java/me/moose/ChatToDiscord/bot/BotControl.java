package me.moose.ChatToDiscord.bot;

import me.moose.ChatToDiscord.Config.ConfigHandler;
import me.moose.ChatToDiscord.event.DiscordChatMessage;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class BotControl {
    private static BotControl instance;
    JDA jda;
    public Guild guild;
    public TextChannel channel;

    public BotControl() {
        instance = this;
    }
    public void loadThings() {
        FMLLog.log(Level.WARN, "I am in " + String.valueOf(jda.getGuilds().size()) + " Guilds");

    //    guild = jda.getGuilds().get(0);
     //   channel = guild.getTextChannelById(ConfigHandler.getInstance().getChannelid());
    }

    public void startBot() throws LoginException {
        jda = new JDABuilder(AccountType.BOT).setToken(ConfigHandler.getInstance().getToken()).build();
        addCommands();
        loadThings();
    }
    public void sendMessageToDiscord(String player, String message, String TYPE) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if (TYPE.equals("MESSAGE")) {
            channel.sendMessage("``%time%``  %player%: %message%".replace("%player%", player).replace("%time%", dtf.format(now)));
        }
        if (TYPE.equals("LEAVE")) {
            channel.sendMessage("``%time%``  %player%: %message%".replace("%player%", player).replace("%time%", dtf.format(now)));
        }
        if (TYPE.equals("JOIN")) {
            channel.sendMessage("``%time%``  * **%player%** left*".replace("%player%", player).replace("%time%", dtf.format(now)));
        }
    }
    public void addCommands() {
        jda.addEventListener(new DiscordChatMessage());
    }
    public Guild getGuild() {
        return guild;
    }
    public void sendMessageToGame(User user, String[] message) {
        StringBuilder sb= new StringBuilder();
        for(String s : message) {
            sb.append(s);
            sb.append(" ");
        }
        String discordText = TextFormatting.AQUA + "[DISCORD] ";
        String discordat = TextFormatting.YELLOW + "@" + user.getName() + " ";
        FMLCommonHandler.instance().getMinecraftServerInstance().sendMessage(new TextComponentString(discordText + discordat +sb.toString()));
    }

    public static BotControl getInstance() {
        return instance;
    }

}
