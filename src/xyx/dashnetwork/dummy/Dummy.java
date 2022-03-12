package xyx.dashnetwork.dummy;

import xyx.dashnetwork.dummy.command.commands.CommandStop;
import xyx.dashnetwork.dummy.listener.Listener;
import xyx.dashnetwork.dummy.listener.listeners.CommandListener;
import xyx.dashnetwork.dummy.listener.listeners.ServerListener;
import xyx.dashnetwork.dummy.logger.Logger;
import xyx.dashnetwork.dummy.logger.utils.Level;

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
