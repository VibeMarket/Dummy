package xyz.dashnetwork.listener.listeners;

import xyz.dashnetwork.command.Command;
import xyz.dashnetwork.listener.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandListener extends Listener {

    @Override
    public void listen() {
        Scanner scanner = new Scanner(System.in);
        String[] args = scanner.nextLine().split(" ");
        String label = args[0];

        Command command = Command.getCommand(label);

        if (command == null)
            System.out.println("Unknown command. Type \"help\" for help.");
        else {
            List<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(args));
            list.remove(0);

            args = list.toArray(new String[list.size()]);

            command.execute(label, args);
        }
    }

}
