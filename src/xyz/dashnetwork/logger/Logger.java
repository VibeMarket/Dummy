package xyz.dashnetwork.logger;

import xyz.dashnetwork.logger.utils.Level;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static void log(Level level, String message) {
        String time = dateFormat.format(new Date());
        String log = "[" + time + " " + level.name() + "]: " + message;

        switch (level) {
            case INFO:
                System.out.println(log);
                break;
            case WARN:
                System.out.println("\u001B[33m" + log);
                break;
            case ERROR:
                System.out.println("\u001B[31m" + log);
                break;
        }
    }

}
