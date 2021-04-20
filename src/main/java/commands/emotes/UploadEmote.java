package commands.emotes;

import commands.Command;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import utils.chat.Responses;
import utils.io.Storage;

import java.io.IOException;

public class UploadEmote implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws IOException {

        if(event.getMessage().getEmotes().size() < 1) {
            event.getTextChannel().sendMessage(Responses.Errors.INSUFFICIENT_ARGS("upload")).queue();
            return;
        }

        Emote entity = event.getMessage().getEmotes().get(0);

        String emoteName = entity.getName();

        Storage.downloadByUrl(entity.getImageUrl(), emoteName);

        event.getTextChannel().sendMessage(Responses.Emotes.UPLOAD_SUCCESSFUL(emoteName)).queue();

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String Description() {
        return "Upload a specific emote to the EmoteBase.";
    }

    @Override
    public String Example() {
        return "upload :pensive:";
    }

    @Override
    public String Usage() {
        return "upload <emote>";
    }

    @Override
    public String Permissions() {
        return null;
    }
}
