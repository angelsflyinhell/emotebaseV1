package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public interface Command {

    boolean called(String[] args, MessageReceivedEvent event);
    void action(String[] args, MessageReceivedEvent event) throws IOException;
    void executed(boolean success, MessageReceivedEvent event);
    String Description();
    String Example();
    String Usage();
    String Permissions();

}
