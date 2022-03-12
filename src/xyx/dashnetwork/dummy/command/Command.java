package xyx.dashnetwork.dummy.command;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    private static List<Command> commands = new ArrayList<>();
    private String description;
    private String[] labels;

    public Command(String description, String... labels) {
        this.description = description;
        this.labels = labels;

        commands.add(this);
    }

    public static List<Command> getCommands() {
        return commands;
    }

    public static Command getCommand(String input) {
        for (Command command : commands)
            for (String label : command.getLabels())
                if (label.equalsIgnoreCase(input))
                    return command;
        return null;
    }

    public String getDescription() {
        return description;
    }

    public String[] getLabels() {
        return labels;
    }

    public abstract void execute(String label, String[] args);

}
