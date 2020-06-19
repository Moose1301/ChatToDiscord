package me.moose.ChatToDiscord;

import me.moose.ChatToDiscord.Config.ConfigHandler;
import me.moose.ChatToDiscord.bot.BotControl;
import me.moose.ChatToDiscord.event.IGChatEvent;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

@Mod(modid = ChatToDiscord.MODID, name = ChatToDiscord.NAME, version = ChatToDiscord.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class ChatToDiscord
{
    private static ChatToDiscord instance;
    public static final String MODID = "chattodiscord";
    public static final String NAME = "Chat to Discord";
    public static final String VERSION = "1.0";
    public static File CONFIG_DIR;
    public Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        instance = this;
        new ConfigHandler();
        ConfigHandler.getInstance().preInit();
        new BotControl();
        try {
            BotControl.getInstance().startBot();
        } catch (LoginException e) {
            e.printStackTrace();
        }


    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new IGChatEvent());

        // some example code
    }

    public static ChatToDiscord getInstance() {
        return instance;
    }
}
