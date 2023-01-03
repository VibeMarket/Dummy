package xyz.dashnetwork.listener.listeners;

import xyz.dashnetwork.Dummy;
import xyz.dashnetwork.listener.Listener;
import xyz.dashnetwork.logger.Logger;
import xyz.dashnetwork.logger.utils.Level;
import xyz.dashnetwork.status.ServerInfo;
import xyz.dashnetwork.status.Status;
import xyz.dashnetwork.status.utils.ChatColor;
import xyz.dashnetwork.status.utils.GameProfile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;

public class ServerListener extends Listener {

    private short port;
    private ServerSocket server;
    private ServerInfo info;

    public ServerListener(short port) {
        this.port = port;

        info = new ServerInfo();
        info.setDescription("&9&lVibeMarket &8&l»&7 &c&o&lMAINTENANCE");
        info.setSoftware("MAINTENANCE");

        info.addPlayer(new GameProfile(ChatColor.translate("&f             &9&n&lVibeMarket"), UUID.randomUUID()));
        info.addPlayer(new GameProfile(ChatColor.translate("&6&lMinecraft &7vibemarket.org"), UUID.randomUUID()));
        info.addPlayer(new GameProfile(ChatColor.translate("&6&lDiscord &7https://discord.gg/XsUvH2p"), UUID.randomUUID()));

        info.setOnline(-1);
        info.setMax(-1);
        info.setProtocol(0);

        try {
            info.setFavicon(ImageIO.read(Dummy.class.getClassLoader().getResource("server-icon.png")));
        } catch (IOException exception) {
            Logger.log(Level.WARN, "Failed to read server-icon.png");
        }

        try {
            server = new ServerSocket(port);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            server.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        super.stop();
    }

    @Override
    public void listen() {
        try {
            Socket socket = server.accept();

            new Thread(() -> {
                new Status(socket, info);
            }).start();
        } catch (SocketException exception) {
            // Shutdown
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
