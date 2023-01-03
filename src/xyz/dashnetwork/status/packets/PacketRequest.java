package xyz.dashnetwork.status.packets;

import xyz.dashnetwork.status.utils.StreamUtils;

import java.io.DataInputStream;
import java.io.IOException;

public class PacketRequest {

    public PacketRequest(DataInputStream input) throws IOException {
        StreamUtils.readVarInt(input); // Packet Size
        StreamUtils.readVarInt(input); // Packet ID
    }

}
