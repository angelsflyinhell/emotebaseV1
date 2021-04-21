package commands.emotes;

import commands.Command;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import utils.chat.Responses;
import utils.io.Imager;
import utils.io.Storage;

import java.io.IOException;
import java.util.List;

public class WidenEmote implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws IOException {
        if (event.getMessage().getEmotes().size() < 1) {
            event.getTextChannel().sendMessage(Responses.Errors.INSUFFICIENT_ARGS("widen")).queue();
            return;
        }

        Emote entity = event.getMessage().getEmotes().get(0);

        String emoteName = entity.getName();
        String imgUrl = entity.getImageUrl();
        String[] entityType = imgUrl.split("\\.");

        Storage.downloadByUrl(imgUrl, emoteName);

        Imager.loadImage(emoteName + "." + entityType[entityType.length - 1]);
        Imager.resize();
        Imager.cutToSquare();
        Imager.save();
        Imager.close();

        List<byte[]> all = Imager.getCurrent(entityType[entityType.length - 1]);

        for (byte[] data : all) {
            Icon icon = Icon.from(data);
            event.getGuild().createEmote(emoteName, icon).queue();
        }

        event.getTextChannel().sendMessage(Responses.Emotes.WIDEN_SUCCESSFUL(emoteName)).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String Description() {
        return null;
    }

    @Override
    public String Example() {
        return null;
    }

    @Override
    public String Usage() {
        return null;
    }

    @Override
    public String Permissions() {
        return null;
    }
}
