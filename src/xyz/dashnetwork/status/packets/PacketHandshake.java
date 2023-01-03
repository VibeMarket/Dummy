package xyz.dashnetwork.status.packets;

import xyz.dashnetwork.status.utils.StreamUtils;

import java.io.DataInputStream;
import java.io.IOException;

public class PacketHandshake {

    private int version, port, next;
    private String hostname;

    public PacketHandshake(DataInputStream input) throws IOException {
        StreamUtils.readVarInt(input); // Packet Size
        StreamUtils.readVarInt(input); // Packet ID

        version = StreamUtils.readVarInt(input); // Client Version
        hostname = StreamUtils.readString(input);
        port = input.readUnsignedShort();
        next = StreamUtils.readVarInt(input); // Ping or Login state
    }

    public int getVersion() {
        return version;
    }

    public int getNext() {
        return next;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

}
