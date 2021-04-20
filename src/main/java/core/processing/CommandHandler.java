package core.processing;

import commands.Command;

import java.io.IOException;
import java.util.HashMap;

public class CommandHandler {

    public static final CommandParser parser = new CommandParser();
    public static HashMap<String, Command> commands = new HashMap<>();
    public static CommandParser parse;

    public static void handleCommand(CommandParser.commandContainer cmd) throws IOException {

        if(commands.containsKey(cmd.invoke)) {

            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if (!safe) {

                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }

        }


    }

}