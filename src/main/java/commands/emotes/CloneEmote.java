package commands.emotes;

import commands.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import utils.chat.Responses;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CloneEmote implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws IOException {

        if (!event.getMember().hasPermission(Permission.MANAGE_EMOTES)) {
            event.getTextChannel().sendMessage(Responses.Errors.INSUFFICIENT_PERMS("clone")).queue();
            return;
        }

        if (event.getMessage().getEmotes().size() < 1) {
            event.getTextChannel().sendMessage(Responses.Errors.INSUFFICIENT_ARGS("clone")).queue();
            return;
        }

        Emote entity = event.getMessage().getEmotes().get(0);

        String emoteName = entity.getName();


        Emote emote = event.getMessage().getEmotes().get(0);
        URL url = new URL(emote.getImageUrl());
        byte[] data = readUrl(url);

        Icon icon = Icon.from(data);
        event.getGuild().createEmote(emoteName, icon).queue();

        event.getTextChannel().sendMessage(Responses.Emotes.ADD_SUCCESSFUL(emoteName)).queue();

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String Description() {
        return "Clone an Emote to another Server.";
    }

    @Override
    public String Example() {
        return "clone :pensive:";
    }

    @Override
    public String Usage() {
        return "clone :emote:";
    }

    @Override
    public String Permissions() {
        return "Manage Emotes";
    }

    private byte[] readUrl(URL url) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] chunk = new byte[4096];
        int bytesRead;

        try (InputStream stream = url.openStream()) {
            while ((bytesRead = stream.read(chunk)) > 0) {
                out.write(chunk, 0, bytesRead);
            }
        }

        return out.toByteArray();
    }
}
