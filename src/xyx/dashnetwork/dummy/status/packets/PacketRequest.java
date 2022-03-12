package xyx.dashnetwork.dummy.status.packets;

import xyx.dashnetwork.dummy.status.utils.StreamUtils;

import java.io.DataInputStream;
import java.io.IOException;

public class PacketRequest {

    public PacketRequest(DataInputStream input) throws IOException {
        StreamUtils.readVarInt(input); // Packet Size
        StreamUtils.readVarInt(input); // Packet ID
    }

}
