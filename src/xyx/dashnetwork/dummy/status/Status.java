package xyx.dashnetwork.dummy.status;

import xyx.dashnetwork.dummy.logger.Logger;
import xyx.dashnetwork.dummy.logger.utils.Level;
import xyx.dashnetwork.dummy.status.packets.PacketHandshake;
import xyx.dashnetwork.dummy.status.packets.PacketRequest;
import xyx.dashnetwork.dummy.status.packets.PacketResponse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Status {

    private DataInputStream input;
    private DataOutputStream output;

    public Status(Socket socket, ServerInfo info) {
        String address = socket.getInetAddress().getHostAddress();

        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            PacketHandshake handshake = new PacketHandshake(input);
            String hostname = handshake.getHostname();
            int version = handshake.getVersion();

            String data = address + " (" + version + ") \"" + hostname + "\"";

            if (handshake.getNext() == 1) {
                new PacketRequest(input);

                Logger.log(Level.INFO, "Received from " + data);

                new PacketResponse(output, info);
            } else
                Logger.log(Level.WARN, "Login attempt from " + data);

            input.close();
            output.close();
            socket.close();
        } catch (EOFException exception) {
            Logger.log(Level.WARN, "Bad data received from " + address);
        } catch (SocketException exception) {
            Logger.log(Level.WARN, "Connection closed from " + address);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
