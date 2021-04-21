package core;

import commands.Help;
import commands.emotes.CloneEmote;
import commands.emotes.UploadEmote;
import commands.emotes.WidenEmote;
import core.processing.CommandHandler;
import core.processing.CommandListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import utils.Config;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(Config.TOKEN);
        builder.setCompression(Compression.ZLIB);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.addEventListeners(new CommandListener());
        builder.setActivity(Activity.watching("beta™"));

        builder.build();
        Commands();
    }

    private static void Commands() {
        CommandHandler.commands.put("help", new Help());

        CommandHandler.commands.put("upload", new UploadEmote());
        CommandHandler.commands.put("clone", new CloneEmote());
        CommandHandler.commands.put("widen", new WidenEmote());
    }
}
