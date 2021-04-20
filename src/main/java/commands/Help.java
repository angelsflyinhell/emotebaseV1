package commands;

import core.processing.CommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class Help implements Command{

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws IOException {
        String prefix = ">";
        if(!(args.length >= 1)) {

            event.getTextChannel().sendMessage(
                    new EmbedBuilder()
                            .setTitle("Command-Help")
                            .setDescription("Use `" + prefix + "help <command>` for more info!")
                            .setThumbnail(event.getJDA().getSelfUser().getAvatarUrl())
                            .build()
            ).queue();
        }else {
            try {
                event.getTextChannel().sendMessage(

                        new EmbedBuilder()

                                .setTitle("Command Help for '" + args[0] + "'")
                                .setDescription(CommandHandler.commands.get(args[0]).Description())
                                .addField("Usage", "```" + prefix + CommandHandler.commands.get(args[0]).Usage() + "```", false)
                                .addField("Example", "```" + prefix + CommandHandler.commands.get(args[0]).Example() + "```", false)
                                .addField("Permissions", String.valueOf(CommandHandler.commands.get(args[0]).Permissions()).replace("null", "None"), false)
                                .setThumbnail(event.getJDA().getSelfUser().getAvatarUrl())
                                .build()

                ).queue();
            }catch (Exception e) {
                return;
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String Description() {
        return "A helpful documentation of all commands!";
    }

    @Override
    public String Example() {
        return "help";
    }

    @Override
    public String Usage() {
        return "help";
    }

    @Override
    public String Permissions() {
        return null;
    }

}
