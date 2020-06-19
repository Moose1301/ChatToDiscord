package me.moose.ChatToDiscord.Config;


import jdk.nashorn.internal.objects.annotations.Getter;
import me.moose.ChatToDiscord.ChatToDiscord;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigHandler {

    private static ConfigHandler instance;
    public ConfigHandler() {
        instance = this;
    }
    private Logger logger;
    public String token;
    public String channelid;
    private Configuration config = null;
    private File configFile;

    public void preInit() {
        configFile = new File(Loader.instance().getConfigDir(), "chattodiscord.cfg");
        config = new Configuration(configFile);
        syncFromFiles();
    }


    public Configuration getConfig() {
        return config;
    }

    public void serverPreInit() {

    }
    public void syncFromFiles() {
        syncConfig(true, true);
    }
    private void syncConfig(boolean lfcf, boolean rffc) {
        if(lfcf) {
            config.load();
        }

        Property ptoken = config.get(getCategory("Discord"), "token", "NULL");
        token = ptoken.getString();
        channelid = config.get(getCategory("Discord"), "ChannelID", "NULL").getString();
        config.save();

    }


    public static ConfigHandler getInstance() {
        return instance;
    }


    public String getChannelid() {
        return channelid;
    }
    public String getToken() {
        return token;
    }
    public String getCategory(String input) {
        if(input.equals("Discord")) {
            return "discord";
        }
        return "Null";
    }
}
