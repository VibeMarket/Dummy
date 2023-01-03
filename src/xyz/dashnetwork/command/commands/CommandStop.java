package xyz.dashnetwork.command.commands;

import xyz.dashnetwork.Dummy;
import xyz.dashnetwork.command.Command;

public class CommandStop extends Command {

    public CommandStop() {
        super("Stops the server", "stop", "end");
    }

    @Override
    public void execute(String label, String[] args) {
        Dummy.shutdown();
    }

}
