package xyx.dashnetwork.dummy.listener.listeners;

import xyx.dashnetwork.dummy.Dummy;
import xyx.dashnetwork.dummy.listener.Listener;
import xyx.dashnetwork.dummy.logger.Logger;
import xyx.dashnetwork.dummy.logger.utils.Level;
import xyx.dashnetwork.dummy.status.ServerInfo;
import xyx.dashnetwork.dummy.status.Status;
import xyx.dashnetwork.dummy.status.utils.ChatColor;
import xyx.dashnetwork.dummy.status.utils.GameProfile;

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
        info.setDescription("&6&lDashNetwork &6&l»&7 &c&o&lMAINTENANCE");
        info.setSoftware("MAINTENANCE");

        info.addPlayer(new GameProfile(ChatColor.translate("&f             &6&n&lDashNetwork"), UUID.randomUUID()));
        info.addPlayer(new GameProfile(ChatColor.translate("&6&lMinecraft &7play.dashnetwork.xyz"), UUID.randomUUID()));
        info.addPlayer(new GameProfile(ChatColor.translate("&6&lDiscord &7discord.dashnetwork.xyz"), UUID.randomUUID()));

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

            new Status(socket, info);
        } catch (SocketException exception) {
            // Shutdown
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
