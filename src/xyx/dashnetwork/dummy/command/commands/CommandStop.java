package xyx.dashnetwork.dummy.command.commands;

import xyx.dashnetwork.dummy.Dummy;
import xyx.dashnetwork.dummy.command.Command;

public class CommandStop extends Command {

    public CommandStop() {
        super("Stops the server", "stop", "end");
    }

    @Override
    public void execute(String label, String[] args) {
        Dummy.shutdown();
    }

}
