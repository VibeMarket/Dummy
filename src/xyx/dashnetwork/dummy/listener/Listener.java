package xyx.dashnetwork.dummy.listener;

import java.util.ArrayList;
import java.util.List;

public abstract class Listener implements Runnable {

    public static List<Listener> listeners = new ArrayList<>();
    private Thread thread;
    private boolean running;

    public Listener() {
        thread = new Thread(this, getClass().getSimpleName());
        running = false;

        listeners.add(this);
    }

    public static void stopAll() {
        for (Listener listener : listeners)
            listener.stop();
    }

    public void start() {
        running = true;
        thread.start();
    }

    public void stop() {
        thread.interrupt();
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public abstract void listen();

    @Override
    public void run() {
        while (running)
            listen();
    }

}
