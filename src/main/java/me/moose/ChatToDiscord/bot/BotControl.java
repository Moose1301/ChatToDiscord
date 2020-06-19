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
import net.minecraft.server.management.PlayerList;
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
    public void loadthings()  {
        try {
            FMLLog.log(Level.WARN, "I am in " + jda.awaitReady().getGuilds().size() + " Guilds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            guild = jda.awaitReady().getGuilds().get(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel = guild.getTextChannelById(ConfigHandler.getInstance().getChannelid());
    }

    public void startBot() throws LoginException {
        jda = new JDABuilder(AccountType.BOT).setToken(ConfigHandler.getInstance().getToken()).build();
        addCommands();
        loadthings();
    }
    public void sendMessageToDiscord(String player, String message, String TYPE) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        boolean messagetype = TYPE.equals("MESSAGE");
        boolean leave = TYPE.equals("LEAVE");
        boolean join = TYPE.equals("JOIN");

        if (messagetype) {

            channel.sendMessage("``%time%``  %player%: %message%".replace("%player%", player).replace("%time%", dtf.format(now).replace("%message%", message)));
        }
        if (leave) {
            channel.sendMessage("``%time%``  **%player%** joined".replace("%player%", player).replace("%time%", dtf.format(now)));
        }
        if (join) {
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
        StringBuilder sb = new StringBuilder();
        for (String s : message) {
            sb.append(s);
            sb.append(" ");
        }
        String discordText = TextFormatting.AQUA + "[DISCORD] ";
        String discordat = TextFormatting.YELLOW + "@" + user.getName() + ": ";
        FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers().forEach(player -> {
            player.sendMessage(new TextComponentString(discordText + discordat + "" + TextFormatting.WHITE + sb.toString()));
        });
    }
    public static BotControl getInstance() {
        return instance;
    }

}
