package xyz.dashnetwork;

import xyz.dashnetwork.command.commands.CommandStop;
import xyz.dashnetwork.listener.Listener;
import xyz.dashnetwork.listener.listeners.CommandListener;
import xyz.dashnetwork.listener.listeners.ServerListener;
import xyz.dashnetwork.logger.Logger;
import xyz.dashnetwork.logger.utils.Level;

public class Dummy {

    public static void main(String[] args) {
        Logger.log(Level.INFO, "Starting...");

        short port = 25565;

        if (args.length > 1 && args[0].equals("-port")) {
            try {
                port = Short.valueOf(args[1]);
            } catch (IllegalArgumentException exception) {
                Logger.log(Level.ERROR, args[1] + " is not a valid port");
            }
        }

        new CommandStop();

        new CommandListener().start();
        new ServerListener(port).start();

        Logger.log(Level.INFO, "Listening on 0.0.0.0:" + port);
    }

    public static void shutdown() {
        Logger.log(Level.INFO, "Shutting down...");

        Listener.stopAll();

        System.exit(0);
    }

}
